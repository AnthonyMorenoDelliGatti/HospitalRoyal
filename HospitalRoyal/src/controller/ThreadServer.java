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

import controller.Hospital;
import view.ServerView;
public class ThreadServer extends Thread {
	Socket client = null;
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Hospital hospital;
	ServerView viewServer;

	public ThreadServer(Socket client, Hospital hospital, ServerView viewServer) throws IOException {
		this.client = client;
		this.hospital = hospital;
		this.viewServer=viewServer;
		outputStream = new DataOutputStream(client.getOutputStream());
		inputStream = new DataInputStream(client.getInputStream());
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
					logLogIn(user);
					if(checkPermissions(user)) {
						outputStream.writeUTF("normalUser");
					}
					outputStream.writeUTF("true");
					outputStream.writeUTF(selectEmailsUser(user));
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
				System.out.println(resul.getString(1));
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

	private void logLogIn(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `log`(`descripcion`, `accion`, `usuario`) VALUES ('" + "" + "'," + 1 + ",'" + user
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
