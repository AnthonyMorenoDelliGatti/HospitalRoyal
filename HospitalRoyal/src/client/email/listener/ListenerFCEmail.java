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

public class ListenerFCEmail implements ActionListener {
	NewEmailView window;
	ArrayList<Archive> archivos;

	public ListenerFCEmail(NewEmailView window, ArrayList<Archive> archivos) {
		this.window = window;
		this.archivos = archivos;
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
			String fechaComoCadena = sdf.format(new Date());
			Archive archivo = new Archive(fileChooser.getSelectedFile().getName(), fechaComoCadena, route);
			archivos.add(archivo);
			window.getFrame().setEnabled(true);
			window.generarListado(archivos);
		} catch (Exception ex) {
			window.getFrame().setEnabled(true);
		}
//		client.storeFile(routeSplitted[routeSplitted.length - 1]);
//		in.close();
//		drop.getFrame().dispose();

	}

}
