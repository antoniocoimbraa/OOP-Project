package com.oop.project;

//Nested enum only make sense to cards
enum Suit {
	CLUBS("Clubs"), DIAMONDS("Diamonds"), HEARTS("Hearts"), SPADES("Spades"), 
	BJOKER("Black Joker"), RJOKER("Red Joker");
	
	private final String suit;
	
	Suit(String suit) {
		this.suit = suit;
	}
	
	public String toString() {
		return suit;
	}
}
