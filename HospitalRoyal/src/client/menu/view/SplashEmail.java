package client.menu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

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
 *description: Clase que crea una ventana splash entre acciones en el email
 */

public class SplashEmail extends JFrame{

	Thread splash = new Thread();
	private JPanel contentPane;
	JProgressBar progressBar = new JProgressBar();
	
	/**
	 * Creacion de la ventana y sus elementos
	 */
	public SplashEmail() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon("iconos\\cargando.gif"));
		gif.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(gif,BorderLayout.EAST);

		pack();
		Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
