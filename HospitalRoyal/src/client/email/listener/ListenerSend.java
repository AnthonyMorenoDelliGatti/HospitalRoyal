package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import client.email.view.NewEmailView;

import client.model.Archive;

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
 * description: Class listener to send an email
 *
 */

public class ListenerSend implements ActionListener{
	String mail;
	String password;
	NewEmailView view;
	ArrayList<Archive> archives;
	/**
	 * 
	 * @param mail: the user mail
	 * @param password: the user password
	 * @param view: the view that contains the email
	 * @param archives: the archives attached to the email
	 */
	public ListenerSend(String mail, String password, NewEmailView view, ArrayList<Archive> archives) {
		this.mail = mail;
		this.password = password;
		this.view = view;
		this.archives = archives;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SplashEmail splash = new SplashEmail();
		splash.toFront();
		SendThread thread = new SendThread(mail, password, view, splash, archives);
		thread.start();
	}

}
