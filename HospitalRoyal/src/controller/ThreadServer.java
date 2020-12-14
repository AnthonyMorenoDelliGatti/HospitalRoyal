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
		try {
			outputStream = new DataOutputStream(client.getOutputStream());
			inputStream = new DataInputStream(client.getInputStream());
			for (int i = 0; i < 3; i++) {// 3 intentos
				String user = inputStream.readUTF();
				String password = inputStream.readUTF();
				if (comprobarUsuario(user, password)) {
					// USUARIO Y CONTRASEÑA CORRECTOS
					outputStream.writeUTF("*");
					break;
				} else {
					outputStream.writeUTF("INCORRECT USER OR PASSWORD");
				}
			}
			outputStream.writeUTF("*");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean comprobarUsuario(String user, String password) {
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
