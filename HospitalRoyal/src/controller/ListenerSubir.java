package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class ListenerSubir implements ActionListener {
	FTPClient client;
	String user;
	public ListenerSubir(FTPClient client, String user) {
		this.client = client;
		this.user = user;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		try {
			client.setFileType(FTP.BINARY_FILE_TYPE);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(fileChooser);
			String route = fileChooser.getSelectedFile().getAbsolutePath();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(route));
			String[] routeSplitted = route.split("\\\\");
			System.out.println(routeSplitted[routeSplitted.length - 1]);
			client.storeFile(routeSplitted[routeSplitted.length - 1], in);
			in.close();
			System.out.println("UPLOAD SUCCESFULL");
			//log(user, 4, "Upload: " + routeSplitted[routeSplitted.length - 1]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
