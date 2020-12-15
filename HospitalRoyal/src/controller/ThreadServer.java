package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.ServerData;

public class ThreadServer extends Thread {
	Socket client = null;
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Hospital hospital;
	ServerData serverData;

	public ThreadServer(Socket client, Hospital hospital) throws IOException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		this.client = client;
		this.hospital = hospital;
		outputStream = new DataOutputStream(client.getOutputStream());
		inputStream = new DataInputStream(client.getInputStream());
		serverData = new ServerData();
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
					if(checkPermissions(user)) {
						outputStream.writeUTF("normalUser");
					}
					logLogOut(user);
					outputStream.writeUTF("true");
					break;
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

	private boolean checkPermissions(String user) {
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void logLogOut(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(serverData.getUrlDB(), serverData.getUserDB(), "");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('" + "se ha desconectado" + "'," + 2 + ",'" + user
					+ "')";
			statement.execute(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void logLogIn(String user) {
		try {
			
			Connection connection = DriverManager.getConnection(serverData.getUrlDB(), serverData.getUserDB(), "");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `log`(`descripción`, `accion`, `usuario`) VALUES ('" + "se ha conectado" + "'," + 1 + ",'" + user
					+ "')";
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkUser(String user, String password) {
		try {
			Connection connection = DriverManager.getConnection(serverData.getUrlDB(), serverData.getUserDB(), "");
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
