package com.kwest;

/**
 * Tejas Shah and Nehemiah Elias
 * APCS
 * Mr. Hunter
 * Main.java
 * 11/29/18
 * This program is the program used to run the entire program
 * by consolidating the game logic into an object and 
 * then starting the Login GUI. 
 * RUN THIS PROGRAM TO PLAY THE GAME.
 */

/**
 * Class uses to run the entire project
 */
public class Main {
	public static void main(String[] args) {
		Game game = new Game();	
		new LoginFrame(game);
	}
}