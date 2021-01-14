package client.ftp.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.net.smtp.SMTPClient;

import client.email.view.EmailMenuWindow;
import client.model.Email;

public class ListenerSearch implements KeyListener {

	private EmailMenuWindow emailWindow;
	private JPanel emailBox;
	String user;
	ArrayList<Email> mails;
	ArrayList<Email> mailsb = new ArrayList<Email>();
	public ListenerSearch(EmailMenuWindow emailWindow, ArrayList<Email> mails) {
		this.emailWindow = emailWindow;
		this.mails = mails;
	}
	private void buscar() {
		mailsb.clear();
		if(emailWindow.getTxtSearch().getText().equals("")) {
			emailWindow.viewEmails(mails);
		}else {
			for (Email mail : mails) {
		        int resultado = mail.getUser().indexOf(emailWindow.getTxtSearch().getText());
		        if(resultado != -1) {
		        	mailsb.add(mail);
		        }
		    }
			for (Email mail : mailsb) {
				System.out.println(mail.getUser());
			}
		    emailWindow.viewEmails(mailsb);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		buscar();
		
	}

}
