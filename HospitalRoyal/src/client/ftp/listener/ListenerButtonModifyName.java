package client.ftp.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

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
 *	description: method that control the button modify name
 */

public class ListenerButtonModifyName implements ActionListener{

	private JTextField name;
	
	/**
	 * class' constructor
	 * 
	 * @param name textfield with the name of the file
	 * @param fileFtp file of the ftp
	 */
	public ListenerButtonModifyName(JTextField name, FileFtp fileFtp) {
		this.name = name;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		name.setEditable(true);
		name.setBackground(Color.WHITE);
		name.requestFocus();
	}
}
