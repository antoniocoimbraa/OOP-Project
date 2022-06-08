package com.oop.project;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


class Deck {
	
	// It stores the current deck?
	private Set<Card> deck;
	
	Deck() {
		deck = new HashSet<>();
		
		// Adds all cards aside from jokers
		for(Rank rank: Rank.values())
			for(Suit suit: Suit.values())
				if(!rank.equals(Rank.BWJOKER) && !rank.equals(Rank.CJOKER))
					if(!suit.equals(Suit.BWJOKER) && !suit.equals(Suit.CJOKER))
						deck.add(new Card(rank.toString()+suit.toString()));
		
		// Adds two jokers (fool) to the deck.
		deck.add(new Card("FO"));
		deck.add(new Card("OL"));
	}
	
	// Getter
	public Set<Card> getDeck() {
		return deck;
	}
	
	// Returns the total number of cards in the deck
	public int countCards() {
		return deck.size();
	}
	
	// Returns the added cards
	public Set<Card> addCards(Card[] cards) {
		Set<Card> addedCards = new HashSet<>();
		
		for(Card card:cards)
			if(deck.add(card))
				addedCards.add(card);
	
		return addedCards;
	}
	
	// Check if the deck has a card
	public Set<Card> hasCards(Card[] cards) {
		Set<Card> hasCards = new HashSet<>();
		
		for(Card card:cards) {
			if(deck.contains(card))
				hasCards.add(card);
		}
		
		return hasCards;
	}
	
	// Removes cards from the deck. Return the cards added.
	public Set<Card> removeCards(Card[] cards) {
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