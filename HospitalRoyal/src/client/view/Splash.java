package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import client.controller.Client;
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
 *description: Clase que funciona como splash al iniciar la aplicacion como cliente.
 */

public class Splash extends JFrame implements Runnable{

	Thread splash = new Thread();
	private JPanel contentPane;
	JProgressBar progressBar = new JProgressBar();

	/**
	 * Creacion de la ventana
	 */
	public Splash() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon("iconos\\Logo.PNG"));
		contentPane.add(icon,BorderLayout.CENTER);
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon("iconos\\cargando.gif"));
		contentPane.add(gif,BorderLayout.EAST);
		contentPane.setBackground(new Color(255, 209, 247));
		pack();
		setLocationRelativeTo(null);
	}
	
	@Override
	public void run() {
		try {
			this.setVisible(true);
			Thread.sleep(2000);
			this.dispose();
			Client client = new Client();		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
