package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import client.email.view.SplashDownloadFile;
import client.email.view.SplashSubidaArchivo;


public class ListenerDescargar implements ActionListener {
	Part unaParte;
	JPanel panel;
	public ListenerDescargar(Part unaParte, JPanel panel) {
		this.unaParte = unaParte;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InputStream is;
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.showOpenDialog(fc);
		    String path = fc.getSelectedFile().getAbsolutePath();
			is = unaParte.getInputStream();
			File f = new File(path+ "/" + unaParte.getFileName());
			FileOutputStream fos = new FileOutputStream(f);
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.close();
			panel.setEnabled(false);
			SplashDownloadFile splash = new SplashDownloadFile(panel);
			splash.setVisible(true);
		} catch (Exception e1) {
			
		}
		
	}


}
