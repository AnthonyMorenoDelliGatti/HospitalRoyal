package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import model.ServerData;
import view.ClientView;
import view.MenuView;
import view.StartMenuView;

public class Client {
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	private Socket Client;
	private MenuView vMenu;
	private String user;
	private ServerData serverData;
	private StartMenuView vStartMenu;

	public Client() {
		serverData = new ServerData();
		ClientView v = new ClientView();
		String Host = "localhost";
		int Puerto = 5000;
		String password;
		boolean adminUser = true;
		try {
			Client = new Socket(Host, Puerto);
			outputStream = new DataOutputStream(Client.getOutputStream());
			inputStream = new DataInputStream(Client.getInputStream());
		} catch (Exception e) {
			v.getLabelInfo3().setText(
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
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
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
				v.getLabelInfo3().setText("Máximo 3 intentos para iniciar sesion");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
			try {
				serverStr = inputStream.readUTF();
				v.getLabelInfo3().setText(serverStr);
				v.pack();
			} catch (Exception e) {

			}
		}
		user = v.getTextUser().getText();
		password = v.getTextPassword().getText();
		v.dispose();
		FTPClient client = new FTPClient();
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

	private void StartMenu(boolean adminUser, FTPClient client) {
		vStartMenu = new StartMenuView();
		vStartMenu.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					client.logout();
					client.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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
		vStartMenu.getButtonFTP().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuFTP(adminUser, client);
				vStartMenu.setVisible(false);
			}

		});
		vStartMenu.getButtonMail().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mail(adminUser, client);
				vStartMenu.setVisible(false);
			}

		});
	}

	/**
	 * metodo que gestiona el menu del ftp
	 * 
	 * @param adminUser parametro que gestiona si el usuario tiene permiso de
	 *                  administrador o no
	 * 
	 * @param client    parametro que guarda las funciones del ftpClient
	 */
	private void MenuFTP(boolean adminUser, FTPClient client) {
		vMenu = new MenuView();
		if (!adminUser) {
			normalUserPermissions();
		}

		vMenu.getButtonCreate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(serverData.getUrlDB(), serverData.getUserDB(),
							"");
					Statement statement = connection.createStatement();
					String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('"
							+ "ha creado una carpeta" + "'," + 5 + ",'" + user + "')";
					statement.execute(sql);
					statement.close();
					connection.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		vMenu.getButtonDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(serverData.getUrlDB(), serverData.getUserDB(),
							"");
					Statement statement = connection.createStatement();
					String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('"
							+ "ha borrado una carpeta" + "'," + 6 + ",'" + user + "')";
					statement.execute(sql);
					statement.close();
					connection.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		vMenu.getButtonDownload().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		vMenu.getButtonRename().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		vMenu.getButtonUpload().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					client.setFileType(FTP.BINARY_FILE_TYPE);
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.showOpenDialog(fileChooser);
					String route = fileChooser.getSelectedFile().getAbsolutePath();
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(route));
					String[] routeSplitted = route.split("\\\\");
					System.out.println(routeSplitted[routeSplitted.length - 1]);
					client.storeFile(routeSplitted[routeSplitted.length - 1], in);
					in.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void Mail(boolean adminUser, FTPClient client) {
		
	}


	private void normalUserPermissions() {
		vMenu.getButtonCreate().setEnabled(false);
		vMenu.getButtonDelete().setEnabled(false);
		vMenu.getButtonDownload().setEnabled(false);
		vMenu.getButtonRename().setEnabled(false);
	}
}
