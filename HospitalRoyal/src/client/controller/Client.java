package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import client.email.view.EmailMenuWindow;
import client.ftp.listener.ListenerCloseWindow;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.login.view.Login;
import client.menu.listener.ListenerAbout;
import client.menu.listener.ListenerEmail;
import client.menu.listener.ListenerAdminFTP;
import client.menu.listener.ListenerUserFTP;
import client.menu.view.StartMenuView;
import client.model.Paths;
import client.model.ServerData;

public class Client {
	DataOutputStream outputStream;
	DataInputStream inputStream;
	Socket Client;
	FTPWindow ftpWindow;
	FileView explorer;
	private ServerData serverData;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailwindow;
	FTPClient client;
	MethodList method;
	String user, password, email;
	Boolean adminUser;
	Paths paths = new Paths();
	String host = "localhost"; //Change localhost with 192.168.13.122 if the server is operational

	public Client() {
		serverData = new ServerData();
		method = new MethodList();

		startLogin();
	}

	public void startLogin() {
		Login v = new Login();
		v.setVisible(true);
		v.getClose().addActionListener(new ListenerCloseWindow(v));

		int Puerto = 5000;
		adminUser = true;

		try {
			Client = new Socket(host, Puerto);
			outputStream = new DataOutputStream(Client.getOutputStream());
			inputStream = new DataInputStream(Client.getInputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"The connection to the Server could not be established, the program will close",
					"ERROR", JOptionPane.WARNING_MESSAGE);
			v.pack();
			try {
				Thread.sleep(5 * 1000);
			} catch (Exception e2) {
				System.out.println(e2);
			}
			System.exit(0);
		}

		v.getButtonLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = v.getTextUser().getText();
				String password = v.getTextPassword().getText();
				try {
					if (!user.equals("") && !password.equals("")) {
						outputStream.writeUTF(user);
						outputStream.writeUTF(password);
						String serverStr = inputStream.readUTF();
						if (serverStr.equals("INCORRECT USER OR PASSWORD")) {
							JOptionPane.showMessageDialog(null, "User or password incorrect", "FAILED TO LOGIN",
									JOptionPane.WARNING_MESSAGE);
							v.getTextPassword().setText("");

						} else if (serverStr.equals("DB NOT CONNECTED")) {
							JOptionPane.showMessageDialog(null,
									"The database is not available, sorry for the issue.\nTry it again later",
									"FAILED TO LOGIN", JOptionPane.WARNING_MESSAGE);
						} else if (serverStr.equals("ALREADY CONNECTED")) {
							JOptionPane.showMessageDialog(null,
									"User is already connected",
									"FAILED TO LOGIN", JOptionPane.WARNING_MESSAGE);
						} else {
							login(v);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter username and password");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		v.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					outputStream.writeUTF("*");
					inputStream.close();
					outputStream.close();
					Client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e2) {
					System.out.println(e2);
				}
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowOpened(WindowEvent e) {

			}

		});
	}

	private void login(Login v) {
		String serverStr = "";
		try {
			serverStr = inputStream.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (serverStr.equals("normalUser")) {
			adminUser = false;
		}
		try {
			serverStr = inputStream.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email = serverStr;
		user = v.getTextUser().getText();
		password = v.getTextPassword().getText();
		v.dispose();
		client = new FTPClient();
		try {
			client.connect(host);
			boolean login = client.login(user, password);
			if (!login) {
				client.disconnect();
				System.exit(1);
			}
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null,
					"Can¨t conect to the FTP Server, sorry for the issue.\nTry it again later", "FAILED TO LOGIN",
					JOptionPane.WARNING_MESSAGE);
		}
		StartMenu(adminUser, client);
	}

	private void StartMenu(boolean adminUser, FTPClient client) {
		vStartMenu = new StartMenuView();
		vStartMenu.getBtnClose().addActionListener(
				new client.menu.listener.ListenerClose(client, outputStream, this, user, vStartMenu));
		vStartMenu.getBtnAbout().addActionListener(new ListenerAbout());

		if (adminUser) {
			vStartMenu.getButtonFTP().addActionListener(new ListenerAdminFTP(paths, client, ftpWindow, user, explorer, method, vStartMenu, password, outputStream));
			vStartMenu.getButtonMail().addActionListener(new ListenerEmail(client, user, email, vStartMenu, emailwindow, password));
		} else {

			vStartMenu.getButtonFTP().addActionListener(new ListenerUserFTP(paths, client, ftpWindow, user, explorer, method, vStartMenu, password, outputStream));
			vStartMenu.getButtonMail().addActionListener(new ListenerEmail(client, user, email, vStartMenu, emailwindow, password));
		}
	}
}
