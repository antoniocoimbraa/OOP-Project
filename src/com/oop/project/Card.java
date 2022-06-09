package com.oop.project;

import java.util.Objects;


/**
 * 
 * @author Group 45
 *
 */

class Card {
	
	private final Rank rank;
	private final Suit suit;
	
	// by convention: 3C (rank, suit)
	// TODO: code for null case
	// TODO: add throw exception
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
	
	
	
	// Custom methods
	
	// Check if the card is valid
	public boolean valid(String card) {
		
		char[] rankSuit = card.toCharArray();
		
		// Checks the string for length 2
		boolean condLength = rankSuit.length == 2;
		
		// Checks if rank and suit are not null
		boolean condRankNotNull = Rank.belong(rankSuit[0]) != null;
		boolean condSuitNotNull = Suit.belong(rankSuit[1]) != null;
		
		// Checks for foul card (anything that has a joker) (e.g.): "FC", "OC or "FD"
		boolean condRankBWJOKER = Rank.belong('F') == Rank.BWJOKER;
		boolean condSuitBWJOKER = Suit.belong('O') == Suit.BWJOKER;
		boolean condRankCJOKER = Rank.belong('O') == Rank.CJOKER;
		boolean condSuitCJOKER = Suit.belong('L') == Suit.CJOKER;
	
		
		if(condLength)
			// Checks for valid chars for cards 
			if(!condRankNotNull && !condSuitNotNull)
				// True for cards "FO"
				if(condRankBWJOKER && condSuitBWJOKER)
					return true;
				// True for cards "OL"
				if(condRankCJOKER && condSuitCJOKER)
					return true;
				// True if card has no joker chars (e.g.) "F*", "*O" or "FO"
				if(!condRankBWJOKER && !condSuitBWJOKER)
					if(!condRankCJOKER && !condRankCJOKER)
						return true;
			
		return false;
	}
}