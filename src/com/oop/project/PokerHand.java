package com.oop.project;

import java.util.Arrays;
import java.util.List;

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
	
	public void set(int pos, Card card) {
		try {
			hand.set(pos-1, card);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("PokerHand:set:Invalid position");
		}
	}
	
	public void check() {
		
	}
}