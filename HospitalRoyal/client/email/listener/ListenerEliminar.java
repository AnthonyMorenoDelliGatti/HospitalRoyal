package email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Archivo;

public class ListenerEliminar implements ActionListener {

	private Archivo archivo;

	public ListenerEliminar(Archivo archivo) {
		this.archivo = archivo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] opciones = { "SI", "NO" };
		int eleccion = JOptionPane.showOptionDialog(null, "¿Corfima su eliminación?",
				"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "");
		
		if (eleccion == JOptionPane.YES_OPTION) {
			// Agregar el proceso de eliminación
			// Se tendría que eliminar el panel o actualizar todo el listado 
		} 
	}

}
