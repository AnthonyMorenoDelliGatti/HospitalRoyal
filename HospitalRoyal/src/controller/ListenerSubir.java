package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import model.ArchivoFtp;
import view.VistaArchivos;
import view.VistaPrincipal;

public class ListenerSubir implements ActionListener {
	FTPClient client;
	String user;
	Methods method;
	VistaPrincipal v;
	VistaArchivos lista;
	public ListenerSubir(FTPClient client, String user, VistaPrincipal v, VistaArchivos lista, Methods method) {

		this.client = client;
		this.user = user;
		this.v = v;
		this.lista = lista;
		this.method = method;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
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
			method.log(user, 4, "Upload: " + routeSplitted[routeSplitted.length - 1]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
