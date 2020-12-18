package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame {
	JPanel rootPanel;
	JLabel labelInfo3;
	JButton buttonLogin;
	JTextField textUser, textPassword;
	public Login() {
		rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));	
		setResizable(false);
		
		ArrayList<JPanel> paneles = new ArrayList<>();
		generarPaneles(paneles);
		
		paneles.get(0).add(new JLabel("LOGIN"));
		Color colorCabecera = new Color(255, 194, 121);
		paneles.get(0).setBackground(colorCabecera);
		
		paneles.get(1).add(new JLabel("Usuario"));
		paneles.get(3).add(new JLabel("Password"));
		
		textUser = new JTextField(15);
		paneles.get(2).add(textUser);
		
		textPassword = new JTextField(15);
		paneles.get(4).add(textPassword);
		
		buttonLogin = new JButton("Iniciar Sesion");
		paneles.get(5).add(buttonLogin);
		labelInfo3 = new JLabel();
		add(labelInfo3);
		setContentPane(rootPanel);
	}

	private void generarPaneles(ArrayList<JPanel> paneles) {
		for (int i = 0; i < 6; i++) {
			paneles.add(new JPanel());
			paneles.get(i).setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			rootPanel.add(paneles.get(i));
		}
	}
	public JLabel getLabelInfo3() {
		return labelInfo3;
	}
	public JButton getButtonLogin() {
		return buttonLogin;
	}
	public JTextField getTextUser() {
		return textUser;
	}

	public JTextField getTextPassword() {
		return textPassword;
	}
}
