package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataOutputStream;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import client.model.Archive;
import client.model.FileFtp;
/**
 * 
 * 
 * @authors Anthony Moreno Delli Gatti
 * 			Francisco Manuel Rodriguez Martin
 * 			Juan Salguero Ibarrola
 * 			Nicolas Rosa Hinojosa
 * 			Gonzalo Ruiz de Mier Mora 
 * 
 * date	13/01/2021
 * 
 * @version 1.0
 * 
 * description: Class listener to change the name of an archive
 *
 */

public class ListenerModifyName implements ActionListener, FocusListener{

	private Archive archive;
	private JTextField name;
	/**
	 * 
	 * @param archive: the archive to change name
	 * @param name: the JTextField that contains the name
	 */
	public ListenerModifyName(Archive archive, JTextField name) {
		this.archive = archive;
		this.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		checkName();
	}
	/**
	 * Method to change the name
	 */
	private void checkName() {
		String text = name.getText();
		if(text.trim().length() > 0) {
			archive.setName(text);
		}
		else{
			name.setText(archive.getName());
		}
		name.setEditable(false);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		checkName();
	}

}
