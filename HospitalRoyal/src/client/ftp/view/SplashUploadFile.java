package client.ftp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.Border;

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
 *description: Ventana que funciona como splash al ejecutar acciones de la ventana ftp
 */
public class SplashUploadFile extends JFrame {

	Thread splash = new Thread();
	private JProgressBar bar;
	JPanel panel = new JPanel();
	JButton btn;

	/**
	 * Creacion de la ventana con sus elementos
	 * @param v
	 * @param tipe
	 */
	public SplashUploadFile(FTPWindow v, String tipe) {
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.white);
		this.setUndecorated(true);
		btn = new JButton("");
		if (tipe.equals("upload")) {
			btn.setIcon(new ImageIcon(("iconos\\subidaSplash.png")));
		} else if (tipe.equals("download")) {
			btn.setIcon(new ImageIcon(("iconos\\descargaSplash.png")));
		}
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
					v.setEnabled(true);
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
