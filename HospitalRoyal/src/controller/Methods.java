package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import model.Archivo;
import view.VistaArchivos;
import view.VistaPrincipal;

public class Methods {
	
	public Methods() {

	}
	public void cargarDatosLista(ArrayList<Archivo> archivos, FTPClient client, VistaPrincipal view, VistaArchivos explorer) {
		archivos.clear();
		try {
			FTPFile[] fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				String nameFile = fileList[i].getName();
				int isDirectory = 0;
				if (fileList[i].isDirectory()) {
					isDirectory = 1;
				}
				String path = client.printWorkingDirectory();
				String time = client.getModificationTime(path + nameFile);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
				String lastModification = "";
				try {
					String timePart = time.split(" ")[0];
					Date modificationTime = dateFormat.parse(timePart);
					Calendar calendarModification = Calendar.getInstance(TimeZone.getDefault());
					calendarModification.setTime(modificationTime);
					lastModification = "" + calendarModification.get(Calendar.DAY_OF_MONTH) + "/"
							+ (calendarModification.get(Calendar.MONTH)+1) + "/" + calendarModification.get(Calendar.YEAR)
							+ " " + (calendarModification.get(Calendar.HOUR)+1) + ":"
							+ calendarModification.get(Calendar.MINUTE) + ":"
							+ calendarModification.get(Calendar.SECOND);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				archivos.add(new Archivo(nameFile, lastModification, isDirectory, (path + nameFile)));
				view.getCentro().removeAll();
				view.agregarExplorador(explorer.visualizarListado(archivos));
				view.pack();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
