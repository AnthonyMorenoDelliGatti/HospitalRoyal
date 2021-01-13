package client.ftp.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Methods;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.ArchivoFtp;
import client.model.Paths;



public class ListenerArchivo implements MouseListener {

	private JPanel panel;
	private ArchivoFtp archivo;
	private FTPWindow vista;
	private Paths paths;
	private FTPClient client;
	private Methods method;
	private VistaArchivos explorer;
	private JTextField name;

	public ListenerArchivo(JPanel panel, ArchivoFtp archivo, FTPWindow vista, Paths paths, FTPClient client,
			Methods method, VistaArchivos vistaArchivos, JTextField name) {
		this.panel = panel;
		this.archivo = archivo;
		this.vista = vista;
		this.paths = paths;
		this.client = client;
		this.method = method;
		this.explorer = vistaArchivos;
		this.name = name;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) { // si se hace doble click
			if (archivo.getIsCarpeta() == 1) { // y el archivo es carpeta
				// se abre carpeta
				try {
					String nuevaDireccion;
					if (client.printWorkingDirectory().equalsIgnoreCase("/")) {
						nuevaDireccion = client.printWorkingDirectory() + archivo.getNombre();
					} else {
						nuevaDireccion = client.printWorkingDirectory() + "/" + archivo.getNombre();
					}
					client.changeWorkingDirectory(nuevaDireccion);
					vista.getButtons().get(0).setEnabled(true);
					vista.getButtons().get(1).setEnabled(false);
					paths.getPathguardados().clear();
					vista.pack();
					method.cargarDatosLista(client, vista, explorer);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Seleccion de carpetas
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
		name.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(Color.white);
		name.setBackground(Color.white);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
