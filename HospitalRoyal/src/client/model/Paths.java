package client.model;

import java.util.ArrayList;

public class Paths {
	ArrayList<String> pathguardados;
	String pathLimit;
	public Paths() {
		pathguardados = new ArrayList<>();
	}
	public ArrayList<String> getPathguardados() {
		return pathguardados;
	}
	public void setPathguardados(ArrayList<String> pathguardados) {
		this.pathguardados = pathguardados;
	}
	public String getPathLimit() {
		return pathLimit;
	}
	public void setPathLimit(String pathLimit) {
		this.pathLimit = pathLimit;
	}
	
}
