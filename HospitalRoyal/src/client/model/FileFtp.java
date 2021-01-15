package client.model;

import java.util.Date;

public class FileFtp implements Comparable<FileFtp> {

	private String name, direction, lastModificationDate;
	private int isFolder;

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
