package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import model.ArchivoFtp;

public class ListenerDescargar implements ActionListener{

	FTPClient client;
	String direccion, nombre;
	Methods method;
	public ListenerDescargar(String direccion, String nombre, FTPClient client, Methods method) {
		this.direccion = direccion;
		this.client = client;
		this.nombre = nombre;
		this.method = method;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		File file;
		String filepath = "";
		String path = "";
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setDialogTitle("Select a directory to download");
		int returnVal = f.showDialog(null, "Download");
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			file = f.getSelectedFile();
			path = (file.getAbsolutePath().toString());
			filepath = path+File.separator+nombre;
			try {
				client.setFileType(FTP.BINARY_FILE_TYPE);
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filepath));
				if(client.retrieveFile(direccion, out)) {
					JOptionPane.showMessageDialog(null, filepath + " ==> Se ha descargado correctamente");
				}else {
					JOptionPane.showMessageDialog(null, filepath + " ==> No se ha podido descargar");
				}
			} catch (Exception e) {
				
			}
			
		}
	}

}
