package com.oop.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// TODO: create method to print commands
// TODO: create method to print results
public class Machine {
	
	// Stores the name of the file that contains the cards for debug mode
	private String cardFile;

	// Stores the name the file that contains the commands for debug mode
	private String cmdFile;
	
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
	
	
	// Constructor
	Machine(Deck deck) {
		this.deck = deck;
	}
	
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
	public String getCardFileName() {
		return cardFileName;
	}
	
	public String getCmdFileName() {
		return cmdFileName;
	}
	
	public int getCredits() {
		return credit;
	}	

	public String getCmdList() {
		return cmdList.toString();
	}

	public String getCardList() {
		return cardList.toString();
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public char getMode() {
		return mode;
	}
	
	public void setFileCard(String cardFileName) {
		this.cardFileName = cardFileName;
	}
	
	public void setFileCmd(String cmdFileName) {
		this.cmdFileName = cmdFileName;
	}

	@Override
	public String toString() {
		String cardFile = "card file: " + this.cardFile + "\n";
		String cmdFile = "cmd file: " + this.cmdFile + "\n";
		String card = "cards: " + this.cardList + "\n";
		String cmd = "cmd: " + this.cmdList + "\n";
		String credit = "credit(s): " + this.credit + "\n";
		String deck = "deck:\n" + this.deck;
		String mode = "mode: " + this.mode + "\n";
		
		return cardFile+cmdFile+card+cmd+credit+deck+mode;
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
		System.out.println(cmdLine.split(cmdLine));
		return cmdLine.split(cmdLine);
	}
	
	// Counts the total cards of a deck
	public String count() {
		return String.valueOf(deck.size());
	}
	
	// Displays the deck
	public String deck() {
		return this.deck.toString();
	}
	
	// Draw cards
	public String draw(int numberOfDraws) {
		return deck.draw(numberOfDraws);
	}
	
	// New deck
	public String newDeck() {
		this.deck = new Deck();
		return this.deck.toString();
	}
	
	// shuffles deck
	public String shuffle() {
		this.deck.shuffle();
		return this.deck.toString();
	}
	
	
}
