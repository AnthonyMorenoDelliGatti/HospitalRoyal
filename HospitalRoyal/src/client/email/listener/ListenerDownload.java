package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import client.email.view.SplashDownloadFile;
import client.email.view.SplashSubidaArchivo;

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
 * description: Class listener to download an attached file on an email
 *
 */

public class ListenerDownload implements ActionListener {
	Part part;
	JPanel panel;
	/**
	 * 
	 * @param part : the part which contais the file to download
	 * @param panel : the panel to open the splash
	 */
	public ListenerDownload(Part part, JPanel panel) {
		this.part = part;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InputStream is;
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.showOpenDialog(fc);
		    String path = fc.getSelectedFile().getAbsolutePath();
			is = part.getInputStream();
			File f = new File(path+ "/" + part.getFileName());
			FileOutputStream fos = new FileOutputStream(f);
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.close();
			panel.setEnabled(false);
			SplashDownloadFile splash = new SplashDownloadFile(panel);
			splash.setVisible(true);
		} catch (Exception e1) {
			
		}
		
	}


}
