package com.oop.project;

import java.util.Arrays;
import java.util.List;

public class PokerHand {
	
	private final static int handSize = 5;
	private final List<Card> hand = Arrays.asList(new Card[handSize]);

	PokerHand(Object[] cards) {
		int i = 0;
		for(i = 0; i < 5; i++)
			hand.set(i,Card.class.cast(cards[i]));
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	// Holds one card
	public PokerHand hold(int pos, Object[] cards) {
		Card card = null;
		
		for(Object obj:cards)
			card = Card.class.cast(obj);
		
		try {
			hand.set(pos-1, card);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("PokerHand:set:Invalid position");
		}
		return new PokerHand(hand.toArray());
	}
	
	@Override
	public String toString() {
		String result = "";
		for(Card card:hand)
			result = result + card + " "; 
		return result;
	}
}