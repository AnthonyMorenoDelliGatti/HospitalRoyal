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

import javax.swing.JOptionPane;

import client.controller.Methods;
import servidor.view.ServerView;

public class ThreadServer extends Thread {
	Socket client = null;
	DataInputStream inputStream;
	DataOutputStream outputStream;
	Hospital hospital;
	static Methods method;
	static ServerView viewServer;
	static boolean connectedDB;
	private String user;

	public ThreadServer(Socket client, Hospital hospital, ServerView viewServer, Methods method) throws IOException {
		this.client = client;
		this.hospital = hospital;
		this.viewServer = viewServer;
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
			int loop = 1;
			while (loop == 1) {
				user = inputStream.readUTF();
				String password = inputStream.readUTF();
				if (checkUser(user, password)) {
					if (checkConnectedUser(user)) {
						outputStream.writeUTF("CORRECT USER OR PASSWORD");
						// USUARIO Y CONTRASE�A CORRECTOS
						log(user, 1, "Log in");
						changeLoginStatus(1, user);
						if (checkPermissions(user)) {
							outputStream.writeUTF("normalUser");
						} else {
							outputStream.writeUTF("adminUser");
						}
						outputStream.writeUTF(selectEmailsUser(user));
						while (loop == 1) {
							String action = inputStream.readUTF();
							int actionId = Integer.parseInt(action);
							switch (actionId) {
							case 2:
								// Logout
								log(user, 2, "Logout");
								viewServer.getArea().append("\n" + client.toString() + " logged out");
								changeLoginStatus(0, user);
								loop = 0;
								break;
							case 3:
								// Borrar archivo
								String fileName = inputStream.readUTF();
								log(user, 3, "Delete file: " + fileName);
								viewServer.getArea().append("\n" + client.toString() + " deleted file: " + fileName);
								break;
							case 4:
								// Subir archivo
								String route = inputStream.readUTF();
								log(user, 4, "Upload: " + route);
								viewServer.getArea().append("\n" + client.toString() + " uploaded: " + route);
								break;
							case 5:
								// Crear carpeta
								String folder = inputStream.readUTF();
								log(user, 5, "Created directory: " + folder);
								viewServer.getArea().append("\n" + client.toString() + " created directory: " + folder);
								break;
							case 6:
								// Borrar carpeta
								String folderName = inputStream.readUTF();
								log(user, 6, " has deleted the folder " + folderName);
								viewServer.getArea()
										.append("\n" + client.toString() + " deleted the folder " + folderName);
								break;
							case 7:
								// Cambiar nombre
								String name = inputStream.readUTF();
								String newName = inputStream.readUTF();
								log(user, 7, " renamed file: " + name + " to: " + newName);
								viewServer.getArea().append(
										"\n" + client.toString() + " renamed file: " + name + " to: " + newName);
								break;
							case 8:
								// Descargar archivo
								String fileNameDownload = inputStream.readUTF();
								log(user, 8, " downloaded file: " + fileNameDownload);
								viewServer.getArea()
										.append("\n" + client.toString() + " downloaded file: " + fileNameDownload);
								break;
							}
						}
					} else {
						outputStream.writeUTF("ALREADY CONNECTED");
					}
				} else {
					if (!connectedDB) {
						outputStream.writeUTF("DB NOT CONNECTED");
					} else {
						outputStream.writeUTF("INCORRECT USER OR PASSWORD");
					}
				}
			}
		} catch (IOException e) {
			viewServer.getArea().append("\n" + client.toString() + " has been suddenly disconnected");
			changeLoginStatus(0, user);
		}
	}

	private boolean checkConnectedUser(String user) {
		try {
			Statement statement = DBConnection();
			String sql = "SELECT EstadoConectado FROM usuario WHERE usuario LIKE '" + user + "'";
			ResultSet resul;
			resul = statement.executeQuery(sql);
			while (resul.next()) {
				if (resul.getInt(1) == 0) {
					return true;
				}
			}
			connectedDB = true;
		} catch (ClassNotFoundException | SQLException e) {

			viewServer.getArea().append("\n***WARNING: The database is not available***");
			connectedDB = false;
		}
		return false;
	}

	private void changeLoginStatus(int i, String user) {
		String email = "";
		try {
			Statement statement = DBConnection();
			String sql = "UPDATE `usuario` SET `EstadoConectado` = '" + i + "'  WHERE usuario LIKE '" + user + "'";
			statement.executeUpdate(sql);
			connectedDB = true;
		} catch (ClassNotFoundException | SQLException e) {
			viewServer.getArea().append("\n***WARNING: The database is not available***");
			connectedDB = false;
		}
	}

	private String selectEmailsUser(String user) {
		String email = "";
		try {
			Statement statement = DBConnection();
			String sql = "SELECT correo FROM usuario WHERE usuario LIKE '" + user + "'";
			ResultSet resul;
			resul = statement.executeQuery(sql);
			while (resul.next()) {
				email = resul.getString(1); // send the email of the user
			}
			connectedDB = true;
		} catch (ClassNotFoundException | SQLException e) {
			viewServer.getArea().append("\n***WARNING: The database is not available***");
			connectedDB = false;
		}
		return email;
	}

	private boolean checkPermissions(String user) {
		try {
			Statement statement = DBConnection();
			String sql = "SELECT * FROM usuario";
			ResultSet resul;
			resul = statement.executeQuery(sql);
			while (resul.next()) {
				if (user.equals(resul.getString(1))) {
					if (resul.getInt(3) == 1) {
						return true;
					}
				}
			}
			connectedDB = true;
		} catch (ClassNotFoundException | SQLException e) {
			viewServer.getArea().append("\n***WARNING: The database is not available***");
			connectedDB = false;
		}
		return false;
	}

	private static boolean checkUser(String user, String password) {
		try {
			Statement statement = DBConnection();
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
			connectedDB = true;
		} catch (ClassNotFoundException | SQLException e) {
			viewServer.getArea().append("\n***WARNING: The database is not available***");
			connectedDB = false;
		}
		return false;
	}

	public static Statement DBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital_royal", "root", "");
		Statement statement = connection.createStatement();
		return statement;
	}

	public void log(String user, int action, String description) {
		try {
			Statement statement = DBConnection();
			String sql = "INSERT INTO `log`(`descripcion`, `accion`, `usuario`) VALUES ('" + description + "'," + action
					+ ",'" + user + "')";
			statement.execute(sql);
			statement.close();
			connectedDB = true;
		} catch (ClassNotFoundException | SQLException e) {
			viewServer.getArea().append("\n***WARNING: The database is not available***");
			connectedDB = false;
		}
	}
}
