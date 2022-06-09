package com.oop.project;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

// TODO: can we make DECK an interface?
class Deck {
	
	
	// It stores the current deck
	private LinkedHashSet<Card> deck;
	
	
	// TODO: 
	Deck() {
		deck = new LinkedHashSet<>();
		
		// Adds all cards aside from jokers
		for(Rank rank: Rank.values())
			for(Suit suit: Suit.values())
				if(!rank.equals(Rank.BWJOKER) && !rank.equals(Rank.CJOKER))
					if(!suit.equals(Suit.BWJOKER) && !suit.equals(Suit.CJOKER))
						deck.add(new Card(rank.toString()+suit.toString()));
		
		// Adds two jokers (fool) to the deck.
		deck.add(new Card("FO")); // black and white joker
		deck.add(new Card("OL")); // colored joker
	}
	
	// Getter
	public Set<Card> getDeck() {
		return deck;
	}
	
	// Returns the total number of cards in the deck
	public int size() {
		return deck.size();
	}
	
	// TODO: throw exception for add cards when card is not valid
	public String add(String[] cards) {
		
		LinkedList<String> addedCards = new LinkedList<>();
				
		for(String card:cards)
			// Checks for a valid card
			if(Card.valid(card)) {
				deck.add(new Card(card));
				addedCards.add(card);
			}
			else
				System.out.println("deck.java:add: Invalid card!");
	
		return addedCards.toString();
	}
	
	// Check if the deck has a card
	public String has(String[] cards) {
		
		LinkedList<String> matchedCards = new LinkedList<>();
		
		for(String card:cards) {
			if(deck.contains(card))
				matchedCards.add(card);
		
			
		return matchedCards.toString();
	}
	
	
	public Set<Card> removeCards(Card[] cards) {
		// Removes cards from the deck. Return the cards added.
		// TODO: change name from removeCards to remove
		// TODO: change argument type from Card[] to String[]
		
		
		Set<Card> removedCards = new HashSet<>();
		
		for(Card card:cards)
			if(deck.remove(card))
				removedCards.add(card);
		
		return removedCards;
	}
	
	// Draws one card 
	private Card draw() {
		Iterator<Card> itr = deck.iterator();
		Random random = new Random();
		int randNum = random.nextInt(deck.size());
		int i = 0;
		
		for(i = 0; i < randNum; i++)
			itr.next();
		
		return itr.next();
	}
	
	// Draws and removes cards from the deck.
	public Set<Card> drawRandomCards(int total) {
		Set<Card> drawnCards = new HashSet<>();
		Card card = null;
		int i = 0;
		
		for(i = 0; i < total; i++) {
			card = draw();
			drawnCards.add(card);
			deck.remove(card);
		}
		
		return drawnCards;
	}
	
	@Override
	public String toString() {
		return deck.toString();
	}
}