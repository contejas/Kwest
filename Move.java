package com.kwest;

/**
 * Tejas Shah
 * APCS
 * Mr. Hunter
 * Move.java
 * 11/29/18
 * This program contains the enum for all the different Moves
 * players can choose from when playing the game. They are
 * all consolidated into single values to avoid String 
 * comparison problems when comparing non-number
 * values.
 */

/**
 * Enum of all the different Move choices a player makes
 */
public enum Move {
	SHIELD, SWORD, POTION, // normal gameplay moves
	CHARGE, STAY, // Charge moves
	FALL, PUSH, // Deadlock moves
	LEFT, RIGHT, CENTER // Parry moves
}
