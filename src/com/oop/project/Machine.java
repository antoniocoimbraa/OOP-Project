package com.oop.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// TODO: create method to print commands
// TODO: create method to print results



public class Machine {
	
	// Stores the name of the file that contains the cards for debug mode
	private String cardFileName;

	// Stores the name the file that contains the commands for debug mode
	private String cmdFileName;
	
	// TODO: change cmdList type from String[] to fifo set type
	// Stores the list of commands
	private String[] cmdList;
	
	// TODO: change cardList type from String[] to fifo set type
	private String[] cardList;
	
	// Store the total amount of credits available to the player.
	private int credit;
	
	// Stores the present deck being played
	private Deck deck;
	
	// Stores game mode type. (d)debug mode or (s)simulation mode.
	private char mode;
	
	
	// CONSTRUCTOR
	// TODO: code for simulation mode
	Machine(String[] cmmdLine) {
		char mode = cmmdLine[0].toCharArray()[1];
		
		switch(mode) {
			// Case for (d)ebug mode
			case 'd':
				this.cardFileName = cmmdLine[2];
				this.cmdFileName = cmmdLine[3];
				this.cardList = parseFile(cmmdLine[2]);
				this.cmdList = parseFile(cmmdLine[3]);
				this.credit = (int) Integer.parseInt(cmmdLine[1]);
				this.deck = new Deck();
				this.mode = cmmdLine[0].toCharArray()[1];
				break;
			// Case for (s)imulation mode
			case 's':
				break;
			default:
				System.out.println("Machine constructor failed!");
				break;
		}
	}
	
	// GETTERS AND SETTER METHODS
	/**
	 * 
	 * @return
	 */
	public int getCredits() {
		return credit;
	}	
	/**
	 * 
	 * @return
	 */
	public String getFileCard() {
		return cardFileName;
	}
	/**
	 * 
	 * @return
	 */
	public String getFileCmd() {
		return cmdFileName;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public char getMode() {
		return mode;
	}
	
	public void setCredits(int credits) {
		this.credit = credits;
	}
	
	public void setFileCard(String cardFileName) {
		this.cardFileName = cardFileName;
	}
	
	public void setFileCmd(String cmdFileName) {
		this.cardFileName = cmdFileName;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setMode(char mode) {
		this.mode = mode;
	}
	
	
	
	// CUSTOM METHODS
	// TODO: Create a welcome method upon machine constructor call
	// TODO: Create a shuffle method to shuffle deck of cards
	
	
	public String[] parseFile(String cmdFileName) {
		
		File fp = new File(cmdFileName);
		Scanner sc = null;
		
		String data = "";
		
		try {
			sc = new Scanner(fp);
			while(sc.hasNextLine()) {
				data = sc.nextLine();
			}
			sc.close();
			return data.split(" ");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new String[]{};
		}
	}
	
	
	// Parse command line
	public String[] parseCmd(String cmdLine) {
		return cmdLine.split(cmdLine);
	}
	
	// m
}
