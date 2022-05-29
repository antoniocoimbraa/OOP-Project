package com.oop.project;

//Nested enum only make sense to cards
enum Rank {
	A("Ace"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
	EIGHT("8"), NINE("9"), TEN("10"), JACK("Jack"), QUEEN("Queen"), KING("King"), 
	BJOKER("Black Joker"), RJOKER("Red Joker");
	
	private String rank;
	
	// Enum suit constructor is implicitly private
	Rank(String rank){
		this.rank = rank;
	}
	
	public String toString() {
		return rank;
	}	
}
