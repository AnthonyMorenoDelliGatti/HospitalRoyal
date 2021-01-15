package client.model;
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
 *	description: class that control the files data
 */
public class Archive{

	private String name, lastModificationDate, direction;
/**
 * class' constructor
 * @param name name of the file
 * @param lastModificationDate last time the file was modified
 * @param direction direction of the file
 */
	public Archive(String name, String lastModificationDate, String direction) {
		this.name = name;
		this.lastModificationDate = lastModificationDate;
		this.direction = direction;
	}
/**
 * method that get the extension of the file
 * @return the type of file 
 */
	public String getExtension() {
		String extension = "";
		try {
			extension = name.split("\\.")[1];
		}
		catch(Exception e){
			extension = "folder";
		}
		
		return extension;
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

	public void setLastModificationDate(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
