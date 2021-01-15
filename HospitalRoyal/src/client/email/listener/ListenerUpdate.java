package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Store;

import client.email.view.EmailMenuWindow;
import client.menu.view.SplashEmail;

public class ListenerUpdate implements ActionListener {
	EmailMenuWindow emailWindow;
	Store store;
	String email;
	public ListenerUpdate(EmailMenuWindow emailWindow, Store store, String email) {
		this.emailWindow = emailWindow;
		this.store = store;
		this.email = email;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SplashEmail splash = new SplashEmail();
		splash.toFront();
		UpdateThread thread = new UpdateThread(emailWindow, store, email, splash);
		thread.start();
	}

}
