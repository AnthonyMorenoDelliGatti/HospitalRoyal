package client.ftp.listener;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.FileFtp;
import client.model.Paths;

public class ListenerFile implements MouseListener {

	private JPanel panel;
	private FileFtp fileFtp;
	private FTPWindow view;
	private Paths paths;
	private FTPClient client;
	private MethodList method;
	private FileView explorer;
	private JTextField name;

	public ListenerFile(JPanel panel, FileFtp fileFtp, FTPWindow vista, Paths paths, FTPClient client,
			MethodList method, FileView fileView, JTextField name) {
		this.panel = panel;
		this.fileFtp = fileFtp;
		this.view = vista;
		this.paths = paths;
		this.client = client;
		this.method = method;
		this.explorer = fileView;
		this.name = name;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { // if double click is made
			if (fileFtp.getIsFolder() == 1) { //and the file is a folder
				// the folder is open
				try {
					String newDirecction;
					if (client.printWorkingDirectory().equalsIgnoreCase("/")) {
						newDirecction = client.printWorkingDirectory() + fileFtp.getName();
					} else {
						newDirecction = client.printWorkingDirectory() + "/" + fileFtp.getName();
					}
					client.changeWorkingDirectory(newDirecction);
					view.getButtons().get(0).setEnabled(true);
					view.getButtons().get(1).setEnabled(false);
					paths.getSavedPaths().clear();
					method.DataListLoad(client, view, explorer);
					
					view.pack();
					view.setBounds(600,600,600,view.getBounds().height);
					view.setLocationRelativeTo(null);
					if(view.getBounds().height>=600) {
						view.setBounds(600,600,600,600);
						view.setLocationRelativeTo(null);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Folder selecction
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
		name.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(Color.white);
		name.setBackground(Color.white);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
