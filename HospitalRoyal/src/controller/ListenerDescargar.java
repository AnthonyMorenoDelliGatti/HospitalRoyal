package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import model.Archivo;

public class ListenerDescargar implements ActionListener {

	FTPClient client;
	String direccion;
	String nombre;
	Methods method;
	String user;

	public ListenerDescargar(String direccion, String nombre, FTPClient client, Methods method, String user) {
		this.direccion = direccion;
		this.client = client;
		this.nombre = nombre;
		this.method = method;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		FTPFile[] fileList;
		try {
			fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().equals(nombre)) {
					JFileChooser f = new JFileChooser();
					f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					f.showOpenDialog(f);
					String path = f.getSelectedFile().getAbsolutePath();
					OutputStream outputStream = new BufferedOutputStream(
							new FileOutputStream(path + File.separator + nombre));
					client.setFileType(FTP.BINARY_FILE_TYPE);
					client.retrieveFile("." + direccion, outputStream);
					System.out.println("DOWNLOAD SUCCESFULL");
					method.log(user, 8, " Download file: " + nombre);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
