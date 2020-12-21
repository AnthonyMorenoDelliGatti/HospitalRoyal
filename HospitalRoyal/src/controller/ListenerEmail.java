package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.SMTPClient;

import model.Email;
import view.EmailMenuWindow;
import view.StartMenuView;

public class ListenerEmail implements MouseListener {

	private EmailMenuWindow vista;
	private JPanel panel;
	private Email email;
	private FTPClient client;
	private StartMenuView vStarMenu;

	public ListenerEmail(JPanel panel, Email email, FTPClient client, StartMenuView vStartMenu, String user) {
		this.panel = panel;
		this.email = email;
		vStartMenu.setVisible(false);
		SMTPClient smtpclient = new SMTPClient();
		try {
			smtpclient.connect("localhost");
		} catch (IOException e) {
			e.printStackTrace();
		}
		vista = new EmailMenuWindow(user);
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnRecharge().addActionListener(new ListenerUpdate());
		vista.getBtnSearch().addKeyListener(new ListenerSearch(vista.getTxtSearch(), user));
		vista.getBtnClose().addActionListener(new ListenerClose(vista.getFrame()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {

		}
	}

	/**
	 * Selecci�n de carpetas
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!email.getIsRead()) {
			panel.setBackground(vista.WHITE);
		} else {
			panel.setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
