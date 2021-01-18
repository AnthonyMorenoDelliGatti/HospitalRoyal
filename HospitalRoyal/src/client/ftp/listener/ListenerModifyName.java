package client.ftp.listener;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

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
 *	description: class that control the option of modify name
 */
public class ListenerModifyName implements FocusListener, KeyListener {

	private FileFtp fileFtp;
	private JTextField name;
	FTPClient client;
	private String user;
	DataOutputStream outputStream;
	boolean nameExist = false;

	/**
	 * class' constructor
	 * 
	 * @param fileFtp ftp's file
	 * @param name jtextfield with the name of the file
	 * @param client ftp's client
	 * @param user name of the user
	 * @param outputStream
	 */
	public ListenerModifyName(FileFtp fileFtp, JTextField name, FTPClient client, String user,
			DataOutputStream outputStream) {
		this.fileFtp = fileFtp;
		this.name = name;
		this.client = client;
		this.user = user;
		this.outputStream = outputStream;
	}
	/**
	 * method that check if the name of the file is a valid one
	 */
	private void checkName() {
		String text = name.getText();
		String previousName = fileFtp.getName();
		if (text.trim().length() > 0) {
			if(!cambiarnombre(text, previousName)) {
				fileFtp.setName(text);
				name.setText(previousName);
			}
		} else {
			name.setText(previousName);
		}
		name.setEditable(false);
		name.setBackground(Color.white);
	}
	/**
	 * method that change the name of a file
	 * 
	 * @param newName the new name of the file
	 * 
	 * @param name the previous name of the file
	 */
	private boolean cambiarnombre(String newName, String name) {
		FTPFile[] fileList;
		FTPFile[] fileList2;
		try {
			fileList2 = client.listFiles();
			for (int i = 0; i < fileList2.length; i++) {
				if (fileList2[i].getName().equals(newName)) {
					JOptionPane.showMessageDialog(null, "The name already exist", "ERROR", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
			fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().equals(name)) {
					client.rename(fileList[i].getName(), newName);
				}
			}
			outputStream.writeUTF("7");
			outputStream.writeUTF(name);
			outputStream.writeUTF(newName);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		checkName();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			checkName();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
