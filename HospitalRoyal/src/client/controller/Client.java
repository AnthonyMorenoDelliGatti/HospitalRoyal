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

		String serverStr = "";
		while (!serverStr.equals("true")) {
			if (serverStr.equals("normalUser")) {
				adminUser = false;
			}
			if (serverStr.equals("false")) {
				v.getLabelInfo().setText("Maximo 3 intentos para iniciar sesion");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
			try {
				serverStr = inputStream.readUTF();
				v.getLabelInfo().setText(serverStr);
				v.pack();
			} catch (Exception e) {

			}
		}
		try {
			serverStr = inputStream.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		email = serverStr;
		user = v.getTextUser().getText();
		password = v.getTextPassword().getText();
		v.dispose();
		client = new FTPClient();
		String servFTP = "localhost";
		System.out.println("Nos conectamos a: " + servFTP);
		try {
			client.connect(servFTP);
			boolean login = client.login(user, password);
			if (login) {
				System.out.println("Login correcto...");
			} else {
				System.out.println("Login incorrecto...");
				client.disconnect();
				System.exit(1);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		StartMenu(adminUser, client);
	}

//	private void enviarConGMail(String destinatario, String asunto, String cuerpo) {
//		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
//		// remitente también.
//		String remitente = "hospitalroyalcontact"; // Para la dirección nomcuenta@gmail.com
//		String clave = "Hospitalroyal2021";
//
//		Properties props = System.getProperties();
//		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
//		props.put("mail.smtp.user", remitente);
//		props.put("mail.smtp.clave", clave); // La clave de la cuenta
//		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
//		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
//		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
//
//		Session session = Session.getDefaultInstance(props);
//		MimeMessage message = new MimeMessage(session);
//
//		try {
//			message.setFrom(new InternetAddress(remitente));
//			message.addRecipients(Message.RecipientType.TO, destinatario); // Se podran añadir varios de la misma
//																			// manera
//			message.setSubject(asunto);
//			message.setText(cuerpo);
//			Transport transport = session.getTransport("smtp");
//			transport.connect("smtp.gmail.com", remitente, clave);
//			transport.sendMessage(message, message.getAllRecipients());
//			transport.close();
//		} catch (MessagingException me) {
//			me.printStackTrace(); // Si se produce un error
//		}
//	}

	private void StartMenu(boolean adminUser, FTPClient client) {
		vStartMenu = new StartMenuView();
		vStartMenu.getBtnClose().addActionListener(new client.menu.listener.ListenerClose(client, method, this, user, vStartMenu));
		vStartMenu.getBtnAbout().addActionListener(new ListenerAbout());
				
		if (adminUser) {
			vStartMenu.getButtonFTP().addActionListener(new ListenerAdminFTP(paths, client, ftpWindow, user, explorer, method, vStartMenu, password, outputStream));
			vStartMenu.getButtonMail().addActionListener(new ListenerEmail(client, user, email, vStartMenu, emailwindow, password, this));
		} else {
			vStartMenu.getButtonFTP().addActionListener(new ListenerUserFTP(user, client, paths, this));
					/*
					ArrayList<ArchivoFtp> archivos = new ArrayList<>();
					principalView = new VistaPrincipal(client, user, explorer, method);
					explorer = new VistaArchivos(client, archivos, method, principalView, password, outputStream, paths);
					method.cargarDatosLista(client, principalView, explorer);
					principalView.setVisible(true);
					principalView.pack();
					// se introducen los listener a los botones
					// volver al padre
					principalView.getButtons().get(0).addActionListener(
							new ListenerReturn(client,method,principalView,explorer,paths));
					//volver al anterior
					principalView.getButtons().get(1).addActionListener(
							new ListenerReturnForward(client,method,principalView,explorer,paths));
					// crear carpeta
					principalView.getButtons().get(2).addActionListener(
							new ListenerCreateFolder(client, archivos, method, principalView, explorer, password, outputStream));
					// eliminar archivos y carpetas
					principalView.getButtons().get(3)
							.addActionListener(new ListenerSubir(client, user, principalView, explorer, method, outputStream));
					vStartMenu.setVisible(false);*/
		
			vStartMenu.getButtonMail().addActionListener(new ListenerEmail(client, user, email, vStartMenu, emailwindow, password, this));
	
		}
	}

	public void exists(FTPClient client) {
		boolean hasDirectory = false;
		FTPFile[] files;
		try {
			files = client.listFiles();
			for (int i = 0; i < files.length; i++) {
				int type = files[i].getType();
				if (type == 1) {
					if (files[i].getName().equals(user)) {
						hasDirectory = true;
					}
				}
			}
			if (!hasDirectory) {
				client.makeDirectory("/" + user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
