package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.smtp.SMTPClient;

import controllerMail.ListenerArchivo;
import controllerMail.ListenerBotonModificarNombre;
import controllerMail.ListenerEliminar;
import controllerMail.ListenerModificarNombre;
import controllerMail.ListenerClose;

import model.ArchivoMail;
import model.Email;

public class NewEmailView {
	
	private JFrame frame;
	private JTextField to;
	private JTextField subject;
	private Color headerColor;
	private Color body;
	private JPanel filesPanel;
	private JScrollPane scrollPane;
	private JPanel dropPanel;
	private JButton close;
	private JTextPane textPane;
	private JButton send;
	private JButton upLoad;
	private String user;
	private SMTPClient client;
	
	public NewEmailView(SMTPClient client, String user) {
		this.client = client;
		this.user = user + "@HospitalRoyal.es";
		initialize();
		dropFile();
//		
//		if(archivos.isEmpty()) {
//			scrollPane.setVisible(false);
//			
//		}
//		else { // cargar archivos 
//			for(int i = 0; i < archivos.size(); i++) {
//				filesPanel.add(new Button("a"));
//			}
//		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setUndecorated(true);

		headerColor = new Color(204, 252, 255);
		body = Color.WHITE;

		JPanel headBoard = new JPanel();
		frame.getContentPane().add(headBoard, BorderLayout.NORTH);
		headBoard.setLayout(new BoxLayout(headBoard, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		headBoard.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(headerColor);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBackground(headerColor);

		JLabel lblNewLabel_2 = new JLabel("NEW EMAIL");
		lblNewLabel_2.setBounds(10, 11, 80, 20);
		panel_3.add(lblNewLabel_2);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_4);
		panel_4.setBackground(headerColor);

		close = new JButton("");
		close.setIcon(new ImageIcon("iconos/remove-button.png"));
		close.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		close.setBorder(emptyBorder);
		close.setBackground(headerColor);
		panel_4.add(close);
		close.addActionListener(new ListenerClose(frame));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		headBoard.add(panel_1);
		panel_1.setBackground(body);

		JLabel lblNewLabel = new JLabel("To         ");
		panel_1.add(lblNewLabel);

		to = new JTextField();
		panel_1.add(to);
		to.setColumns(20);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		headBoard.add(panel_2);
		panel_2.setBackground(body);

		JLabel lblNewLabel_1 = new JLabel("Subject");
		panel_2.add(lblNewLabel_1);

		subject = new JTextField();
		panel_2.add(subject);
		subject.setColumns(20);

		dropPanel = new JPanel();
		frame.getContentPane().add(dropPanel, BorderLayout.CENTER);
		dropPanel.setLayout(null);
		dropPanel.setBackground(body);

		textPane = new JTextPane();
		textPane.setBounds(10, 11, 430, 146);
		textPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dropPanel.add(textPane);

		filesPanel = new JPanel();
		filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(filesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		filesPanel.setBackground(body);
		scrollPane.setBounds(10, 173, 430, 160);
		dropPanel.add(scrollPane);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setHgap(25);
		frame.getContentPane().add(panel_6, BorderLayout.SOUTH);
		panel_6.setBackground(body);

		send = new JButton("");
		send.setIcon(new ImageIcon("iconos/enviar.png"));
		send.setFocusPainted(false);
		send.setBorder(emptyBorder);
		send.setBackground(body);
		panel_6.add(send);

		upLoad = new JButton("");
		upLoad.setIcon(new ImageIcon("iconos/subir.png"));
		upLoad.setFocusPainted(false);
		upLoad.setBorder(emptyBorder);
		upLoad.setBackground(body);
		panel_6.add(upLoad);
		
		frame.setVisible(true);
	}

	private void dropFile() {
		TransferHandler th = new TransferHandler() {

			@Override
			public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
				return true;
			}

			@Override
			public boolean importData(JComponent comp, Transferable t) {
				try {
					ArrayList<ArchivoMail> archivos = new ArrayList<>();
					List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
					for (File i : files) {

						// String nombre, String ultFechaModificacion, int isCarpeta, String direccion
						archivos.add(new ArchivoMail(i.getName(), "" + i.lastModified(), i.getAbsolutePath()));
						// implementar el proceso de splash
					}
					
					generarListado(archivos);
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return true;
			}
		};
		dropPanel.setTransferHandler(th);
	}

	private void generarListado(ArrayList<ArchivoMail> archivos) {
		JPanel panel;
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);
		for (ArchivoMail i : archivos) {
			panel = new JPanel();
			panel.setLayout(experimentLayout);
			
			JLabel l = obtenerIcono(i);
			panel.add(l);
			
			JTextField nombre = generarNombre(panel, i);
			nombre.setBackground(body);
			
			//panel.add(new JLabel(i.getUltFechaModificacion()));
			
			panel.addMouseListener(new ListenerArchivo(panel, i));

			JPopupMenu menu = generarMenu(nombre, i);

			
			panel.setComponentPopupMenu(menu);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			filesPanel.add(panel);
		}
	}

	private JPopupMenu generarMenu(JTextField nombre, ArchivoMail archivo) {
		JPopupMenu menu = new JPopupMenu();

		JMenuItem item = new JMenuItem("Cambiar nombre");
		item.addActionListener(new ListenerBotonModificarNombre(nombre));
		menu.add(item);

		JMenuItem item3 = new JMenuItem("Eliminar");
		item3.addActionListener(new ListenerEliminar(archivo));
		menu.add(item3);
		return menu;
	}

	private JTextField generarNombre(JPanel panel, ArchivoMail i) {
		JTextField nombre = new JTextField(10);
		nombre.setText(i.getNombre());
		panel.add(nombre);
		nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ListenerModificarNombre listener = new ListenerModificarNombre(i, nombre);
		nombre.addActionListener(listener);
		nombre.addFocusListener(listener);
		nombre.setEditable(false);

		return nombre;
	}

	private JLabel obtenerIcono(ArchivoMail i) {
		String direcIcono;
		if (i.getExtension().equalsIgnoreCase("folder")) {
			direcIcono = "iconos/carpeta.png";
		} else {
			direcIcono = "iconos/text-document.png";
		}
		Icon icon = new ImageIcon(Email.class.getResource(direcIcono));
		JLabel l = new JLabel(icon);
		return l;
	}

	public JTextField getTo() {
		return to;
	}

	public void setTo(JTextField to) {
		this.to = to;
	}

	public JTextField getSubject() {
		return subject;
	}

	public void setSubject(JTextField subject) {
		this.subject = subject;
	}

	public JButton getClose() {
		return close;
	}

	public void setClose(JButton close) {
		this.close = close;
	}

	public JButton getSend() {
		return send;
	}

	public void setSend(JButton send) {
		this.send = send;
	}

	public JButton getUpLoad() {
		return upLoad;
	}

	public void setUpLoad(JButton upLoad) {
		this.upLoad = upLoad;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
	
}
