package client.email.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Archive;

public class ListenerEliminarArchivo implements ActionListener {
	Archive archive;
	NewEmailView newEmailView;
	public ListenerEliminarArchivo(Archive archive, NewEmailView newEmailView) {
		this.archive = archive;
		this.newEmailView = newEmailView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		newEmailView.getArchives().remove(newEmailView.getArchives().indexOf(archive));
		newEmailView.generarListado(newEmailView.getArchives());

	}

}
