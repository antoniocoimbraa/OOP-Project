package com.oop.project;


//Nested enum only make sense to cards
enum Suit {
	
	
	CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S"), 
	BJOKER("BJ"), RJOKER("RJ");
	
	
	private final String suit;
	
	
	Suit(String suit) {
		this.suit = suit;
	}
	
	
	public String toString() {
		return suit;
	}
}
