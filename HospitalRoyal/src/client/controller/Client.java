package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.smtp.SMTPClient;

import client.email.listener.ListenerClose;
import client.email.view.EmailMenuWindow;
import client.ftp.listener.ListenerCloseWindow;
import client.ftp.listener.ListenerCreateFolder;
import client.ftp.listener.ListenerReturn;
import client.ftp.listener.ListenerReturnForward;
import client.ftp.listener.ListenerSubir;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.login.view.Login;
import client.menu.listener.ListenerAbout;
import client.menu.listener.ListenerEmail;
import client.menu.listener.ListenerAdminFTP;
import client.menu.listener.ListenerUserFTP;
import client.menu.view.StartMenuView;
import client.model.ArchivoFtp;
import client.model.Paths;
import client.model.ServerData;

public class Client {
	DataOutputStream outputStream;
	DataInputStream inputStream;
	Socket Client;
	FTPWindow ftpWindow;
	VistaArchivos explorer;
	private ServerData serverData;
	private StartMenuView vStartMenu;
	private EmailMenuWindow emailwindow;
	FTPClient client;
	Methods method;
	String user, password, email;
	Boolean adminUser;
	Paths paths = new Paths();

	public Client() {
		serverData = new ServerData();
		method = new Methods();

		startLogin();
	}

	public void startLogin() {
		Login v = new Login();
		v.setVisible(true);
		v.getClose().addActionListener(new ListenerCloseWindow(v));

		String Host = "localhost";
		int Puerto = 5000;
		adminUser = true;

		try {
			Client = new Socket(Host, Puerto);
			outputStream = new DataOutputStream(Client.getOutputStream());
			inputStream = new DataInputStream(Client.getInputStream());
		} catch (Exception e) {
			v.getLabelInfo().setText(
					"The connection to the Server could not be established, the program will close in 5 seconds");
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
							v.getTextPassword().setText("");;
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

	private void login(Login v) throws IOException {
		String serverStr = "";
		serverStr = inputStream.readUTF();
		if (serverStr.equals("normalUser")) {
			adminUser = false;
		}
		serverStr = inputStream.readUTF();
		email = serverStr;
		user = v.getTextUser().getText();
		password = v.getTextPassword().getText();
		v.dispose();
		client = new FTPClient();
		String servFTP = "localhost";
		System.out.println("Conected to: " + servFTP);
		try {
			client.connect(servFTP);
			boolean login = client.login(user, password);
			if (login) {
				System.out.println("Correct login...");
			} else {
				System.out.println("Incorrect login...");
				client.disconnect();
				System.exit(1);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
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
			vStartMenu.getButtonMail().addActionListener(new ListenerEmail(client, user, email, vStartMenu, emailwindow, password, this));
		} else {
			vStartMenu.getButtonFTP().addActionListener(new ListenerUserFTP(user, client, paths, this));
			vStartMenu.getButtonMail().addActionListener(new ListenerEmail(client, user, email, vStartMenu, emailwindow, password, this));
	
		}
	}		
}
