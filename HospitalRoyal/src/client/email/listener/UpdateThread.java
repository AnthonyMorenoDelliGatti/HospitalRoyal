package client.email.listener;

import java.util.ArrayList;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;


import client.email.view.EmailMenuWindow;
import client.menu.view.SplashEmail;
import client.model.Email;
/**
 * 
 * 
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
 * description: Thread to update the inbox
 *
 */

public class UpdateThread extends Thread {

	EmailMenuWindow emailWindow;
	Store store;
	String email;
	SplashEmail splash;
/**
 * 
 * @param emailWindow: the window that contains the emails
 * @param store: Objects which store all the emails in the inbox
 * @param email: user email
 * @param splash: the splash during update
 */
	public UpdateThread(EmailMenuWindow emailWindow, Store store, String email, SplashEmail splash) {
		this.emailWindow = emailWindow;
		this.store = store;
		this.email = email;
		this.splash = splash;
	}

	@Override
	public void run() {
		Folder folder;
		try {
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			folder.isOpen();
			Message[] mensajes = folder.getMessages();
			ArrayList<Email> mails = new ArrayList<>();
			for (int i = 0; i < mensajes.length; i++) {
				Email correo = new Email(email, mensajes[i].getSubject(), mensajes[i].getFrom()[0].toString(),
						mensajes[i], mensajes[i].getSentDate().toString(), false);
				mails.add(correo);
			}
			emailWindow.viewEmails(mails);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		splash.dispose();
	}

}
