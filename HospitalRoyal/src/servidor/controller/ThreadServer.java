package servidor.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.controller.Methods;
import servidor.view.ServerView;

public class ThreadServer extends Thread {
	Socket client = null;
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Hospital hospital;
	Methods method;
	ServerView viewServer;

	public ThreadServer(Socket client, Hospital hospital, ServerView viewServer, Methods method) throws IOException {
		this.client = client;
		this.hospital = hospital;
		this.viewServer=viewServer;
		outputStream = new DataOutputStream(client.getOutputStream());
		inputStream = new DataInputStream(client.getInputStream());
		this.method = method;
	}

	public void run() {
		viewServer.getArea().append("\nCOMMUNICATING: " + client.toString());
		int choice = 0;
		try {
			outputStream = new DataOutputStream(client.getOutputStream());
			inputStream = new DataInputStream(client.getInputStream());
			for (int i = 0; i < 3; i++) {// 3 intentos
				String user = inputStream.readUTF();
				String password = inputStream.readUTF();
				if (checkUser(user, password)) {
					// USUARIO Y CONTRASE�A CORRECTOS
					method.log(user, 1, "Log in");
					if(checkPermissions(user)) {
						outputStream.writeUTF("normalUser");
					}
					outputStream.writeUTF("true");
					outputStream.writeUTF(selectEmailsUser(user));
					while(true) {
						String action = inputStream.readUTF();
						int actionId = Integer.parseInt(action);
						switch (actionId) {
						case 3:
							//Borrar archivo
							String fileName = inputStream.readUTF();
							method.log(user, 3, "Delete file: " + fileName);
							break;
						case 4:
							//Subir archivo
							String route = inputStream.readUTF();
							method.log(user, 4, "Upload: " + route);
							break;
						case 5:
							//Crear carpeta
							String folder = inputStream.readUTF();
							method.log(user, 5, " Created directory: " + folder);
							break;
						case 6:
							//Borrar carpeta
							String folderName = inputStream.readUTF();
							method.log(user, 6, "ha borrado la carpeta " + folderName);
							break;
						case 7:
							//Cambiar nombre
							String name = inputStream.readUTF();
							String newName = inputStream.readUTF();
							method.log(user, 7, " Renamed file: " + name + " to: " + newName);
							break;
						case 8:
							//Descargar archivo
							String fileNameDownload = inputStream.readUTF();
							method.log(user, 8, " Download file: " + fileNameDownload);
							break;
						}
					}
				} else {
					outputStream.writeUTF("INCORRECT USER OR PASSWORD");
				}
			}
			outputStream.writeUTF("false");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String selectEmailsUser(String user) {
		String email="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
			Statement statement = connection.createStatement();
			String sql = "SELECT correo FROM usuario WHERE usuario LIKE '"+user+"'";
			ResultSet resul;
			resul = statement.executeQuery(sql);
			while (resul.next()) {
				email=resul.getString(1); //send the email of the user
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

	private boolean checkPermissions(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM usuario";
			ResultSet resul;
			resul = statement.executeQuery(sql);
			while (resul.next()) {
				if (user.equals(resul.getString(1))) {
					if(resul.getInt(3)==1) {
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
