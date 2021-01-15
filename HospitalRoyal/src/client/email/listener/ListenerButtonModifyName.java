package client.email.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.model.FileFtp;

/**
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
 * description: Class listener to activate the editable and the focus atributte on the name to change
 *
 */

public class ListenerButtonModifyName implements ActionListener{

	private JTextField name;
	/**
	 * 
	 * @param name: JTextField which will be renamed
	 * 
	 */
	public ListenerButtonModifyName(JTextField name) {
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		name.setEditable(true);
		name.requestFocus();
	}
}
