package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import controller.Client;

public class Splash extends JFrame implements Runnable{

	Thread splash = new Thread();
	private JPanel contentPane;
	JProgressBar progressBar = new JProgressBar();
	
	public Splash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 668);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel icono = new JLabel();
		icono.setIcon(new ImageIcon(Splash.class.getResource("..\\iconos\\Logo.PNG")));
		contentPane.add(icono,BorderLayout.CENTER);
		
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon(Splash.class.getResource("..\\iconos\\cargando.gif")));
		contentPane.add(gif,BorderLayout.EAST);
		
		
		contentPane.setBackground(new Color(255, 209, 247));
		pack();
	}
	
	@Override
	public void run() {
		try {
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			Thread.sleep(4000);
			this.dispose();
			Client client = new Client();		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
