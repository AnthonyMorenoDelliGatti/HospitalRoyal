package client.ftp.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import client.model.Archive;
import client.view.Splash;

public class DropFile {

	private JFrame frame;
	private JPanel dropPanel;
	private Color headerColor;
	private JButton close;
	private JButton save;
	private JButton fileChooserBtn;
	private ArrayList<String> pathFiles=new ArrayList<>(); 
	private FTPWindow v;

	/**
	 * Create the application.
	 * @param v 
	 */
	public DropFile(FTPWindow v) {
		initialize();
		dropFile();
		this.v=v;
	}

	public void cargarDatos(Archive archivo) {
		// Consejo: guardaria en un array los paneles para borrar m�s tarde
		JPanel panel = obterPanelArchivo(archivo); 
		dropPanel.add(panel);
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
					ArrayList<Archive> archivos = new ArrayList<>();
					List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
					for (File i : files) {
						archivos.add(new Archive(i.getName(), "" + i.lastModified(), i.getAbsolutePath()));
						pathFiles.add(i.getPath());
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
	
	public ArrayList<String> getPathFiles() {
		return pathFiles;
	}

	private void generarListado(ArrayList<Archive> archivos) {
		JPanel panel;
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);
		for (Archive i : archivos) {
			panel = new JPanel();
			panel.setLayout(experimentLayout);
			
			JLabel l = obtenerIcono(i);
			panel.add(l);
			
			JTextField nombre = generarNombre(panel, i);
			nombre.setBackground(Color.white);
			
			//panel.addMouseListener(new ListenerArchivo(panel, i));

			JPopupMenu menu = generarMenu(nombre, i);

			
			panel.setComponentPopupMenu(menu);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			dropPanel.add(panel);
		}
	}
	
	private JPopupMenu generarMenu(JTextField nombre, Archive archivo) {
		JPopupMenu menu = new JPopupMenu();

		JMenuItem item = new JMenuItem("Cambiar nombre");
		//item.addActionListener(new ListenerBotonModificarNombre(nombre));
		menu.add(item);

		JMenuItem item3 = new JMenuItem("Eliminar");
		//item3.addActionListener(new ListenerEliminar(archivo));
		menu.add(item3);
		return menu;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		headerColor = new Color(204, 252, 255);
		 
		frame = new JFrame();
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				SplashUploadFile splash = new SplashUploadFile(v, "upload");
				splash.setVisible(true);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setUndecorated(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("iconos//ftp.png"));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.white);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_3.setBackground(headerColor);
		panel.add(panel_3);
		
		fileChooserBtn = new JButton("");
		fileChooserBtn.setIcon(new ImageIcon("iconos//importar.png"));
		fileChooserBtn.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		fileChooserBtn.setBorder(emptyBorder);
		fileChooserBtn.setBackground(headerColor);
		panel_3.add(fileChooserBtn);
		
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.setBackground(headerColor);
		panel.add(panel_4);
		
		close = new JButton("");
		close.setIcon(new ImageIcon("iconos//remove-button.png"));
		close.setFocusPainted(false);
		emptyBorder = BorderFactory.createEmptyBorder();
		close.setBorder(emptyBorder);
		close.setBackground(headerColor);
		panel_4.add(close);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		panel_1.setBackground(Color.white);
		
		dropPanel = new JPanel();
		dropPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));
		dropPanel.setBackground(Color.white);
		dropPanel.setLayout(new BoxLayout(dropPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scroll = new JScrollPane(dropPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		dropPanel.add(panel_5);
		
		JLabel lblArrastreElArchivo = new JLabel("Arrastre el archivo ");
		panel_5.add(lblArrastreElArchivo);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		dropPanel.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("iconos//descargar.png"));
		panel_6.add(lblNewLabel_1);
		
		panel_1.add(scroll);
		

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setBackground(Color.white);

		save = new JButton("SAVE");
		save.setBackground(Color.WHITE);
		save.setForeground(Color.CYAN);
		save.setFocusPainted(false);
		emptyBorder = BorderFactory.createEmptyBorder();
		save.setBorder(emptyBorder);
		panel_2.add(save);
		
		frame.setLocationRelativeTo(null);	
	}
	
	private JPanel obterPanelArchivo(Archive archivo) {
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);
		JPanel panel = new JPanel();
		panel.setLayout(experimentLayout);

		JLabel l = obtenerIcono(archivo);
		panel.add(l);

		JTextField nombre = generarNombre(panel, archivo);

		panel.add(new JLabel(archivo.getUltFechaModificacion()));

		//panel.addMouseListener(new ListenerArchivo(panel, archivo));

		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		return panel;
	}
	

	private JTextField generarNombre(JPanel panel, Archive i) {
		JTextField nombre = new JTextField(10);
		nombre.setText(i.getName());
		panel.add(nombre);
		nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//ListenerModificarNombre listener = new ListenerModificarNombre(i, nombre);
		//nombre.addActionListener(listener);
		//nombre.addFocusListener(listener);
		nombre.setEditable(false);

		return nombre;
	}

	private JLabel obtenerIcono(Archive i) {
		String direcIcono;
		if (i.getExtension().equalsIgnoreCase("folder")) {
			direcIcono = "iconos//carpeta.png";
		} else {
			direcIcono = "iconos//text-document.png";
		}
		Icon icon = new ImageIcon(direcIcono);
		JLabel l = new JLabel(icon);
		return l;
	}

	public JButton getClose() {
		return close;
	}

	public void setClose(JButton close) {
		this.close = close;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getSave() {
		return save;
	}

	public void setSave(JButton save) {
		this.save = save;
	}

	public JButton getFileChooserBtn() {
		return fileChooserBtn;
	}

	public void setFileChooserBtn(JButton fileChooserBtn) {
		this.fileChooserBtn = fileChooserBtn;
	}
	

}
