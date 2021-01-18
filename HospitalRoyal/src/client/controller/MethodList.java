package client.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import client.ftp.view.FTPWindow;
import client.ftp.view.FileView;
import client.model.FileFtp;

/**
 * 
 * @authors Anthony Moreno Delli Gatti
 *			Francisco Manuel Rodriguez Martin
 *			Juan Salguero Ibarrola
 *			Nicolas Rosa Hinojosa
 *			Gonzalo Ruiz de Mier Mora
 *
 *	date 13/01/2021
 *
 *	@version 1.0
 *
 *	description: class that makes the files list on the ftp view
 */
public class MethodList {
	/**
	 * class' constructor
	 */
	public MethodList() {

	}
	/**
	 * method that create the file list of the ftpview
	 * 
	 * @param client ftp client of the server
	 * @param view window of the ftp server
	 * @param explorer window where the file list will be
	 */
	public void DataListLoad(FTPClient client, FTPWindow view, FileView explorer) {
		try {
			ArrayList<FileFtp> FileFtps = new ArrayList<>();
			FTPFile[] fileList = client.listFiles();
			if(fileList.length <= 0) {
				FileFtps.add(new FileFtp("This folder is empty", "", 0, ""));
			}
			if(fileList.length > 0) {
			for (int i = 0; i < fileList.length; i++) {
				String nameFile = fileList[i].getName();
				int isDirectory = 0;
				if (fileList[i].isDirectory()) {
					isDirectory = 1;
				}
				String path = client.printWorkingDirectory();
				if(!path.equals("/")) {
					path=path+"/";
				}
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
				FileFtps.add(new FileFtp(nameFile, lastModification, isDirectory, (path + nameFile)));
				view.getCenter().removeAll();
				view.addExplorer(explorer.ShowLists(FileFtps));
				//view.pack();
			}
			}else {
				view.getCenter().removeAll();
				view.addExplorer(explorer.ShowLists(FileFtps));
				//view.pack();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
