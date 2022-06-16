package com.oop.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Machine {
	
	// store the total number of bets
	private int bet;
	
	// Stores the name of the file that contains the cards for debug mode
	private String cardFile;

	// Stores commands
	private Deque<String> feed = new LinkedList<>();
	
	// Stores the name the file that contains the commands for debug mode
	private String commandFile;
	
	// Store the total amount of credits available to the player.
	private int credit;
	
	// Stores list of cards
	private Deque<Card> deck = new LinkedList<>();
	
	private final PokerHand hand = new PokerHand();
	
	// Stores game mode type. (d)debug mode or (s)simulation mode.
	private char mode;
	
	// Total number of deals
	private int nbdeals;
	
	
	// CONSTRUCTOR
	Machine(String[] args) {		
		try {
			this.mode = args[0].charAt(1);
			
			String cmdEntry1 = args[1];
			String cmdEntry2 = args[2];
			String cmdEntry3 = args[3];
			
			switch(this.mode) {
				case 'd': Debug(cmdEntry1,cmdEntry2,cmdEntry3); break;
				case 's': Simulation(cmdEntry1,cmdEntry2,cmdEntry3); break;					
					default: 
						System.out.println(""); break;
			}
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Invalide mode. Pick 'd' or 's'.");
		}
	}

	@Override
	public String toString() {
		String mode = String.valueOf(this.mode);
		
		String header = "";
		String end = "";
		
		String bet = "\tBet = " + String.valueOf(this.bet) + "\n";
		String cardFile = "\tCard file = " + this.cardFile + "\n";
		String feed = "\tCommands = " + this.feed.toString() + "\n";
		String commandFile = "\tCommand file " + this.commandFile + "\n";
		String credit = "\tCredit = " + String.valueOf(this.credit) + "\n";
		String deck = "\tDeck = " + this.deck.toString() + "\n";	
		String nbdeals = "\tTotal deals = " + String.valueOf(this.nbdeals) + "\n";
		
		switch(mode) {
		case "d": 
			header = "(Debug mode)\n";
			end = "(Debug mode end)\n\n\n";
			break;
		case "s": 
			header = "(Simulation mode)\n";
			end = "(Simulation mode end)\n\n\n";
			break;
		}
		
		return header + 
				bet + credit + nbdeals + "\n" +
				commandFile + feed + "\n" +
				cardFile + deck + 
				end;
	}
	
	private void Debug(String cmdEntry1, String cmdEntry2, String cmdEntry3) {
		this.bet = 5;
		this.cardFile = cmdEntry3;
		
		for(String cmd:parse(cmdEntry2))
			feed.add(cmd);
		
		this.commandFile = cmdEntry2;
		this.credit = Integer.valueOf(cmdEntry1);
		
		this.deck = Card.newDeck(parse(cmdEntry3));
		
		this.nbdeals = 0;
	}
	
	private void Simulation(String cmdEntry1, String cmdEntry2, String cmdEntry3) {
		this.bet = Integer.valueOf(cmdEntry2);
		this.cardFile = "(no file needed for simulation mode)";
		this.feed = null;
		this.commandFile = "(no file neede for simulation mode)";
		this.credit = Integer.valueOf(cmdEntry1);
		this.deck = Card.newDeck();
		this.nbdeals = Integer.valueOf(cmdEntry3);
	}
	
	private static String[] parse(String fileName) {
		File fp = new File(fileName);
		Scanner sc = null;
		String line = "";
		
		try {
			sc = new Scanner(fp);
			while(sc.hasNextLine()) {
				line = sc.nextLine();
			}
			sc.close();
			return line.split(" ");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Machine can't open file: " + fileName);
			return new String[] {};
		}
	}
	
	public void play() {
		while(this.feed.peekFirst() != null) {
			String cmd = this.feed.removeFirst();
			switch(Command.getConstant(cmd)) {
				case BET: Bet(); break;
				case CREDIT: Credit(); break;
				case DEAL: Deal(); break;
				case HOLD: Hold(); break;
				case ADVICE: Advice(); break;
				case STATISTICS: Statistics(); break;
					default:
						System.out.println("Invalid command");
						break;
			}
		}
	}
	
	private void Bet() {
		System.out.println("-cmd b" );
		System.out.println("Player is betting " + bet);
		System.out.println();
		credit = credit - bet;
	}
	
	private void Credit() {
		System.out.println("-cmd $" );
		System.out.println("Player's credit is " + credit);
		System.out.println();
	}
	
	private void Deal() {
		int i = 0;
		
		if(deck.size() > 6) {
			for(Card card:drawCard(5)) {
				i++;
				hand.set(i, card);
			}
			System.out.println("-cmd d" );
			System.out.println("Player's hand " + hand);
			System.out.println("");
		}
		else 
			System.out.println("There are not enough cards to complete this game");
	}
	
	private void Hold() {
		if(deck.size() > 1) {
			try {
				int hold1 = Integer.valueOf(feed.removeFirst());
				int hold2 = Integer.valueOf(feed.removeFirst());
				
				if(hold1 > 0 && hold1 < 6 && hold2 > 0 && hold2 < 6) {
					hand.set(hold1, deck.removeFirst());
					hand.set(hold2, deck.removeFirst());
					System.out.println("-cmd h " + hold1 + " " + hold2);
					System.out.println("Player's hand " + hand);
					System.out.println();
				}
				else {
					System.out.println("Out of bound hold.");
				}
			} catch(NumberFormatException e) {
				System.out.println("Invalid hold positions");
			}
		}
	}
	
	private void Advice() {
		
	}
	
	private void Statistics() {
		
	}
	
	public String deckSize() {
		return String.valueOf(deck.size());
	}
	
	public Deque<Card> drawCard(int numberOfDraws) {
		Deque<Card> deck = new LinkedList<Card>();
		for(int i = 0; i < numberOfDraws; i++) {
			try {
				deck.add(this.deck.removeFirst());
			} catch(NoSuchElementException  e) {
				System.out.println("Deck is empty");
			}
		}
		return new LinkedList<Card>(deck);
	}
	
	public String shuffleDeck() {
		List<Card> deck = new LinkedList<Card>(this.deck);
		Collections.shuffle(deck);
		this.deck = new ArrayDeque<Card>(deck);
		return this.deck.toString(); 
	}
}
