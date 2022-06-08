package com.oop.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Machine {
	
	// Stores the name of the card-file.txt file. It can be different from 'card-file.txt'.
	private String cardFileName;

	// Stores a pointer to cmd-file.txt file. It can be different from 'cmd-file.txt'.
	private String cmdFileName;
	
	// 
	private String[] cmdList;
	
	//
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
		case 'd':
			this.cardFileName = cmmdLine[2];
			this.cmdFileName = cmmdLine[3];
			this.cardList = parseFile(cmmdLine[2]);
			this.cmdList = parseFile(cmmdLine[3]);
			this.credit = (int) Integer.parseInt(cmmdLine[1]);
			this.deck = new Deck();
			this.mode = cmmdLine[0].toCharArray()[1];
			break;
		case 's':
			break;
			default:
				System.out.println("Machine constructor failed!");
		}
		

	}
	
	
	// CONSTRUCTOR for debug mode
	Machine(int credit, String cardFileName, String cmdFileName) {
		this.cardFileName = cardFileName;
		this.cmdFileName = cmdFileName;
		this.cardList = parseFile(cardFileName);
		this.cmdList = parseFile(cmdFileName);
		this.credit = credit;
		this.deck = new Deck();
		this.mode = 'd';
	}
	
	
	
	// DEFAULT GETTER AND SETTER
	
	public int getCredits() {
		return credit;
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
	
	
	
	// METHODS
	
	// ParseFile
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
}
