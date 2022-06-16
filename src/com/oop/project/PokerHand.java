package com.oop.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHand {
	
	private final static int handSize = 5;
	private final static List<Card> hand = Arrays.asList(new Card[handSize]);
	private final Map<Rank,Integer> rankHits = new HashMap<Rank,Integer>();
	private final Map<Suit,Integer> suitHits = new HashMap<Suit,Integer>();
	
	PokerHand(Object[] cards) {
		int i = 0;
		Integer countRank = null;
		Integer countSuit = null;
		Rank rank = null;
		Suit suit = null;
	
		for(i = 0; i < 5; i++)
			hand.set(i,Card.class.cast(cards[i]));
		
		for(Card card:hand) {
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

	public Map<Rank,Integer> getRankHits() {
		return rankHits;
	}
	
	public Map<Suit,Integer> getSuitHits() {
		return suitHits;
	}
	
	@Override
	public String toString() {
		String result = "";
		for(Card card:hand)
			result = result + card + " "; 
		return result;
	}
	
	public int[] check() {
		int[] hand = new int[9];
		if(jackOrBetter()) hand[0] = 1;
		if(twoPair()) hand[1] = 1;
		if(threeOfAKind()) hand[2] = 1;
		if(straigth()) hand[3] = 1;
		if(flush()) hand[4] = 1;
		if(fullHouse()) hand[5] = 1;
		if(fourOfAKind()) hand[6] = 1;
		if(straightFlush()) hand[7] = 1;
		if(royalFlush()) hand[8] = 1;
		return hand;
	}
	
	// Holds one card
	public void hold(int pos, Card newCard) {
		Card oldCard = null;
		try {
			oldCard = hand.set(pos-1, newCard);
			resetHits(oldCard, newCard);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("PokerHand:set:Invalid position");
		}
	}
	
	// Peeks one card
	public Card peek(int pos) {
		return hand.get(pos);
	}
	
	private void resetHits(Card oldCard, Card newCard) {
		Rank oldRank = oldCard.getRank();
		Suit oldSuit = oldCard.getSuit();
		Rank newRank = newCard.getRank();
		Suit newSuit = newCard.getSuit();
		Integer count = null;
		
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet()) {
			if(entry.getKey() == oldRank) {
				count = entry.getValue();
				rankHits.put(oldRank, --count);
			}
		}
		count = rankHits.get(newRank);
		if(count == null)
			rankHits.put(newRank,1);
		else
			rankHits.put(newRank, ++count);
		
		count = null;
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet()) {
			if(entry.getKey() == oldSuit) {
				count = entry.getValue();
				suitHits.put(oldSuit, --count);
			}
		}
		count = suitHits.get(newSuit);
		if(count == null)
			suitHits.put(newSuit,1);
		else
			suitHits.put(newSuit, ++count);
	}
	
	private boolean flush() {
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 5)
				return true;
		return false;
	}
	
	private boolean fourOfAKind() {		
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 4)
				return true;
		
		return false;
	}
	
	private boolean fullHouse() {
		boolean pair = false;
		boolean threes = false;
		
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet()) {
			if(entry.getValue() == 2) pair = true;
			if(entry.getValue() == 3) threes = true;
		}
				
		return pair && threes;
	}
	
	private boolean jackOrBetter() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(Rank.JACK.compareTo(entry.getKey()) <= 0)
				if(entry.getValue() == 2)
					return true;
		return false;
	}
	
	private boolean royalFlush() {
		List<Card> sortedList = new ArrayList<>(hand);
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
	
	private boolean straigth() {
		List<Card> sortedList = new ArrayList<>(hand);
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
	
	private boolean straightFlush() {
		List<Card> sortedList = new ArrayList<>(hand);
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
	
	private boolean threeOfAKind() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 3)
				return true;
		return false;
	}
	
	private boolean twoPair() {
		int totPairs = 0;
		
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 2)
			totPairs++;
		return totPairs>2;
	}
}