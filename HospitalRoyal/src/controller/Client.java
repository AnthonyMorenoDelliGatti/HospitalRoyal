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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import model.Archivo;
import model.ServerData;
import sun.security.jca.GetInstance;
import view.VistaArchivos;
import view.VistaPrincipal;
import view.Login;
import view.StartMenuView;

public class Client {
	DataOutputStream outputStream;
	DataInputStream inputStream;
	Socket Client;
	VistaPrincipal vista;
	VistaArchivos explorer;
	private ServerData serverData;
	private StartMenuView vStartMenu;
	FTPClient client;

	public Client() {
		serverData = new ServerData();
		Login v = new Login();
		v.setVisible(true);
		v.pack();
		String Host = "localhost";
		int Puerto = 5000;
		String user, password;
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
				v.getLabelInfo3().setText("Maximo 3 intentos para iniciar sesion");
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
//		if (!adminUser) {
//			normalUserPermissions();
//		}
//		vMenu.addWindowListener(new WindowListener() {
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				try {
//					client.logout();
//					client.disconnect();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (Exception e2) {
//					System.out.println(e2);
//				}
//				System.exit(0);
//			}
//
//			@Override
//			public void windowActivated(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowIconified(WindowEvent e) {
//
//			}
//
//			@Override
//			public void windowOpened(WindowEvent e) {
//
//			}
//
//		});
//		vMenu.getButtonCreate().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				CreateFolder createFolder = new CreateFolder(client);
//			}
//		});
//		vMenu.getButtonDelete().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				DeleteFolder deleteFolder = new DeleteFolder(client);
//			}
//		});
//		vMenu.getButtonDownload().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		vMenu.getButtonRename().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		vMenu.getButtonUpload().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					client.setFileType(FTP.BINARY_FILE_TYPE);
//					JFileChooser fileChooser = new JFileChooser();
//					fileChooser.showOpenDialog(fileChooser);
//					String route = fileChooser.getSelectedFile().getAbsolutePath();
//					BufferedInputStream in = new BufferedInputStream(new FileInputStream(route));
//					String[] routeSplitted = route.split("\\\\");
//					System.out.println(routeSplitted[routeSplitted.length - 1]);
//					client.storeFile(routeSplitted[routeSplitted.length - 1], in);
//					in.close();
//					System.out.println("UPLOAD SUCCESFULL");
//					log(user, 4, "Upload: " + routeSplitted[routeSplitted.length - 1]);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//
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
				vista = new VistaPrincipal();
				explorer = new VistaArchivos();
				ArrayList<Archivo> archivos = new ArrayList<>();
				cargarDatosLista(archivos);
				vista.agregarExplorador(explorer.visualizarListado(archivos));
				vista.setVisible(true);
				vista.pack();
				vStartMenu.setVisible(false);
			}

		});
		vStartMenu.getButtonMail().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				vStartMenu.setVisible(false);
			}

		});
	}

	private void MenuFTP(boolean adminUser, FTPClient client) {

	}

	private void Mail(boolean adminUser, FTPClient client) {

	}

	private void log(String user, int action, String description) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(serverData.getUrlDB(), serverData.getUserDB(), "");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('" + description + "',"
					+ action + ",'" + user + "')";
			statement.execute(sql);
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private static void normalUserPermissions() {
//		vista.getButtonCreate().setEnabled(false);
//		vista.getButtonDelete().setEnabled(false);
//		vista.getButtonDownload().setEnabled(false);
//		vista.getButtonRename().setEnabled(false);
//	}
	private void cargarDatosLista(ArrayList<Archivo> archivos) {
		archivos.clear();
		try {
			FTPFile[] fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				String nameFile = fileList[i].getName();
				int isDirectory = 0;
				if (fileList[i].isDirectory()) {
					isDirectory = 1;
				}
				String path = client.printWorkingDirectory();
				String time = client.getModificationTime(path + nameFile);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
				String lastModification = "";
				try {
					String timePart = time.split(" ")[0];
					Date modificationTime = dateFormat.parse(timePart);
					Calendar calendarModification = Calendar.getInstance(TimeZone.getDefault());
					calendarModification.setTime(modificationTime);
					lastModification = "" + calendarModification.get(Calendar.DAY_OF_MONTH) + "/"
							+ (calendarModification.get(Calendar.MONTH)+1) + "/" + calendarModification.get(Calendar.YEAR)
							+ " " + (calendarModification.get(Calendar.HOUR)+1) + ":"
							+ calendarModification.get(Calendar.MINUTE) + ":"
							+ calendarModification.get(Calendar.SECOND);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				archivos.add(new Archivo(nameFile, lastModification, isDirectory, (path + nameFile)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
