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
		String user = mail.substring(0, mail.indexOf("@"));
		String to = view.getTo().getText();
		String subject = view.getSubject().getText();
		String message = view.getTextPane().getText();
		MimeMultipart multiPart = new MimeMultipart();
		BodyPart texto = new MimeBodyPart();
		try {
			texto.setText(message);
			multiParte.addBodyPart(texto);
			for(int i = 0; i< archivos.size(); i++) {
				BodyPart archivo = new MimeBodyPart();
				archivo.setDataHandler(new DataHandler(new FileDataSource(archivos.get(i).getDireccion())));
				archivo.setFileName(archivos.get(i).getNombre());
				System.out.println(archivos.get(i).getDireccion());
				multiParte.addBodyPart(archivo);
			}
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		view.getFrame().dispose();
		enviarConGMail(user,password,to,subject,multiPart);
	}
	private void enviarConGMail(String sender, String password, String addressee, String subject, Multipart body) {
		
	Properties props = System.getProperties();
	props.put("mail.smtp.host", "smtp.gmail.com"); // The server SMTP of Google
	props.put("mail.smtp.user", sender);
	props.put("mail.smtp.clave", password); // The password of the account
	props.put("mail.smtp.auth", "true"); // Use autentification through user and password
	props.put("mail.smtp.starttls.enable", "true"); // To connect on a safe way to the SMTP server
	props.put("mail.smtp.port", "587"); // The safe SMTP port of Google

	Session session = Session.getDefaultInstance(props);
	MimeMessage message = new MimeMessage(session);

	try {
		message.setFrom(new InternetAddress(sender));
		message.addRecipients(Message.RecipientType.TO, addressee); // Varios could be added the same way
		message.setSubject(subject);
		message.setContent(body);
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", sender, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		view.getFrame().dispose();
	} catch (Exception me) {
		JOptionPane op = new JOptionPane();
		op.showMessageDialog(view.getFrame(), "An error has ocurred, please check de email adress");
		
	}
}
}
