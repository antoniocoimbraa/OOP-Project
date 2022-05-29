package com.oop.project;

import java.util.Objects;

class Card {

	private final Rank rank;
	private final Suit suit;
	
	Card (Rank rank, Suit suit) {
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
	public String toString() {
		if(rank == Rank.BJOKER || rank == Rank.RJOKER)
			return rank + "";
		else
			return rank + " of " + suit;
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