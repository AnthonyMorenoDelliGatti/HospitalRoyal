package client.menu.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.net.ftp.FTPClient;

import client.email.listener.ListenerSearch;
import client.email.listener.ListenerUpdate;
import client.email.view.EmailMenuWindow;
import client.menu.view.SplashEmail;
import client.menu.view.StartMenuView;
import client.model.Email;

public class EmailThread extends Thread{

	private FTPClient client;
	private String user;
	private String email;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailWindow;
	private String password;
	ArrayList<Email> mails;
	SplashEmail splash;
	
	public EmailThread(FTPClient client, String user, String email, StartMenuView vStartMenu,
			EmailMenuWindow emailWindow, String password, SplashEmail splash) {
		this.client = client;
		this.user = user;
		this.email = email;
		this.vStartMenu = vStartMenu;
		this.emailWindow = emailWindow;
		this.password = password;
		this.splash=splash;
	}
	
	@Override
	public void run() {
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
			mails = new ArrayList<>();
			for (int i = 0; i < mensajes.length; i++) {
				Email correo = new Email(email, mensajes[i].getSubject(), mensajes[i].getFrom()[0].toString(),
						mensajes[i], mensajes[i].getSentDate().toString(), false);
				mails.add(correo);
			}
			emailWindow.viewEmails(mails);
			emailWindow.getTxtSearch().addKeyListener(new ListenerSearch(emailWindow, mails));
			emailWindow.getBtnRecharge().addActionListener(new ListenerUpdate(emailWindow, store, email));
			splash.dispose();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
		}
	}

}
