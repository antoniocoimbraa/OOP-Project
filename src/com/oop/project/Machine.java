package com.oop.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Machine {
	
	// store the total number of bets
	private int bet = 5;
	
	// Stores the name of the file that contains the cards for debug mode
	private String cardFile;

	// Stores commands
	private List<String> feed = null;
	
	// Stores the name the file that contains the commands for debug mode
	private String commandFile;
	
	// Store the total amount of credits available to the player.
	private int credit = 10000;
	
	// Stores list of cards
	private Deque<Card> deck = new LinkedList<>();
	
	// 
	private PokerHand hand;
	
	private final Statistics statistics = new Statistics();
	
	// Stores game mode type. (d)debug mode or (s)simulation mode.
	private char mode;
	
	// Total number of deals
	private int nbdeals = 0;
	
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
		this.cardFile = cmdEntry3;
		String[] cmds = parse(cmdEntry2);
		feed = new ArrayList<>(cmds.length);
		for(String cmd:cmds)
			feed.add(cmd);
		this.commandFile = cmdEntry2;
		this.deck = Card.newDeck(parse(cmdEntry3));
	}
	
	private void Simulation(String cmdEntry1, String cmdEntry2, String cmdEntry3) {
		this.bet = Integer.valueOf(cmdEntry2);
		this.cardFile = "(no file needed for simulation mode)";
		this.commandFile = "(no file neede for simulation mode)";
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
		int i = 0;
		boolean bet = false;
		boolean deal = false;
		boolean hold = false;
		
		Integer num = 0;
		Command cmdAux = null;
		
		Play play = null;
		
		boolean[] hld = new boolean[] {false,false,false,false,false};
		
		for(String command:feed) {
			cmdAux = Command.getConstant(command);
			
			if(cmdAux != null) {
				if(cmdAux.equals(Command.BET)) {
					if(bet) {
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						for(i = 0; i < 5; i++)
							if(hld[i])
								System.out.print(" " + (i+1));
						System.out.println();
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							System.out.println("player wins with a " + 
									play + " and his credit is" + credit);
						}
						
						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					System.out.println("-cmd "+Command.BET.getCommand());
					bet = true;
				}
				if(cmdAux.equals(Command.CREDIT)) {
					if(bet) {
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						for(i = 0; i < 5; i++)
							if(hld[i])
								System.out.print(" " + (i+1));
						System.out.println();

						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							System.out.println("player wins with a " + 
									play + " and his credit is" + credit);
						}
						
						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
				}
				if(cmdAux.equals(Command.DEAL)) {
					if(bet) {
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						for(i = 0; i < 5; i++)
							if(hld[i])
								System.out.print(" " + (i+1));
						System.out.println();
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							System.out.println("player wins with a " + 
									play + " and his credit is" + credit);
						}

						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					if(!deal) {
						// starts hand
						hand = new PokerHand(drawCard(5).toArray());
						deal = true;
					} else {
						System.out.println(Command.DEAL.getCommand() + ": already dealt");
					}
				}
				if(cmdAux.equals(Command.HOLD)) {
					if(bet) {
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						for(i = 0; i < 5; i++)
							if(hld[i])
								System.out.print(" " + (i+1));
						System.out.println();
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							System.out.println("player wins with a " + 
									play + " and his credit is" + credit);
						}

						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					if(deal) {
						if(!hold) {
							hold = true;
							System.out.print("-cmd " + Command.HOLD.getCommand());
						}
						else {
							System.out.println(Command.HOLD.getCommand()+": illegal hold");
						}
					} else {
						System.out.println(Command.HOLD.getCommand() + ": illegal hold");
					}
					
				}
				if(cmdAux.equals(Command.ADVICE)) {
					if(bet) {
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						for(i = 0; i < 5; i++)
							if(hld[i])
								System.out.print(" " + (i+1));
						System.out.println();
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							System.out.println("player wins with a " + 
									play + " and his credit is" + credit);
						}

						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
				}
				if(cmdAux.equals(Command.STATISTICS)) {
					if(bet) {
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						for(i = 0; i < 5; i++)
							if(hld[i])
								System.out.print(" " + (i+1));
						System.out.println();
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							System.out.println("player wins with a " + 
									play + " and his credit is" + credit);
						}
						
						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
				}
			}
			else {
				try {
					num = Integer.valueOf(command);
					
					if(bet) {
						if(num > 0 && num < 6) {
							this.bet = num;
							System.out.println("Player is betting " + num);
							System.out.println("");	
						}
						else {
							System.out.println(Command.BET.getCommand() + ": illegal amount");
							System.out.println("");
						}
						bet = false;
					} 
					if(deal && hold) {
						if(num > 0 && num < 6) {
							hld[num - 1] = true;
						}
						else {
							System.out.println(num + " :Out of range");
							System.out.println("");
						}
					} else {
						System.out.println("Invalid input");
					}
					
				} catch(NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
		}
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
