package client.email.listener;

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
                    System.out.println("Texto " + unaParte.getContentType());
                    System.out.println(unaParte.getContent());
                    ev.getTextPane().append(unaParte.getContent().toString());
                    System.out.println("---------------------------------");
                }
                else
                {
                  // Si es imagen, se guarda en fichero y se visualiza en JFrame
                    if (unaParte.isMimeType("image/*"))
                    {
                        System.out.println(
                            "Imagen " + unaParte.getContentType());
                        System.out.println("Fichero=" + unaParte.getFileName());
                        JLabel lblLogo = new JLabel();
                        lblLogo.setIcon( new ImageIcon(
                                ImageIO.read(unaParte.getInputStream())));
                        lblLogo.setBounds(270,10,50,50);    
                        ev.getFilesPanel().add(lblLogo);
                        System.out.println("---------------------------------");

//                        visualizaImagenEnJFrame(unaParte);
                    }
                    else
                    {
                      // Si no es ninguna de las anteriores, se escribe en pantalla
                      // el tipo.
                        System.out.println(
                            "Recibido " + unaParte.getContentType());
                        ev.getFilesPanel().add(new JLabel(unaParte.getFileName()));
                        System.out.println("---------------------------------");
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
