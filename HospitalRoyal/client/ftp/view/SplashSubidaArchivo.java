package ftp.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class SplashSubidaArchivo extends JFrame{

	private JProgressBar barra;
	JPanel panel = new JPanel();
	JButton btn;
	public SplashSubidaArchivo() {
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.white);
		this.setUndecorated(true);
		btn = new JButton("");
		btn.setIcon(new ImageIcon(SplashSubidaArchivo.class.getResource("descargaSplash.png")));
		btn.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btn.setBorder(emptyBorder);
		btn.setBackground(Color.white);
		panel.add(btn,BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		barra=new JProgressBar(0,100);
		barra.setForeground(Color.green);
		panel.add(barra,BorderLayout.SOUTH);
		
		setContentPane(panel);
		pack();
		setVisible(true);
		
		cargar();
	}
	private void cargar() {
		for(int i=0;i<=100;i++) {
			barra.setValue(i);
			if(i==100) {
				System.exit(0);
			}
		}
	}
	
}
