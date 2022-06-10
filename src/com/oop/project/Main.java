package com.oop.project;



//TODO: CHANGE PROJECT AUTHORS
public class Main {

	public static void main(String[] args) {
		
		// Machine 
		Machine machine = new Machine(new Deck());
		
		// Starts a deck with 54 (2 jokers included)
		Deck deck = new Deck();
		
		// Prints out the total number of cards
		System.out.println("Deck size is " + deck.size());
		
		// Prints out the deck
		System.out.println(deck);
		
		// Removes one joker from the deck "FO"
		System.out.println(deck.remove( new String[]{"FO"}));
		
		// Prints out the total number of cards
		System.out.println(deck.size());
		
		// Remove a foul card from the deck
		System.out.println(deck.remove( new String[] {"OF"}));
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Removes the seconds joker from the deck
		System.out.println(deck.remove( new String[] {"OL"} ));
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Adds an existing card to the deck (it can't the size of the deck won't change)
		System.out.println(deck.add( new String[] {"AC"} ));
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Re-adds two jokers to the end of the deck
		System.out.println(deck.add( new String[] {"OL","FO"} ));
		
		// Prints out the deck
		System.out.println(deck);
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Checks if deck has added cards
		System.out.println(deck.has(new String[] {"FO","OL"}));
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Checks if deck has added cards in another order and with extra
		System.out.println(deck.has(new String[] {"FO","OL","FO"}));
				
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Checks if deck has added cards in another order and with extra foul card in the middle
		System.out.println(deck.has(new String[] {"FO","FOUL CARD","FO"}));
						
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Removes all jokers from the deck
		System.out.println(deck.remove(new String[] {"FO","OL"}));
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Removes all jokers from the deck
		System.out.println(deck.shuffle());
		
		// Prints out the total number of cards from the deck
		System.out.println(deck.size());
		
		// Adds the same joker 6 times and shuffle (it adds only one copy)
		System.out.println("After first shuffle (" + deck.size() + ")");
		System.out.println(deck);
		System.out.println(deck.size());
		
		// Starts adding the same joker
		System.out.println(deck.add( new String[]{"FO","FO","FO"}));
		System.out.println(deck.size());

		// displays deck after shuffle
		System.out.println("Deck after added joker and third shuffle (" + deck.size() + ")");
		System.out.println(deck.shuffle());
		System.out.println(deck.size());
		
		
		// Shuffles internal deck
		System.out.println(machine.shuffleDeck());
		
		// Display deck
		System.out.println(machine.deck());
		
		// Removes old deck and starts a new deck
		System.out.println(machine.newDeck());
		
		// Draws a card and shows current deck
		System.out.println(machine.drawCard(1));
		System.out.println(machine.deck());
		
	}
}
