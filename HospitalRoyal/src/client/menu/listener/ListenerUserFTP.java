package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Client;
import client.model.Paths;

public class ListenerUserFTP implements ActionListener{

	private String user;
	private FTPClient client;
	private Paths paths;
	private Client client2;
	
	public ListenerUserFTP(String user, FTPClient client, Paths paths, Client client2) {
		this.user = user;
		this.client = client;
		this.paths = paths;
		this.client2 = client2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		client2.exists(client);
		try {
			client.changeWorkingDirectory(user);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			paths.setPathLimit(client.printWorkingDirectory());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

}
