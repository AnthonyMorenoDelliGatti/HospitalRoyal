package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataOutputStream;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import client.model.Archivo;
import client.model.ArchivoFtp;

public class ListenerModificarNombre implements ActionListener, FocusListener{

	private Archivo archivo;
	private JTextField nombre;
	
	public ListenerModificarNombre(Archivo archivo, JTextField nombre) {
		this.archivo = archivo;
		this.nombre = nombre;
	}
	
	public ListenerModificarNombre(ArchivoFtp i, JTextField nombre2, FTPClient client, String user,
			DataOutputStream outputStream) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comprobarNombre();
	}

	private void comprobarNombre() {
		String text = nombre.getText();
		if(text.trim().length() > 0) {
			archivo.setNombre(text);
			// enviar al servidor el nuevo nombre
		}
		else{
			nombre.setText(archivo.getNombre());
		}
		nombre.setEditable(false);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		comprobarNombre();
	}

}