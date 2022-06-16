package com.oop.project;

import java.util.HashMap;

enum Suit {
	CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");
	
	private final String suit;
	private static final HashMap<String,Suit> map;
	
	Suit(String suit) {
		this.suit = suit;
	}
	
	public String getSuit() {
		return suit;
	}
		
	static {
		map = new HashMap<String,Suit>();
	
		for(Suit s:Suit.values())
			try {
				map.put(s.getSuit(),s);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static Suit getConstant(String constant) {
		return map.get(constant);
	}
}