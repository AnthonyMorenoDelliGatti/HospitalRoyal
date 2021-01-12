package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import client.email.view.NewEmailView;


public class ListenerSend implements ActionListener{
	String mail;
	String password;
	NewEmailView view;
	public ListenerSend(String mail, String password, NewEmailView view) {
		this.mail = mail;
		this.password = password;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = mail.substring(0, mail.indexOf("@"));
		String to = view.getTo().getText();
		String subject = view.getSubject().getText();
		String message = view.getTextPane().getText();
		enviarConGMail(user,password,to,subject,message);
	}
	private void enviarConGMail(String remitente, String clave, String destinatario, String asunto, String cuerpo) {
		
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
		message.addRecipients(Message.RecipientType.TO, destinatario); // Se podran añadir varios de la misma manera
		message.setSubject(asunto);
		message.setText(cuerpo);
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", remitente, clave);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	} catch (MessagingException me) {
		me.printStackTrace(); // Si se produce un error
	}
}
}
