package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import model.Archivo;
import view.VistaArchivos;
import view.VistaPrincipal;

public class ListenerEliminar implements ActionListener {

	private Archivo archivo;
	private ArrayList<Archivo> archivos;
	private FTPClient client;
	private Methods method;
	private VistaPrincipal view;
	private VistaArchivos explorer;

	public ListenerEliminar(Archivo archivo, ArrayList<Archivo> archivos, FTPClient client, Methods method,
			VistaPrincipal view, VistaArchivos explorer) {
		this.archivo = archivo;
		this.archivos = archivos;
		this.client = client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] opciones = { "SI", "NO" };
		int eleccion = JOptionPane.showOptionDialog(null, "Corfima su eliminacion?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "");

		if (eleccion == JOptionPane.YES_OPTION) {
			try {
				String originalPath = client.printWorkingDirectory();
			// proceso de eliminacion
			if (archivo.getIsCarpeta() == 0) {
				try {
					client.deleteFile(archivo.getDireccion());				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					if(!client.removeDirectory(archivo.getDireccion())) {
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
			method.cargarDatosLista(archivos, client, view, explorer);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			};
		}
	}

	private void delete(FTPFile[] listFiles) {
		if(listFiles.length != 0) {
		for(FTPFile file : listFiles) {
			if (!file.isDirectory()) {
				try {
					client.deleteFile(client.printWorkingDirectory()+"/"+file.getName());				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					if(!client.removeDirectory(client.printWorkingDirectory()+"/"+file.getName())) {
						String previousPath = client.printWorkingDirectory();
						System.out.println(client.printWorkingDirectory()+"/"+file.getName());
						client.changeWorkingDirectory(client.printWorkingDirectory()+"/"+file.getName());
						delete(client.listFiles());
						client.changeWorkingDirectory(previousPath);
						client.removeDirectory(client.printWorkingDirectory()+"/"+file.getName());
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
