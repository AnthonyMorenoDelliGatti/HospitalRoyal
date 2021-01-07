package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import org.apache.commons.net.smtp.SMTPClient;

import controller.ListenerClose;
import controller.ListenerEmail;
import controller.ListenerSearch;
import model.Email;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Scrollbar;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class EmailMenuWindow {

	public static final Color WHITE = new Color(255, 255, 255);
	private Color headerColor;
	private JFrame frame;
	private JPanel emailBox;
	private JTextField textField;
	String client;
	public EmailMenuWindow(String user) {
		this.client = user;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(WHITE);
		frame.setBounds(100, 100, 429, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		headerColor = new Color(255, 194, 121);

		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("//iconos//anadir.png")));
		btnNewButton.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton.setBorder(emptyBorder);
		btnNewButton.setBackground(headerColor);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("//iconos//recargar.png")));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorder(emptyBorder);
		btnNewButton_1.setBackground(headerColor);

		JButton close = new JButton("");
		close.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("//iconos//cerrar.png")));
		close.setFocusPainted(false);
		close.setBorder(emptyBorder);
		close.setBackground(headerColor);
		close.addActionListener(new ListenerClose(frame));

		textField = new JTextField();
		textField.setColumns(10);

		JButton search = new JButton("");
		search.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("//iconos//buscar.png")));
		search.setFocusPainted(false);
		search.setBorder(emptyBorder);
		search.setBackground(headerColor);
		search.addKeyListener(new ListenerSearch(textField, client));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(textField, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(search, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
						.addComponent(close, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(close,
								GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(15)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36,
												Short.MAX_VALUE)
										.addComponent(search, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36,
												Short.MAX_VALUE)
										.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36,
												Short.MAX_VALUE)
										.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		panel.setBackground(headerColor);
		panel.setLayout(gl_panel);

		emailBox = new JPanel();

		emailBox.setLayout(new BoxLayout(emailBox, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(emailBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);

	}

	// Agregar un correo
	public void viewEmails(Email email) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		panel.add(new JLabel(email.getUser()));
		panel.add(new JLabel(email.getSubject()));
		panel.add(new JLabel(email.getFecha()));

		if (!email.getIsRead()) {
			panel.setBackground(WHITE);
		}

		panel.addMouseListener(new ListenerEmail(panel, email));
		emailBox.add(panel);
	}

	// vaciar el buzon
	public void restartEmailBox() {
		emailBox.removeAll();
	}
}
