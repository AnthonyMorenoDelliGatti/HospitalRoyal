package client.ftp.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.ftp.FTPClient;

import client.controller.MethodList;
import client.ftp.listener.ListenerFile;
import client.ftp.listener.ListenerButtonModifyName;
import client.ftp.listener.ListenerEliminate;
import client.ftp.listener.ListenerModifyName;
import client.ftp.listener.ListenerDownload;
import client.model.FileFtp;
import client.model.Paths;

public class FileView {

	JMenuItem item, item2, item3;
	FTPClient client;
	MethodList method;
	FTPWindow view;
	ArrayList<FileFtp> filesFtp;
	DataOutputStream outputStream;
	String user;
	Paths paths;
	JPopupMenu menu;
	boolean admin;

	public FileView(FTPClient client, ArrayList<FileFtp> filesFtp, MethodList method, FTPWindow view, String user,
			DataOutputStream outputStream, Paths paths, boolean admin) {
		this.client = client;
		this.method = method;
		this.view = view;
		this.filesFtp = filesFtp;
		this.user = user;
		this.paths = paths;
		this.outputStream = outputStream;
		this.admin = admin;
	}

	public JPanel ShowLists(ArrayList<FileFtp> archives) {
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);

		header(rootPanel, experimentLayout);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		generateList(panel, experimentLayout, archives);
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rootPanel.add(scrollPane);
		return rootPanel;
	}

	private void generateList(JPanel rootPanel, GridLayout experimentLayout, ArrayList<FileFtp> filesFtp) {
		JPanel panel;
		Collections.sort(filesFtp);
		for (FileFtp i : filesFtp) {
			if (i.getLastModificationDate().equals("")) {
				panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setLayout(experimentLayout);
				JLabel message = new JLabel();
				message.setText(i.getName());
				panel.add(message);
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			} else {
				panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setLayout(experimentLayout);
				JLabel l = obtainIcon(i);
				panel.add(l);
				JTextField name = generateName(panel, i);
				panel.add(new JLabel("" + i.getLastModificationDate()));
				panel.addMouseListener(new ListenerFile(panel, i, view, paths, client, method, this, name));
				if (admin) {
					JPopupMenu menu = generateMenu(name, i);
					panel.setComponentPopupMenu(menu);
				}
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			}
			rootPanel.add(panel);
		}
	}

	private JPopupMenu generateMenu(JTextField name, FileFtp fileFtp) {
		JPopupMenu menu = new JPopupMenu();

		item = new JMenuItem("Change name");
		item.addActionListener(new ListenerButtonModifyName(name, fileFtp));
		menu.add(item);
		if (fileFtp.getIsFolder() == 0) {
			JMenuItem item2 = new JMenuItem("Download");
			item2.addActionListener(new ListenerDownload(fileFtp.getDirection(), fileFtp.getName(), client, method,
					user, outputStream, view));
			menu.add(item2);
		}

		item3 = new JMenuItem("Eliminate");
		item3.addActionListener(
				new ListenerEliminate(fileFtp, filesFtp, client, method, view, this, user, outputStream));
		menu.add(item3);
		return menu;
	}

	private JTextField generateName(JPanel panel, FileFtp i) {
		JTextField name = new JTextField(10);
		name.setText(i.getName());
		panel.add(name);
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.addKeyListener(new ListenerModifyName(i, name, client, user, outputStream));
		name.addFocusListener(new ListenerModifyName(i, name, client, user, outputStream));
		name.setEditable(false);
		name.setBackground(Color.WHITE);

		return name;
	}

	private JLabel obtainIcon(FileFtp i) {
		String direcIcon;
		if (i.getIsFolder() == 1) {
			direcIcon = "iconos//carpeta.png";
		} else {
			direcIcon = "iconos//text-document.png";
		}
		Icon icon = new ImageIcon(direcIcon);
		JLabel l = new JLabel(icon);
		return l;
	}

	private void header(JPanel rootPanel, GridLayout experimentLayout) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(experimentLayout);
		panel.add(new JLabel(""));
		panel.add(new JLabel("Name"));
		panel.add(new JLabel("Modification time"));

		rootPanel.add(panel);
	}

	public JPopupMenu getMenu() {
		return menu;
	}

}
