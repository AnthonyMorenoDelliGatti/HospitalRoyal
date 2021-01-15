package client.email.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Archivo;

public class ListenerEliminarArchivo implements ActionListener {
	Archivo archivo;
	NewEmailView newEmailView;
	public ListenerEliminarArchivo(Archivo archivo, NewEmailView newEmailView) {
		this.archivo = archivo;
		this.newEmailView = newEmailView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		newEmailView.getArchivos().remove(newEmailView.getArchivos().indexOf(archivo));
		newEmailView.generarListado(newEmailView.getArchivos());

	}

}
