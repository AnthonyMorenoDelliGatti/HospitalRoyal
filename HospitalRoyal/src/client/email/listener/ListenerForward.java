package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.email.view.NewEmailView;
import client.model.Email;

public class ListenerForward implements ActionListener {
	Email email;
	String password;
	public ListenerForward(Email email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NewEmailView ev = new NewEmailView(email.getTo(), password);
		ev.getSubject().setText(email.getSubject());
		ev.getTextPane().setText("\n\n\n\n---------- Forwarded message ---------" + "\n From: " + email.getUser() + 
				"\n Date: " + email.getFecha() + "\n Subject: " + email.getSubject() + "\n To: " + email.getTo());
		ev.getTextPane().setCaretPosition(0);
		ev.getTextPane().requestFocus();
	}

}
