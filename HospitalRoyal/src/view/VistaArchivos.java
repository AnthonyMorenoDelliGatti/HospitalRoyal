package view;

import java.awt.GridLayout;
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

import controller.ListenerBotonModificarNombre;
import controller.ListenerDescargar;
import controller.ListenerEliminar;
import controller.ListenerModificarNombre;
import controller.Methods;
import model.ArchivoFtp;
import controller.ListenerArchivo;

public class VistaArchivos {

	JMenuItem item, item2, item3;
	FTPClient client;
	Methods method;
	VistaPrincipal vista;
	ArrayList<ArchivoFtp> archivos;
	String user;

	public VistaArchivos(FTPClient client, ArrayList<ArchivoFtp> archivos, Methods method, VistaPrincipal vista,
			String user) {
		this.client = client;
		this.method = method;
		this.vista = vista;
		this.archivos = archivos;
		this.user = user;
	}

	public JPanel visualizarListado(ArrayList<ArchivoFtp> archivos) {
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

	private void generarListado(JPanel rootPanel, GridLayout experimentLayout, ArrayList<ArchivoFtp> archivos) {
		JPanel panel;
		Collections.sort(archivos);
		for (ArchivoFtp i : archivos) {
			panel = new JPanel();
			panel.setLayout(experimentLayout);

			JLabel l = obtenerIcono(i);
			panel.add(l);
			JTextField nombre = generarNombre(panel, i);
			panel.add(new JLabel("" + i.getUltFechaModificacion()));
			panel.addMouseListener(new ListenerArchivo(panel, i));
			JPopupMenu menu = generarMenu(nombre, i);
			panel.setComponentPopupMenu(menu);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			rootPanel.add(panel);

		}
	}

	private JPopupMenu generarMenu(JTextField nombre, ArchivoFtp archivo) {
		JPopupMenu menu = new JPopupMenu();

		item = new JMenuItem("Cambiar nombre");
		item.addActionListener(new ListenerBotonModificarNombre(nombre, archivo));
		menu.add(item);
		JMenuItem item2 = new JMenuItem("Descargar");
		item2.addActionListener(new ListenerDescargar(archivo.getDireccion(), archivo.getNombre(), client, method, user));
		menu.add(item2);
		item3 = new JMenuItem("Eliminar");
		item3.addActionListener(new ListenerEliminar(archivo, archivos, client, method, vista, this, user));
		menu.add(item3);
		return menu;
	}

	private JTextField generarNombre(JPanel panel, ArchivoFtp i) {
		JTextField nombre = new JTextField(10);
		nombre.setText(i.getNombre());
		panel.add(nombre);
		nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ListenerModificarNombre listener = new ListenerModificarNombre(i, nombre, client, user, method);
		nombre.addKeyListener(listener);
		nombre.setEditable(false);

		return nombre;
	}

	private JLabel obtenerIcono(ArchivoFtp i) {
		String direcIcono;
		if (i.getIsCarpeta() == 1) {
			direcIcono = "..\\iconos\\carpeta.png";
		} else {
			direcIcono = "..\\iconos\\text-document.png";
		}
		Icon icon = new ImageIcon(direcIcono);
		JLabel l = new JLabel(icon);
		return l;
	}

	private void cabecera(JPanel rootPanel, GridLayout experimentLayout) {
		JPanel panel = new JPanel();
		panel.setLayout(experimentLayout);
		panel.add(new JLabel(""));
		panel.add(new JLabel("Nombre"));
		panel.add(new JLabel("Fecha modificacion"));
		rootPanel.add(panel);
	}

	public JMenuItem getItem() {
		return item;
	}

	public void setItem(JMenuItem item) {
		this.item = item;
	}

	public JMenuItem getItem2() {
		return item2;
	}

	public void setItem2(JMenuItem item2) {
		this.item2 = item2;
	}

	public JMenuItem getItem3() {
		return item3;
	}

	public void setItem3(JMenuItem item3) {
		this.item3 = item3;
	}

}
