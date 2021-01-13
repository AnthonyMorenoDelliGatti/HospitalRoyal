package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;


import org.apache.commons.net.ftp.FTPClient;

import client.controller.Client;
import client.email.view.EmailMenuWindow;
import client.menu.view.StartMenuView;
import client.model.Email;

public class ListenerEmail implements ActionListener {

	private FTPClient client;
	private String user;
	private String email;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailWindow;
	private String password;
	private Client client2;

	public ListenerEmail(FTPClient client, String user, String email, StartMenuView vStartMenu,
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
		vStartMenu.setVisible(false);
		emailWindow = new EmailMenuWindow(user, password, email, vStartMenu);
		emailWindow.getFrame().setVisible(true);
		emailWindow.getFrame().setLocationRelativeTo(null);
		Properties prop = new Properties();
		prop.setProperty("mail.pop3.starttls.enable", "false");
		prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.pop3.socketFactory.fallback", "false");
		prop.setProperty("mail.pop3.port", "995");
		prop.setProperty("mail.pop3.socketFactory.port", "995");
		Session sesion = Session.getInstance(prop);
		Store store;
		try {
			store = sesion.getStore("pop3");
			store.connect("pop.gmail.com", email, password);
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			folder.isOpen();
			Message[] mensajes = folder.getMessages();
			Object mensaje = "";
			for (int i = 0; i < mensajes.length; i++) {
				Multipart mp = (Multipart) mensajes[i].getContent(); // here it breaks
				BodyPart bp = mp.getBodyPart(0);
				mensaje = bp.getContent();
				emailWindow.viewEmails(new Email(email, mensajes[i].getSubject(), mensajes[i].getFrom()[0].toString(),
						mensaje, mensajes[i].getSentDate().toString(), false));
			}
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			client.changeWorkingDirectory(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
