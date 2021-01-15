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

import client.controller.MethodList;
import client.ftp.view.DropFile;
import client.ftp.view.FTPWindow;
import client.ftp.view.SplashUploadFile;
import client.ftp.view.FileView;

public class ListenerSave implements ActionListener{

	private DropFile drop;
	private FTPClient client;
	private DataOutputStream outputStream;
	private FTPWindow v;
	private MethodList method;
	private FileView lista;
	public ListenerSave(DropFile drop, FTPClient client, DataOutputStream outputStream, FTPWindow v, MethodList method, FileView lista) {
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
				method.DataListLoad(client, v, lista);
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
