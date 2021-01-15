package client.model;

import java.util.ArrayList;
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
 *	description: class that control the paths the ftp can or has used
 */
public class Paths {
	ArrayList<String> savedpaths;
	String pathLimit;
	/**
	 * class' constructor
	 */
	public Paths() {
		savedpaths = new ArrayList<>();
	}
	public ArrayList<String> getSavedPaths() {
		return savedpaths;
	}
	public void setSavedPaths(ArrayList<String> savedpaths) {
		this.savedpaths = savedpaths;
	}
	public String getPathLimit() {
		return pathLimit;
	}
	public void setPathLimit(String pathLimit) {
		this.pathLimit = pathLimit;
	}
	
}
