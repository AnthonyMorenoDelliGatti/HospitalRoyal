package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Archivo;

public class ListenerDescargar implements ActionListener{

	private Archivo archivo;
	// Faltaria el objeto que permita ordenar la descarga al servidor
	public ListenerDescargar(Archivo archivo, String user) {
		this.archivo = archivo;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//implementar la descarga y el splash correspondiente
	}

}
