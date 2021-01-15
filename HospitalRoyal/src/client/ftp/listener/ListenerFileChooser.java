package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.DropFile;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.FileFtp;

public class ListenerFileChooser implements ActionListener {

	private FTPClient client;
	private String user;
	private MethodList method;
	private FTPWindow v;
	private FileView lista;
	private DataOutputStream outputStream;
	private DropFile drop;

	public ListenerFileChooser(FTPClient client, String user, FTPWindow v, FileView lista, MethodList method,
			DataOutputStream outputStream, DropFile drop) {
		this.client = client;
		this.user = user;
		this.v = v;
		this.lista = lista;
		this.method = method;
		this.outputStream = outputStream;
		this.drop = drop;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			drop.getFrame().setEnabled(false);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(fileChooser);
			String route = "";
			route = fileChooser.getSelectedFile().getAbsolutePath();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(route));
			String[] routeSplitted = route.split("\\\\");
			client.storeFile(routeSplitted[routeSplitted.length - 1], in);
			in.close();
			drop.getFrame().dispose();
			ArrayList<FileFtp> archivos = new ArrayList<>();
			v.pack();
			method.DataListLoad(client, v, lista);
			outputStream.writeUTF("4");
			outputStream.writeUTF(routeSplitted[routeSplitted.length - 1]);
			drop.getFrame().dispose();
		} catch (Exception e1) {
			drop.getFrame().dispose();
		}
	}
}
