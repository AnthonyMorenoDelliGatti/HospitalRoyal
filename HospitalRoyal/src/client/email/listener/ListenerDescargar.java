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

public class ListenerDescargar implements ActionListener {
	Part unaParte;

	public ListenerDescargar(Part unaParte) {
		this.unaParte = unaParte;
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


}
