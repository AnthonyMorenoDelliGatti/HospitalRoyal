package client.email.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import client.email.listener.ListenerDescargar;
import client.model.Email;

public class EmailView {
	EmailMenuWindow vista;
	Button responder;
	Button reenviar;
	private static JPanel archivos;
	private static JPanel imagenes;
	private static String texto;

	public EmailView(Email email, EmailMenuWindow vista) {
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
		imagenes = new JPanel(new GridLayout(1, 0));
		archivos = new JPanel();
		archivos.setLayout(new GridLayout(0, 1));
		responder = new Button("Reply");
		reenviar = new Button("Forward");
		botones.add(responder);
		botones.add(reenviar);
		v.getContentPane().add(scroll, BorderLayout.NORTH);
		v.getContentPane().add(imagenes, BorderLayout.EAST);
		v.getContentPane().add(archivos, BorderLayout.WEST);
		v.getContentPane().add(botones, BorderLayout.AFTER_LAST_LINE);
		editor.setVisible(true);
		editor.setContentType("text/html");
		texto = "<b>From: </b>" + email.getUser() + "&nbsp&nbsp&nbsp&nbsp<b>Send Date: </b>" + email.getFecha()
				+ " <br> " + "<b>Subject: </b>" + email.getSubject() + " <hr> ";
		analizaParteDeMensaje(email.getContent());
		editor.setText(texto);
		v.setVisible(true);
		v.pack();
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
				vista.getFrame().setEnabled(true);

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

	private static void analizaParteDeMensaje(Part unaParte) {
		try {
			// Si es multipart, se analiza cada una de sus partes recursivamente.
			if (unaParte.isMimeType("multipart/*")) {
				Multipart multi;
				multi = (Multipart) unaParte.getContent();

				for (int j = 0; j < multi.getCount(); j++) {
					analizaParteDeMensaje(multi.getBodyPart(j));
				}
			} else {
				// Si es texto, se escribe el texto.
				if (unaParte.isMimeType("text/plain")) {
					texto += unaParte.getContent().toString();
				} else {
					// Si es imagen, se guarda en fichero y se visualiza en JFrame
					if (unaParte.isMimeType("image/*")) {
						JLabel lblLogo = new JLabel();
						lblLogo.setBounds(270, 10, 100, 100);
						Image img = ImageIO.read(unaParte.getInputStream());
						Image dimg = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
						lblLogo.setIcon(new ImageIcon(dimg));
						JPopupMenu menu = new JPopupMenu();
						JMenuItem item = new JMenuItem("Descargar");
						item.addActionListener(new ListenerDescargar(unaParte, imagenes));
						menu.add(item);
						imagenes.setComponentPopupMenu(menu);
						imagenes.add(lblLogo);
					} else {
						JLabel lblarchivo = new JLabel();
						lblarchivo.setMaximumSize(new Dimension(100, 100));
						lblarchivo.setBackground(Color.white);
						JPopupMenu menu = new JPopupMenu();
						JMenuItem item = new JMenuItem("Descargar");
						item.addActionListener(new ListenerDescargar(unaParte, archivos));
						menu.add(item);
						archivos.setComponentPopupMenu(menu);
						archivos.add(new JLabel(unaParte.getFileName()));

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
