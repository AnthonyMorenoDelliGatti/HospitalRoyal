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

/**
 * 
 * @authors Anthony Moreno Delli Gatti
 * 			Francisco Manuel Rodriguez Martin
 * 			Juan Salguero Ibarrola
 * 			Nicolas Rosa Hinojosa
 * 			Gonzalo Ruiz de Mier Mora 
 * 
 * date	13/01/2021
 * 
 * @version 1.0
 * 
 * description: Class listener to reply an email
 *
 */

public class ListenerReply implements ActionListener {
	private Email email;
	String password;
	static NewEmailView ev;
	EmailMenuWindow view;
	/**
	 * 
	 * @param email: the user email
	 * @param password: the user password
	 * @param view: the view that contains the mail
	 */
	public ListenerReply(Email email, String password, EmailMenuWindow view) {
		this.email = email;
		this.password = password;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ev = new NewEmailView(email.getTo(), password, view.getFrame());
		ev.getTo().setText(email.getUser());
		ev.getSubject().setText("Re: "+email.getSubject());
		ev.getTextPane().setText("\n\n\n\n On "+email.getDate() + ", " + email.getUser() + " wrote:\n");
		analizePartOfTheMessage(email.getContent());
	}
	/**
	 * Method to obtain the text of a mail
	 * 
	 * @param part: the part that contains the text of the mail
	 */
	private static void analizePartOfTheMessage(Part part)
    {
        try
        {
          //If is multipart, each part will be analized recursively.
            if (part.isMimeType("multipart/*"))
            {
                Multipart multi;
                multi = (Multipart) part.getContent();

                for (int j = 0; j < multi.getCount(); j++)
                {
                    analizePartOfTheMessage(multi.getBodyPart(j));
                }
            }
            else
            {
              // If is text, text will be written.
                if (part.isMimeType("text/plain"))
                {
                    ev.getTextPane().append(part.getContent().toString());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
