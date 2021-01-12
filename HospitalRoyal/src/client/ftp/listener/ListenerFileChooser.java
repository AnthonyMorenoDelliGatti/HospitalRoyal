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

import client.controller.Methods;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.ArchivoFtp;



public class ListenerFileChooser implements ActionListener{

	private FTPClient client;
	private String user;
	private Methods method;
	private FTPWindow v;
	private VistaArchivos lista;
	private DataOutputStream outputStream;
	
	public ListenerFileChooser(FTPClient client, String user, FTPWindow v, VistaArchivos lista, Methods method, DataOutputStream outputStream) {
		this.client = client;
		this.user = user;
		this.v = v;
		this.lista = lista;
		this.method = method;
		this.outputStream = outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			client.setFileType(FTP.BINARY_FILE_TYPE);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(fileChooser);
			String route = "";
			route = fileChooser.getSelectedFile().getAbsolutePath();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(route));
			String[] routeSplitted = route.split("\\\\");
			client.storeFile(routeSplitted[routeSplitted.length - 1], in);
			in.close();
			System.out.println("UPLOAD SUCCESFULL");

			ArrayList<ArchivoFtp> archivos = new ArrayList<>();
			method.cargarDatosLista(client, v, lista);
			outputStream.writeUTF("4");
			outputStream.writeUTF(routeSplitted[routeSplitted.length - 1]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}
