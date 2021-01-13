package client.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.menu.view.AboutView;

public class ListenerAbout implements ActionListener{
	AboutView aboutView;
	@Override
	public void actionPerformed(ActionEvent e) {
		aboutView = new AboutView();
		aboutView.setVisible(true);
		aboutView.getButtonReturn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aboutView.dispose();
			}
			
		});
	}

}
