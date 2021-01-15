package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.Paths;


public class ListenerReturnForward implements ActionListener{
	FTPClient client;
	MethodList method;
	FTPWindow mainView;
	FileView explorer;
	Paths paths;
	public ListenerReturnForward(FTPClient client, MethodList method, FTPWindow mainView, FileView explorer, Paths paths) {
		this.client = client;
		this.method = method;
		this.mainView = mainView;
		this.explorer = explorer;
		this.paths = paths;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			try {
				if(paths.getPathguardados().size()>0) {
					client.changeWorkingDirectory(paths.getPathguardados().get(paths.getPathguardados().size()-1));
					paths.getPathguardados().remove(paths.getPathguardados().size()-1);
					method.DataListLoad(client,mainView, explorer);

					mainView.pack();
					mainView.setBounds(600,600,600,mainView.getBounds().height);
					mainView.setLocationRelativeTo(null);
					if(mainView.getBounds().height>=600) {
						mainView.setBounds(600,600,600,600);
						mainView.setLocationRelativeTo(null);
					}
				mainView.getButtons().get(0).setEnabled(true);
					if(paths.getPathguardados().size() == 0) {
						mainView.getButtons().get(1).setEnabled(false);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

	}
}
