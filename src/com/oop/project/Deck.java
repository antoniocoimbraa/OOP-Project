package com.oop.project;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

class Deck {
	
	private Set<Card> deck;

	Deck() {	
		deck = new HashSet<>();
		
		// Adds all cards aside from jokers
		for(Rank rank: Rank.values()) {
			for(Suit suit: Suit.values()) {
				
				// The jokers are added later. Otherwise with for cycles we get more than 2.
				if(!rank.equals(Rank.BJOKER) && !rank.equals(Rank.RJOKER))
					if(!suit.equals(Suit.BJOKER) && !suit.equals(Suit.RJOKER))
						deck.add(new Card(rank,suit));
			}
		}
		
		// Adds two jokers. One black and one red.
		deck.add(new Card(Rank.BJOKER,Suit.BJOKER));
		deck.add(new Card(Rank.RJOKER,Suit.RJOKER));
	}
	// Deck methods
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
		
		Set<Card> listOfCards = new HashSet<>();
		
		for(Card card:cards) {
			if(deck.contains(card))
				listOfCards.add(card);
		}
		
		return listOfCards;
	}
	// Removes cards from the deck. Return the cards added.
	public Set<Card> removeCards(Card[] cards) {
		
		Set<Card> listOfCardsAdded = new HashSet<>();
		
		for(Card card:cards)
			if(deck.remove(card))
				listOfCardsAdded.add(card);
		
		return listOfCardsAdded;
	}
	
	// Draws one card.
	private Card draw() {
		Iterator<Card> itr = deck.iterator();
		Random random = new Random();
		int randNum = random.nextInt(deck.size());
		int i = 0; // variable used in for cycle to iterate through the deck
		
		for(i = 0; i < randNum; i++)
			itr.next();
		
		return itr.next();
	}
	// Draws and removes cards from the deck.
	public Set<Card> drawCards(int total) {
		Set<Card> cards = new HashSet<>();
		Card card = null;
		int i = 0;
		
		for(i = 0; i < total; i++) {
			card = draw();
			cards.add(card);
			deck.remove(card);
		}
		
		return cards;
	}
	
	@Override
	public String toString() {
		return deck.toString();
	}
}