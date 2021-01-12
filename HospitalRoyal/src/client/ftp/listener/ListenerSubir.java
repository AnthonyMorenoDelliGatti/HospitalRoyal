package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Methods;
import client.ftp.view.DropFile;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;



public class ListenerSubir implements ActionListener {
	FTPClient client;
	String user;
	Methods method;
	FTPWindow v;
	VistaArchivos lista;
	DataOutputStream outputStream;
	public ListenerSubir(FTPClient client, String user, FTPWindow v, VistaArchivos lista, Methods method, DataOutputStream outputStream) {

		this.client = client;
		this.user = user;
		this.v = v;
		this.lista = lista;
		this.method = method;
		this.outputStream = outputStream;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		DropFile drop = new DropFile();
		drop.getFrame().setVisible(true);
		drop.getClose().addActionListener(new ListenerCloseWindow(drop.getFrame()));
		drop.getSave().addActionListener(new ListenerSave());
		drop.getFileChooserBtn().addActionListener(new ListenerFileChooser(client, user, v, lista, method, outputStream));
	}
}
