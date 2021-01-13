package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.menu.view.AboutView;

public class ListenerAbout implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		AboutView aboutView = new AboutView();
	}

}
