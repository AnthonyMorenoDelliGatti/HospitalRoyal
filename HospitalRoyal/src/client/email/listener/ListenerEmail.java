package client.email.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.apache.commons.net.ftp.FTPClient;

import client.email.view.EmailMenuWindow;
import client.email.view.EmailView;
import client.menu.view.StartMenuView;
import client.model.Email;


public class ListenerEmail implements MouseListener {

	private EmailMenuWindow view;
	private JPanel panel;
	private Email email;
	private FTPClient client;
	private StartMenuView vStarMenu;
	String password;

	public ListenerEmail(JPanel panel, Email email, EmailMenuWindow view, String password) {
		this.panel = panel;
		this.email = email;
		this.view = view;
		this.password = password;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			try
	        {
				view.getFrame().setEnabled(false);
	            EmailView ev = new EmailView(email, view);
	            ev.getRespond().addActionListener(new ListenerReply(email, password));
	            ev.getResend().addActionListener(new ListenerForward(email, password));
	        }
	        catch (Exception er)
	        {
	            er.printStackTrace();
	        }
		}
	}

	/**
	 * Select folder
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!email.getIsRead()) {
			panel.setBackground(view.WHITE);
		} else {
			panel.setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
