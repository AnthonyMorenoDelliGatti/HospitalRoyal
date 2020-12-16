package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Archivo;

public class ListenerBotonModificarNombre implements ActionListener{

	private JTextField nombre;
	
	public ListenerBotonModificarNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		nombre.setEditable(true);
		nombre.requestFocus();
	}
}
