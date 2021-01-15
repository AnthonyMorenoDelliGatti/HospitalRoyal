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
				"\n Date: " + email.getFecha() + "\n Subject: " + email.getSubject() + "\n To: " + email.getTo());
		ev.getTextPane().setCaretPosition(0);
		ev.getTextPane().requestFocus();
		analizaParteDeMensaje(email.getContent());
			
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
                else
                {
                  // Si es imagen, se guarda en fichero y se visualiza en JFrame
                    if (unaParte.isMimeType("image/*"))
                    {
                        JLabel lblLogo = new JLabel();
                        lblLogo.setIcon( new ImageIcon(
                                ImageIO.read(unaParte.getInputStream())));
                        lblLogo.setSize(new Dimension(150,150));   
                        ev.getFilesPanel().add(lblLogo);

//                        visualizaImagenEnJFrame(unaParte);
                    }
                    else
                    {
                      JLabel lblarchivo = new JLabel(unaParte.getFileName());
                      lblarchivo.setSize(new Dimension(150,100));
                      lblarchivo.setBackground(Color.white);
                        ev.getFilesPanel().add(lblarchivo);
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


