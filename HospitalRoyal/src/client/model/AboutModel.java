package client.model;

import java.util.ArrayList;

public class AboutModel {
	ArrayList<String> autores = new ArrayList<>();
	String nombre;
	String version;
	String copyright;
	String fecha;

	public AboutModel() {
		nombre = "Hospital Royal app";
		version = "1.0.0";
		copyright = "Hospital Royal, 2021";
		autores.add("Anthony Moreno Delli Gatti");
		autores.add("Francisco Manuel Rodriguez Martin");
		autores.add("Juan Salguero Ibarrola");
		autores.add("Nicolas Rosa Hinojosa");
		autores.add("Gonzalo Ruiz de Mier Mora");
		fecha = "13/01/2021";
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public String getNombre() {
		return nombre;
	}

	public String getVersion() {
		return version;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getFecha() {
		return fecha;
	}
}
