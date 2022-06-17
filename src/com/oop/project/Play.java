package com.oop.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Play {
	OTHER("OTHER"), JACKSORBETTER("JACKS OR BETTER"),TWOPAIR("TWO PAIR"),THREEOFAKIND("THREE OF A KIND"),
	STRAIGHT("STRAIGHT"), FLUSH("FLUSH"), FULLHOUSE("FULL HOUSE"),
	FOUROFAKIND("FOUR OF A KIND"), STRAIGHTFLUSH("STRAIGHT FLUSH"),
	ROYALFLUSH("ROYAL FLUSH");
	
	private final String play;
	private static Map<Rank,Integer> rankHits = new HashMap<Rank,Integer>();
	private static Map<Suit,Integer> suitHits = new HashMap<Suit,Integer>();
	
	Play(String play) {
		this.play = play;
	}
	
	public String getPlay() {
		return play;
	}
	
	private static void hits(Object[] cards) {
		Integer countRank = null;
		Integer countSuit = null;
		Rank rank = null;
		Suit suit = null;
		Card card = null;
		rankHits = new HashMap<Rank,Integer>();
		suitHits = new HashMap<Suit,Integer>();
		
		for(Object obj:cards) {
			card = Card.class.cast(obj);
			rank = card.getRank();
			suit = card.getSuit();
			countRank = rankHits.get(rank);
			countSuit = suitHits.get(suit);
			
			if(countRank == null)
				rankHits.put(rank,1);
			else
				rankHits.put(rank, ++countRank);
			
			if(countSuit == null)
				suitHits.put(suit,1);
			else
				suitHits.put(suit, ++countSuit);
		}
	}
	
	
	private static boolean jackOrBetter(Object[] cards) {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(Rank.JACK.compareTo(entry.getKey()) <= 0)
				if(entry.getValue() == 2)
					return true;
		return false;
	}
	
	private static boolean flush(Object[] cards) {
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 5)
				return true;
		return false;
	}
	
	private static boolean fourOfAKind(Object[] cards) {		
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 4)
				return true;
		return false;
	}
	
	private static boolean fullHouse(Object[] cards) {
		boolean pair = false;
		boolean threes = false;
		
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet()) {
			if(entry.getValue() == 2) pair = true;
			if(entry.getValue() == 3) threes = true;
		}
		return pair && threes;
	}
	
	private static boolean royalFlush(Object[] cards) {
		List<Card> sortedList = new ArrayList<>();
		for(Object obj:cards)
			sortedList.add(Card.class.cast(obj));
		Collections.sort(sortedList);
		Card card = null;
		Card nextCard = null;
		int listSize = sortedList.size();
		int i = 0;
		
		for(i = 0; i < listSize - 1; i++) {
			card = sortedList.get(i);
			nextCard = sortedList.get(i+1);
			if(!card.isNeighbour(nextCard))
				return false;
		}

		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(Rank.TEN.compareTo(entry.getKey()) > 0)
				return false;
		
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 5) 
				return true;
		return false;
	}
	
	private static boolean straigth(Object[] cards) {
		List<Card> sortedList = new ArrayList<>();
		for(Object obj:cards)
			sortedList.add(Card.class.cast(obj));
		Collections.sort(sortedList);
		Card card = null;
		Card nextCard = null;
		int listSize = sortedList.size();
		int i = 0;
		
		for(i = 0; i < listSize - 1; i++) {
			card = sortedList.get(i);
			nextCard = sortedList.get(i+1);
			if(!card.isNeighbour(nextCard))
				return false;
		}
		return true;
	}
	
	private static boolean straightFlush(Object[] cards) {
		List<Card> sortedList = new ArrayList<>();
		for(Object obj:cards)
			sortedList.add(Card.class.cast(obj));
		Collections.sort(sortedList);
		Card card = null;
		Card nextCard = null;
		int listSize = sortedList.size();
		int i = 0;
		
		for(i = 0; i < listSize - 1; i++) {
			card = sortedList.get(i);
			nextCard = sortedList.get(i+1);
			if(!card.isNeighbour(nextCard))
				return false;
		}
		
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 5)
				return true;
		return false;
	}
	
	private static boolean threeOfAKind(Object[] cards) {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 3)
				return true;
		return false;
	}
	
	private static boolean twoPair(Object[] cards) {
		int totPairs = 0;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 2)
			totPairs++;
		return totPairs>1;
	}
	
	public static Play check(Object[] cards) {
		hits(cards);
		
		if(royalFlush(cards))
			return Play.ROYALFLUSH;
		if(straightFlush(cards))
			return Play.STRAIGHTFLUSH;
		if(fourOfAKind(cards))
			return Play.FOUROFAKIND;
		if(fullHouse(cards))
			return Play.FULLHOUSE;
		if(flush(cards))
			return Play.FLUSH;
		if(straigth(cards))
			return Play.STRAIGHT;
		if(threeOfAKind(cards))
			return Play.THREEOFAKIND;
		if(twoPair(cards))
			return Play.TWOPAIR;
		if(jackOrBetter(cards))
			return Play.JACKSORBETTER;
		return Play.OTHER;
	}
	
	@Override
	public String toString() {
		return play;
	}
}
