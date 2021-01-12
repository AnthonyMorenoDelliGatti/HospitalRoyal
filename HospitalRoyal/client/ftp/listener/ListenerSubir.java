package ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import ftp.view.DropFile;
import ftp.view.FTPWindow;
import ftp.view.VistaArchivos;
import controller.Methods;
import model.ArchivoFtp;

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
