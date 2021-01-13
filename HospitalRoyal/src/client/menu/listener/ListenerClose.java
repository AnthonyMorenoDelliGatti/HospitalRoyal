package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Client;
import client.controller.Methods;
import client.menu.view.StartMenuView;

public class ListenerClose implements ActionListener {

	private FTPClient client;
	private Methods method;
	private String user;
	private Client client2;
	private StartMenuView vStartMenu;

	public ListenerClose(FTPClient client, Methods method, Client client2, String user, StartMenuView vStartMenu) {
		this.client = client;
		this.method = method;
		this.user = user;
		this.client2 = client2;
		this.vStartMenu = vStartMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			client.logout();
			client.disconnect();
			method.log(user, 2, "Logout");
			vStartMenu.dispose();
			client2.startLogin();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

}
