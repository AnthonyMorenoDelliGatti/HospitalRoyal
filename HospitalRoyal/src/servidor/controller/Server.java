package servidor.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import client.controller.MethodList;
import servidor.view.ServerView;

/**
 * @author Anthony Moreno Delli Gatti
 *         Francisco Manuel Rodriguez Martin
 *         Juan Salguero Ibarrola
 *         Nicolas Rosa Hinojosa
 *         Gonzalo Ruiz de Mier Mora
 *         
 *date 15/01/2021
 *
 *@version 1.0
 *
 *description: Clase que funciona como servidor de la aplicacion
 */
public class Server {

	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	static MethodList method;

	/**
	 * Metodo principal para ejecutar el servidor
	 * @param args
	 */
	public static void main(String[] args) {
		ServerView viewServer = new ServerView();
		viewServer.getArea().append("Server started... ");
		method = new MethodList();
		try {
			ServerSocket server;
			server = new ServerSocket(5000);
			while (true) {
				Socket client = new Socket();
				client = server.accept();
				ThreadServer hilo = new ThreadServer(client, viewServer, method);
				hilo.start();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Server already started", "FAILED STARTING SERVER",
					JOptionPane.WARNING_MESSAGE);
			viewServer.dispose();
		}
	}

}
