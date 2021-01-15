package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataOutputStream;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;

import client.model.Archive;
import client.model.FileFtp;

public class ListenerModifyName implements ActionListener, FocusListener{

	private Archive archive;
	private JTextField name;
	
	public ListenerModifyName(Archive archive, JTextField name) {
		this.archive = archive;
		this.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		checkName();
	}

	private void checkName() {
		String text = name.getText();
		if(text.trim().length() > 0) {
			archive.setName(text);
			//send the new name to the server
		}
		else{
			name.setText(archive.getName());
		}
		name.setEditable(false);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		checkName();
	}

}
