package client.ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import client.menu.view.StartMenuView;

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
 *	description: class that control the close button
 */

public class ListenerClose implements ActionListener {

	private JFrame frame;
	private StartMenuView vStartMenu;
	
	/**
	 * class' constructor
	 * 
	 * @param frame ftp window
	 * @param vStartMenu start menu window
	 */
	public ListenerClose(JFrame frame, StartMenuView vStartMenu) {
		this.frame = frame;
		this.vStartMenu = vStartMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vStartMenu.setVisible(true);
		frame.dispose();
	}
}
