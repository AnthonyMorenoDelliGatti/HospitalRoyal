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
/**
 * @author Anthony Moreno Delli Gatti
 *         Francisco Manuel Rodriguez Martin
 *         Juan Salguero Ibarrola
 *         Nicolas Rosa Hinojosa
 *         Gonzalo Ruiz de Mier Mora
 *         
 *date 15/01/2021
 *
 *@version 1.0
 *
 *description: Clase que en la que puedes soltar archivos para descargarlos
 */
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
	 * Constructor e inicializador de la aplicacion.
	 * @param v 
	 */
	public DropFile(FTPWindow v) {
		initialize();
		dropFile();
		this.v=v;
	}

	/**
	 * Metodo que carga los archivos soltados
	 * @param archivo
	 */
	public void cargarDatos(Archive archivo) {
		JPanel panel = obterPanelArchivo(archivo); 
		dropPanel.add(panel);
	}

	/**
	 * Metodo para que se puedan soltar archivos
	 */
	private void dropFile() {
		TransferHandler th = new TransferHandler() {
			
			@Override
			public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
				return true;
			}

			@Override
			public boolean importData(JComponent comp, Transferable t) {
				try {
					ArrayList<Archive> archives = new ArrayList<>();
					List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
					for (File i : files) {
						archives.add(new Archive(i.getName(), "" + i.lastModified(), i.getAbsolutePath()));
						pathFiles.add(i.getPath());
					}
					
					generarListado(archives);
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

	/**
	 * Metodo que genera los archivos en la ventana
	 * @param archives
	 */
	private void generarListado(ArrayList<Archive> archives) {
		JPanel panel;
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);
		for (Archive i : archives) {
			panel = new JPanel();
			panel.setLayout(experimentLayout);
			
			JLabel l = obtainIcon(i);
			panel.add(l);
			
			JTextField name = generateName(panel, i);
			name.setBackground(Color.white);
			
			JPopupMenu menu = generarMenu(name, i);

			panel.setComponentPopupMenu(menu);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			dropPanel.add(panel);
		}
	}
	
	/**
	 * Metodo que crea un menu al hacer click derecho sobre los archivos
	 * @param name
	 * @param archivo
	 * @return - devuelve un menu en el que puedes cambiar de nombre o eliminar
	 */
	private JPopupMenu generarMenu(JTextField name, Archive archivo) {
		JPopupMenu menu = new JPopupMenu();

		JMenuItem item = new JMenuItem("Change name");
		menu.add(item);

		JMenuItem item3 = new JMenuItem("Eliminate");
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
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				SplashUploadFile splash = new SplashUploadFile(v, "upload");
				splash.setVisible(true);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
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
		
		JLabel lblDragTheFile = new JLabel("Drag the file ");
		panel_5.add(lblDragTheFile);
		
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
	
	private JPanel obterPanelArchivo(Archive archive) {
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);
		JPanel panel = new JPanel();
		panel.setLayout(experimentLayout);

		JLabel l = obtainIcon(archive);
		panel.add(l);

		JTextField nombre = generateName(panel, archive);

		panel.add(new JLabel(archive.getLastModificationDate()));


		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		return panel;
	}
	

	private JTextField generateName(JPanel panel, Archive i) {
		JTextField name = new JTextField(10);
		name.setText(i.getName());
		panel.add(name);
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.setEditable(false);

		return name;
	}

	private JLabel obtainIcon(Archive i) {
		String direcIcon;
		if (i.getExtension().equalsIgnoreCase("folder")) {
			direcIcon = "iconos//carpeta.png";
		} else {
			direcIcon = "iconos//text-document.png";
		}
		Icon icon = new ImageIcon(direcIcon);
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
