package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Client;

public class Splash extends JFrame implements Runnable{

	Thread splash = new Thread();
	private JPanel contentPane;
	
	public Splash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Splash.class.getResource("..\\HospitalRoyal\\iconos\\Logo.PNG")));
		contentPane.add(label, BorderLayout.CENTER);
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
