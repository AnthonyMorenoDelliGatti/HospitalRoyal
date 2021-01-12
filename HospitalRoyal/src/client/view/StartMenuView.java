package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenuView extends JFrame {

	JPanel panel = new JPanel();
	BoxLayout datos = new BoxLayout(panel, BoxLayout.Y_AXIS);
	JButton buttonMail;
	JButton buttonFTP;

	public StartMenuView() {
		super("HOSPITAL ROYAL");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(400, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));

		setLocationRelativeTo(null);

		ArrayList<JPanel> paneles = new ArrayList<>();
		generarPaneles(paneles);

		paneles.get(0).add(new JLabel("HOSPITAL ROYAL"));
		Color colorCabecera = new Color(255, 194, 121);
		paneles.get(0).setBackground(colorCabecera);

		buttonMail = new JButton("Mail");
		buttonMail.setPreferredSize(new Dimension(300, 25));
		paneles.get(1).add(buttonMail);

		buttonFTP = new JButton("FTP");
		buttonFTP.setPreferredSize(new Dimension(300, 25));
		paneles.get(2).add(buttonFTP);

		setContentPane(panel);
		pack();
		setVisible(true);
		

	}

	private void generarPaneles(ArrayList<JPanel> paneles) {
		for (int i = 0; i < 3; i++) {
			paneles.add(new JPanel());
			paneles.get(i).setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			panel.add(paneles.get(i));
		}
	}

	public JButton getButtonMail() {
		return buttonMail;
	}

	public void setButtonMail(JButton buttonMail) {
		this.buttonMail = buttonMail;
	}

	public JButton getButtonFTP() {
		return buttonFTP;
	}

	public void setButtonFTP(JButton buttonFTP) {
		this.buttonFTP = buttonFTP;
	}
	
}
