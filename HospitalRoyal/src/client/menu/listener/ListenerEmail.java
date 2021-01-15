package client.menu.listener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Client;
import client.email.listener.ListenerSearch;
import client.email.listener.ListenerUpdate;
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
	ArrayList<Email> mails;

	public ListenerEmail(FTPClient client, String user, String email, StartMenuView vStartMenu,
			EmailMenuWindow emailWindow, String password) {
		this.client = client;
		this.user = user;
		this.email = email;
		this.vStartMenu = vStartMenu;
		this.emailWindow = emailWindow;
		this.password = password;
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
			mails = new ArrayList<>();
			for (int i = 0; i < mensajes.length; i++) {
				Email correo = new Email(email, mensajes[i].getSubject(), mensajes[i].getFrom()[0].toString(),
						mensajes[i], mensajes[i].getSentDate().toString(), false);
				mails.add(correo);
			}
			emailWindow.viewEmails(mails);
			emailWindow.getTxtSearch().addKeyListener(new ListenerSearch(emailWindow, mails));
			emailWindow.getBtnRecharge().addActionListener(new ListenerUpdate(emailWindow, store, email));
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
		}
		try {
			client.changeWorkingDirectory(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	
}
