package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.Paths;


public class ListenerReturn implements ActionListener{
	FTPClient client;
	MethodList method;
	FTPWindow mainView;
	FileView explorer;
	Paths paths;
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
