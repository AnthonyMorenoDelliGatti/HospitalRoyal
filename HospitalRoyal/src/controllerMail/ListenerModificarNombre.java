package controllerMail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import model.ArchivoMail;

public class ListenerModificarNombre implements ActionListener, FocusListener{

	private ArchivoMail archivo;
	private JTextField nombre;
	
	public ListenerModificarNombre(ArchivoMail archivo, JTextField nombre) {
		this.archivo = archivo;
		this.nombre = nombre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		comprobarNombre();
	}

	private void comprobarNombre() {
		String text = nombre.getText();
		if(text.trim().length() > 0) {
			archivo.setNombre(text);
			// enviar al servidor el nuevo nombre
		}
		else{
			nombre.setText(archivo.getNombre());
		}
		nombre.setEditable(false);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		comprobarNombre();
	}

}
