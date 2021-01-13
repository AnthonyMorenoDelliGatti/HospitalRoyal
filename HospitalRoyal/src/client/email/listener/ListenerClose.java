package client.email.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.apache.commons.net.smtp.SMTPClient;

import client.menu.view.StartMenuView;

public class ListenerClose implements ActionListener{

	private JFrame frame;
	private StartMenuView vStartMenu;
	
	public ListenerClose(JFrame frame, StartMenuView vStartMenu) {
		this.frame = frame;
		this.vStartMenu = vStartMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		vStartMenu.setVisible(true);
	}
}
