package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThreadServer extends Thread {
	Socket client = null;
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Hospital hospital;

	public ThreadServer(Socket client, Hospital hospital) throws IOException {
		this.client = client;
		this.hospital = hospital;
		outputStream = new DataOutputStream(client.getOutputStream());
		inputStream = new DataInputStream(client.getInputStream());
	}

	public void run() {
		System.out.println("COMMUNICATING: " + client.toString());
		int choice = 0;
		try {
			outputStream = new DataOutputStream(client.getOutputStream());
			inputStream = new DataInputStream(client.getInputStream());
			for (int i = 0; i < 3; i++) {// 3 intentos
				String user = inputStream.readUTF();
				String password = inputStream.readUTF();
				if (checkUser(user, password)) {
					// USUARIO Y CONTRASEÑA CORRECTOS
					logLogIn(user);
					outputStream.writeUTF("*");
					logLogOut(user);
					break;
				} else {
					outputStream.writeUTF("INCORRECT USER OR PASSWORD");
				}
			}
			outputStream.writeUTF("*");
			choice = Integer.parseInt(inputStream.readUTF());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (choice) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:
			System.out.println("Llega");
			BufferedInputStream bis;
			BufferedOutputStream bos;
			int in;
			byte[] receivedData;
			String file;
			receivedData = new byte[1024];
			try {
				bis = new BufferedInputStream(client.getInputStream());
				DataInputStream dis = new DataInputStream(client.getInputStream());
				// Recibimos el nombre del fichero
				file = dis.readUTF();
				file = file.substring(file.indexOf('\\') + 1, file.length());
				// Para guardar fichero recibido
				bos = new BufferedOutputStream(new FileOutputStream(file));
				while ((in = bis.read(receivedData)) != -1) {
					bos.write(receivedData, 0, in);
				}
				bos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void logLogOut(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('" + "" + "'," + 2 + ",'" + user
					+ "')";
			statement.execute(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void logLogIn(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('" + "" + "'," + 1 + ",'" + user
					+ "')";
			statement.execute(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean checkUser(String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM usuario";
			ResultSet resul;
			resul = statement.executeQuery(sql);
			while (resul.next()) {
				if (user.equals(resul.getString(1))) {
					if (password.equals(resul.getString(2))) {
						return true;
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
