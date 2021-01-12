package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import client.controller.Methods;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.ArchivoFtp;



public class ListenerEliminar implements ActionListener {

	private ArchivoFtp archivo;
	private ArrayList<ArchivoFtp> archivos;
	private FTPClient client;
	private Methods method;
	private FTPWindow view;
	private VistaArchivos explorer;
	private String user;
	DataOutputStream outputStream;

	public ListenerEliminar(ArchivoFtp archivo, ArrayList<ArchivoFtp> archivos, FTPClient client, Methods method,
			FTPWindow view, VistaArchivos explorer, String user, DataOutputStream outputStream) {
		this.archivo = archivo;
		this.archivos = archivos;
		this.client = client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
		this.user = user;
		this.outputStream = outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] opciones = { "SI", "NO" };
		int eleccion = JOptionPane.showOptionDialog(null, "¿Corfima su eliminación?", "Confirmación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "");

		if (eleccion == JOptionPane.YES_OPTION) {
			try {
				String originalPath = client.printWorkingDirectory();
				// proceso de eliminacion
				if (archivo.getIsCarpeta() == 0) {
					try {
						client.deleteFile(archivo.getDireccion());

						outputStream.writeUTF("3");
						outputStream.writeUTF(archivo.getNombre());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						if (!client.removeDirectory(archivo.getDireccion())) {
							String previousPath = client.printWorkingDirectory();
							client.changeWorkingDirectory(archivo.getDireccion());
							delete(client.listFiles());
							client.changeWorkingDirectory(previousPath);
							client.removeDirectory(archivo.getDireccion());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// se actualiza la vista
				try {
					client.changeWorkingDirectory(originalPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				method.cargarDatosLista(client, view, explorer);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			;
		}
	}

	private void delete(FTPFile[] listFiles) {
		if (listFiles.length != 0) {
			for (FTPFile file : listFiles) {
				if (!file.isDirectory()) {
					try {
						client.deleteFile(client.printWorkingDirectory() + "/" + file.getName());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						if (!client.removeDirectory(client.printWorkingDirectory() + "/" + file.getName())) {
							String previousPath = client.printWorkingDirectory();
							System.out.println(client.printWorkingDirectory() + "/" + file.getName());
							client.changeWorkingDirectory(client.printWorkingDirectory() + "/" + file.getName());
							delete(client.listFiles());
							client.changeWorkingDirectory(previousPath);
							client.removeDirectory(client.printWorkingDirectory() + "/" + file.getName());

							outputStream.writeUTF("6");
							outputStream.writeUTF(file.getName());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

	}

}
