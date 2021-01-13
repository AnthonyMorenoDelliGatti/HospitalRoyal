package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.SMTPClient;

import client.controller.Client;
import client.email.view.EmailMenuWindow;
import client.menu.view.StartMenuView;

public class ListenerUserEmail implements ActionListener {

	private FTPClient client;
	private String user;
	private String email;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailWindow;
	private String password;
	private Client client2;

	public ListenerUserEmail(FTPClient client, String user, String email, StartMenuView vStartMenu,
			EmailMenuWindow emailWindow, String password, Client client2) {
		this.client = client;
		this.user = user;
		this.email = email;
		this.vStartMenu = vStartMenu;
		this.emailWindow = emailWindow;
		this.password = password;
		this.client2 = client2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vStartMenu.setVisible(false);
		SMTPClient smtpclient = new SMTPClient();
		emailWindow = new EmailMenuWindow(email, password, user, vStartMenu);
		client2.exists(client);
		try {
			client.changeWorkingDirectory(user);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private Boolean comprobarEmail() {
		return null;
	}
}
