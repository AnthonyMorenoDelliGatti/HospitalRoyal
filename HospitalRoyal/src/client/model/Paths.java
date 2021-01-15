package client.model;

import java.util.ArrayList;

public class Paths {
	ArrayList<String> savedpaths;
	String pathLimit;
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
