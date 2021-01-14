package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.Paths;


public class ListenerReturnForward implements ActionListener{
	FTPClient client;
	MethodList method;
	FTPWindow principalView;
	VistaArchivos explorer;
	Paths paths;
	public ListenerReturnForward(FTPClient client, MethodList method, FTPWindow principalView, VistaArchivos explorer, Paths paths) {
		this.client = client;
		this.method = method;
		this.principalView = principalView;
		this.explorer = explorer;
		this.paths = paths;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			try {
				if(paths.getPathguardados().size()>0) {
					client.changeWorkingDirectory(paths.getPathguardados().get(paths.getPathguardados().size()-1));
					paths.getPathguardados().remove(paths.getPathguardados().size()-1);
					method.cargarDatosLista(client,principalView, explorer);

					principalView.pack();
					principalView.setBounds(600,600,600,principalView.getBounds().height);
					principalView.setLocationRelativeTo(null);
					if(principalView.getBounds().height>=600) {
						principalView.setBounds(600,600,600,600);
						principalView.setLocationRelativeTo(null);
					}
				principalView.getButtons().get(0).setEnabled(true);
					if(paths.getPathguardados().size() == 0) {
						principalView.getButtons().get(1).setEnabled(false);
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}
}
