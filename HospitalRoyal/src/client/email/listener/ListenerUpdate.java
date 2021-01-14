package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import client.email.view.EmailMenuWindow;
import client.model.Email;

public class ListenerUpdate implements ActionListener {
	EmailMenuWindow emailWindow;
	Store store;
	String email;
	public ListenerUpdate(EmailMenuWindow emailWindow, Store store, String email) {
		this.emailWindow = emailWindow;
		this.store = store;
		this.email = email;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		
	}

}
