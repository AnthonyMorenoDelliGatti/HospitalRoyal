package client.controller;

import client.view.Splash;
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
 *	description: class that start the program
 *
 */
public class Main {
/**
 * Method main of the program 
 * 
 * @param args
 */
	public static void main(String[] args) {
		Splash splash = new Splash();
		Thread threadSplash = new Thread(splash);
		threadSplash.start();
	}

}
