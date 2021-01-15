package client.email.listener;

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
 * description: Class listener to search an email by the user that sent it
 *
 */

public class ListenerSearch implements KeyListener {

	private EmailMenuWindow emailWindow;
	private JPanel emailBox;
	String user;
	ArrayList<Email> mails;
	ArrayList<Email> mailsb = new ArrayList<Email>();
	/**
	 * 
	 * @param emailWindow: the window that contains the mails
	 * @param mails: an ArrayList that contains all the emails in the inbox
	 */
	public ListenerSearch(EmailMenuWindow emailWindow, ArrayList<Email> mails) {
		this.emailWindow = emailWindow;
		this.mails = mails;
	}
	/**
	 * Method to search
	 */
	private void search() {
		mailsb.clear();
		if(emailWindow.getTxtSearch().getText().equals("")) {
			emailWindow.viewEmails(mails);
		}else {
			for (Email mail : mails) {
		        int result = mail.getUser().indexOf(emailWindow.getTxtSearch().getText());
		        if(result != -1) {
		        	mailsb.add(mail);
		        }
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
		search();
		
	}

}
