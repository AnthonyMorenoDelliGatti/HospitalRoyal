package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import model.Archivo;

public class ListenerModificarNombre implements KeyListener {

	private Archivo archivo;
	private JTextField nombre;
	FTPClient client;
	private String user;
	private Methods method;
	public ListenerModificarNombre(Archivo archivo, JTextField nombre, FTPClient client, String user, Methods method) {
		this.archivo = archivo;
		this.nombre = nombre;
		this.client = client;
		this.user = user;
		this.method = method;
	}

	private void comprobarNombre() {
		String text = nombre.getText();
		String nombreanterior = archivo.getNombre();
		if (text.trim().length() > 0) {
			archivo.setNombre(text);
			cambiarnombre(text, nombreanterior);

		} else {
			nombre.setText(archivo.getNombre());
		}
		nombre.setEditable(false);
	}

	private void cambiarnombre(String nombre2, String nombre) {
		FTPFile[] fileList;
		try {
			fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().equals(nombre)) {
					client.rename(fileList[i].getName(), nombre2);
				}
			}
			System.out.println("RENAME SUCCESFULL");
			method.log(user, 7, " Renamed file: " + nombre + " to: " + nombre2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			comprobarNombre();
		} else {
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
