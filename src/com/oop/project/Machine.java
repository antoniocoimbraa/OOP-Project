package com.oop.project;

import java.io.File;
import java.io.FileNotFoundException;
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
	
	private final int balance = credit;
	
	// Stores list of cards
	private Deque<Card> deck = new LinkedList<>();
	
	// Store the current hand being played
	private PokerHand hand;
	
	private final Statistics statistics = new Statistics(balance);
	
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
		
		play();
	}
	
	private void Simulation(String cmdEntry1, String cmdEntry2, String cmdEntry3) {
		this.bet = Integer.valueOf(cmdEntry2);
		this.cardFile = "(no file needed for simulation mode)";
		this.commandFile = "(no file neede for simulation mode)";
		this.deck = Card.newDeck();
		this.nbdeals = Integer.valueOf(cmdEntry3);
		
		while(nbdeals != 0 && credit != 0) {
			deck = Card.newDeck();
			deck = shuffleDeck(deck);
			hand = new PokerHand(drawCard(5).toArray());
			Play play = Play.check(hand.getHand().toArray());
			Advice adv = new Advice(hand);
			System.out.println(adv.whatsAdvised());
			statistics.sum(play);
			credit = credit - bet;
			nbdeals--;
		}
		
		System.out.println("(Simulation mode)\n");
		System.out.println(statistics);
		System.out.println("(End of simulation mode)");
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
	
	private void play() {
		int i = 0;
		int index = 0;
		boolean bet = false;
		boolean deal = false;
		boolean hold = false;
		
		Integer num = 0;
		Command cmdAux = null;
		
		Play play = null;
		
		boolean[] hld = new boolean[] {false,false,false,false,false};
		
		for(String command:feed) {
			index++;
			cmdAux = Command.getConstant(command);
			
			if(cmdAux != null) {
				if(cmdAux.equals(Command.BET)) {
					if(bet) {
						this.credit = this.credit - this.bet;
						System.out.println("");
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						for(i = 0; i < 5; i++) {
							if(hld[i])
								System.out.print(" " + (i+1));
							if(!hld[i])
								hand = hand.hold(i+1, drawCard(1).toArray());
						}
						System.out.println();
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							this.credit = this.credit - this.bet;
							this.credit = this.credit + this.bet * Bonus.bonus(hand);
							System.out.println("player wins with a " + 
									play + " and his credit is " + credit);
							System.out.println();
						}
						
						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					System.out.print("-cmd "+Command.BET.getCommand());
					bet = true;
				}
				if(cmdAux.equals(Command.CREDIT)) {
					if(bet) {
						//this.credit = this.credit - this.bet;
						System.out.println("");
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						for(i = 0; i < 5; i++) {
							if(hld[i])
								System.out.print(" " + (i+1));
							if(!hld[i])
								hand = hand.hold(i+1, drawCard(1).toArray());
						}
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						System.out.println("Player's hand " + hand.toString());
						
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							this.credit = this.credit - this.bet;
							this.credit = this.credit + this.bet * Bonus.bonus(hand);
							System.out.println("player wins with a " + 
									play + " and his credit is " + credit);
							System.out.println();
						}
						
						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					System.out.println("-cmd "+Command.CREDIT.getCommand());
					System.out.println("Player's credit is " + (this.credit - this.bet));
					System.out.println();
				}
				if(cmdAux.equals(Command.DEAL)) {
					if(bet) {
						//this.credit = this.credit - this.bet;
						System.out.println("");
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						for(i = 0; i < 5; i++) {
							if(hld[i])
								System.out.print(" " + (i+1));
							if(!hld[i])
								hand = hand.hold(i+1, drawCard(1).toArray());
						}
						System.out.println();
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							this.credit = this.credit - this.bet;
							this.credit = this.credit + this.bet * Bonus.bonus(hand);
							System.out.println("player wins with a " + 
									play + " and his credit is " + credit);
							System.out.println();
						}

						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					if(!deal) {
						// starts hand
						hand = new PokerHand(drawCard(5).toArray());
						
						System.out.println("-cmd " + Command.DEAL.getCommand());
						System.out.println("Player's hand is " + hand.toString());
						System.out.println();
						deal = true;
					} else {
						System.out.println(Command.DEAL.getCommand() + ": already dealt");
					}
				}
				
				if(cmdAux.equals(Command.HOLD)) {
					if(bet) {
						System.out.println("");
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						for(i = 0; i < 5; i++) {
							if(hld[i])
								System.out.print(" " + (i+1));
							if(!hld[i])
								hand = hand.hold(i+1, drawCard(1).toArray());
						}
						System.out.println();
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							this.credit = this.credit - this.bet;
							this.credit = this.credit + this.bet * Bonus.bonus(hand);
							System.out.println("player wins with a " + 
									play + " and his credit is " + credit);
							System.out.println();
						}

						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					if(deal) {
						if(!hold) {
							hold = true;
							System.out.println("-cmd " + Command.HOLD.getCommand());
						}
						else {
							System.out.println("-cmd " + Command.HOLD.getCommand());
							System.out.println(Command.HOLD.getCommand()+": illegal hold");
							System.out.println();
						}
					} else {
						System.out.println("-cmd " + Command.HOLD.getCommand());
						System.out.println(Command.HOLD.getCommand() + ": illegal hold");
						System.out.println();
					}
					
				}
				
				if(cmdAux.equals(Command.ADVICE)) {
					if(bet) {
						//this.credit = this.credit - this.bet;
						System.out.println("");
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						for(i = 0; i < 5; i++) {
							if(hld[i])
								System.out.print(" " + (i+1));
							if(!hld[i])
								hand = hand.hold(i+1, drawCard(1).toArray());
						}
						System.out.println();
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							this.credit = this.credit - this.bet;
							this.credit = this.credit + this.bet * Bonus.bonus(hand);
							System.out.println("player wins with a " + 
									play + " and his credit is " + credit);
							System.out.println();
						}

						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
				}
				
				if(cmdAux.equals(Command.STATISTICS)) {
					if(bet) {
						this.credit = this.credit - this.bet;
						System.out.println("");
						System.out.println("Player is betting " + this.bet);
						System.out.println("");
						bet = false;
					}
					
					if(deal && hold) {
						for(i = 0; i < 5; i++) {
							if(hld[i])
								System.out.print(" " + (i+1));
							if(!hld[i])
								hand = hand.hold(i+1, drawCard(1).toArray());
						}
						System.out.println();
						play = Play.check(hand.getHand().toArray());
						statistics.sum(play);
						
						System.out.println("Player's hand " + hand.toString());
						if(play.equals(Play.OTHER)) {
							this.credit = this.credit - this.bet;
							System.out.println("player loses and his credit is " + this.credit);
							System.out.println();
						}
						else {
							//TODO:
							this.credit = this.credit - this.bet;
							this.credit = this.credit + this.bet * Bonus.bonus(hand);
							System.out.println("player wins with a " + 
									play + " and his credit is " + credit);
							System.out.println();
						}
						
						hld = new boolean[] {false,false,false,false,false};
						deal = false;
						hold = false;
					}
					
					System.out.println("-cmd " + Command.STATISTICS.getCommand());
					System.out.println("Printing statistics...");
					statistics.setBalance(this.balance);
					statistics.setCredit(this.credit);
					System.out.println(statistics);
				}
			}
			else {
				try {
					num = Integer.valueOf(command);
					
					if(bet) {
						if(num > 0 && num < 6) {
							this.bet = num;
							System.out.println();
							System.out.println("Player is betting " + num);
							System.out.println("");
						}
						else {
							System.out.println(" " + num);
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
						
						if(feed.size() == index) {
							for(i = 0; i < 5; i++) {
								if(hld[i])
									System.out.print(" " + (i+1));
								if(!hld[i])
									hand = hand.hold(i+1, drawCard(1).toArray());
							}
							System.out.println();
							play = Play.check(hand.getHand().toArray());
							statistics.sum(play);
							
							System.out.println("Player's hand " + hand.toString());
							if(play.equals(Play.OTHER)) {
								this.credit = this.credit - this.bet;
								System.out.println("player loses and his credit is " + this.credit);
								System.out.println();
							}
							else {
								//TODO:
								Integer bonus = Bonus.bonus(hand);
								this.credit = this.credit - this.bet;
								this.credit = this.credit + this.bet * bonus;
								System.out.println("player wins with a " + 
										play + " and his credit is " + credit);
								System.out.println();
							}
							
							hld = new boolean[] {false,false,false,false,false};
							deal = false;
							hold = false;
						}
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
	
	public Deque<Card> shuffleDeck(Deque<Card> deck) {
		List<Card> sorted = new ArrayList<Card>(deck);
		Collections.shuffle(sorted);
		return new LinkedList<>(sorted); 
	}
}
