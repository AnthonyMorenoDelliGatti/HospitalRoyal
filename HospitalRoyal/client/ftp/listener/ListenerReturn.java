package ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import ftp.view.FTPWindow;
import ftp.view.VistaArchivos;
import controller.Methods;
import model.Paths;

public class ListenerReturn implements ActionListener{
	FTPClient client;
	Methods method;
	FTPWindow principalView;
	VistaArchivos explorer;
	Paths paths;
	public ListenerReturn(FTPClient client, Methods method, FTPWindow principalView, VistaArchivos explorer, Paths paths) {
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
