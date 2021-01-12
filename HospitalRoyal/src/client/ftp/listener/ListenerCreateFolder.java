package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;

import model.ArchivoFtp;
import view.CreateFolderView;
import view.VistaArchivos;
import view.VistaPrincipal;

public class ListenerCreateFolder implements ActionListener {

	FTPClient client;
	Methods method;
	VistaPrincipal view ;
	VistaArchivos explorer ;
	String user;
	DataOutputStream outputStream;

	ArrayList<ArchivoFtp> archivos;
	public ListenerCreateFolder(FTPClient client, ArrayList<ArchivoFtp> archivos, Methods method, VistaPrincipal view , VistaArchivos explorer, String user, DataOutputStream outputStream) {
		this.client= client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
		this.user = user;
		this.archivos=archivos;
		this.outputStream=outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreateFolderView createView = new CreateFolderView();
		createView.setVisible(true);
		createView.pack();
		createView.getBtnCreate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String folder = "/" + createView.getTxtNameFolder().getText();
				try {
					Boolean success = client.makeDirectory(client.printWorkingDirectory()+folder);
					if (success) {
						createView.dispose();
					} else {
						createView.getLblMessage().setText("Failed to create directory");
					}
					method.cargarDatosLista(client, view, explorer);
					outputStream.writeUTF("5");
					outputStream.writeUTF(folder);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}