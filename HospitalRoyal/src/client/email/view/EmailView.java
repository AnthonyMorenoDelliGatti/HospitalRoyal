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

import client.email.listener.ListenerDownload;
import client.model.Email;

public class EmailView {
	EmailMenuWindow view;
	Button respond;
	Button resend;
	private static JPanel panelFiles;
	private static JPanel images;
	private static String text;

	public EmailView(Email email, EmailMenuWindow view) {
		this.view = view;
		BorderLayout layoutmail = new BorderLayout();
		JFrame v = new JFrame("" + email.getTo());
		JEditorPane editor = new JEditorPane();
		v.setBounds(100, 100, 430, 300);
		JScrollPane scroll = new JScrollPane(editor);
		v.setLayout(layoutmail);
		scroll.setBounds(0, 0, 430, 300);
		editor.setBounds(0, 0, 430, 300);
		editor.setEditable(false);
		JPanel buttons = new JPanel(new FlowLayout());
		images = new JPanel(new GridLayout(1, 0));
		panelFiles = new JPanel();
		panelFiles.setLayout(new GridLayout(0, 1));
		respond = new Button("Reply");
		resend = new Button("Forward");
		buttons.add(respond);
		buttons.add(resend);
		v.getContentPane().add(scroll, BorderLayout.NORTH);
		v.getContentPane().add(images, BorderLayout.EAST);
		v.getContentPane().add(panelFiles, BorderLayout.WEST);
		v.getContentPane().add(buttons, BorderLayout.AFTER_LAST_LINE);
		editor.setVisible(true);
		editor.setContentType("text/html");
		text = "<b>From: </b>" + email.getUser() + "&nbsp&nbsp&nbsp&nbsp<b>Send Date: </b>" + email.getDate()
				+ " <br> " + "<b>Subject: </b>" + email.getSubject() + " <hr> ";
		analizePartOfTheMessage(email.getContent());
		editor.setText(text);
		v.setVisible(true);
		v.pack();
		v.setLocationRelativeTo(null);
		email.setIsRead(true);
		v.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				view.getFrame().setEnabled(true);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				view.getFrame().setEnabled(true);
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
	}

	public Button getRespond() {
		return respond;
	}

	public Button getResend() {
		return resend;
	}

	private static void analizePartOfTheMessage(Part part) {
		try {
			// If is multipart, each part is analized recursively.
			if (part.isMimeType("multipart/*")) {
				Multipart multi;
				multi = (Multipart) part.getContent();

				for (int j = 0; j < multi.getCount(); j++) {
					analizePartOfTheMessage(multi.getBodyPart(j));
				}
			} else {
				// If is text, text is written.
				if (part.isMimeType("text/plain")) {
					text += part.getContent().toString();
				} else {
					// If is image, is saved in file and visualized on JFrame
					if (part.isMimeType("image/*")) {
						JLabel lblLogo = new JLabel();
						lblLogo.setBounds(270, 10, 100, 100);
						Image img = ImageIO.read(part.getInputStream());
						Image dimg = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
						lblLogo.setIcon(new ImageIcon(dimg));
						JPopupMenu menu = new JPopupMenu();
						JMenuItem item = new JMenuItem("Download");
						item.addActionListener(new ListenerDownload(part, images));
						menu.add(item);
						images.setComponentPopupMenu(menu);
						images.add(lblLogo);
					} else {
						JLabel lblFile = new JLabel();
						lblFile.setMaximumSize(new Dimension(100, 100));
						lblFile.setBackground(Color.white);
						JPopupMenu menu = new JPopupMenu();
						JMenuItem item = new JMenuItem("Download");
						item.addActionListener(new ListenerDownload(part, panelFiles));
						menu.add(item);
						panelFiles.setComponentPopupMenu(menu);
						panelFiles.add(new JLabel(part.getFileName()));

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
