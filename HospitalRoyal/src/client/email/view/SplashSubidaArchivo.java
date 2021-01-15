package client.email.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.Border;

import client.ftp.view.FTPWindow;

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
 * description: Window splash while the user is uploading a file
 *
 */

public class SplashSubidaArchivo extends JFrame {

	Thread splash = new Thread();
	private JProgressBar barra;
	JPanel panel = new JPanel();
	JButton btn;
	/**
	 * 
	 * @param panel2 the panel to show the splash
	 */
	public SplashSubidaArchivo(JFrame panel2) {
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.white);
		this.setUndecorated(true);
		btn = new JButton("");
		btn.setIcon(new ImageIcon(("iconos\\subidaSplash.png")));
		btn.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btn.setBorder(emptyBorder);
		btn.setBackground(Color.white);
		panel.add(btn, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JProgressBar progressbar = new JProgressBar();
		progressbar.setStringPainted(true);
		progressbar.setValue(0);
		progressbar.setForeground(Color.green);
		int timerDelay = 10;
		new Timer(timerDelay, new ActionListener() {
			private int index = 0;
			private int maxIndex = 100;

			public void actionPerformed(ActionEvent e) {
				if (index < maxIndex) {
					progressbar.setValue(index);
					index++;
				} else {
					progressbar.setValue(maxIndex);
					((javax.swing.Timer) e.getSource()).stop();
					panel2.setEnabled(true);
					dispose();
				}
			}
		}).start();

		progressbar.setValue(progressbar.getMinimum());
		panel.add(progressbar, BorderLayout.SOUTH);

		setContentPane(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
