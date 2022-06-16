package com.oop.project;

import java.util.ArrayDeque;
import java.util.Deque;

class Card implements Comparable<Card> {
	
	private final Rank rank;
	private final Suit suit;
	
	private static final Deque<Card> deck = new ArrayDeque<Card>();
	
	Card (Rank rank, Suit suit) {
        if (rank == null || suit == null)
            throw new NullPointerException(rank + ", " + suit);
        this.rank = rank;
        this.suit = suit;
	}
	
	@Override
    public int compareTo(Card c) {
		return rank.compareTo(c.rank);
    }

	@Override
	public String toString() {
		return rank.getRank() + suit.getSuit();
	}
	
	static {
		for(Suit suit: Suit.values())
			for(Rank rank: Rank.values())
				if(!rank.equals(Rank.BWJOKER) && !rank.equals(Rank.CJOKER))
					if(!suit.equals(Suit.BWJOKER) && !suit.equals(Suit.CJOKER))
						deck.addLast(new Card(rank,suit));
	}
	
	public boolean isNeighbour(Card card) {
		if(card.rank.ordinal() + 1 == rank.ordinal())
			return true;
		if(card.rank.ordinal() == rank.ordinal())
			return true;
		return false;
	}
	
	public boolean isSuit(Card card) {
		if(suit.compareTo(card.suit) == 0)
			return true;
		return false;
	}
		
	public static Deque<Card> newDeck() {
		return new ArrayDeque<Card>(deck);
	}
	
	public static Deque<Card> newDeck(String[] cards) {
		Deque<Card> deck = new ArrayDeque<Card>();
		
		for(String card:cards) {
			try {
				String srank = String.valueOf(card.charAt(0));
				String ssuit = String.valueOf(card.charAt(1));
				Rank rank = Rank.getConstant(srank);
				Suit suit = Suit.getConstant(ssuit);
				deck.addLast(new Card(rank,suit));
			} catch(NullPointerException | IndexOutOfBoundsException e) {
				System.out.println("Invalid card: " + card + " (didn't add it).");
			}
		}
		
		return new ArrayDeque<Card>(deck);
	}
}