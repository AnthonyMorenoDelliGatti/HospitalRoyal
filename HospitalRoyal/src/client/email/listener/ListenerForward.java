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

public class ListenerForward implements ActionListener {
	Email email;
	static NewEmailView ev;
	String password;
	public ListenerForward(Email email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ev = new NewEmailView(email.getTo(), password);
		ev.getSubject().setText(email.getSubject());
		ev.getTextPane().setText("\n\n\n\n---------- Forwarded message ---------" + "\n From: " + email.getUser() + 
				"\n Date: " + email.getDate() + "\n Subject: " + email.getSubject() + "\n To: " + email.getTo());
		ev.getTextPane().setCaretPosition(0);
		ev.getTextPane().requestFocus();
		analizePartOfTheMessage(email.getContent());
			
		}
	private static void analizePartOfTheMessage(Part part)
    {
        try
        {
          // If is  multipart, each part will be analised recursively.
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
              // If is text, text is written.
                if (part.isMimeType("text/plain"))
                {
                    ev.getTextPane().append(part.getContent().toString());
                }
                else
                {
                  // If is image, it will be saved in file and show on JFrame
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
                      JLabel lblFile = new JLabel(part.getFileName());
                      lblFile.setSize(new Dimension(150,100));
                      lblFile.setBackground(Color.white);
                        ev.getFilesPanel().add(lblFile);
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


