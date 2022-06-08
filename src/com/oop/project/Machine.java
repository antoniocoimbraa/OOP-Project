package com.oop.project;


public class Machine {

	
	private char[] cmd;
	
	//
	private char[] card;
	
	// Stores the name of the card-file.txt file. It can be different from 'card-file.txt'.
	private String cardFileName;

	// Stores a pointer to cmd-file.txt file. It can be different from 'cmd-file.txt'.
	private String cmdFileName;
	
	// Store the total amount of credits available to the player.
	private int credits;
	
	// Stores the present deck being played
	private Deck deck;
	
	// Stores game mode type. (d)debug mode or (s)simulation mode.
	private char mode;
	
	
	// Constructor
	Machine() {
		this.credits = 0;
		this.cardFileName = "";
		this.cmdFileName= "";
		
		// It starts in debug mode by default.
		this.mode = 'd';
	}
	
	
	
	public int getCredits() {
		return credits;
	}	
	
	public String getFileCard() {
		return cardFileName;
	}

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
		this.credits = credits;
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
	
	
	
}
