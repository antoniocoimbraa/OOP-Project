package com.oop.project;


enum Rank {
	// 13 ranks + 2 jokers
	ACE('A'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),
	EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'),
	// Black joker
	BLACKJOKER('B'),
	// Colored joker
	COLOREDJOKER('C');
	
	private char rank;
	
	Rank(char rank){
		this.rank = rank;
	}
	
	public static boolean check(char input) {
		switch(input) {
			case 'A':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case 'T':
			case 'J':
			case 'Q':
			case 'K':
			case 'B':
			case 'C':
				return true;
			default:
				return false;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(rank);
	}	
}
