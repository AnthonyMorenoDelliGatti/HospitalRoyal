package login.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Login extends JFrame {

	private JTextField textUser;
	private JPasswordField textPassword;
	private JButton buttonLogin;
	private JLabel labelInfo;
	private JButton close;

	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 533, 533);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("..//iconos//logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);	

	    setLocationRelativeTo(null);	
		
		close = new JButton("");
		close.setBounds(468, 11, 60, 35);
		close.setIcon(new ImageIcon("..//iconos//cerrar.png"));
		close.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		close.setBorder(emptyBorder);
		close.setOpaque(false);
		close.setBackground(new Color(0,0,0,0));
		getContentPane().add(close);

		textUser = new JTextField();
		textUser.setBounds(233, 192, 136, 20);
		getContentPane().add(textUser);
		textUser.setColumns(10);

		JLabel iconUser = new JLabel("");
		iconUser.setIcon(new ImageIcon("..//iconos//perfil.png"));
		iconUser.setBounds(179, 181, 34, 39);
		getContentPane().add(iconUser);

		textPassword = new JPasswordField();
		textPassword.setBounds(233, 259, 136, 20);
		getContentPane().add(textPassword);
		textPassword.setColumns(10);
		textPassword.setEchoChar('*');

		JLabel iconPassword = new JLabel("");
		iconPassword.setIcon(new ImageIcon("..//iconos//password.png"));
		iconPassword.setBounds(179, 244, 32, 50);
		getContentPane().add(iconPassword);

		buttonLogin = new JButton("LOGIN");
		buttonLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonLogin.setForeground(new Color(244, 112, 255));
		buttonLogin.setBounds(255, 346, 89, 23);
		getContentPane().add(buttonLogin);

		labelInfo = new JLabel("");
		labelInfo.setForeground(new Color(255, 255, 255));
		labelInfo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		labelInfo.setBounds(233, 106, 136, 50);
		getContentPane().add(labelInfo);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("..//iconos//hospital.jpg"));
		background.setBounds(0, 0, 533, 533);
		getContentPane().add(background);

	}

	public JTextField getTextUser() {
		return textUser;
	}

	public void setTextUser(JTextField textUser) {
		this.textUser = textUser;
	}

	public JTextField getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(JPasswordField textPassword) {
		this.textPassword = textPassword;
	}

	public JButton getButtonLogin() {
		return buttonLogin;
	}

	public void setButtonLogin(JButton buttonLogin) {
		this.buttonLogin = buttonLogin;
	}

	public JLabel getLabelInfo() {
		return labelInfo;
	}

	public void setLabelInfo(JLabel labelInfo) {
		this.labelInfo = labelInfo;
	}

	public JButton getClose() {
		return close;
	}

	public void setClose(JButton close) {
		this.close = close;
	}
	
}
