package client.ftp.listener;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import client.controller.MethodList;
import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.FileFtp;

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
 *	description: class that control the eliminate option
 */
public class ListenerEliminate implements ActionListener {

	private FileFtp fileFtp;
	private ArrayList<FileFtp> filesFtp;
	private FTPClient client;
	private MethodList method;
	private FTPWindow view;
	private FileView explorer;
	private String user;
	DataOutputStream outputStream;

	/**
	 * class' constructor
	 * 
	 * @param fileFtp file of the ftp
	 * @param filesFtp arraylist of files of the ftp
	 * @param client ftp client
	 * @param method object that contains the class that make the list of files
	 * @param view ftp's window
	 * @param explorer window that contains the list of files
	 * @param user name of the user
	 * @param outputStream
	 */
	public ListenerEliminate(FileFtp fileFtp, ArrayList<FileFtp> filesFtp, FTPClient client, MethodList method,
			FTPWindow view, FileView explorer, String user, DataOutputStream outputStream) {
		this.fileFtp = fileFtp;
		this.filesFtp = filesFtp;
		this.client = client;
		this.method = method;
		this.view = view;
		this.explorer = explorer;
		this.user = user;
		this.outputStream = outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] opciones = { "YES", "NO" };
		int eleccion = JOptionPane.showOptionDialog(null, "Confirm it's elimination?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "");

		if (eleccion == JOptionPane.YES_OPTION) {
			try {
				String originalPath = client.printWorkingDirectory();
				if (fileFtp.getIsFolder() == 0) {
					try {
						client.deleteFile(fileFtp.getDirection());

						outputStream.writeUTF("3");
						outputStream.writeUTF(fileFtp.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						if (!client.removeDirectory(fileFtp.getDirection())) {
							String previousPath = client.printWorkingDirectory();
							client.changeWorkingDirectory(fileFtp.getDirection());
							delete(client.listFiles());
							client.changeWorkingDirectory(previousPath);
							client.removeDirectory(fileFtp.getDirection());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				// View is updated
				try {
					client.changeWorkingDirectory(originalPath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				method.DataListLoad(client, view, explorer);

				Rectangle size=new Rectangle(600,600,600,600);
				if(view.getBounds()==size) {
					
				}else {
					view.pack();
					view.setBounds(600,600,600,view.getBounds().height);
					view.setLocationRelativeTo(null);
					if(view.getBounds().height>600) {
						view.setBounds(600,600,600,600);
						view.setLocationRelativeTo(null);
					}
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			;
		}
	}
/**
 *  method that delete a file
 * @param listFiles list of files of the ftp
 */
	private void delete(FTPFile[] listFiles) {
		if (listFiles.length != 0) {
			for (FTPFile file : listFiles) {
				if (!file.isDirectory()) {
					try {
						client.deleteFile(client.printWorkingDirectory() + "/" + file.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						if (!client.removeDirectory(client.printWorkingDirectory() + "/" + file.getName())) {
							String previousPath = client.printWorkingDirectory();
							System.out.println(client.printWorkingDirectory() + "/" + file.getName());
							client.changeWorkingDirectory(client.printWorkingDirectory() + "/" + file.getName());
							delete(client.listFiles());
							client.changeWorkingDirectory(previousPath);
							client.removeDirectory(client.printWorkingDirectory() + "/" + file.getName());

							outputStream.writeUTF("6");
							outputStream.writeUTF(file.getName());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}

}
