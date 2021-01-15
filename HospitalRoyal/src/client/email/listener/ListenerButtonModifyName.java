package client.email.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.model.FileFtp;


public class ListenerButtonModifyName implements ActionListener{

	private JTextField nombre;
	
	public ListenerButtonModifyName(JTextField nombre, FileFtp archivo) {
		this.nombre = nombre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		nombre.setEditable(true);
		nombre.requestFocus();
	}
}
