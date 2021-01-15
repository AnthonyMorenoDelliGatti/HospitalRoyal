package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Store;

import client.email.view.EmailMenuWindow;
import client.menu.view.SplashEmail;

/**
 * 
 * @authors Anthony Moreno Delli Gatti
 * 			Francisco Manuel Rodriguez Martin
 * 			Juan Salguero Ibarrola
 * 			Nicolas Rosa Hinojosa
 * 			Gonzalo Ruiz de Mier Mora 
 * 
 * date	13/01/2021
 * 
 * @version 1.0
 * 
 * description: Class listener to update the inbox
 *
 */

public class ListenerUpdate implements ActionListener {
	EmailMenuWindow emailWindow;
	Store store;
	String email;
	/**
	 * 
	 * @param emailWindow: the window that contains the emails
	 * @param store: the emails in the inbox
	 * @param email: the user email
	 */
	public ListenerUpdate(EmailMenuWindow emailWindow, Store store, String email) {
		this.emailWindow = emailWindow;
		this.store = store;
		this.email = email;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SplashEmail splash = new SplashEmail();
		splash.toFront();
		UpdateThread thread = new UpdateThread(emailWindow, store, email, splash);
		thread.start();
	}

}
