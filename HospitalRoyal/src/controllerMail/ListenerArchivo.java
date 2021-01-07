package controllerMail;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.ArchivoMail;

public class ListenerArchivo implements MouseListener {

	private JPanel panel;
	private ArchivoMail archivo;

	public ListenerArchivo(JPanel panel, ArchivoMail archivo) {
		this.panel = panel;
		this.archivo = archivo;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			if(archivo.getExtension().equalsIgnoreCase("folder")) {
				System.out.println("Abrir carpeta");
			}
		}
	}

	/**
	 * Selección de carpetas
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
