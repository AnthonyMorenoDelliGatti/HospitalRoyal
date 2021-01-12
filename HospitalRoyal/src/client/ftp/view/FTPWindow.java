package client.ftp.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.Methods;
import client.view.StartMenuView;


/**
 *	Window that allows you to view files, rename, create folders and upload files.
 *
 */
public class FTPWindow extends JFrame {

	private JPanel rootPanel;
	private JPanel leftHeader;
	private JPanel body;
	private JPanel rigthHeader;
	private Color colorHeader;
	private ArrayList<JButton> buttons = new ArrayList();
	VistaArchivos lista;
	private Methods method;
	StartMenuView principalView;

	/**
	 * Builder
	 * 
	 * @param client - FTPClient - contains customer
	 * @param user   - String - 
	 * @param lista  - VistaArchivos -
	 * @param method - Methods -
	 * @param vStartMenu 
	 */
	public FTPWindow(FTPClient client, String user, VistaArchivos lista, Methods method, StartMenuView principalView) {
		colorHeader = new Color(204, 252, 255);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos//ftp.png"));
		
		this.lista = lista;
		this.method = method;
		this.principalView = principalView;
		rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		
		JPanel header = generateHeader();

		body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));
		
		
		rootPanel.add(header);
		rootPanel.add(body);
		setContentPane(rootPanel);

		generateOptions();
		
		setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				principalView.setVisible(true);	
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
						
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * Generate the header
	 * 
	 * @return header - JPanel - contains the header
	 * 
	 */
	private JPanel generateHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		leftHeader = new JPanel();
		leftHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		leftHeader.setBackground(colorHeader);

		rigthHeader = new JPanel();
		rigthHeader.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		rigthHeader.setBackground(colorHeader);
		
		header.add(leftHeader);
		header.add(rigthHeader);
		
		return header;
	}

	/**
	 * Generate the options found in the header
	 * 
	 */
	private void generateOptions() {
		JButton back = generateHeaderButton("iconos//atras.png", leftHeader);
		back.setEnabled(false);
		
		JButton forward = generateHeaderButton("iconos//flecha-correcta.png", leftHeader);
		forward.setEnabled(false);
		
		generateHeaderButton("iconos//folder2.png", leftHeader); // folder
		generateHeaderButton("iconos//subir.png", leftHeader); // upload
		generateHeaderButton("iconos//cerrar.png", rigthHeader);// close
	}

	/**
	 * Generates a button that is placed in the header
	 * 
	 * @param direction - String -
	 * @param panel - JPanel - 
	 * @return - JButton - 
	 */
	private JButton generateHeaderButton(String direction, JPanel panel) {
		JButton button = new JButton();
		Icon icon = new ImageIcon(direction);
		button.setIcon(icon);
		button.setBackground(colorHeader);
		button.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button.setBorder(emptyBorder);
		panel.add(button);
		buttons.add(button);
		return button;
	}

	/**
	 * Add a panel to the body
	 * 
	 * @param jPanel - JPanel - contains a panel containing the files to display
	 */
	public void addExplorer(JPanel jPanel) {
		body.add(jPanel);
	}

	/**
	 * Replace one panel with another in the body
	 * 
	 * @param explorer
	 */
	public void updateExplorer(JPanel explorer) {
		body.remove(1); // borra el anterior explorador
		addExplorer(explorer);
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}

	public JPanel getCentro() {
		return body;
	}

	public void setCentro(JPanel centro) {
		this.body = centro;
	}
}
