package com.kwest;

/**
 * Tejas Shah and Nehemiah Elias
 * APCS
 * Mr. Hunter
 * LoginFrame.java
 * 11/29/18
 * This program contains GUI that first starts up
 * to explain the rules of the Game and a button
 * to let the player start playing. It runs as
 * the very first GUI screen.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * GUI shown at start of project to explain rules and start game
 */
public class LoginFrame {
	
	private JFrame frame;
	private JButton button;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	
	/**
	 * Constructor to initialize and build Frame
	 * @param game Main game instance 
	 */
	public LoginFrame(Game game) {    
		frame = new JFrame("Kwest Start Screen");
		button = new JButton("START");    
		button.setBounds(190,190,100,100); 
		
		label1 = new JLabel();		
		label1.setText("Welcome to Kwest!");
		label1.setBounds(10,-220,500,500);
		
		label2 = new JLabel();
		label2.setText("You have three options: Sword, Shield, or Potion. ");
		label2.setBounds(10,-200,500,500);
		
		label3 = new JLabel();
		label3.setText("The Sword reduces your opponent's life if his shield isn't up. ");
		label3.setBounds(10,-180,500,500);
			
		label4 = new JLabel();
		label4.setText("You use one energy point each time you use the sword. ");
		label4.setBounds(10,-160,500,500);
		
		label5 = new JLabel();
		label5.setText("The Shield protects you from your opponent's sword. ");
		label5.setBounds(10,-140,500,500);
		
		label6 = new JLabel();
		label6.setText("The Potion gives you energy to use the Sword. ");
		label6.setBounds(10,-120,500,500);
		
		label7 = new JLabel();
		label7.setText("If the opponent uses the sword and you use the potion, you die! ");
		label7.setBounds(10,-100,500,500);
		
		label8 = new JLabel();
		label8.setText("AP Computer Science Final Project by Tejas Shah and Nehemiah Elias");
		label8.setBounds(10,80,500,500);
		
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(label4);
		frame.add(label5);
		frame.add(label6);
		frame.add(label7);
		frame.add(label8);
		frame.add(button);    
		frame.setBackground(Color.CYAN);
		frame.setSize(500,500);
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
		button.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent arg0) {
					new PlayFrame(game);
					frame.dispose();
			}          
		});
	}
	
	/**
	 * Simple function to destroy Login Frame upon starting the game
	 */
	public void destroy() {
		frame.dispose();
	}
 }
