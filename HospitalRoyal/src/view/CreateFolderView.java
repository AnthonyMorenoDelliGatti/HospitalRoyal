package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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


public class CreateFolderView extends JFrame{
	JTextField txtNameFolder;
	JButton btnCreate;
	JLabel lblMessage;
	JPanel panel;
	public CreateFolderView() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.white);
		
		setLocationRelativeTo(null);
		ArrayList<JPanel> paneles = new ArrayList<>();
		generarPaneles(paneles);
		
		JLabel lblNameFolder = new JLabel("Name: ");
		lblNameFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paneles.get(0).add(lblNameFolder);Color colorCabecera = new Color(255, 194, 121);
		paneles.get(0).setBackground(colorCabecera);
		
		//paneles.get(1).setBackground(Color.white);
		
		Icon icon = new ImageIcon("iconos\\carpeta.png");
		JLabel l = new JLabel(icon);
		paneles.get(1).add(l);
		
		txtNameFolder = new JTextField();
		txtNameFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNameFolder.setColumns(10);
		paneles.get(1).add(txtNameFolder);

		btnCreate = new JButton("Create");
		paneles.get(1).add(btnCreate);

		setContentPane(panel);
		pack();
		setVisible(true);
	}
	private void generarPaneles(ArrayList<JPanel> paneles) {
		for (int i = 0; i < 3; i++) {
			paneles.add(new JPanel());
			paneles.get(i).setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			panel.add(paneles.get(i));
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
