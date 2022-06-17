package com.oop.project;

enum Play {
	JACKSORBETTER("JACKS OR BETTER"),TWOPAIR("TWO PAIR"),THREEOFAKIND("THREE OF A KIND"),
	STRAIGHT("STRAIGHT"), FLUSH("FLUSH"), FULLHOUSE("FULL HOUSE"),
	FOUROFAKIND("FOUR OF A KIND"), STRAIGHTFLUSH("STRAIGHT FLUSH"),
	ROYALFLUSH("ROYAL FLUSH"), OTHER("OTHER");
	
	private final String play;
	
	Play(String play) {
		this.play = play;
	}
	
	public String getPlay() {
		return play;
	}
}
