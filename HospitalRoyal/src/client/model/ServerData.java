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
 *	description: class that control data of the server
 */
public class ServerData {
	String urlDB;
	String userDB;
	/**
	 * class' constructor
	 */
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
