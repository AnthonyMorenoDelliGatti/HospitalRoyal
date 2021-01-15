package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.Paths;

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
 *	description: class that control the return button
 */
public class ListenerReturn implements ActionListener{
	FTPClient client;
	MethodList method;
	FTPWindow mainView;
	FileView explorer;
	Paths paths;
	/**
	 * class' constructoror
	 * 
	 * @param client ftp's client
	 * @param method object that contains the class that makes the list of files
	 * @param mainView Ftp's main window
	 * @param explorer window that contains the list of files
	 * @param paths object that contains the paths of the ftp
	 */
	public ListenerReturn(FTPClient client, MethodList method, FTPWindow mainView, FileView explorer, Paths paths) {
		this.client = client;
		this.method = method;
		this.mainView = mainView;
		this.explorer = explorer;
		this.paths = paths;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			try {
				if(!client.printWorkingDirectory().equalsIgnoreCase(paths.getPathLimit())) {
					paths.getSavedPaths().add(client.printWorkingDirectory());
					client.changeToParentDirectory();
					mainView.getButtons().get(1).setEnabled(true);
					
					method.DataListLoad(client, mainView, explorer);
					if(client.printWorkingDirectory().equalsIgnoreCase(paths.getPathLimit())) {
						mainView.getButtons().get(0).setEnabled(false);
					}

					mainView.pack();
					mainView.setBounds(600,600,600,mainView.getBounds().height);
					mainView.setLocationRelativeTo(null);
					if(mainView.getBounds().height>=600) {
						mainView.setBounds(600,600,600,600);
						mainView.setLocationRelativeTo(null);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
}
