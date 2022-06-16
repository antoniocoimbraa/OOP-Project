package com.oop.project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokerHand {
	
	private final static int handSize = 5;
	private final List<Card> hand = Arrays.asList(new Card[handSize]);
	
	PokerHand() {
		// Default intialization
		hand.set(0, new Card(Rank.ACE,Suit.SPADES));
		hand.set(1, new Card(Rank.ACE,Suit.DIAMONDS));
		hand.set(2, new Card(Rank.ACE,Suit.CLUBS));
		hand.set(3, new Card(Rank.ACE,Suit.HEARTS));
		hand.set(4, new Card(Rank.QUEEN,Suit.CLUBS));
	}
	
	@Override
	public String toString() {
		String result = "";
		for(Card card:hand)
			result = result + card + " "; 
		return result;
	}
	
	public Card get(int pos) {
		return hand.get(pos);
	}
	
	public void set(int pos, Card card) {
		try {
			hand.set(pos-1, card);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("PokerHand:set:Invalid position");
		}
	}
	
	public void check() {
		
	}
	
	private boolean jackOrBetter() {
		int i = 0;
		int j = 0;
		
		int size = hand.size();

		int hits = 0;
		
		boolean[] result = {false, false, false, false};
		Set<Rank> ranks = new HashSet<>();

				
	}
	
	private boolean twoPair() {
		
	}
	
	private boolean threeOfAKind() {
		
	}
	
	private boolean straight() {
		
	}
	
	private boolean flush() {
		
	}
	
	private boolean fullHouse() {
		
	}
	
	private boolean fourOfAKind() {
		
	}
	
	private boolean straightFlush() {
		
	}
	
	private boolean royalFlush() {
		
	}
}