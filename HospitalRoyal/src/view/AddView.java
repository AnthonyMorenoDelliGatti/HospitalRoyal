package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddView extends JFrame {
	JTextField txtReveiver , txtAffair;
	JTextArea txtMessage;
	JButton btnSend;
	JLabel lblMessage;
	public AddView() {
		JLabel lblReceiver = new JLabel("Destinatario del correo");
		lblReceiver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReceiver.setBounds(20, 20, 200, 25);
		add(lblReceiver);
		
		txtReveiver = new JTextField();
		txtReveiver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtReveiver.setBounds(250,20, 200, 25);
		txtReveiver.setColumns(10);
		add(txtReveiver);
		
		JLabel lblAffair = new JLabel("Asunto");
		lblAffair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAffair.setBounds(20, 60, 200, 25);
		add(lblAffair);
		
		txtAffair = new JTextField();
		txtAffair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAffair.setBounds(250,60, 200, 25);
		txtAffair.setColumns(10);
		add(txtAffair);
		
		JLabel lblMessageEmail = new JLabel("Mensaje");
		lblMessageEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessageEmail.setBounds(20, 100, 200, 25);
		add(lblMessageEmail);
		
		txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMessage.setBounds(250,100, 200, 125);
		txtMessage.setColumns(10);
		add(txtMessage);
		
		btnSend = new JButton("Enviar");
		btnSend.setBounds(20, 250, 200, 25);
		add(btnSend);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessage.setBounds(250, 250, 200, 25);
		add(lblMessage);
		
		setVisible(true);
		pack();
	}
	public JTextField getTxtReveiver() {
		return txtReveiver;
	}
	public void setTxtReveiver(JTextField txtReveiver) {
		this.txtReveiver = txtReveiver;
	}
	public JTextField getTxtAffair() {
		return txtAffair;
	}
	public void setTxtAffair(JTextField txtAffair) {
		this.txtAffair = txtAffair;
	}
	public JTextArea getTxtMessage() {
		return txtMessage;
	}
	public void setTxtMessage(JTextArea txtMessage) {
		this.txtMessage = txtMessage;
	}
	public JButton getBtnSend() {
		return btnSend;
	}
	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}
	public JLabel getLblMessage() {
		return lblMessage;
	}
	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}
	
}
