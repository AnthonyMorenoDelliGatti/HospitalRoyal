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

public class ListenerUpload implements ActionListener {
	FTPClient client;
	String user;
	MethodList method;
	FTPWindow v;
	FileView list;
	DataOutputStream outputStream;

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
