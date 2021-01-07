package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;

import model.Archivo;
import view.CreateFolderView;
import view.VistaArchivos;
import view.VistaPrincipal;

public class ListenerCreateFolder implements ActionListener {

	FTPClient client;
	Methods method;
	VistaPrincipal view;
	VistaArchivos explorer;

	public ListenerCreateFolder(FTPClient client, Methods method, VistaPrincipal view, VistaArchivos explorer) {
		this.client = client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
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
					Boolean success = client.makeDirectory(folder);
					if (success) {
						createView.getLblMessage().setText("Successfully created directory: " + folder);
						createView.dispose();
					} else {
						createView.getLblMessage().setText("Failed to create directory");
					}
					method.cargarDatosLista(client, view, explorer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}