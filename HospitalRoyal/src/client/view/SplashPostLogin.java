package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashPostLogin extends JFrame{

	private JPanel contentPane;
	
	public SplashPostLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon("iconos\\cargando.gif"));
		contentPane.add(gif,BorderLayout.EAST);
		contentPane.setBackground(new Color(255, 209, 247));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
