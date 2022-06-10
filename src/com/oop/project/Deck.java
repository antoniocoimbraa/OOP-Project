package com.oop.project;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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
	
	// Returns deck size
	public int size() {
		return deck.size();
	}
	
	// TODO: throw exception for add cards when card is not valid
	public String add(String[] cards) {
		LinkedHashSet<String> addedCards = new LinkedHashSet<>();
				
		for(String card:cards)
			// Checks for a valid card
			if(Card.valid(card))
				// If deck doesn't have the card, it adds it
				if(this.deck.add(new Card(card)))
					addedCards.add(card);
	
		return addedCards.toString();
	}
	
	// Draws a total number of cards from the deck
	// TODO: check draw method
	public String draw(int totalOfDraws) {
		
		LinkedHashSet<Card> drawnCards = new LinkedHashSet<>();
		Iterator<Card> itr = this.deck.iterator();
		int i = 0;
		
		for(i = 0; i < totalOfDraws && itr.hasNext(); i++)
			drawnCards.add(itr.next());
		
		this.deck.removeAll(drawnCards);
		
		return drawnCards.toString();
	}
	
	// Check if the deck has a card
	public String has(String[] cards) {
		LinkedList<String> matchedCards = new LinkedList<>();
		
		for(String card:cards)
			if(Card.valid(card))
				if(deck.contains(new Card(card)))
					matchedCards.add(card);
		
		return matchedCards.toString();
	}
	
	// Deck remove
	public String remove(String[] cards) {
		LinkedList<String> removedCards = new LinkedList<>();
		
		for(String card:cards)
			if(deck.remove(new Card(card)))
				removedCards.add(card);
		
		return removedCards.toString();
	}
	
	// Shuffles the deck
	public String shuffle() {
		List<Card> cardList = new ArrayList<Card>(this.deck);
		Collections.shuffle(cardList);
		this.deck = new LinkedHashSet<Card>(cardList);
		
		return this.deck.toString();
	}
	
	@Override
	public String toString() {
		return deck.toString();
	}
}