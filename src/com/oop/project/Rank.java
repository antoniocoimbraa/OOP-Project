package com.oop.project;


enum Rank {
	// 13 ranks + 2 jokers
	ACE('A'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),
	EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'),
	// Black and white joker (Fool)
	BWJOKER('F'),
	// Colored joker (foOl)
	CJOKER('O');
	
	
	
	private final char rank;
	
	
	
	// CONSTRUCTOR
	Rank(char rank){
		this.rank = rank;
	}
	
	// Check if the current enum set as an element with equal value
	public static Rank belong(char element) {
		switch(element) {
			case 'A': 
				return Rank.ACE;
			case '2':
				return Rank.TWO;
			case '3':
				return Rank.THREE;
			case '4':
				return Rank.FOUR;
			case '5':
				return Rank.FIVE;
			case '6':
				return Rank.SIX;
			case '7':
				return Rank.SEVEN;
			case '8':
				return Rank.EIGHT;
			case '9':
				return Rank.NINE;
			case 'T':
				return Rank.TEN;
			case 'J':
				return Rank.JACK;
			case 'Q':
				return Rank.QUEEN;
			case 'K':
				return Rank.KING;
			case 'F':
				return Rank.BWJOKER;
			case 'O':
				return Rank.CJOKER;
			default:
				return null;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(rank);
	}	
}