package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.apache.commons.net.ftp.FTPClient;

import controller.ListenerSubir;
import controller.Methods;

public class VistaPrincipal extends JFrame{

	private JPanel rootPanel;
	private JPanel cabecera;
	private JPanel centro;
	private Color colorCabecera;
	private ArrayList<JButton> buttons = new ArrayList();
	VistaArchivos lista;
	private Methods method;
	
	public VistaPrincipal(FTPClient client, String user, VistaArchivos lista, Methods method) {
		rootPanel = new JPanel();
		this.lista= lista;
		this.method = method;
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));	
		
		cabecera = new JPanel();
		cabecera.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		colorCabecera = new Color(255, 194, 121);
		cabecera.setBackground(colorCabecera);
		
		centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.X_AXIS));	
		
		rootPanel.add(cabecera);
		rootPanel.add(centro);
		setContentPane(rootPanel);
		
		generarOpciones(client, user);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void generarOpciones(FTPClient client, String user) {
		JButton btnatras = generarBotonCabecera("..\\iconos\\atras.png");
		JButton btnadelante = generarBotonCabecera("..\\iconos\\flecha-correcta.png");
		JButton btncarpeta = generarBotonCabecera("..\\iconos\\folder.png");
		JButton btnsubir = generarBotonCabecera("..\\iconos\\upload-file.png");

		btnsubir.addActionListener(new ListenerSubir(client, user, this, lista, method));
	}

	private JButton generarBotonCabecera(String direccion) {
		JButton boton = new JButton();
		Icon icon = new ImageIcon(direccion);
		boton.setIcon(icon);
		boton.setBackground(colorCabecera);
		boton.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		boton.setBorder(emptyBorder);
		cabecera.add(boton);
		buttons.add(boton);
		return boton;
	}
	
	public void agregarExplorador(JPanel jPanel) {
		centro.add(jPanel);
	}
	
	public void actualizarExplorador(JPanel explorer) {
		centro.remove(1); // borra el anterior explorador
		agregarExplorador(explorer);
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}

	public JPanel getCentro() {
		return centro;
	}

	public void setCentro(JPanel centro) {
		this.centro = centro;
	}


}
