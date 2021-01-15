package client.email.listener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.FileFtp;
import client.model.Paths;

public class ListenerFile implements MouseListener {

	private JPanel panel;
	private FileFtp fileFtp;
	private FTPWindow viewFTP;
	private Paths paths;
	private FTPClient client;
	private MethodList method;
	private FileView explorer;
	
	public ListenerFile(JPanel panel, FileFtp fileFtp, FTPWindow viewFtp, Paths paths, FTPClient client, MethodList method, FileView fileView) {
		this.panel = panel;
		this.fileFtp = fileFtp;
		this.viewFTP = viewFtp;
		this.paths = paths;
		this.client = client;
		this.method = method;
		this.explorer = fileView;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { // if a double click is made
			if(fileFtp.getIsCarpeta() == 1) { // and the file is a folder
				// the folder is open
				try {
					String newDirection ;
					if(client.printWorkingDirectory().equalsIgnoreCase("/")) {
						newDirection = client.printWorkingDirectory()+fileFtp.getName();
					}
					else {
						newDirection = client.printWorkingDirectory()+"/"+fileFtp.getName();
					}
					client.changeWorkingDirectory(newDirection);
					System.out.println(newDirection);
					viewFTP.getButtons().get(0).setEnabled(true);
					viewFTP.getButtons().get(1).setEnabled(false);
					paths.getPathguardados().clear();
					method.DataListLoad(client, viewFTP, explorer);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Folder selection
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}

