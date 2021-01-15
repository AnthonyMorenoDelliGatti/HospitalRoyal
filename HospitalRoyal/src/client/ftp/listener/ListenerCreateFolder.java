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
import client.ftp.view.FileView;
import client.model.FileFtp;

/**
 * 
 * @authors Anthony Moreno Delli Gatti
 *			Francisco Manuel Rodriguez Martin
 *			Juan Salguero Ibarrola
 *			Nicolas Rosa Hinojosa
 *			Gonzalo Ruiz de Mier Mora
 *
 *	date 13/01/2021
 *
 *	@version 1.0
 *
 *	description: class that control the create folder windows and create a folder
 */
public class ListenerCreateFolder implements ActionListener {

	FTPClient client;
	MethodList method;
	FTPWindow view;
	FileView explorer;
	String user;
	DataOutputStream outputStream;

	ArrayList<FileFtp> archivos;

	/**
	 * class' constructor
	 * 
	 * @param client the client of the ftp
	 * @param filesFtp arraylist of ftp
	 * @param method object that contains the method to make the list of files
	 * @param view object that contains the ftp window
	 * @param explorer object that contains the window where the list of files is
	 * @param user the name of the user
	 * @param outputStream
	 */
	public ListenerCreateFolder(FTPClient client, ArrayList<FileFtp> filesFtp, MethodList method, FTPWindow view,
			FileView explorer, String user, DataOutputStream outputStream) {
		this.client = client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
		this.user = user;
		this.archivos = filesFtp;
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

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

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
					method.DataListLoad(client, view, explorer);
					
					Rectangle tamanio=new Rectangle(600,600,600,600);
					if(view.getBounds()!=tamanio) {
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
					e.printStackTrace();
				}
			}
		});
	}
}