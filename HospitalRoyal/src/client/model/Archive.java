package client.model;

public class Archive{

	private String name, lastModificationDate, direction;

	public Archive(String name, String lastModificationDate, String direction) {
		this.name = name;
		this.lastModificationDate = lastModificationDate;
		this.direction = direction;
	}

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
