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
 * @author Anthony Moreno Delli Gatti Francisco Manuel Rodriguez Martin Juan
 *         Salguero Ibarrola Nicolas Rosa Hinojosa Gonzalo Ruiz de Mier Mora
 * 
 *         date 15/01/2021
 *
 * @version 1.0
 *
 *          description: Clase que muestra una ventana de informacion de la
 *          aplicacion
 *
 */
public class AboutView extends JFrame {

	private JPanel rootPanel;
	private JPanel header;
	private JPanel body;
	private JPanel rigthHeader;
	private JPanel leftHeader;
	private JButton buttonReturn;
	private Color colorHeader, colorBody;
	private AboutModel model;

	/**
	 * class's constructor
	 */
	public AboutView() {
		setResizable(false);
		setSize(500, 750);
		colorHeader = new Color(204, 252, 255);
		colorBody = new Color(255, 255, 255);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\acerca-de.png"));

		rootPanel = new JPanel();
		rootPanel.setLayout(new FlowLayout());

		header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		header.setBackground(colorHeader);

		leftHeader = new JPanel();
		leftHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		leftHeader.setBackground(colorHeader);
		header.add(leftHeader);

		body = new JPanel();
		body.setLayout(new GridLayout(0, 1));
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
		pack();
	}

	/**
	 * Metodo que genera los elementos de la ventana
	 */
	public void generateElements() {
		model = new AboutModel();

		Border emptyBorder = BorderFactory.createEmptyBorder();
		body.setLayout(new FlowLayout());

		JTextArea textAreaInfo = new JTextArea();
		textAreaInfo.setAlignmentX(body.CENTER_ALIGNMENT);
		textAreaInfo.setOpaque(false);
		textAreaInfo.setEditable(false);
		textAreaInfo.append("Information of the application:\n");

		textAreaInfo.append("\nName: " + model.getName());

		textAreaInfo.append("\nVersion: " + model.getVersion());

		textAreaInfo.append("Authors:");
		for (int i = 0; i < model.getAuthors().size(); i++) {
			textAreaInfo.append("\n -" + model.getAuthors().get(i));
		}

		textAreaInfo.append("\nDate: " + model.getDate());

		JTextArea lblCopyright = new JTextArea("Copyright: " + model.getCopyright() + "©");
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
