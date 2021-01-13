package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.email.view.NewEmailView;
import client.model.Email;

public class ListenerReply implements ActionListener {
	private Email email;
	String password;
	public ListenerReply(Email email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NewEmailView ev = new NewEmailView(email.getTo(), password);
		ev.getTo().setText(email.getUser());
		ev.getSubject().setText("Re: "+email.getSubject());
		ev.getTextPane().setText("\n\n\n\n On "+email.getFecha() + ", " + email.getUser() + " wrote:\n" + email.getText());
		ev.getTextPane().setCaretPosition(0);
		ev.getTextPane().requestFocus();
	}

}
