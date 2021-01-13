package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Client;
import client.email.view.EmailMenuWindow;
import client.menu.view.StartMenuView;

public class ListenerAdminEmail implements ActionListener {

	private FTPClient client; 
	private String user;
	private String email;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailWindow;
	private String password;
	private Client client2;
	
	public ListenerAdminEmail(FTPClient client, String user, String email, StartMenuView vStartMenu,
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
	public void actionPerformed(ActionEvent arg0) {
		
//		enviarConGMail("user.hospitalroyal@gmail.com", "PRUEBA", "FIOHEDAOUFHAF"); //PRUEBA
		
		vStartMenu.setVisible(false);
		emailWindow = new EmailMenuWindow(user, password, email, vStartMenu);
		emailWindow.getFrame().setVisible(true);
		emailWindow.getFrame().setLocationRelativeTo(null);	
	}
}
