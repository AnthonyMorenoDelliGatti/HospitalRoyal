package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.SplashUploadFile;


public class ListenerDownload implements ActionListener {

	FTPClient client;
	String direccion;
	String name;
	MethodList method;
	String user;
	DataOutputStream outputStream;
	FTPWindow vista;

	public ListenerDownload(String direccion, String nombre, FTPClient client, MethodList method, String user, DataOutputStream outputStream, FTPWindow vista) {
		this.direccion = direccion;
		this.client = client;
		this.name = nombre;
		this.method = method;
		this.user = user;
		this.outputStream = outputStream;
		this.vista=vista;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		FTPFile[] fileList;
		vista.setEnabled(false);
		try {
			fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().equals(name)) {
					JFileChooser f = new JFileChooser();
					f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					f.showOpenDialog(f);
					String path = f.getSelectedFile().getAbsolutePath();
					OutputStream outputStream2 = new BufferedOutputStream(
							new FileOutputStream(path + File.separator + name));
					client.setFileType(FTP.BINARY_FILE_TYPE);
					client.retrieveFile("." + direccion, outputStream2);
					SplashUploadFile splash = new SplashUploadFile(vista, "upload");
					splash.setVisible(true);
					outputStream.writeUTF("8");
					outputStream.writeUTF(name);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
