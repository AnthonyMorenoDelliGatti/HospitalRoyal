package client.menu.listener;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.listener.ListenerClose;
import client.ftp.listener.ListenerCreateFolder;
import client.ftp.listener.ListenerReturn;
import client.ftp.listener.ListenerReturnForward;
import client.ftp.listener.ListenerUpload;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.menu.view.StartMenuView;
import client.model.FileFtp;
import client.model.Paths;

public class ListenerAdminFTP implements ActionListener {

	private Paths paths;
	private FTPClient client;
	private FTPWindow ftpWindow;
	private String user;
	private FileView explorer;
	private MethodList method;
	private StartMenuView vStartMenu;
	private String password;
	private DataOutputStream outputStream;

	public ListenerAdminFTP(Paths paths, FTPClient client, FTPWindow ftpWindow, String user, FileView explorer,
			MethodList method, StartMenuView vStartMenu, String password, DataOutputStream outputStream) {
		this.paths = paths;
		this.client = client;
		this.ftpWindow = ftpWindow;
		this.user = user;
		this.explorer = explorer;
		this.method = method;
		this.vStartMenu = vStartMenu;
		this.password = password;
		this.outputStream = outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			paths.setPathLimit(client.printWorkingDirectory());

			ArrayList<FileFtp> filesFtp = new ArrayList<>();
			ftpWindow = new FTPWindow(client, user, explorer, method, vStartMenu);
			explorer = new FileView(client, filesFtp, method, ftpWindow, password, outputStream, paths, true);
			method.DataListLoad(client, ftpWindow, explorer);
			ftpWindow.setVisible(true);
			ftpWindow.setLocationRelativeTo(null);
			Rectangle size=new Rectangle(600,600,600,600);
			if(ftpWindow.getBounds()!=size) {
				ftpWindow.pack();
				ftpWindow.setBounds(600,600,600,ftpWindow.getBounds().height);
				ftpWindow.setLocationRelativeTo(null);
				if(ftpWindow.getBounds().height>=600) {
					ftpWindow.setBounds(600,600,600,600);
					ftpWindow.setLocationRelativeTo(null);
				}
			}

			// listener are introduced to the buttons
			// return to parent
			ftpWindow.getButtons().get(0)
					.addActionListener(new ListenerReturn(client, method, ftpWindow, explorer, paths));
			// return to previous 
			ftpWindow.getButtons().get(1)
					.addActionListener(new ListenerReturnForward(client, method, ftpWindow, explorer, paths));
			// create folder
			ftpWindow.getButtons().get(2).addActionListener(
					new ListenerCreateFolder(client, filesFtp, method, ftpWindow, explorer, password, outputStream));
			// eliminate files and folders
			ftpWindow.getButtons().get(3)
					.addActionListener(new ListenerUpload(client, user, ftpWindow, explorer, method, outputStream));
			vStartMenu.setVisible(false);

			ftpWindow.getButtons().get(4).addActionListener(new ListenerClose(ftpWindow, vStartMenu));
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"The connection to the server could not be established",
					"ERROR", JOptionPane.WARNING_MESSAGE);
			vStartMenu.setVisible(true);
		}
	}
}
