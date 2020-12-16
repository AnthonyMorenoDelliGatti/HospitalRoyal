package controller;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Archivo;

public class ListenerArchivo implements MouseListener {

	private JPanel panel;
	private Archivo archivo;

	public ListenerArchivo(JPanel panel, Archivo archivo) {
		this.panel = panel;
		this.archivo = archivo;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			if(archivo.getIsCarpeta() == 1) {
				System.out.println("Abrir carpeta");
			}
		}
	}

	/**
	 * Selecci�n de carpetas
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
