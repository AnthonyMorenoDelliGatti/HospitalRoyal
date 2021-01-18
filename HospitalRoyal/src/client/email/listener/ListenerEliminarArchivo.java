package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.email.view.NewEmailView;
import client.model.Archive;

/**
 * 
 * 
 * @authors Anthony Moreno Delli Gatti
 * 			Francisco Manuel Rodriguez Martin
 * 			Juan Salguero Ibarrola
 * 			Nicolas Rosa Hinojosa
 * 			Gonzalo Ruiz de Mier Mora 
 * 
 * date	13/01/2021
 * 
 * @version 1.0
 * 
 * description: Listener to delete an attached file
 *
 */

public class ListenerEliminarArchivo implements ActionListener {
	Archive archive;
	NewEmailView newEmailView;
	/**
	 * 
	 * @param archive: archive to delete
	 * @param newEmailView: the view of the email
	 */
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
