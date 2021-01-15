package client.model;

import java.util.Date;
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
 *	description: class that control the data of the ftp's files
 */
public class FileFtp implements Comparable<FileFtp> {

	private String name, direction, lastModificationDate;
	private int isFolder;
/**
 * class' constructor
 * @param name name of the file
 * @param lastModificationTime las date it was modified
 * @param isFolder if it is a folder
 * @param direction direction of the file
 */
	public FileFtp(String name, String lastModificationTime, int isFolder, String direction) {
		this.name = name;
		this.lastModificationDate = lastModificationTime;
		this.isFolder = isFolder;
		this.direction = direction;
	}

	/**
	 * 
	 * Method to sort an array of files if is a folder or not , having preference folder
	 * 
	 */
	@Override
	public int compareTo(FileFtp o) {
		if (isFolder < o.isFolder) {
			return 1;
		}
		if (isFolder > o.isFolder) {
			return -1;
		}
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationTime(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public int getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(int isFolder) {
		this.isFolder = isFolder;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
