package client.menu.view;

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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 * 
 * @authors Anthony Moreno Delli Gatti
 *			Francisco Manuel Rodriguez Martin
 *			Juan Salguero Ibarrola
 *			Nicolas Rosa Hinojosa
 *			Gonzalo Ruiz de Mier Mora
 *
 *	date 13/01/2021
 *
 *	@version 1.0
 *
 *	description: class that control the star menu's window
 */
public class StartMenuView extends JFrame {

	private JButton buttonMail;
	private JButton buttonFTP;
	private JButton btnAbout;
	private JLabel image;
	private JButton btnClose;
		
	/**
	 * class' constructor
	 */
	public StartMenuView() {
		initialize();
		sliderShow();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	/**
	 * method that control the slider of the window
	 */
	public void sliderShow() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					int i = 2;
					while (true) {
						switch (i) {	
						case 1:
							Thread.sleep(5000);
							image.setIcon(new ImageIcon("iconos\\diagnostico.jpg"));
							break;
						case 2:
							Thread.sleep(5000);
							image.setIcon(new ImageIcon("iconos\\cirujanos.jpg"));
							break;
						default:
							i = 0;
							break;
						}
						i++;
					}
				} catch (Exception e) {

				}

			}

		}).start();
	}

	/**
	 * method that initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 682, 436);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));

		Color color = new Color(176, 224, 230);
		JPanel sidebar = new JPanel();
		sidebar.setBackground(color);
		sidebar.setBounds(0, 0, 63, 436);
		getContentPane().add(sidebar);
		sidebar.setLayout(null);

		buttonMail = new JButton("");
		buttonMail.setIcon(new ImageIcon("iconos\\email.png"));
		buttonMail.setBounds(10, 11, 42, 43);
		buttonMail.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		buttonMail.setBorder(emptyBorder);
		buttonMail.setBackground(color);
		sidebar.add(buttonMail);

		btnAbout = new JButton("");
		btnAbout.setIcon(new ImageIcon("iconos\\acerca-de.png"));
		btnAbout.setBounds(10, 391, 42, 34);
		btnAbout.setFocusPainted(false);
		btnAbout.setBorder(emptyBorder);
		btnAbout.setBackground(color);
		sidebar.add(btnAbout);

		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("iconos\\cerrar-sesion.png"));
		btnClose.setBounds(10, 337, 42, 43);
		btnClose.setFocusPainted(false);
		btnClose.setBorder(emptyBorder);
		btnClose.setBackground(color);
		sidebar.add(btnClose);

		buttonFTP = new JButton("");
		buttonFTP.setIcon(new ImageIcon("iconos\\ftp.png"));
		buttonFTP.setBounds(10, 65, 42, 41);
		buttonFTP.setFocusPainted(false);
		buttonFTP.setBorder(emptyBorder);
		buttonFTP.setBackground(color);
		sidebar.add(buttonFTP);

		JPanel body = new JPanel();
		body.setBackground(new Color(224, 255, 255));
		body.setBounds(61, 0, 621, 436);
		getContentPane().add(body);
		body.setLayout(null);

		image = new JLabel("New label");
		image.setIcon(new ImageIcon("iconos\\diagnostico.jpg"));
		image.setBounds(78, 103, 460, 259);
		body.add(image);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("iconos\\hospital_royal.png"));
		lblNewLabel_1.setBounds(117, 21, 350, 58);
		body.add(lblNewLabel_1);
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

	public JButton getBtnAbout() {
		return btnAbout;
	}

	public void setBtnAbout(JButton btnAbout) {
		this.btnAbout = btnAbout;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}
	
}
