package ftp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.commons.net.smtp.SMTPClient;

import view.StartMenuView;

public class ListenerClose implements ActionListener {

	private JFrame frame;
	private SMTPClient client;
	private StartMenuView vStartMenu;
	
	public ListenerClose(JFrame frame, SMTPClient client, StartMenuView vStartMenu) {
		this.frame = frame;
		this.client = client;
		this.vStartMenu = vStartMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			client.disconnect();
			vStartMenu.setVisible(true);
			frame.dispose();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
