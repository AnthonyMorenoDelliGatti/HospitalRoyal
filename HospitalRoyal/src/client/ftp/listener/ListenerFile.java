package client.ftp.listener;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.FileFtp;
import client.model.Paths;

public class ListenerFile implements MouseListener {

	private JPanel panel;
	private FileFtp archivo;
	private FTPWindow vista;
	private Paths paths;
	private FTPClient client;
	private MethodList method;
	private FileView explorer;
	private JTextField name;

	public ListenerFile(JPanel panel, FileFtp archivo, FTPWindow vista, Paths paths, FTPClient client,
			MethodList method, FileView vistaArchivos, JTextField name) {
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
					method.DataListLoad(client, vista, explorer);
					
					vista.pack();
					vista.setBounds(600,600,600,vista.getBounds().height);
					vista.setLocationRelativeTo(null);
					if(vista.getBounds().height>=600) {
						vista.setBounds(600,600,600,600);
						vista.setLocationRelativeTo(null);
					}
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
