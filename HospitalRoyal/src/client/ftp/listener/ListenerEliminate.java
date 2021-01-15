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



public class ListenerEliminate implements ActionListener {

	private FileFtp fileFtp;
	private ArrayList<FileFtp> filesFtp;
	private FTPClient client;
	private MethodList method;
	private FTPWindow view;
	private FileView explorer;
	private String user;
	DataOutputStream outputStream;

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
				if (fileFtp.getIsCarpeta() == 0) {
					try {
						client.deleteFile(fileFtp.getDireccion());

						outputStream.writeUTF("3");
						outputStream.writeUTF(fileFtp.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						if (!client.removeDirectory(fileFtp.getDireccion())) {
							String previousPath = client.printWorkingDirectory();
							client.changeWorkingDirectory(fileFtp.getDireccion());
							delete(client.listFiles());
							client.changeWorkingDirectory(previousPath);
							client.removeDirectory(fileFtp.getDireccion());
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
