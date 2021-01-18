package client.email.listener;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.apache.commons.net.smtp.SMTPClient;

import client.menu.view.StartMenuView;

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
 * description: Class listener to activate the action of close the window and open the previous window
 *
 */

public class ListenerClose implements ActionListener{
	private JFrame frame;
	private StartMenuView vStartMenu;
	/**
	 * 
	 * @param frame: is the frame to close;
	 * @param vStartMenu: the previous window which will be open in close operation
	 */
	public ListenerClose(JFrame frame, StartMenuView vStartMenu) {
		this.frame = frame;
		this.vStartMenu = vStartMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		vStartMenu.setVisible(true);
	}
}
