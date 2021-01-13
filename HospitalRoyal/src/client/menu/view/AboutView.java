package client.menu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import client.model.AboutModel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class AboutView extends JFrame {

	private JPanel rootPanel;
	private JPanel header;
	private JPanel body;
	private JPanel rigthHeader;
	private JPanel leftHeader;
	private JButton buttonReturn;
	private Color colorHeader;
	private AboutModel model;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public AboutView() {
		colorHeader = new Color(204, 252, 255);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\logo.png"));
		
		rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		
		header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		header.setBackground(colorHeader);
		
		leftHeader = new JPanel();
		leftHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		leftHeader.setBackground(colorHeader);
		header.add(leftHeader);
		body = new JPanel();
		
		rootPanel.add(header);
		
		rigthHeader = new JPanel();
		header.add(rigthHeader);
		rigthHeader.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		rigthHeader.setBackground(colorHeader);
		
		buttonReturn = new JButton();
		Icon icon = new ImageIcon("iconos//cerrar.png");
		buttonReturn.setIcon(icon);
		buttonReturn.setBackground(colorHeader);
		buttonReturn.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		buttonReturn.setBorder(emptyBorder);
		rigthHeader.add(buttonReturn);
		rootPanel.add(body);
		

		generateElements();

		setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void generateElements() {
		model = new AboutModel();
		Icon icon = new ImageIcon("iconos//cerrar.png");
		Border emptyBorder = BorderFactory.createEmptyBorder();
		body.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Informacion de la aplicacion:");
		body.add(lblNewLabel);
		setContentPane(rootPanel);
		
		
	}

	public JButton getButtonReturn() {
		return buttonReturn;
	}

	public void setButtonReturn(JButton buttonReturn) {
		this.buttonReturn = buttonReturn;
	}
	
}
