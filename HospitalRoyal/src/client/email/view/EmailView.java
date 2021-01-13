package client.email.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.model.Email;

public class EmailView {
	EmailMenuWindow vista;
	Button responder;
	Button reenviar;
	public EmailView(Email email, EmailMenuWindow vista){
		this.vista = vista;
		BorderLayout layoutmail = new BorderLayout();
		JFrame v = new JFrame("" + email.getTo());
	    JEditorPane editor = new JEditorPane();
	    v.setBounds(100, 100, 430, 300);  
	    JScrollPane scroll = new JScrollPane(editor);
	    v.setLayout(layoutmail);
	    scroll.setBounds(0, 0, 430, 300); 
	    editor.setBounds(0, 0, 430, 300); 
	    editor.setEditable(false);
	    JPanel botones = new JPanel(new FlowLayout());
	    responder = new Button("Reply");
	    reenviar = new Button("Forward");
		botones.add(responder);
		botones.add(reenviar);
	    v.getContentPane().add(scroll,BorderLayout.CENTER);
	    v.getContentPane().add(botones,BorderLayout.SOUTH);
	    editor.setVisible(true);
		editor.setContentType("text/html");
		editor.setText("<b>From: </b>" + email.getUser() + "&nbsp&nbsp&nbsp&nbsp<b>Send Date: </b>" + email.getFecha()+ " <br> " +
		"<b>Subject: </b>"+ email.getSubject()+ " <hr> " + email.getText());
	    v.setVisible(true);
	    v.setLocationRelativeTo(null);
	    email.setIsRead(true);
	    v.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				vista.getFrame().setEnabled(true);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public Button getResponder() {
		return responder;
	}
	public Button getReenviar() {
		return reenviar;
	}
	
}
