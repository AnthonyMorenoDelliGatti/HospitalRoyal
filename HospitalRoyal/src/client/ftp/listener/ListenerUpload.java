package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataOutputStream;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.DropFile;
import client.ftp.view.FTPWindow;
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
 *	description: class that control the upload button
 */
public class ListenerUpload implements ActionListener {
	FTPClient client;
	String user;
	MethodList method;
	FTPWindow v;
	FileView list;
	DataOutputStream outputStream;
	/**
	 * class' construct
	 * 
	 * @param client ftp's client
 	 * @param user name of the user
	 * @param v ftp's main window
	 * @param list window that contains the list of files
	 * @param method object that contains the class that makes the list of files
	 * @param outputStream
	 */
	public ListenerUpload(FTPClient client, String user, FTPWindow v, FileView list, MethodList method,
			DataOutputStream outputStream) {

		this.client = client;
		this.user = user;
		this.v = v;
		this.list = list;
		this.method = method;
		this.outputStream = outputStream;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		v.setEnabled(false);
		DropFile drop = new DropFile(v);
		drop.getFrame().setVisible(true);
		drop.getClose().addActionListener(new ListenerCloseWindow(drop.getFrame()));
		drop.getSave().addActionListener(new ListenerSave(drop, client, outputStream, v, method, list));
		drop.getFileChooserBtn()
				.addActionListener(new ListenerFileChooser(client, user, v, list, method, outputStream, drop));

		v.pack();
		v.setBounds(600, 600, 600, v.getBounds().height);
		v.setLocationRelativeTo(null);
		if (v.getBounds().height >= 600) {
			v.setBounds(600, 600, 600, 600);
			v.setLocationRelativeTo(null);
		}
	}
}
