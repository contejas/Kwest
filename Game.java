package com.kwest;

/**
 * Tejas Shah and Nehemiah Elias
 * APCS
 * Mr. Hunter
 * Game.java
 * 11/29/18
 * This program contains the game object that allows all of 
 * the values that are incorporated into the game's logic to 
 * change and fluctuate in a linear way when interacting with 
 * and taking data from the GUI
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Contains all of the logic and values for the game 
 */
public class Game {
	public static String status = "n"; // "w" = Win, "l" = Loss, "n" = Neutral (No Winner)
	public static String message = "Let's play! Good Luck!";
	
	public static int games = 0;
	public static Random rand = new Random();
	public static Scanner sc = new Scanner(System.in);
	
    public static int energy = 0;
    public static int op_energy = 0;
    public static int over_shield = 0; // system in place to punish overuse of shield
    public static int shield_durability = 7; 
    public static int sword_durability = 9;
    public static int potion_durability = 12;
	
    public static boolean go_after_charge = true;
    
    /**
     * Resets all of the game values to allow the player to replay the game
     */
	public static void reset() {
		games = 0;
		energy = 0;
	    op_energy = 0;
	    over_shield = 0;
	    shield_durability = 7;
	    sword_durability = 9;
	    potion_durability = 12;
	}
	
	/**
	 * A utility function to convert Move enums into Strings to be displayed in the GUI
	 * @param move The move to be converted in enum form
	 * @return the string version of the Move
	 */
	public String moveToString(Move move) {
		if(move == Move.POTION) {
			return("Potion");
		} else if(move == Move.SHIELD) {
			return("Shield");
		} else if(move == Move.SWORD) {
			return("Sword");
		}
		return("ERROR");
	}
	
	/**
	 * Operation to have the CPU opponent pick a move and send it to the game logic
	 * @return the opponent's randomly generated move
	 */
	public Move opponentMove() {
		ArrayList<Move> moves;
		if(op_energy == 0) {
			Move[] moveArray = {Move.POTION, Move.SHIELD};
			moves = new ArrayList<Move>(Arrays.asList(moveArray));
		} else {
			Move[] moveArray = {Move.POTION, Move.SHIELD, Move.SWORD};
			moves = new ArrayList<Move>(Arrays.asList(moveArray));
		}
		int index = rand.nextInt(moves.size());
		Move op_move = moves.get(index);
		switch(op_move) {
		case POTION: 
			op_energy++;
			break;
		case SWORD:
			op_energy--;
			break;
		default:
			break;
		}
		return(op_move);
	}
	
	/**
	 * Describes all of the primary Moves that the game values allow
	 * @return ArrayList of all moves that player is allowed to pick from
	 */
	public static ArrayList<Move> availableMoves() {
		ArrayList<Move> moves;
		if(over_shield != 5) {
			if(energy == 0) {
				Move[] moveArray = {Move.POTION, Move.SHIELD};
				moves = new ArrayList<Move>(Arrays.asList(moveArray));
			} else {
				Move[] moveArray = {Move.POTION, Move.SHIELD, Move.SWORD};
				moves = new ArrayList<Move>(Arrays.asList(moveArray));
			}
		} else if(over_shield == 5) {
			if(energy == 0) {
				Move[] moveArray = {Move.POTION};
				moves = new ArrayList<Move>(Arrays.asList(moveArray));
			} else {
				Move[] moveArray = {Move.POTION, Move.SWORD};
				moves = new ArrayList<Move>(Arrays.asList(moveArray));
			}
		} else {
			Move[] moveArray = {Move.POTION, Move.SHIELD, Move.SWORD};
			moves = new ArrayList<Move>(Arrays.asList(moveArray));
		}
		return(moves);
	}
	
	/**
	 * Models a deadlock situation and sends the outcome to the main game logic
	 * @return Array of the status and message given the outcome
	 */
	public static String[] deadlockMove() {
		String[] result = new String[2];
		int chance = rand.nextInt(2);
		if (chance == 0) {
			result[0] = "l";
			result[1] = "Your opponent overpowered you in a sword deadlock, slicing your head off!";
		} else {
			result[0] = "n";
			result[1] = "You two entered a deadlock, but you got out of it safely!";
		}
		return(result);
	}
	
	/**
	 * Models a charging situation and sends the outcome to the main game logic
	 * @return Array of the status and message given the outcome
	 */
	public static String[] chargeMove() {
		String[] result = new String[2];
		int chance = rand.nextInt(2);
		if (chance == 0) {
			result[0] = "w";
			result[1] = "Your opponent charged at you, but you dodged and reciprocated the blow!";
		} else {
			result[0] = "l";
			result[1] = "The opponent suddenly charged at you and rammed his sword into your heart!";
		}
		return(result);
	}
	
	/**
	 * Models a parry situation and sends the outcome to the main game logic
	 * @return Array of the status and message given the outcome
	 */
	public static String[] parryMove() {
		String[] result = new String[2];
		int chance = rand.nextInt(2);
		if (chance == 0) {
			result[0] = "l";
			result[1] = "The enemy suddenly attacked, and you were unable to parry the attack!";
		} else {
			result[0] = "n";
			result[1] = "You successfully parried the enemy's sudden attack with your shield!";
		}
		return(result);
	}
	
	/**
	 * The main game logic - a standard move operation and sending the response to the GUI
	 * @param your_move The player's move choice in move enum notation
	 * @param op_move The opponent's move choice in move enum notation
	 */
	public void playMove(Move your_move, Move op_move) { // returns ["w", message] if win, ["l", message] if lost, else ["n", message] if no winner yet
		String[] result = new String[2];
		if (your_move == Move.POTION) {
			potion_durability--;
			over_shield = 0;
			energy++;
		} else if (your_move == Move.SHIELD) {
			over_shield++;
			shield_durability--;
		} else if (your_move == Move.SWORD) {
			sword_durability--;
			over_shield = 0;
			energy--;
		}
		
		if(energy == 10) {
			result[0] = "w";
			result[1] = "Your energy level is at 10! With the massive amount of energy in your body, you jump up, do a flip, and slice your opponent clean in half. You Win!";
		} else {
			if(energy == 5) {
				result = chargeMove();
			}
			if(go_after_charge) {
				if(your_move == Move.SWORD && op_move == Move.SHIELD) {
		    		result[0] = "n";
		    		result[1] = "You struck with your Sword, but your opponent blocked it!";
		    	} else if(your_move == Move.SWORD && op_move == Move.SWORD) {
		    		result = deadlockMove();
		    	} else if(your_move == Move.SWORD && op_move == Move.POTION) {
		    		result[0] = "w";
		    		result[1] = "Bingo! While your opponent was taking a potion, you stabbed him with your sword!";
		    	} else if(your_move == Move.SHIELD && op_move == Move.SHIELD)  {
		    		result[0] = "n";
		    		result[1] = "Cowards! You both used your shield!";
		    	} else if(your_move == Move.SHIELD && op_move == Move.SWORD) {
		    		result[0] = "n";
		    		result[1] = "Your opponent went in for the kill with his sword, but you blocked it with your shield!";
		    	} else if(your_move == Move.SHIELD && op_move == Move.POTION) {
		    		int chance = rand.nextInt(5);
		    		if(chance == 2) {
		    			result = parryMove();
		    		} else {
		    			result[0] = "n";
		    			result[1] = "While you had your shield up, your opponent took a sip of his potion!";
		    		}
		    	} else if(your_move == Move.POTION && op_move == Move.SHIELD) {
		    		int chance = rand.nextInt(5);
		    		if(chance == 2) {
		    			result = parryMove();
		    		} else {
		    			result[0] = "n";
		    			result[1] = "While you had your shield up, your opponent took a sip of his potion!";
		    		}
		    	} else if(your_move == Move.POTION && op_move == Move.SWORD) {
		    		result[0] = "l";
		    		result[1] = "Alas! While you were drinking your potion, your opponent killed you with his Sword!";
		    	} else if(your_move == Move.POTION && op_move == Move.POTION) {
		    		result[0] = "n";
		    		result[1] = "Whew! This fight is tiring! You both took a swig of your potion!";
		    	}
			} else {
				go_after_charge = true;
			}
		}
		
        status = result[0];
        message = result[1];
	}
	
	/**
	 * Returns the player's current energy level
	 * @return Game value of player's energy
	 */
	public int getEnergyLevel() {
		return(energy);
	}
	
	/**
	 * Returns status, or if the player has won, lost, or if play should continue
	 * @return Game value of status as a character (but formatted as a String)
	 */
	public String getStatus() {
		return(status);
	}
	
	/**
	 * Returns message, or the lengthy version of explaining the new game progression
	 * @return Game value of message as String
	 */
	public String getMessage() {
		return(message);
	}
}
