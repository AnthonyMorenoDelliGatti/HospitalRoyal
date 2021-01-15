package client.email.listener;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import client.email.view.EmailMenuWindow;
import client.email.view.NewEmailView;
import client.menu.view.SplashEmail;
import client.model.Email;

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
 * description: Thread that send an email
 *
 */

public class SendThread extends Thread {

	String mail;
	String password;
	NewEmailView view;
	SplashEmail splash;
/**
 * 
 * @param mail: the user mail
 * @param password: the user password
 * @param view: the view that contains the email
 * @param splash: the splash window that activates during sending
 */
	public SendThread(String mail, String password, NewEmailView view, SplashEmail splash) {
		this.mail = mail;
		this.password = password;
		this.view = view;
		this.splash=splash;
	}

	@Override
	public void run() {
		String user = mail.substring(0, mail.indexOf("@"));
		String to = view.getTo().getText();
		String subject = view.getSubject().getText();
		String message = view.getTextPane().getText();
		MimeMultipart multiParte = new MimeMultipart();
		BodyPart texto = new MimeBodyPart();
		try {
			texto.setText(message);
			multiParte.addBodyPart(texto);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		view.getFrame().dispose();
		sendEmail(user, password, to, subject, multiParte);
	}
/**
 * Method to send an email
 * 
 * @param remitente: the user that send the email
 * @param clave: the user password
 * @param destinatario: the user to send
 * @param asunto: the subject
 * @param cuerpo: the contains of the email
 */
	private void sendEmail(String remitente, String clave, String destinatario, String asunto, Multipart cuerpo) {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", clave); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario); // Se podran añadir varios de la misma
																			// manera
			message.setSubject(asunto);
			message.setContent(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			view.getFrame().dispose();
		} catch (MessagingException me) {
			JOptionPane op = new JOptionPane();
			op.showMessageDialog(view.getFrame(), "An error has ocurred, please check de email adress");

		}
		splash.dispose();
	}
}
