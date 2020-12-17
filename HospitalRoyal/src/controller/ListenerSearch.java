package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JTextField;

import org.apache.commons.net.smtp.SMTPClient;

public class ListenerSearch implements KeyListener {

	private JTextField text;
	String client;
	public ListenerSearch(JTextField text, String client) {
		this.text = text;
		this.client = client;
	}
	private String buscar(String archivoABuscar, String client) {
		File directory = new File(client);
	    File[] archivos = directory.listFiles();
	    for (File archivo : archivos) {
	        if (archivo.getName().equals(archivoABuscar + ".txt")) {
	            return archivo.getName();
	        }
	    }
	    return null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(buscar(text.getText() , "C:/Email/"+client));
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
