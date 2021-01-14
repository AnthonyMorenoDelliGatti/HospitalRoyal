package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Client;
import client.controller.MethodList;
import client.menu.view.StartMenuView;

public class ListenerClose implements ActionListener {

	private FTPClient client;
	private DataOutputStream outputStream;
	private String user;
	private Client client2;
	private StartMenuView vStartMenu;

	public ListenerClose(FTPClient client, DataOutputStream outputStream, Client client2, String user, StartMenuView vStartMenu) {
		this.client = client;
		this.outputStream = outputStream;
		this.user = user;
		this.client2 = client2;
		this.vStartMenu = vStartMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			outputStream.writeUTF("2");
			
			vStartMenu.dispose();
			client.logout();
			client.disconnect();
			System.exit(0);
			//client2.startLogin();
		} catch (IOException e1) {
			System.exit(0);
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

}
