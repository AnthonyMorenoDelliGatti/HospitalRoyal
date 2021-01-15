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
			Message[] messages = folder.getMessages();
			ArrayList<Email> mails = new ArrayList<>();
			for (int i = 0; i < messages.length; i++) {
				Email mail = new Email(email, messages[i].getSubject(), messages[i].getFrom()[0].toString(),
						messages[i], messages[i].getSentDate().toString(), false);
				mails.add(mail);
			}
			emailWindow.viewEmails(mails);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
