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
	String direction;
	String name;
	MethodList method;
	String user;
	DataOutputStream outputStream;
	FTPWindow view;

	public ListenerDownload(String direction, String name, FTPClient client, MethodList method, String user,
			DataOutputStream outputStream, FTPWindow view) {
		this.direction = direction;
		this.client = client;
		this.name = name;
		this.method = method;
		this.user = user;
		this.outputStream = outputStream;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		FTPFile[] fileList;
		view.setEnabled(false);
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
					client.retrieveFile("." + direction, outputStream2);
					SplashUploadFile splash = new SplashUploadFile(view, "upload");
					splash.setVisible(true);
					outputStream.writeUTF("8");
					outputStream.writeUTF(name);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			view.setEnabled(true);
		}
	}

}
