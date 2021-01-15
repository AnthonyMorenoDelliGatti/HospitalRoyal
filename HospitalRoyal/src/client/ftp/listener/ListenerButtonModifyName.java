package client.ftp.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import client.model.FileFtp;


public class ListenerButtonModifyName implements ActionListener{

	private JTextField name;
	
	public ListenerButtonModifyName(JTextField name, FileFtp fileFtp) {
		this.name = name;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		name.setEditable(true);
		name.setBackground(Color.WHITE);
		name.requestFocus();
	}
}
