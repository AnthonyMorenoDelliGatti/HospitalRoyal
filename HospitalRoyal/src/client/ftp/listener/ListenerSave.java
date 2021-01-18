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
/**
 * 
 * @authors Anthony Moreno Delli Gatti
 *			Francisco Manuel Rodriguez Martin
 *			Juan Salguero Ibarrola
 *			Nicolas Rosa Hinojosa
 *			Gonzalo Ruiz de Mier Mora
 *
 *	date 13/01/2021
 *
 *	@version 1.0
 *
 *	description: class that control the save button of the drop file
 */
public class ListenerSave implements ActionListener{

	private DropFile drop;
	private FTPClient client;
	private DataOutputStream outputStream;
	private FTPWindow v;
	private MethodList method;
	private FileView list;
	/**
	 * class' constructor
	 * @param drop window that allow to drag a file to upload
	 * @param client ftp's client 
	 * @param outputStream
	 * @param v ftp's window
	 * @param method object that contains the class that makes the list of files
	 * @param list window that contains the list of files
	 */
	public ListenerSave(DropFile drop, FTPClient client, DataOutputStream outputStream, FTPWindow v, MethodList method, FileView list) {
		this.drop=drop;
		this.client=client;
		this.outputStream=outputStream;
		this.v=v;
		this.method=method;
		this.list=list;
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
				method.DataListLoad(client, v, list);
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
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
