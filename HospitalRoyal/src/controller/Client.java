package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import view.ClientView;
import view.MenuView;

public class Client {
	static DataOutputStream outputStream;
	static DataInputStream inputStream;
	static Socket Client;

	public static void main(String[] args) throws IOException {
		
	}
		
	    /*
		ClientView v = new ClientView();
		String Host = "localhost";
		int Puerto = 5000;
		try {
			Client = new Socket(Host, Puerto);
			outputStream = new DataOutputStream(Client.getOutputStream());
			inputStream = new DataInputStream(Client.getInputStream());
		} catch (Exception e) {
			v.getLabelInfo3().setText(
					"The connection to the Server could not be established, the program will close in 5 seconds");
			v.pack();
			try {
				Thread.sleep(5 * 1000);
			} catch (Exception e2) {
				System.out.println(e2);
			}
			System.exit(0);
		}
		v.getButtonLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = v.getTextUser().getText();
				String password = v.getTextPassword().getText();
				try {
					if (!user.equals("") && !password.equals("")) {
						outputStream.writeUTF(user);
						outputStream.writeUTF(password);
					} else {
						JOptionPane.showMessageDialog(null, "Enter username and password");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		v.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					outputStream.writeUTF("*");
					inputStream.close();
					outputStream.close();
					Client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e2) {
					System.out.println(e2);
				}
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

		});

		String serverStr = "";
		while (!serverStr.equals("*")) {
			try {
				serverStr = inputStream.readUTF();
				v.getLabelInfo3().setText(serverStr);
				v.pack();
			} catch (Exception e) {

			}
		}
		v.dispose();
		MenuView vMenu = new MenuView();
		vMenu.getButtonCreate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		vMenu.getButtonDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		vMenu.getButtonDownload().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		vMenu.getButtonRename().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		vMenu.getButtonUpload().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					outputStream.writeUTF("5");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vMenu.setVisible(false);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(fileChooser);
				try {
					String route = fileChooser.getSelectedFile().getAbsolutePath(); // Ruta obtenida
					File f = new File(route);
					DataInputStream input;
					BufferedInputStream bis;
					BufferedOutputStream bos;
					bis = new BufferedInputStream(new FileInputStream(f));
					bos = new BufferedOutputStream(Client.getOutputStream());
					outputStream.writeUTF(f.getName());
					int in;
					byte[] byteArray = new byte[8192];
					while ((in = bis.read(byteArray)) != -1) {
						bos.write(byteArray, 0, in);
					}
					bis.close();
					bos.close();
				} catch (Exception except) {
					JOptionPane.showMessageDialog(null, "File has not been selected");
				}
			}

		});
	}*/
}
