package com.oop.project;

enum Suit {
	// 4 suits + 2 jokers
	CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S'),
	// Black and white joker (fOol)
	BWJOKER('O'),
	// Colored joker (fooL)
	CJOKER('L');
	
	
	
	private final char suit;
	

	
	// CONSTRUCTOR
	Suit(char suit) {
		this.suit = suit;
	}
	
	// Check if the current enum set as an element with equal value
	public static Suit belong(char value) {
		switch(value) {
			case 'C':
				return Suit.CLUBS;
			case 'D':
				return Suit.DIAMONDS;
			case 'H':
				return Suit.HEARTS;
			case 'S':
				return Suit.SPADES;
			case 'O':
				return Suit.BWJOKER;
			case 'L':
				return Suit.CJOKER;
			default:
				return null;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(suit);
	}
}