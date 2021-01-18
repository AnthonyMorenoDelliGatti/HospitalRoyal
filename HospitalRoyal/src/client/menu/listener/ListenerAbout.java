package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.menu.view.AboutView;

/**
 * @author Anthony Moreno Delli Gatti
 *         Francisco Manuel Rodriguez Martin
 *         Juan Salguero Ibarrola
 *         Nicolas Rosa Hinojosa
 *         Gonzalo Ruiz de Mier Mora
 *         
 *date 15/01/2021
 *
 *@version 1.0
 *
 *description: Clase que realiza las acciones del about
 */
public class ListenerAbout implements ActionListener{
	AboutView aboutView;
	@Override
	public void actionPerformed(ActionEvent e) {
		aboutView = new AboutView();
		aboutView.setVisible(true);
		aboutView.getButtonReturn().addActionListener(new ActionListener() {

			/**
			 * Metodo que cierra la ventana
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				aboutView.dispose();
			}
			
		});
	}

}
