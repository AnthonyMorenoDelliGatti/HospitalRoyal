package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.Paths;


public class ListenerReturn implements ActionListener{
	FTPClient client;
	MethodList method;
	FTPWindow principalView;
	VistaArchivos explorer;
	Paths paths;
	public ListenerReturn(FTPClient client, MethodList method, FTPWindow principalView, VistaArchivos explorer, Paths paths) {
		this.client = client;
		this.method = method;
		this.principalView = principalView;
		this.explorer = explorer;
		this.paths = paths;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			try {
				if(!client.printWorkingDirectory().equalsIgnoreCase(paths.getPathLimit())) {
					paths.getPathguardados().add(client.printWorkingDirectory());
					client.changeToParentDirectory();
					principalView.getButtons().get(1).setEnabled(true);
					principalView.pack();
					method.cargarDatosLista(client, principalView, explorer);
					if(client.printWorkingDirectory().equalsIgnoreCase(paths.getPathLimit())) {
						principalView.getButtons().get(0).setEnabled(false);
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}

}
