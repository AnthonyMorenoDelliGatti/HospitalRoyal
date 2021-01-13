package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Methods;
import client.ftp.view.CreateFolderView;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.ArchivoFtp;

public class ListenerCreateFolder implements ActionListener {

	FTPClient client;
	Methods method;
	FTPWindow view;
	VistaArchivos explorer;
	String user;
	DataOutputStream outputStream;

	ArrayList<ArchivoFtp> archivos;

	public ListenerCreateFolder(FTPClient client, ArrayList<ArchivoFtp> archivos, Methods method, FTPWindow view,
			VistaArchivos explorer, String user, DataOutputStream outputStream) {
		this.client = client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
		this.user = user;
		this.archivos = archivos;
		this.outputStream = outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.setEnabled(false);
		CreateFolderView createView = new CreateFolderView();
		createView.setVisible(true);
		createView.pack();
		createView.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				view.setEnabled(true);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				view.setEnabled(true);

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		createView.getBtnCreate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String folder = "/" + createView.getTxtNameFolder().getText();
				try {
					Boolean success = client.makeDirectory(client.printWorkingDirectory() + folder);
					if (success) {
						createView.dispose();
						view.setEnabled(true);
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