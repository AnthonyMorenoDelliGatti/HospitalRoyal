package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.Archivo;
import client.model.ArchivoFtp;


public class ListenerEliminar implements ActionListener {

	private Archivo archivo;

	public ListenerEliminar(Archivo archivo) {
		this.archivo = archivo;
	}

	public ListenerEliminar(ArchivoFtp archivo2, ArrayList<ArchivoFtp> archivos, FTPClient client, MethodList method,
			FTPWindow vista, VistaArchivos vistaArchivos, String user, DataOutputStream outputStream) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] opciones = { "SI", "NO" };
		int eleccion = JOptionPane.showOptionDialog(null, "¿Corfima su eliminación?",
				"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "");
		
		if (eleccion == JOptionPane.YES_OPTION) {
			// Agregar el proceso de eliminación
			// Se tendría que eliminar el panel o actualizar todo el listado 
		} 
	}

}
