package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
		v.setEnabled(false);
		DropFile drop = new DropFile(v);
		drop.getFrame().setVisible(true);
		drop.getClose().addActionListener(new ListenerCloseWindow(drop.getFrame()));
		drop.getSave().addActionListener(new ListenerSave(drop, client, outputStream, v, method, lista));
		drop.getFileChooserBtn().addActionListener(new ListenerFileChooser(client, user, v, lista, method, outputStream, drop));
		v.pack();
	}
}
