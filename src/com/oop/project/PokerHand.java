package com.oop.project;

import java.util.Arrays;
import java.util.List;

public class PokerHand {
	
	private final static int handSize = 5;
	private final List<Card> hand = Arrays.asList(new Card[handSize]);
	
	PokerHand() {
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
		return false;
	}
	
	private boolean twoPair() {
		return false;
	}
	
	private boolean threeOfAKind() {
		return false;
	}
	
	private boolean straight() {
		return false;
	}
	
	private boolean flush() {
		return false;
	}
	
	private boolean fullHouse() {
		return false;
	}
	
	private boolean fourOfAKind() {
		return false;
	}
	
	private boolean straightFlush() {
		return false;
	}
	
	private boolean royalFlush() {
		return false;
	}
}