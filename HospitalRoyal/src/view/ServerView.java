package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerView extends JFrame{
	
	private JPanel panel;
	//private ArrayList<JLabel>acciones=new ArrayList<JLabel>();
	private JTextArea area;
	
	public ServerView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
		panel = new JPanel();
		BoxLayout ventana = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(ventana);
		setContentPane(panel);
		
		area= new JTextArea(10, 30);
		JScrollPane scrollPane = new JScrollPane(area); 
		area.setEditable(false);
		panel.add(scrollPane);
		
		pack();
		setVisible(true);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}
	
/*
	public ArrayList<JLabel> getAcciones() {
		return acciones;
	}

	public void setAcciones(ArrayList<JLabel> acciones) {
		this.acciones = acciones;
	}
	*/
}
