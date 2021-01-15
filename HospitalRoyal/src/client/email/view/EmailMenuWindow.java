package client.email.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.smtp.SMTPClient;

import client.email.listener.ListenerClose;
import client.email.listener.ListenerEmail;
import client.email.listener.ListenerSearch;
import client.ftp.view.FileView;
import client.menu.view.StartMenuView;
import client.model.FileFtp;
import client.model.Email;

public class EmailMenuWindow {

	public static final Color WHITE = new Color(255, 255, 255);
	private Color headerColor;
	private JFrame frame;
	private JPanel panel;
	private JPanel emailBox;
	private JTextField txtSearch, textField;
	private JButton btnAdd, btnRecharge, btnClose;
	JLabel lblSearch;

	private StartMenuView vStartMenu;
	private NewEmailView newEmail;
	String user;
	String password;
	String email;

	public EmailMenuWindow(String user, String password, String email, StartMenuView vStartMenu) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.vStartMenu = vStartMenu;
		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(WHITE);
		frame.setBounds(100, 100, 429, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\email.png"));

		headerColor = new Color(255, 194, 121);

		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon("iconos/anadir.png"));
		btnAdd.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnAdd.setBorder(emptyBorder);
		btnAdd.setBackground(headerColor);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ArrayList<String> archivos = new ArrayList<>();
							NewEmailView window = new NewEmailView(email, password);
							window.getFrame().setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});

		btnRecharge = new JButton("");
		btnRecharge.setIcon(new ImageIcon("iconos/recargar.png"));
		btnRecharge.setFocusPainted(false);
		btnRecharge.setBorder(emptyBorder);
		btnRecharge.setBackground(headerColor);

		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("iconos/cerrar.png"));
		btnClose.setFocusPainted(false);
		btnClose.setBorder(emptyBorder);
		btnClose.setBackground(headerColor);
		btnClose.addActionListener(new ListenerClose(frame, vStartMenu));

		txtSearch = new JTextField(10);
		lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon("iconos/buscar.png"));
		lblSearch.setBorder(emptyBorder);
		lblSearch.setBackground(headerColor);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRecharge, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblSearch, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSearch, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(lblSearch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
								.addComponent(btnRecharge, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		panel.setBackground(headerColor);
		panel.setLayout(gl_panel);

		emailBox = new JPanel();

		emailBox.setLayout(new BoxLayout(emailBox, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(emailBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);

	}

	// Add a mail
	public void viewEmails(ArrayList<Email> mails) {
		emailBox.removeAll();
		
		if (mails.isEmpty()) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1));
			panel.add(new JLabel("The inbox is empty..."));
			panel.setBackground(WHITE);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			emailBox.add(panel);
		} else {
			for (Email i : mails) {
				panel = new JPanel();
				panel.setLayout(new GridLayout(0, 1));
				panel.add(new JLabel("From: " + i.getUser()));
				panel.add(new JLabel("Subject: " + i.getSubject()));
				panel.add(new JLabel("Send: " + i.getDate().toString()));
				if (!i.getIsRead()) {
					panel.setBackground(WHITE);
				}
				panel.addMouseListener(new ListenerEmail(panel, i, this, password));
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
				panel.setMaximumSize(new Dimension(430,75));
				panel.repaint();
				emailBox.add(panel);
			}
			
		}
		emailBox.updateUI();
	}

	// empty the inbox
	public void restartEmailBox() {
		emailBox.removeAll();
	}

	public JTextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(JTextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnRecharge() {
		return btnRecharge;
	}

	public void setBtnRecharge(JButton btnRecharge) {
		this.btnRecharge = btnRecharge;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
