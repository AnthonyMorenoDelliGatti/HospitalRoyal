package client.email.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.apache.commons.net.smtp.SMTPClient;

import client.view.StartMenuView;

public class ListenerClose implements ActionListener{

	private JFrame frame;
	
	public ListenerClose(JFrame frame, SMTPClient client, StartMenuView vStartMenu) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
	}
}
