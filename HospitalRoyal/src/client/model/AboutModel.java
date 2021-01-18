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
 *	description: class that control data of the about windows
 */
public class AboutModel {
	ArrayList<String> authors = new ArrayList<>();
	String name;
	String version;
	String copyright;
	String date;
/**
 * class' constructor
 */
	public AboutModel() {
		name = "Hospital Royal app";
		version = "1.0.0";
		copyright = "Warning.This computer program is protected by '\n'copyright laws and international treaties. The use of this '\n'product implies the acceptance of hospital royal's license";
		authors.add("Anthony Moreno Delli Gatti");
		authors.add("Francisco Manuel Rodriguez Martin");
		authors.add("Juan Salguero Ibarrola");
		authors.add("Nicolas Rosa Hinojosa");
		authors.add("Gonzalo Ruiz de Mier Mora");
		date = "13/01/2021";
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getDate() {
		return date;
	}
	
}
