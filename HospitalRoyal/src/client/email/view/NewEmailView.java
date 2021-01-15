package client.email.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

import client.email.listener.ListenerArchivo;
import client.email.listener.ListenerBotonModificarNombre;
import client.email.listener.ListenerClose;
import client.email.listener.ListenerCloseWindow;
import client.email.listener.ListenerEliminar;
import client.email.listener.ListenerFCEmail;
import client.email.listener.ListenerModificarNombre;
import client.email.listener.ListenerSend;
import client.email.view.SplashSubidaArchivo;
import client.model.Archivo;
import client.model.Email;

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
	private JTextArea textPane;
	private JButton send;
	private JLabel lbldrag;
	private JButton upLoad;
	private JPanel panelArchivos;
	private ArrayList<Archivo> archivos;
	String email;
	String password;
	JFrame frameMenu;

	public NewEmailView(String email, String password, JFrame frameMenu) {
		this.email = email;
		this.password = password;
		this.frameMenu = frameMenu;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setUndecorated(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("iconos\\email.png"));
		archivos = new ArrayList<>();
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
		close.setIcon(new ImageIcon("iconos\\remove-button.png"));

		close.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		close.setBorder(emptyBorder);
		close.setBackground(headerColor);
		panel_4.add(close);
		close.addActionListener(new ListenerCloseWindow(frame));

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
		textPane = new JTextArea();
		textPane.setBounds(10, 11, 430, 146);
		textPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPane.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(textPane);
		scroll.setBounds(10, 11, 430, 146);
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dropPanel.add(scroll);
		filesPanel = new JPanel();
		filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(filesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		filesPanel.setBackground(body);
		scrollPane.setBounds(10, 173, 430, 160);
		dropPanel.add(scrollPane);
		generarListado(archivos);
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setHgap(25);
		frame.getContentPane().add(panel_6, BorderLayout.SOUTH);
		panel_6.setBackground(body);

		send = new JButton("");
		send.setIcon(new ImageIcon("iconos\\enviar.png"));

		send.setFocusPainted(false);
		send.setBorder(emptyBorder);
		send.setBackground(body);
		send.addActionListener(new ListenerSend(email, password, this, archivos));
		panel_6.add(send);

		upLoad = new JButton("");
		upLoad.setIcon(new ImageIcon("iconos\\subir.png"));
		upLoad.setFocusPainted(false);
		upLoad.setBorder(emptyBorder);
		upLoad.setBackground(body);
		upLoad.addActionListener(new ListenerFCEmail(this, archivos));
		panel_6.add(upLoad);
		dropFile();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				frameMenu.setEnabled(true);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				frameMenu.setEnabled(true);

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public JPanel getFilesPanel() {
		return filesPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void dropFile() {
		TransferHandler th = new TransferHandler() {

			@Override
			public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
				return true;
			}

			@Override
			public boolean importData(JComponent comp, Transferable t) {
				try {
					List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
					for (File i : files) {

						archivos.add(new Archivo(i.getName(), "" + i.lastModified(), i.getAbsolutePath()));
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
		dropPanel.updateUI();
	}

	public void generarListado(ArrayList<Archivo> archivos) {
		filesPanel.removeAll();
		GridLayout experimentLayout = new GridLayout(0, 1, 5, 5);
		panelArchivos = new JPanel();
		panelArchivos.setLayout(experimentLayout);
		if (archivos.size() != 0) {
			for (Archivo i : archivos) {
				JLabel l = obtenerIcono(i);
				l.setMaximumSize(new Dimension(430, 75));
				panelArchivos.add(l);

				JTextField nombre = generarNombre(panelArchivos, i);
				nombre.setBackground(body);

				// panel.add(new JLabel(i.getUltFechaModificacion()));

				// panel.addMouseListener(new ListenerArchivo(panel, i));

				JPopupMenu menu = generarMenu(nombre, i);

				panelArchivos.setComponentPopupMenu(menu);
				panelArchivos.setBorder(new EmptyBorder(10, 10, 10, 10));
				
			}
		} else {
			lbldrag = new JLabel(new ImageIcon("iconos\\descargar.png"));
			lbldrag.setText("Drag a file to attach");
			lbldrag.setSize(new Dimension(430, 160));
			panelArchivos = new JPanel();
			panelArchivos.add(lbldrag);
		}
		filesPanel.add(panelArchivos);
		filesPanel.updateUI();
		SwingUtilities.updateComponentTreeUI(frame);
		SplashSubidaArchivo splash = new SplashSubidaArchivo(frame);
		splash.setVisible(true);
	}

	private JPopupMenu generarMenu(JTextField nombre, Archivo archivo) {
		JPopupMenu menu = new JPopupMenu();

		JMenuItem item = new JMenuItem("Eliminar");
		item.addActionListener(new ListenerEliminarArchivo(archivo, this));
		menu.add(item);
		return menu;
	}

	private JTextField generarNombre(JPanel panel, Archivo i) {
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

	private JLabel obtenerIcono(Archivo i) {
		String direcIcono;
		if (i.getExtension().equalsIgnoreCase("folder")) {
			direcIcono = "iconos\\carpeta.png";
		} else {
			direcIcono = "iconos\\text-document.png";
		}
		Icon icon = new ImageIcon(direcIcono);
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

	public JTextArea getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextArea textPane) {
		this.textPane = textPane;
	}

	public ArrayList<Archivo> getArchivos() {
		return archivos;
	}

}
