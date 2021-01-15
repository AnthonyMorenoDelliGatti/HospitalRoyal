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

import client.email.view.NewEmailView;
import client.model.Email;

public class ListenerReply implements ActionListener {
	private Email email;
	String password;
	static NewEmailView ev;
	public ListenerReply(Email email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ev = new NewEmailView(email.getTo(), password);
		ev.getTo().setText(email.getUser());
		ev.getSubject().setText("Re: "+email.getSubject());
		ev.getTextPane().setText("\n\n\n\n On "+email.getDate() + ", " + email.getUser() + " wrote:\n");
		analizePartOfTheMessage(email.getContent());
	}
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
                else
                {
                  //if is image, a file is save and is show on JFrame
                	if (part.isMimeType("image/*"))
                    {
                        JLabel lblLogo = new JLabel();
                        lblLogo.setIcon( new ImageIcon(
                                ImageIO.read(part.getInputStream())));
                        lblLogo.setSize(new Dimension(150,150));   
                        ev.getFilesPanel().add(lblLogo);

                    }
                    else
                    {
                      JLabel lblarchive = new JLabel(part.getFileName());
                      lblarchive.setSize(new Dimension(150,100));
                      lblarchive.setBackground(Color.white);
                        ev.getFilesPanel().add(lblarchive);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
