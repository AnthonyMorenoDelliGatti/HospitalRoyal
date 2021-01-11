package controller;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import org.apache.commons.net.ftp.FTPClient;

import model.ArchivoFtp;
import model.Paths;
import view.VistaArchivos;
import view.VistaPrincipal;

public class ListenerArchivo implements MouseListener {

	private JPanel panel;
	private ArchivoFtp archivo;
	private VistaPrincipal vista;
	private Paths paths;
	private FTPClient client;
	private Methods method;
	private VistaArchivos explorer;
	
	public ListenerArchivo(JPanel panel, ArchivoFtp archivo, VistaPrincipal vista, Paths paths, FTPClient client, Methods method, VistaArchivos vistaArchivos) {
		this.panel = panel;
		this.archivo = archivo;
		this.vista = vista;
		this.paths = paths;
		this.client = client;
		this.method = method;
		this.explorer = vistaArchivos;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) { // si se hace doble click
			if(archivo.getIsCarpeta() == 1) { // y el archivo es carpeta
				// se abre carpeta
				try {
					client.changeWorkingDirectory(client.printWorkingDirectory()+archivo.getNombre());
					vista.getButtons().get(0).setEnabled(true);
					vista.getButtons().get(1).setEnabled(false);
					paths.getPathguardados().clear();
					method.cargarDatosLista(client, vista, explorer);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Selecci�n de carpetas
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(null);
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
