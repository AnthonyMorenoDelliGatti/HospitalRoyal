package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;

import client.email.view.EmailMenuWindow;
import client.menu.view.SplashEmail;
import client.menu.view.StartMenuView;
import client.model.Email;

/**
 * @author Anthony Moreno Delli Gatti
 *         Francisco Manuel Rodriguez Martin
 *         Juan Salguero Ibarrola
 *         Nicolas Rosa Hinojosa
 *         Gonzalo Ruiz de Mier Mora
 *         
 *date 15/01/2021
 *
 *@version 1.0
 *
 *description:Clase que acciona la ejecucion del mail y sus vistas
 */

public class ListenerEmail implements ActionListener {

	private FTPClient client;
	private String user;
	private String email;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailWindow;
	private String password;
	ArrayList<Email> mails;

	/**
	 * Constructor de la clase
	 * @param client
	 * @param user
	 * @param email
	 * @param vStartMenu
	 * @param emailWindow
	 * @param password
	 */
	public ListenerEmail(FTPClient client, String user, String email, StartMenuView vStartMenu,
			EmailMenuWindow emailWindow, String password) {
		this.client = client;
		this.user = user;
		this.email = email;
		this.vStartMenu = vStartMenu;
		this.emailWindow = emailWindow;
		this.password = password;
	}

	/**
	 * Metodo que pone en ejecucion las acciones y ventanas del mail
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SplashEmail splash = new SplashEmail();
		vStartMenu.setVisible(false);
		emailWindow = new EmailMenuWindow(user, password, email, vStartMenu);
		emailWindow.getFrame().setVisible(true);
		emailWindow.getFrame().setLocationRelativeTo(null);
		splash.toFront();
		EmailThread threadEmail = new EmailThread(client, user, email, vStartMenu, emailWindow, password, splash);
		threadEmail.start();
	}

	

	
}
