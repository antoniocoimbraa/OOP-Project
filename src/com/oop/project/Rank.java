package com.oop.project;

import java.util.HashMap;

enum Rank {
	TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
	NINE("9"), TEN("T"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");
	 
	private final String rank;
	private static final HashMap<String,Rank> map;

	Rank(String rank) {
		this.rank = rank;
	}
	
	public String getRank() {
		return rank;
	}
		
	static {
		map = new HashMap<String,Rank>();
	
		for(Rank r:Rank.values())
			try {
				map.put(r.getRank(),r);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static Rank getConstant(String constant) {
		return map.get(constant);
	}
}