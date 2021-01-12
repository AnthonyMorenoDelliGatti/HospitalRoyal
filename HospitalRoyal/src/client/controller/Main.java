package client.controller;

import client.view.Splash;

public class Main {

	public static void main(String[] args) {
		Splash splash = new Splash();
		Thread threadSplash = new Thread(splash);
		threadSplash.start();
	}

}
