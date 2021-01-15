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
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import client.model.AboutModel;
import javax.swing.JLabel;
import java.awt.GridLayout;

/**
 * @author Anthony Moreno Delli Gatti
 *         Francisco Manuel Rodriguez Martin
 *         Juan Salguero Ibarrola
 *         Nicolas Rosa Hinojosa
 *         Gonzalo Ruiz de Mier Mora
 *         
 *date 15/01/2021
 *
 *@version 1.0
 *
 *description: Clase que muestra una ventana de informacion de la aplicacion
 */
public class AboutView extends JFrame {

	private JPanel rootPanel;
	private JPanel header;
	private JPanel body;
	private JPanel rigthHeader;
	private JPanel leftHeader;
	private JButton buttonReturn;
	private Color colorHeader , colorBody;
	private AboutModel model;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public AboutView() {
		setResizable(false);
		setSize(500, 750);
		colorHeader = new Color(204, 252, 255);
		colorBody = new Color(255, 255, 255);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\acerca-de.png"));
		
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
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(colorBody);
		rootPanel.add(header);
		
		rigthHeader = new JPanel();
		header.add(rigthHeader);
		rigthHeader.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		rigthHeader.setBackground(colorHeader);
		
		Icon logoAcercaDe = new ImageIcon("iconos//acerca-de.png");
		JLabel lblicono = new JLabel(logoAcercaDe);
		leftHeader.add(lblicono);
		
		buttonReturn = new JButton();
		Icon icon = new ImageIcon("iconos//cerrar.png");
		buttonReturn.setIcon(icon);
		buttonReturn.setBackground(colorBody);
		buttonReturn.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		buttonReturn.setBorder(emptyBorder);
		rigthHeader.add(buttonReturn);
		rootPanel.add(body);
		

		generateElements();

		setUndecorated(true);

		setLocationRelativeTo(null);
	}

	/**
	 * Metodo que genera los elementos de la ventana
	 */
	public void generateElements() {
		model = new AboutModel();
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		body.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblInfo = new JLabel("Information of the application:");
		lblInfo.setAlignmentX(body.CENTER_ALIGNMENT);
		body.add(lblInfo);
		
		Icon logo = new ImageIcon("iconos//mini logo.png");
		
		JLabel lblname = new JLabel("Name: "+model.getName());
		lblname.setAlignmentX(body.CENTER_ALIGNMENT);
		body.add(lblname);	
		
		JLabel lblicono = new JLabel(logo);
		lblicono.setAlignmentX(body.CENTER_ALIGNMENT);
		body.add(lblicono);
		
		JLabel lblversion = new JLabel("Version: "+model.getVersion());
		lblversion.setAlignmentX(body.CENTER_ALIGNMENT);
		body.add(lblversion);	
		
		for(int i = 0 ; i < model.getAuthors().size() ; i++) {
			JLabel lblauthors = new JLabel(model.getAuthors().get(i));
			lblauthors.setAlignmentX(body.CENTER_ALIGNMENT);
			body.add(lblauthors);	
		}
		
		JLabel lblDate = new JLabel("Date: "+model.getDate());
		lblDate.setAlignmentX(body.CENTER_ALIGNMENT);
		body.add(lblDate);
		
		JTextArea lblCopyright = new JTextArea("Copyright: "+model.getCopyright()+"©");
		lblCopyright.setAlignmentX(body.CENTER_ALIGNMENT);
		lblCopyright.setOpaque(false);
		body.add(lblCopyright);
		
		
		setContentPane(rootPanel);
	}

	public JButton getButtonReturn() {
		return buttonReturn;
	}

	public void setButtonReturn(JButton buttonReturn) {
		this.buttonReturn = buttonReturn;
	}
	
}
