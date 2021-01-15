package client.email.listener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.email.view.EmailMenuWindow;
import client.email.view.NewEmailView;
import client.model.Email;

public class ListenerReply implements ActionListener {
	private Email email;
	String password;
	static NewEmailView ev;
	EmailMenuWindow vista;
	public ListenerReply(Email email, String password, EmailMenuWindow vista) {
		this.email = email;
		this.password = password;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ev = new NewEmailView(email.getTo(), password, vista.getFrame());
		ev.getTo().setText(email.getUser());
		ev.getSubject().setText("Re: "+email.getSubject());
		ev.getTextPane().setText("\n\n\n\n On "+email.getFecha() + ", " + email.getUser() + " wrote:\n");
		analizaParteDeMensaje(email.getContent());

//		ev.getTextPane().setCaretPosition(0);
//		ev.getTextPane().requestFocus();
	}
	private static void analizaParteDeMensaje(Part unaParte)
    {
        try
        {
          // Si es multipart, se analiza cada una de sus partes recursivamente.
            if (unaParte.isMimeType("multipart/*"))
            {
                Multipart multi;
                multi = (Multipart) unaParte.getContent();

                for (int j = 0; j < multi.getCount(); j++)
                {
                    analizaParteDeMensaje(multi.getBodyPart(j));
                }
            }
            else
            {
              // Si es texto, se escribe el texto.
                if (unaParte.isMimeType("text/plain"))
                {
                    ev.getTextPane().append(unaParte.getContent().toString());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
