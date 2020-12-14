package view;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientView extends JFrame {

	GridLayout layout = new GridLayout(0, 2);
	JLabel labelInfo, labelInfo2, labelInfo3;
	JButton buttonLogin;
	TextField textUser, textPassword;

	public ClientView() {
		super("HOSPITAL ROYAL");
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(layout);
		labelInfo = new JLabel("User: ");
		add(labelInfo);
		textUser = new TextField(15);
		add(textUser);
		labelInfo2 = new JLabel("Password: ");
		add(labelInfo2);
		textPassword = new TextField(15);
		add(textPassword);
		buttonLogin = new JButton("Login");
		add(buttonLogin);
		labelInfo3 = new JLabel();
		add(labelInfo3);
		pack();
		setVisible(true);
	}

	public JButton getButtonLogin() {
		return buttonLogin;
	}

	public TextField getTextUser() {
		return textUser;
	}

	public TextField getTextPassword() {
		return textPassword;
	}

	public JLabel getLabelInfo3() {
		return labelInfo3;
	}

}
