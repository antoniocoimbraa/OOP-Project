package com.oop.project;

public enum Pay {
	ROYALFLUSH(250),STRAIGHTFLUSH(50),FOURACES(160),FOUR24(80),FOUR5K(50),
	FULLHOUSE(10),FLUSH(7),STRAIGHT(5),THREEOFAKIND(3),TWOPAIR(1),JACKSORBETTER(1);
	
	private final Integer multiplier;
	
	Pay(Integer multiplier) {
		this.multiplier = multiplier;
	}
}
