package client.ftp.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import client.model.ArchivoFtp;


public class ListenerBotonModificarNombre implements ActionListener{

	private JTextField nombre;
	
	public ListenerBotonModificarNombre(JTextField nombre, ArchivoFtp archivo) {
		this.nombre = nombre;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		nombre.setEditable(true);
		nombre.setBackground(Color.WHITE);
		nombre.requestFocus();
	}
}
