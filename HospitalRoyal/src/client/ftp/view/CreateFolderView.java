package client.ftp.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
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
 *description:Ventana con la que puedes crear nuevas carpetas
 */
public class CreateFolderView extends JFrame{
	JTextField txtNameFolder;
	JButton btnCreate;
	JLabel lblMessage;
	JPanel panel;
	
	/**
	 * Creacion de la ventana
	 */
	public CreateFolderView() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("iconos//ftp.png"));
		
		setLocationRelativeTo(null);
		ArrayList<JPanel> panels = new ArrayList<>();
		generarPaneles(panels);
		
		panels.get(0).setBackground(Color.white);
		
		Icon icon = new ImageIcon("iconos//carpeta.png");
		JLabel l = new JLabel(icon);
		panels.get(0).add(l);
		
		txtNameFolder = new JTextField();
		txtNameFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNameFolder.setColumns(10);
		panels.get(0).add(txtNameFolder);

		btnCreate = new JButton("Create");
		panels.get(0).add(btnCreate);
		
		setContentPane(panel);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	/**
	 * Metodo para generar paneles en la ventana
	 * @param panels
	 */
	private void generarPaneles(ArrayList<JPanel> panels) {
		for (int i = 0; i < 1; i++) {
			panels.add(new JPanel());
			panels.get(i).setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			panel.add(panels.get(i));
		}
	}

	public JTextField getTxtNameFolder() {
		return txtNameFolder;
	}
	public void setTxtNameFolder(JTextField txtNameFolder) {
		this.txtNameFolder = txtNameFolder;
	}
	public JButton getBtnCreate() {
		return btnCreate;
	}
	public void setBtnCreate(JButton btnCreate) {
		this.btnCreate = btnCreate;
	}
	public JLabel getLblMessage() {
		return lblMessage;
	}
	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
	
}
