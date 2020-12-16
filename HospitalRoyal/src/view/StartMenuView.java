package view;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartMenuView extends JFrame {
	GridLayout layout = new GridLayout(0, 2);
	JButton buttonMail;
	JButton buttonFTP;

	public StartMenuView() {
		super("HOSPITAL ROYAL");
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(layout);
		
		buttonMail = new JButton("Mail");
		add(buttonMail);
		
		buttonFTP = new JButton("FTP");
		add(buttonFTP);
		
		pack();
		setVisible(true);

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
