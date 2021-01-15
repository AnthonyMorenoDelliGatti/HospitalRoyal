package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;

import client.email.view.NewEmailView;
import client.model.Archive;

/**
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
 * description: Class listener to attach an file to an email using the filechosser button
 *
 */

public class ListenerFCEmail implements ActionListener {
	NewEmailView window;
	ArrayList<Archive> archives;
	/**
	 * 
	 * @param window: the frame of email to add the file
	 * @param archivos: an arraylist that contains al the archives attach
	 */
	public ListenerFCEmail(NewEmailView window, ArrayList<Archive> archives) {
		this.window = window;
		this.archives = archives;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			window.getFrame().setEnabled(false);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(fileChooser);
			String route = "";
			route = fileChooser.getSelectedFile().getAbsolutePath();
			String[] routeSplitted = route.split("\\\\");
			long lastModification = fileChooser.getSelectedFile().lastModified();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String stringDate = sdf.format(new Date());
			Archive archive = new Archive(fileChooser.getSelectedFile().getName(), stringDate, route);//create an archive object with the selected file and the sistem date
			archives.add(archive);
			window.getFrame().setEnabled(true);
			window.generarListado(archives);
		} catch (Exception ex) {
			window.getFrame().setEnabled(true);
		}
//		client.storeFile(routeSplitted[routeSplitted.length - 1]);
//		in.close();
//		drop.getFrame().dispose();

	}

}
