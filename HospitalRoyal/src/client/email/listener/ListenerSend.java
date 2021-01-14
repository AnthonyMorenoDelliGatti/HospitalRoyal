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
		MimeMultipart multiParte = new MimeMultipart();
		BodyPart texto = new MimeBodyPart();
		try {
			texto.setText(message);
			multiParte.addBodyPart(texto);
//			for(int i = 0; i< view.getFilesPanel().getComponentCount(); i++) {
//				BodyPart archivo = new MimeBodyPart();
//				archivo.setDataHandler(new DataHandler(new FileDataSource(view.getFilesPanel().getComponent(i).)));
//				multiParte.addBodyPart(archivo);
//			}
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		view.getFrame().dispose();
		enviarConGMail(user,password,to,subject,multiParte);
	}
	private void enviarConGMail(String remitente, String clave, String destinatario, String asunto, Multipart cuerpo) {
		
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
}
}
