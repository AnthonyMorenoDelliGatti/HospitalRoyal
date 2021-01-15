package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import com.sun.mail.handlers.multipart_mixed;

import client.email.view.NewEmailView;

import client.model.Archivo;

import client.menu.view.SplashEmail;



public class ListenerSend implements ActionListener{
	String mail;
	String password;
	NewEmailView view;
	ArrayList<Archivo> archivos;
	public ListenerSend(String mail, String password, NewEmailView view, ArrayList<Archivo> archivos) {
		this.mail = mail;
		this.password = password;
		this.view = view;
		this.archivos = archivos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SplashEmail splash = new SplashEmail();
		splash.toFront();
		SendThread thread = new SendThread(mail, password, view, splash);
		thread.start();
	}

}
