package com.oop.project;

import java.util.Objects;

class Card {
	
	private final Rank rank;
	private final Suit suit;
	
	// by convention: 3C (rank, suit)
	Card (String card) {
		// by convention: rankSuit[0] = rank, rankSuit[1] = suit 
		char[] rankSuit = card.toCharArray();

		this.rank = Rank.belong(rankSuit[0]);
		this.suit = Suit.belong(rankSuit[1]);
	}
	
	
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	
	
	@Override
	public String toString() {
		return rank.toString() + suit.toString();
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