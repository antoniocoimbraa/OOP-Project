package com.oop.project;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

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
	
	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
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
				deck.addLast(new Card(rank,suit));
	}
	
	public boolean isNeighbour(Card card) {
		if(card.rank.ordinal() + 1 == rank.ordinal())
			return true;
		if(card.rank.ordinal() == rank.ordinal() + 1)
			return true;
		return false;
	}
	
	public boolean isPair(Card card) {
		if(card.compareTo(card) == 0)
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

	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}
}