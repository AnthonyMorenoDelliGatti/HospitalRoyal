package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ListenerSearch implements ActionListener {

	private JTextField text;

	public ListenerSearch(JTextField text) {
		this.text = text;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(text.getText());
		// implementar el proceso de busqueda y actualizar el panel
	}

}
