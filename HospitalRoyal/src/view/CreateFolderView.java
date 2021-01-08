package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateFolderView extends JFrame{
	JTextField txtNameFolder;
	JButton btnCreate;
	JLabel lblMessage;
	public CreateFolderView() {
		setLocationRelativeTo(null);
		JLabel lblNameFolder = new JLabel("Name of the new Folder:");
		lblNameFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNameFolder.setBounds(20, 20, 200, 25);
		add(lblNameFolder);
		
		txtNameFolder = new JTextField();
		txtNameFolder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNameFolder.setBounds(250,20, 200, 25);
		txtNameFolder.setColumns(10);
		add(txtNameFolder);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(20, 60, 200, 25);
		add(btnCreate);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessage.setBounds(250, 60, 200, 25);
		add(lblMessage);
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
