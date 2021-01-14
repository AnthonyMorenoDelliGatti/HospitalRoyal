package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Methods;
import client.ftp.view.DropFile;
import client.ftp.view.FTPWindow;
import client.ftp.view.SplashSubidaArchivo;
import client.ftp.view.VistaArchivos;

public class ListenerSave implements ActionListener{

	private DropFile drop;
	private FTPClient client;
	private DataOutputStream outputStream;
	private FTPWindow v;
	private Methods method;
	private VistaArchivos lista;
	public ListenerSave(DropFile drop, FTPClient client, DataOutputStream outputStream, FTPWindow v, Methods method, VistaArchivos lista) {
		this.drop=drop;
		this.client=client;
		this.outputStream=outputStream;
		this.v=v;
		this.method=method;
		this.lista=lista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList <String> pathFiles = drop.getPathFiles();
		for(String path : pathFiles) {
			BufferedInputStream in;
			try {
				in = new BufferedInputStream(new FileInputStream(path));
				String[] routeSplitted = path.split("\\\\");
				client.storeFile(routeSplitted[routeSplitted.length - 1], in);
				in.close();
				method.cargarDatosLista(client, v, lista);
				outputStream.writeUTF("4");
				outputStream.writeUTF(routeSplitted[routeSplitted.length - 1]);				
				drop.getFrame().dispose();
				

				v.pack();
				v.setBounds(600,600,600,v.getBounds().height);
				v.setLocationRelativeTo(null);
				if(v.getBounds().height>=600) {
					v.setBounds(600,600,600,600);
					v.setLocationRelativeTo(null);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
