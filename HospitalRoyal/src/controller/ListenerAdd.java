package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import view.AddView;

public class ListenerAdd implements ActionListener {
	
	String user,mail;
	SMTPClient client;
	
	public ListenerAdd(SMTPClient client, String user) {
		this.user = user;
		mail = user + "@HospitalRoyal.es";
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AddView addView = new AddView();
		addView.getBtnSend().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.login();
					SimpleSMTPHeader Header = new SimpleSMTPHeader(mail,addView.getTxtReveiver().getText(),addView.getTxtAffair().getText());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}		
		});
	}

}
