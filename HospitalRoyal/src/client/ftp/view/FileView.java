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
	FTPWindow vista;
	ArrayList<FileFtp> archivos;
	DataOutputStream outputStream;
	String user;
	Paths paths;
	JPopupMenu menu;
	boolean admin;

	public FileView(FTPClient client, ArrayList<FileFtp> archivos, MethodList method, FTPWindow vista, String user,
			DataOutputStream outputStream, Paths paths, boolean admin) {
		this.client = client;
		this.method = method;
		this.vista = vista;
		this.archivos = archivos;
		this.user = user;
		this.paths = paths;
		this.outputStream = outputStream;
		this.admin = admin;
	}

	public JPanel visualizarListado(ArrayList<FileFtp> archivos) {
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);

		cabecera(rootPanel, experimentLayout);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		generarListado(panel, experimentLayout, archivos);
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rootPanel.add(scrollPane);
		return rootPanel;
	}

	private void generarListado(JPanel rootPanel, GridLayout experimentLayout, ArrayList<FileFtp> archivos) {
		JPanel panel;
		Collections.sort(archivos);
		for (FileFtp i : archivos) {
			if (i.getUltFechaModificacion().equals("")) {
				panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setLayout(experimentLayout);
				JLabel mensaje = new JLabel();
				mensaje.setText(i.getNombre());
				panel.add(mensaje);
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			} else {
				panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setLayout(experimentLayout);
				JLabel l = obtenerIcono(i);
				panel.add(l);
				JTextField nombre = generarNombre(panel, i);
				panel.add(new JLabel("" + i.getUltFechaModificacion()));
				panel.addMouseListener(new ListenerFile(panel, i, vista, paths, client, method, this, nombre));
				if (admin) {
					JPopupMenu menu = generarMenu(nombre, i);
					panel.setComponentPopupMenu(menu);
				}
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			}
			rootPanel.add(panel);
		}
	}

	private JPopupMenu generarMenu(JTextField nombre, FileFtp archivo) {
		JPopupMenu menu = new JPopupMenu();

		item = new JMenuItem("Cambiar nombre");
		item.addActionListener(new ListenerButtonModifyName(nombre, archivo));
		menu.add(item);
		if (archivo.getIsCarpeta() == 0) {
			JMenuItem item2 = new JMenuItem("Descargar");
			item2.addActionListener(new ListenerDownload(archivo.getDireccion(), archivo.getNombre(), client, method,
					user, outputStream, vista));
			menu.add(item2);
		}

		item3 = new JMenuItem("Eliminar");
		item3.addActionListener(
				new ListenerEliminate(archivo, archivos, client, method, vista, this, user, outputStream));
		menu.add(item3);
		return menu;
	}

	private JTextField generarNombre(JPanel panel, FileFtp i) {
		JTextField nombre = new JTextField(10);
		nombre.setText(i.getNombre());
		panel.add(nombre);
		nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nombre.addKeyListener(new ListenerModifyName(i, nombre, client, user, outputStream));
		nombre.addFocusListener(new ListenerModifyName(i, nombre, client, user, outputStream));
		nombre.setEditable(false);
		nombre.setBackground(Color.WHITE);

		return nombre;
	}

	private JLabel obtenerIcono(FileFtp i) {
		String direcIcono;
		if (i.getIsCarpeta() == 1) {
			direcIcono = "iconos//carpeta.png";
		} else {
			direcIcono = "iconos//text-document.png";
		}
		Icon icon = new ImageIcon(direcIcono);
		JLabel l = new JLabel(icon);
		return l;
	}

	private void cabecera(JPanel rootPanel, GridLayout experimentLayout) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(experimentLayout);
		panel.add(new JLabel(""));
		panel.add(new JLabel("Nombre"));
		panel.add(new JLabel("Fecha modificacion"));

		rootPanel.add(panel);
	}

	public JPopupMenu getMenu() {
		return menu;
	}

}
