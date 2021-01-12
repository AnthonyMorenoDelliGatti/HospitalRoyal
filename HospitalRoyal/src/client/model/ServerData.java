package client.model;

public class ServerData {
	String urlDB;
	String userDB;
	public ServerData() {
		urlDB = "jdbc:mysql://localhost/hospital_royal";
		userDB = "root";
	}
	public String getUrlDB() {
		return urlDB;
	}
	public void setUrlDB(String urlDB) {
		this.urlDB = urlDB;
	}
	public String getUserDB() {
		return userDB;
	}
	public void setUserDB(String userDB) {
		this.userDB = userDB;
	}
	
}
