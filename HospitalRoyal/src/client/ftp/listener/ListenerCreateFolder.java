package client.ftp.listener;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.CreateFolderView;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.ArchivoFtp;

public class ListenerCreateFolder implements ActionListener {

	FTPClient client;
	MethodList method;
	FTPWindow view;
	VistaArchivos explorer;
	String user;
	DataOutputStream outputStream;

	ArrayList<ArchivoFtp> archivos;

	public ListenerCreateFolder(FTPClient client, ArrayList<ArchivoFtp> archivos, MethodList method, FTPWindow view,
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
						JOptionPane.showMessageDialog(null, "The folder already exist or the process has failed", "FAILED TO CREATE DIRECTORY",
								JOptionPane.WARNING_MESSAGE);
					}
					method.cargarDatosLista(client, view, explorer);
					
					Rectangle tamanio=new Rectangle(600,600,600,600);
					if(view.getBounds()==tamanio) {
						
					}else {
						view.pack();
						view.setBounds(600,600,600,view.getBounds().height);
						view.setLocationRelativeTo(null);
						if(view.getBounds().height>=600) {
							view.setBounds(600,600,600,600);
							view.setLocationRelativeTo(null);
						}
					}
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