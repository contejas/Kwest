package com.kwest;

/**
 * Tejas Shah
 * APCS
 * Mr. Hunter
 * PlayFrame.java
 * 11/29/18
 * This program contains the GUI for the main screen that
 * the player uses to actually make decisions and play the
 * game. It displays buttons, text, and images in an 
 * attempt to create an iterative game experience.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.util.ArrayList;

/**
 * GUI that visualizes the main game play loop
 */
public class PlayFrame {
	
	private JFrame frame;
	private JPanel panel;
	private JButton shield;
	private JButton sword;
	private JButton potion;
	private JLabel description;
	private JLabel message;
	private JLabel player_labeler;
	private JLabel player_img;
	private JLabel opponent_img;
	private JLabel player_weapon_img;
	private JLabel opponent_weapon_img;
	
	/**
	 * Function to dynamically change Description JLabel of GUI
	 * @param text String to change JLabel to
	 */
	public void setDescText(String text) {
		description.setText(text);
		frame.add(description);
	}
	
	/**
	 * Function to dynamically change Message JLabel of GUI
	 * @param text String to change JLabel to
	 */
	public void setMessage(String text) {
		message.setText(text);
		frame.add(message);
	}
	
	/**
	 * A utility function to end the game if result[0] returns a game-terminating character
	 * @param game The main game object
	 * @throws InterruptedException in case there are any errors with System.exit(0)
	 */
	public void checkStatus(Game game) throws InterruptedException {
		String status = game.getStatus();	
		if(status == "l") {
			JOptionPane.showMessageDialog(null, "You Lost!");
			Thread.sleep(2);
			System.exit(0);
		} else if(status == "w") {
			JOptionPane.showMessageDialog(null, "You Win!");
			Thread.sleep(2);
			System.exit(0);
		} else if(status == "n") {
			// do nothing, continue game
		}
	}
	
	/**
	 * Function to dynamically change the Weapon Images of GUI
	 * @param move Player's move choice
	 * @param op_move Opponent's move choice
	 */
	public void setWeaponImages(Move move, Move op_move) {
		switch(move) {
		case POTION:
			player_weapon_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Potion.png"));
			break;
		case SWORD:
			player_weapon_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Player_Sword.png"));
			break;
		case SHIELD:
			player_weapon_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Shield.png"));
			break;
		}
		switch(op_move) {
		case POTION:
			opponent_weapon_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Potion.png"));
			break;
		case SWORD:
			opponent_weapon_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Enemy_Sword.png"));
			break;
		case SHIELD:
			opponent_weapon_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Shield.png"));
			break;
		}
	}
	
	/**
	 * Constructor to create the updated GUI and continue play of the game
	 * @param game Main game instance
	 */
	public PlayFrame(Game game) {    
		frame = new JFrame("Kwest"); 
		panel = new JPanel();
		shield = new JButton("Shield");
		sword = new JButton("Sword");
		potion = new JButton("Potion");
		
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		shield.setBounds(100, 30, 100, 50);
		sword.setBounds(200, 30, 100, 50);
		sword.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
		potion.setBounds(300, 30, 100, 50);
		
		description = new JLabel();
		description.setBounds(10, 100, 500, 100);
		
		message = new JLabel();
		message.setBounds(10, 120, 500, 100);
		
		player_labeler = new JLabel("YOU                                                                                                          ENEMY");
		player_labeler.setBounds(50, 350, 500, 100);
		panel.add(player_labeler);
		
		player_img = new JLabel();
		player_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Player.png"));
		player_img.setBounds(-30, 200, 200, 200);
		panel.add(player_img);
		
		opponent_img = new JLabel();
		opponent_img.setIcon(new ImageIcon("C:/users/200322103/workspace/Kwest/src/com/kwest/Kwest_Enemy.png"));
		opponent_img.setBounds(315, 200, 200, 200);
		panel.add(opponent_img);
		
		player_weapon_img = new JLabel();
		player_weapon_img.setBounds(110, 200, 200, 200);
		panel.add(player_weapon_img);
		
		opponent_weapon_img = new JLabel();
		opponent_weapon_img.setBounds(275, 200, 200, 200);
		panel.add(opponent_weapon_img);
		
		ArrayList<Move> available_moves = game.availableMoves();
		
		if(available_moves.contains(Move.SHIELD)) {
			panel.add(shield);
		}
		if(available_moves.contains(Move.SWORD)) {
			panel.add(sword);
		}
		if(available_moves.contains(Move.POTION)) {
			panel.add(potion);
		}
		
		frame.setContentPane(panel);
		frame.setSize(500,500);    
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Move op_move = game.opponentMove();
		
		shield.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.playMove(Move.SHIELD, op_move);
				String move_str = game.moveToString(Move.SHIELD);
				String op_move_str = game.moveToString(op_move);
				frame.dispose();
				PlayFrame newframe = new PlayFrame(game);
				newframe.setWeaponImages(Move.SHIELD, op_move);
				newframe.setDescText("You picked " + move_str + ", Your energy level is now " + game.getEnergyLevel() + ", your opponent chose " + op_move_str);
				newframe.setMessage(game.getMessage());
				try {
					checkStatus(game);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}          
		});
		
		sword.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.playMove(Move.SWORD, op_move);
				String move_str = game.moveToString(Move.SWORD);
				String op_move_str = game.moveToString(op_move);
				frame.dispose();
				PlayFrame newframe = new PlayFrame(game);
				newframe.setWeaponImages(Move.SWORD, op_move);
				newframe.setDescText("You picked " + move_str + ", Your energy level is now " + game.getEnergyLevel() + ", your opponent chose " + op_move_str);
				newframe.setMessage(game.getMessage());
				try {
					checkStatus(game);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}          
		});
		
		potion.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.playMove(Move.POTION, op_move);
				String move_str = game.moveToString(Move.POTION);
				String op_move_str = game.moveToString(op_move);
				frame.dispose();
				PlayFrame newframe = new PlayFrame(game);
				newframe.setWeaponImages(Move.POTION, op_move);
				newframe.setDescText("You picked " + move_str + ", Your energy level is now " + game.getEnergyLevel() + ", your opponent chose " + op_move_str);
				newframe.setMessage(game.getMessage());
				try {
					checkStatus(game);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}         
 }
