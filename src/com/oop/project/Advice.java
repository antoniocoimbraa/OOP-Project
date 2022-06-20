package com.oop.project;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Advice {
	private final static int handSize = 5;
	private final List<Card> hand = new LinkedList<>();
	private final static Map<Rank,Integer> rankHits = new HashMap<>();
	private final static Map<Suit,Integer> suitHits = new HashMap<>();
	
	Advice(PokerHand pokerhand) {
		List<Card> hand = new LinkedList<>();
		hand = pokerhand.getHand();
		Collections.sort(hand);
		Object[] sorted = hand.toArray();

		// initializes hand array
		for(Object obj:sorted) {
			Card card = Card.class.cast(obj);
			Rank rank = card.getRank();
			Suit suit = card.getSuit();
			
			this.hand.add(card);
			
			if(!rankHits.containsKey(rank))
				rankHits.put(rank,1);
			else
				rankHits.put(rank,rankHits.get(rank)+1);
			
			if(!suitHits.containsKey(suit))
				suitHits.put(suit,1);
			else
				suitHits.put(suit,suitHits.get(suit)+1);
		}
	}
	
	// 1.1
	private List<Card> straightFlush() {
		List<Card> list = new LinkedList<>();
		int i = 0;
		Card curr = null;
		Card next = null;
		
		if(suitHits.size() == 1) {
			for(i = 0; i < handSize - 1; i++) {
				curr = hand.get(i);
				next = hand.get(i+1);
				if(!curr.isNeighbour(next))
					return new LinkedList<>();
				else
					list.add(curr);
			}
		}
		return new LinkedList<>(list);
	}
	
	// 1.2
	private List<Card> fourOfAKind() {
		List<Card> list = new LinkedList<>();
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 4) {
				for(Card card:hand) {
					if(card.getRank().equals(entry.getKey()))
						list.add(card);
				}
				return new LinkedList<>(list);
			}
		return new LinkedList<>();
	}
	
	// 1.3
	private List<Card> royalFlush() {
		List<Card> list = new LinkedList<>();
		int i = 0;
		Card curr = null;
		Card next = null;
		if(suitHits.size() == 1) {
			for(i = 0; i < handSize - 1; i++) {
				curr = hand.get(i);
				next = hand.get(i+1);
				if(!curr.isNeighbour(next))
					return new LinkedList<>();
				else 
					list.add(curr);
			}
		}
		return new LinkedList<>(list);
	}
	
	// 2.
	private List<Card> fourToARoyalFlush() {
		List<Card> list = new LinkedList<>();
		int royal = 0;
		// C,D,H,S
		int[] suits = {0,0,0,0};
		
		Rank rank = null;
		Suit suit = null;
		Card curr = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();
			
			if(Rank.TEN.compareTo(rank) >= 0) {
				royal++;
				if(Suit.CLUBS.equals(suit))
					++suits[0];
				if(Suit.DIAMONDS.equals(suit))
					++suits[1];
				if(Suit.HEARTS.equals(suit))
					++suits[2];
				if(Suit.SPADES.equals(suit))
					++suits[3];
			}
		}
		
		for(int i = 0; i < 4; i++) {
			if(suits[i] == 4 && royal == 4) {
				for(Card card:hand) {
					if(card.getRank().ordinal() == i)
						list.add(card);
				}
				return new LinkedList<>(list);
			}
		}
		return new LinkedList<>();
	}
	
	//3.
	private List<Card> threeAces() {
		List<Card> list = new LinkedList<>();
		for(Card card: hand) {
			if(card.getRank().equals(Rank.ACE))
				list.add(card);
		}
		if(list.size() == 3)
			return new LinkedList<>(list);
		else
			return new LinkedList<>();
	}
	
	// 4.1
	private List<Card> straight() {
		List<Card> list = new LinkedList<>();
		Card curr = null;
		Card next = null;
		for(int i = 0; i < handSize; i++) {
			curr = hand.get(i);
			next = hand.get(i+1);
			if(!curr.isNeighbour(next))
				return new LinkedList<>();
		}
		
		for(Card card:hand)
			list.add(card);
		return new LinkedList<>(list);
	}
	
	// 4.2
	private List<Card> flush() {
		List<Card> list = new LinkedList<>();
		if(suitHits.size() == 1) {
			for(Card card:hand)
				list.add(card);
			return new LinkedList<>(list);
		}
		return new LinkedList<>();
	}
	
	// 4.3
	private List<Card> fullHouse() {
		List<Card> list = new LinkedList<>();
		int tot = 0;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet()) {
			if(entry.getValue() == 2)
				tot++;
			if(entry.getValue() == 3)
				tot++;
		}
		
		if(tot == 2)
			return new LinkedList<>(hand);
		return new LinkedList<>();
	}
	
	//5. NO ACES
	private List<Card> threeOfAKind() {
		Rank rank = null;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet()) {
			rank = entry.getKey();
			if(entry.getValue() == 3 && !Rank.ACE.equals(rank));
				//return true;
		}
		//return false;
		return new LinkedList<>();
	}
	
	// 6. 
	private List<Card> fourToAStraightFlush() {
		List<Card> list = new LinkedList<>();
		int i = 0, j = 0, tot = 0;
		int band = 5;
		// A,K,Q,J,T,9,8,7,6,5,4,3,2
		boolean[] ranks = new boolean[13];
		Suit suits = null;
		
		// incializa o ranks
		for(i = 0; i < 13; i++)
			ranks[i] = false;
		
		
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet()) {
			suits = entry.getKey();
		
			for(Card card:hand)
				if(card.getSuit().equals(suits))
					ranks[card.getRank().ordinal()] = true;
		
			for(i = 0; i < ranks.length; i++) {
				for(j = i, tot = 0; j < i + band; j++) {
					if(j >= ranks.length)
						if(ranks[j - ranks.length])
							tot++;
					if(j < ranks.length)
						if(ranks[j])
							tot++;
				}
				if(tot == 4);
					//return true;
			}
			
			for(i = 0; i < 13; i++)
				ranks[i] = false;
		}
		//return false;
		return new LinkedList<>();
	}
	
	// 7.
	private List<Card> twoPair() {
		List<Card> list = new LinkedList<>();
		int tot = 0;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 2)
				tot++;
		//return tot == 2;
		return new LinkedList<>();
	}
	
	// 8.
	private List<Card> highPair() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(Rank.JACK.compareTo(entry.getKey()) >= 0)
				if(entry.getValue() == 2);
					//return true;
		//return false;
		return new LinkedList<>();
	}
	
	// 9.
	private List<Card> fourToAFlush() {
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 4);
				//return true;
		//return false;
		return new LinkedList<>();
	}
	
	// 10.
	private List<Card> threeToARoyalFlush() {
		List<Card> list = new LinkedList<>();
		int royal = 0;
		// C,D,H,S
		int[] suits = {0,0,0,0};
		
		Rank rank = null;
		Suit suit = null;
		Card curr = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();
			
			if(Rank.TEN.compareTo(rank) >= 0) {
				royal++;
			
				if(Suit.CLUBS.equals(suit)) 
					++suits[0];
				if(Suit.DIAMONDS.equals(suit)) 
					++suits[1];
				if(Suit.HEARTS.equals(suit)) 
					++suits[2];
				if(Suit.SPADES.equals(suit)) 
					++suits[3];
			}
		}
		
		for(int s:suits)
			if(s == 4 && royal == 3);
				//return true;
		//return false;
		return new LinkedList<>();
	}
	
	// 11.
	private List<Card> fourToAnOutsideStraight() {
		List<Card> list = new LinkedList<>();
		int tot = 0;
		Card curr = null;
		Card next = null;
		
		for(int i = 0; i < handSize - 1; i++) {
			curr = hand.get(i);
			next = hand.get(i+1);
			if(!curr.isNeighbour(next));
				//return false;
		}
		//return true;
		return new LinkedList<>();
	}
	
	// 12.
	private List<Card> lowPair() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(Rank.JACK.compareTo(entry.getKey()) <= 0)
				if(entry.getValue() == 2);
					//return true;
		//return false;
		return new LinkedList<>();
	}
	
	// 13. 
	private List<Card> akqjUnsuited() {
		int high = 0;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(Rank.JACK.compareTo(entry.getKey()) >= 0)
				high++;
		//return high == 4;
		return new LinkedList<>();
	}
	
	// 14. TYPE 1
	private List<Card> threeToAStraightFlush1() {
		List<Card> list = new LinkedList<>();
		int i = 0, j = 0, tot = 0;
		int band = 5;
		// A,K,Q,J,T,9,8,7,6,5,4,3,2
		boolean[] ranks = new boolean[13];
		Suit suits = null;
		
		// incializa o ranks
		for(i = 0; i < 13; i++)
			ranks[i] = false;
		
		
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet()) {
			suits = entry.getKey();
		
			for(Card card:hand)
				if(card.getSuit().equals(suits))
					ranks[card.getRank().ordinal()] = true;
		
			for(i = 0; i < ranks.length; i++) {
				for(j = i, tot = 0; j < i + band; j++) {
					if(j >= ranks.length)
						if(ranks[j - ranks.length])
							tot++;
					if(j < ranks.length)
						if(ranks[j])
							tot++;
				}
				if(tot == 3);
					//return true;
			}
			
			for(i = 0; i < 13; i++)
				ranks[i] = false;
		}
		//return false;
		return new LinkedList<>();
	}
	
	// 15.
	private List<Card> fourToAnInsideStraightWith3HighCards() {
		List<Card> list = new LinkedList<>();
		int i = 0, j = 0, tot = 0;
		int band = 5;
		// A,K,Q,J,T,9,8,7,6,5,4,3,2
		boolean[] ranks = new boolean[13];
		Suit suits = null;
		int high = 0;
		
		// incializa o ranks
		for(i = 0; i < 13; i++)
			ranks[i] = false;
		
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet()) {
			suits = entry.getKey();
		
			for(Card card:hand)
				if(card.getSuit().equals(suits))
					ranks[card.getRank().ordinal()] = true;
		
			for(i = 0; i < 3; i++) {
				for(j = i, tot = 0; j < i + band; j++) {
					if(j >= ranks.length)
						if(ranks[j - ranks.length])
							tot++;
					if(j < ranks.length)
						if(ranks[j])
							tot++;
				}
				if(tot == 3);
					//return true;
			}
			
			for(i = 0; i < 13; i++)
				ranks[i] = false;
		}
		//return false;
		return new LinkedList<>();
	}
	
	// 16.
	private List<Card> qjSuited() {
		List<Card> list = new LinkedList<>();
		int tot = 0;
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet()) {
			for(Card card:hand) {
				if(card.getRank().equals(Rank.QUEEN))
					tot++;
				if(card.getRank().equals(Rank.JACK))
					tot++;
			}
			if(tot == 2)
				//return true;
			tot = 0;
		}
		//return false;
		
		return new LinkedList<>(list);
	}
	
	// 17.
	private List<Card> threeToAFlushWith2HighCards() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 18.
	private List<Card> twoSuitedHighCards() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 19.
	private List<Card> fourToAnInsideStraightWith2HighCards() {
		List<Card> list = new LinkedList<>();
		return new LinkedList<>();
	}
	
	// 20. TYPE 2
	private List<Card> threeToAStraightFlush2() {
		List<Card> list = new LinkedList<>();
		return new LinkedList<>();
	}
	
	// 21.
	private List<Card> fourToAnInsideStraightWith1HighCard() {
		List<Card> list = new LinkedList<>();
		return new LinkedList<>();
	}
	
	// 22.
	private List<Card> kqjUnsuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 23.
	private List<Card> jtSuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 24.
	private List<Card> qjUnsuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 25.
	private List<Card> threeToAFlushWith1HighCard() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 26.
	private List<Card> qtSuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 27. TYPE3
	private List<Card> threeToAStraightFlush3() {
		List<Card> list = new LinkedList<>();
		return list;
		
	}
	
	// 28.1
	private List<Card> kqUnsuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 28.2
	private List<Card> kjUnsuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 29.
	private List<Card> ace() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 30.
	private List<Card> ktSuited() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 31.1
	private List<Card> jack() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 31.2
	private List<Card> queen() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 31.3
	private List<Card> king() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 32.
	private List<Card> fourToAnInsideStraightWithNoHighCards() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 33.
	private List<Card> threeToAFlushWithNoHighCards() {
		List<Card> list = new LinkedList<>();
		return list;
	}
	
	// 34. DISCARD 
	
	public List<Card> whatsAdvised() {
		List<Card> list = new LinkedList<>();
		
		List<List<Card>> bigList = new LinkedList<>();
		boolean[] empty = null;
		
		bigList.add(straightFlush()); //0
		bigList.add(fourOfAKind()); //1
		bigList.add(royalFlush()); //2
		bigList.add(fourToARoyalFlush()); //3
		bigList.add(threeAces()); //4
		bigList.add(straight()); //5
		bigList.add(flush()); //6
		bigList.add(fullHouse()); //7
		bigList.add(threeOfAKind()); //8
		bigList.add(fourToAStraightFlush()); //9
		bigList.add(twoPair()); //10
		bigList.add(highPair()); //11
		bigList.add(fourToAFlush()); //12
		bigList.add(threeToARoyalFlush()); //13
		bigList.add(fourToAnOutsideStraight()); //14
		bigList.add(lowPair()); //15
		bigList.add(akqjUnsuited()); //16
		bigList.add(threeToAStraightFlush1()); //17
		bigList.add(fourToAnInsideStraightWith3HighCards()); //18
		bigList.add(qjSuited()); //19
		bigList.add(threeToAFlushWith2HighCards()); //20
		bigList.add(twoSuitedHighCards()); //21
		bigList.add(fourToAnInsideStraightWith2HighCards()); //22
		bigList.add(threeToAStraightFlush2()); //23
		bigList.add(fourToAnInsideStraightWith1HighCard()); //24
		bigList.add(kqjUnsuited()); //25
		bigList.add(jtSuited()); //26
		bigList.add(qjUnsuited()); //27
		bigList.add(threeToAFlushWith1HighCard()); //28
		bigList.add(qtSuited()); //29
		bigList.add(threeToAStraightFlush3()); //30
		bigList.add(kqUnsuited()); //31
		bigList.add(kjUnsuited()); //32
		bigList.add(ace()); //33
		bigList.add(ktSuited()); //34
		bigList.add(jack()); //35
		bigList.add(queen()); //36
		bigList.add(king()); //37
		bigList.add(fourToAnInsideStraightWithNoHighCards()); //38
		bigList.add(threeToAFlushWithNoHighCards()); // 39
		
		// Initializes list
		empty = new boolean[bigList.size()];
		
		for(int i = 0; i < bigList.size(); i++) {
			if(bigList.get(i).isEmpty())
				empty[i] = true;
			else
				empty[i] = false;
		}
		
		// Difficult hands
		// 1
		if(empty[0] && empty[3])
			return bigList.get(0);
		// 2
		if(empty[3] && empty[5]) 
			return bigList.get(3);
		// 3
		if(empty[3] && empty[6]) 
			return bigList.get(3);
		// 4
		if(empty[4] && empty[7]) 
			return bigList.get(4);
		// 5
		if(empty[7] && empty[8]) 
			return bigList.get(7);
		// 6
		if(empty[6] && empty[9]) 
			return bigList.get(6);
		// 7
		if(empty[5] && empty[9]) 
			return bigList.get(5);
		// 8
		if(empty[5] && empty[13]) 
			return bigList.get(5);
		// 9
		if(empty[9] && empty[13]) 
			return bigList.get(9);
		// 10
		if(empty[10] && empty[13]) 
			return bigList.get(10);
		// 11
		if(empty[11] && empty[12]) 
			return bigList.get(11);
		// 12
		if(empty[11] && empty[13]) 
			return bigList.get(11);
		// 13
		if(empty[12] && empty[13]) 
			return bigList.get(12);
		// 14
		if(empty[12] && empty[15]) 
			return bigList.get(12);
		// 15
		if(empty[13] && empty[14]) 
			return bigList.get(13);
		// 16
		if(empty[13] && empty[15]) 
			return bigList.get(13);
		// 17
		if(empty[14] && empty[15]) 
			return bigList.get(14);
		// 18
		if(empty[14] && empty[17]) 
			return bigList.get(14);
		// 19
		if(empty[15] && empty[17]) 
			return bigList.get(15);
		// 20
		if(empty[15] && empty[18]) 
			return bigList.get(15);
		// 21
		if(empty[16] && empty[17]) 
			return bigList.get(16);
		// 22
		if(empty[16] && empty[18]) 
			return bigList.get(16);
		// 23
		if(empty[16] && empty[19]) 
			return bigList.get(16);
		// 24
		if(empty[17] && empty[22]) 
			return bigList.get(17);
		// 25
		if(empty[17] && empty[26]) 
			return bigList.get(17);
		// 26
		if(empty[18] && empty[19]) 
			return bigList.get(18);
		// 27
		if(empty[18] && empty[20]) 
			return bigList.get(18);
		// 28
		if(empty[18] && empty[21]) 
			return bigList.get(18);
		// 29
		if(empty[18] && empty[23]) 
			return bigList.get(18);
		// 30
		if(empty[19] && empty[20]) 
			return bigList.get(19);
		// 31
		if(empty[19] && empty[22]) 
			return bigList.get(19);
		// 32
		if(empty[19] && empty[23]) 
			return bigList.get(19);
		if(empty[19] && empty[23]) 
			return bigList.get(30);
		
		// 33
		if(empty[20] && empty[21]) 
			return bigList.get(20);
		// 34
		if(empty[20] && empty[22]) 
			return bigList.get(20);
		// 35
		if(empty[20] && empty[24]) 
			return bigList.get(20);
		// 36
		if(empty[21] && empty[22]) 
			return bigList.get(21);
		// 37
		if(empty[21] && empty[23]) 
			return bigList.get(21);
		if(empty[21] && empty[30]) 
			return bigList.get(30);
		
		// 38
		if(empty[22] && empty[23]) 
			return bigList.get(22);
		if(empty[22] && empty[30]) 
			return bigList.get(22);
		// 39
		if(empty[22] && empty[28]) 
			return bigList.get(22);
		// 40
		if(empty[23] && empty[24]) 
			return bigList.get(23);
		// 41
		if(empty[24] && empty[26]) 
			return bigList.get(24);
		// 42
		if(empty[24] && empty[28]) 
			return bigList.get(24);
		// 43
		if(empty[24] && empty[30]) 
			return bigList.get(24);
		// 44
		if(empty[26] && empty[27]) 
			return bigList.get(26);
		// 45
		if(empty[26] && empty[28]) 
			return bigList.get(26);
		// 46
		if(empty[26] && empty[30]) 
			return bigList.get(26);
		// 47
		if(empty[26] && empty[31]) 
			return bigList.get(26);
		if(empty[26] && empty[32]) 
			return bigList.get(26);
		
		// 48 
		if(empty[26] && empty[33]) 
			return bigList.get(26);
		// 49
		if(empty[26] && empty[38]) 
			return bigList.get(26);
		// 50
		if(empty[26] && empty[39]) 
			return bigList.get(26);
		// 51
		if(empty[27] && empty[28]) 
			return bigList.get(27);
		// 52
		if(empty[27] && empty[29]) 
			return bigList.get(27);
		// 53
		if(empty[27] && empty[30]) 
			return bigList.get(27);
		// 54
		if(empty[27] && empty[33]) 
			return bigList.get(27);
		// 55
		if(empty[27] && empty[39]) 
			return bigList.get(27);
		// 56
		if(empty[28] && empty[29]) 
			return bigList.get(28);
		// 57
		if(empty[28] && empty[31]) 
			return bigList.get(28);
		if(empty[28] && empty[32]) 
			return bigList.get(28);
		
		// 58
		if(empty[28] && empty[33]) 
			return bigList.get(28);
		// 59
		if(empty[28] && empty[34]) 
			return bigList.get(28);
		// 60
		if(empty[28] && empty[38]) 
			return bigList.get(28);
		// 61
		if(empty[29] && empty[30]) 
			return bigList.get(29);
		// 62
		if(empty[29] && empty[31]) 
			return bigList.get(29);
		// 63
		if(empty[29] && empty[35]) 
			return bigList.get(29);
		if(empty[29] && empty[37]) 
			return bigList.get(29);
		if(empty[29] && empty[33]) 
			return bigList.get(29);
		
		// 64
		if(empty[29] && empty[38]) 
			return bigList.get(29);
		// 65
		if(empty[29] && empty[39]) 
			return bigList.get(29);
		// 66
		if(empty[30] && empty[31]) 
			return bigList.get(30);
		if(empty[30] && empty[32]) 
			return bigList.get(30);
		
		// 67
		if(empty[30] && empty[35]) 
			return bigList.get(30);
		if(empty[30] && empty[36]) 
			return bigList.get(30);
		if(empty[30] && empty[37]) 
			return bigList.get(30);
		if(empty[30] && empty[33]) 
			return bigList.get(30);
		
		// 68
		if(empty[30] && empty[34]) 
			return bigList.get(30);
		// 69
		if(empty[30] && empty[38]) 
			return bigList.get(30);
		// 70
		if(empty[31] && empty[33]) 
			return bigList.get(31);
		if(empty[32] && empty[33]) 
			return bigList.get(32);
		
		// 71
		if(empty[31] && empty[34]) 
			return bigList.get(31);
		if(empty[32] && empty[34]) 
			return bigList.get(32);
		
		// 72
		if(empty[31] && empty[39]) 
			return bigList.get(31);
		if(empty[32] && empty[39]) 
			return bigList.get(32);
		
		// 73
		if(empty[33] && empty[34]) 
			return bigList.get(33);
		// 74
		if(empty[33] && empty[35]) 
			return bigList.get(33);
		if(empty[33] && empty[36]) 
			return bigList.get(33);
		if(empty[33] && empty[37]) 
			return bigList.get(33);
		
		// 75
		if(empty[33] && empty[38]) 
			return bigList.get(33);
		// 76
		if(empty[33] && empty[39]) 
			return bigList.get(33);
		// 77
		if(empty[34] && empty[38]) 
			return bigList.get(34);
		// 78
		if(empty[34] && empty[39]) 
			return bigList.get(34);
		// 79
		if(empty[35] && empty[38]) 
			return bigList.get(35);
		if(empty[36] && empty[38]) 
			return bigList.get(36);
		if(empty[37] && empty[38]) 
			return bigList.get(37);
		
		// 80
		if(empty[35] && empty[39]) 
			return bigList.get(35);
		if(empty[36] && empty[39]) 
			return bigList.get(36);
		if(empty[37] && empty[39]) 
			return bigList.get(37);
		
		// 81
		if(empty[38] && empty[39]) 
			return bigList.get(38);
		
		for(int i = 0; i < bigList.size(); i++)
			if(!bigList.get(i).isEmpty())
				return bigList.get(i);
		
		return new LinkedList<Card>();
	}
	
	/*
	private boolean sequence(List<Card> card) {
		int i = 0, handSize = 5;
		Card curr = null, next = null;
		
		for(i = 0; i < handSize - 1; i++) {
			curr = card.get(i);
			next = card.get(i+1);
			if(!curr.isNeighbour(next))
				return false;
		}
		return true;
	}
	
	private boolean straightFlush() {
		Card card = null, nextCard = null;
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 4)
				for(int i = 0; i < 5; i++) {
					card = hand.get(i);
					nextCard = hand.get(i+1);
					if(!card.isNeighbour(nextCard))
						return false;
				}
		return true;
	}
	private boolean fourToARoyalFlush() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getKey() == Rank.JACK && rankHits.size() == 5)
				return true;
		return false;
	}
	private boolean straight(int i) {
		if(i <= 1) return true;
	
		for(int j = 0; j < 5 - i + 1; j++)
			if(sequence(hand.subList(j, i - 1 + j)))
				return true;
		return false;
	}
	private boolean flush() {
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() != 5)
				return false;
		return true;
	}
	private boolean threeAces() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getKey() == Rank.ACE)
				if(entry.getValue() == 3)
					return true;
		return false;
	}
	private boolean fullHouse() {
		int tot = 0;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet()) {
			if(entry.getValue() == 2)
				tot = tot + 2;
			if(entry.getValue() == 3)
				tot = tot + 3;
		}
		if(tot == 5) return true;
		return false;
	}
	private boolean threeOfKind() {
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getKey() != Rank.ACE && entry.getValue() == 3)
				return true;
		return false;
	}
	private boolean fourToAStraightFlush() {
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 4) {
				if(sequence(hand.subList(1, hand.size())))
					return true;
				if(sequence(hand.subList(0, hand.size()-1)))
					return true;
			}
		return false;
	}
	private boolean threeToAStraightFlush() {
		int i = 0;
		int tot = 0;
		Card curr = null;
		Card next = null;
		
		int seq = 3;
		
		for(i =0; i < 5 - seq; i++) {
			curr = hand.get(i);
			next = hand.get(i+1);
			if(curr.isNeighbour(next) && curr.isSuit(next))
				tot++;
		}
		
		return tot == 3 ? true:false;
	}
	private boolean threeToARoyalFlush() {
		int i = 0;
		Card curr = null;
		
		Map<Suit,Integer> royal = new HashMap<>();

		for(i = 0; i < 5; i++) {
			curr = hand.get(i);
			if(Rank.TEN.compareTo(curr.getRank()) >= 0) {
				if(!royal.containsKey(curr.getSuit()))
					royal.put(curr.getSuit(), 1);
				else
					royal.put(curr.getSuit(), royal.get(curr.getSuit()) + 1);
			}
		}
		return royal.size() == 3;
	}
	private boolean twoPair() {
		int tot = 0;
		for(Map.Entry<Rank,Integer> entry: rankHits.entrySet())
			if(entry.getValue() == 2)
				tot++;
		return tot == 2;
	}
	private boolean highPair() {
		int[] count = {0,0,0,0};
		Card card = null;
		
		for(int i = 0; i < 5; i++) {
			card = hand.get(i);
			
			if(Rank.JACK.equals(card.getRank()))
				++count[3];
			if(Rank.QUEEN.equals(card.getRank()))
				++count[2];
			if(Rank.KING.equals(card.getRank()))
				++count[1];
			if(Rank.ACE.equals(card.getRank()))
				++count[0];
		}
		for(int i = 0; i < 5; i++)
			if(count[i] == 2)
				return true;
		return false;
	}
	private boolean fourToFlush() {
		for(Map.Entry<Suit,Integer> entry: suitHits.entrySet())
			if(entry.getValue() == 4)
				return true;
		return false;
	}
	private boolean lowPair() {
		int[] count = {0,0,0,0,0,0,0,0,0};
		Card card = null;
		
		for(int i = 0; i < count.length; i++) {
			card = hand.get(i);
			
			if(Rank.TEN.equals(card.getRank()))
				++count[0];
			if(Rank.NINE.equals(card.getRank()))
				++count[1];
			if(Rank.EIGHT.equals(card.getRank()))
				++count[2];
			if(Rank.SEVEN.equals(card.getRank()))
				++count[3];
			if(Rank.SIX.equals(card.getRank()))
				++count[4];
			if(Rank.FIVE.equals(card.getRank()))
				++count[5];
			if(Rank.FOUR.equals(card.getRank()))
				++count[6];
			if(Rank.THREE.equals(card.getRank()))
				++count[7];
			if(Rank.TWO.equals(card.getRank()))
				++count[8];
		}
		for(int i = 0; i < count.length; i++)
			if(count[i] == 2)
				return true;
		return false;
	}
	private boolean fourToAnOutsideStraight() {
		for(int i = 0; i < 13 - 5 + 1; i++)
			if(sequence(hand.subList(i, i+5 -1)))
				return true;
		return false;
	}
	private boolean fourToAnInsideStraight() {
		// A,K,Q,J,T,9,8,7,6,5,4,3,2
		boolean[] seq = {false,false,false,false,false,false,false,
				false,false,false,false,false,false};
		
		int i = 0, j = 0, tot = 0;
		int band = 5;
		for(Card card:hand) {
			seq[card.getRank().ordinal()] = true;
		}
		
		
		if(sequence(hand))
			return false;
		
		for(i = 0; i < seq.length; i++) {
			for(j = i, tot = 0; j < i + band; j++) {
				if(j >= seq.length)
					if(seq[j - seq.length])
						tot++;
				if(j < seq.length)
					if(seq[j])
						tot++;
			}
			if(tot == 4)
				return true;
		}
		return false;
	}
	
	private boolean akqjUnsuited() {
		int tot = 0;
		for(Card card:hand) {
			if(Rank.JACK.compareTo(card.getRank()) >= 0)
				tot++;
		}
		return tot == 4;
	}
	private boolean qjSuited() {
		// C,D,H,S
		int[] jack = {0,0,0,0};
		int[] queen = {0,0,0,0};
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();

			if(Rank.JACK.equals(rank) && Suit.CLUBS.equals(suit)) ++jack[0];
			if(Rank.JACK.equals(rank) && Suit.DIAMONDS.equals(suit)) ++jack[1];
			if(Rank.JACK.equals(rank) && Suit.HEARTS.equals(suit)) ++jack[2];
			if(Rank.JACK.equals(rank) && Suit.SPADES.equals(suit)) ++jack[3];
			
			if(Rank.QUEEN.equals(rank) && Suit.CLUBS.equals(suit)) ++queen[0];
			if(Rank.QUEEN.equals(rank) && Suit.DIAMONDS.equals(suit)) ++queen[1];
			if(Rank.QUEEN.equals(rank) && Suit.HEARTS.equals(suit)) ++queen[2];
			if(Rank.QUEEN.equals(rank) && Suit.SPADES.equals(suit)) ++queen[3];	
		}
		
		if(jack[0] == 1 && queen[0] == 1) return true;
		if(jack[1] == 1 && queen[1] == 1) return true;
		if(jack[2] == 1 && queen[2] == 1) return true;
		if(jack[3] == 1 && queen[3] == 1) return true;
		
		return false;
	}
	
	private boolean fourToAnInsideStraightWith2HighCards() {
		int tot = 0;
		if(fourToAnInsideStraight())
			for(Card card:hand)
				if(Rank.JACK.compareTo(card.getRank()) >= 0)
					tot++;	
		return tot == 2;
	}
	
	private boolean jtSuited() {
		// C,D,H,S
		int[] jack = {0,0,0,0};
		int[] ten = {0,0,0,0};
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();

			if(Rank.JACK.equals(rank) && Suit.CLUBS.equals(suit)) ++jack[0];
			if(Rank.JACK.equals(rank) && Suit.DIAMONDS.equals(suit)) ++jack[1];
			if(Rank.JACK.equals(rank) && Suit.HEARTS.equals(suit)) ++jack[2];
			if(Rank.JACK.equals(rank) && Suit.SPADES.equals(suit)) ++jack[3];
			
			if(Rank.TEN.equals(rank) && Suit.CLUBS.equals(suit)) ++ten[0];
			if(Rank.TEN.equals(rank) && Suit.DIAMONDS.equals(suit)) ++ten[1];
			if(Rank.TEN.equals(rank) && Suit.HEARTS.equals(suit)) ++ten[2];
			if(Rank.TEN.equals(rank) && Suit.SPADES.equals(suit)) ++ten[3];	
		}
		
		if(jack[0] == 1 && ten[0] == 1) return true;
		if(jack[1] == 1 && ten[1] == 1) return true;
		if(jack[2] == 1 && ten[2] == 1) return true;
		if(jack[3] == 1 && ten[3] == 1) return true;
		
		return false;
	}
	
	private boolean fourToAnInsideStraightWith3HighCards() {
		int tot = 0;
		if(fourToAnInsideStraight())
			for(Card card:hand)
				if(Rank.JACK.compareTo(card.getRank()) >= 0)
					tot++;	
		return tot == 3;
	}
	
	private boolean threeToAFlushWith2HighCards() {
		int clubs = 0;
		int clubsH = 0;
		int diamonds = 0;
		int diamondsH = 0;
		int hearts = 0;
		int heartsH = 0;
		int spades = 0;
		int spadesH = 0;
		
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();
			
			if(Suit.CLUBS.equals(suit)) {
				clubs++;
				if(Rank.JACK.compareTo(rank) >= 0)
					clubsH++;
			}
			if(Suit.DIAMONDS.equals(suit)) {
				diamonds++;
				if(Rank.JACK.compareTo(rank) >= 0)
					diamondsH++;
			}
			if(Suit.HEARTS.equals(suit)) {
				hearts++;
				if(Rank.JACK.compareTo(rank) >= 0)
					heartsH++;
			}
			if(Suit.SPADES.equals(suit)) {
				spades++;
				if(Rank.JACK.compareTo(rank) >= 0)
					spadesH++;
			}
		}
		
		if(clubs == 3 && clubsH == 2) return true;
		if(diamonds == 3 && diamondsH == 2) return true;
		if(hearts == 3 && heartsH == 2) return true;
		if(spades == 3 && spadesH == 2) return true;
		return false;
	}
	
	private boolean twoSuitedHighCards() {
		int clubs = 0;
		int clubsH = 0;
		int diamonds = 0;
		int diamondsH = 0;
		int hearts = 0;
		int heartsH = 0;
		int spades = 0;
		int spadesH = 0;
		
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();
			
			if(Suit.CLUBS.equals(suit)) {
				clubs++;
				if(Rank.JACK.compareTo(rank) >= 0)
					clubsH++;
			}
			if(Suit.DIAMONDS.equals(suit)) {
				diamonds++;
				if(Rank.JACK.compareTo(rank) >= 0)
					diamondsH++;
			}
			if(Suit.HEARTS.equals(suit)) {
				hearts++;
				if(Rank.JACK.compareTo(rank) >= 0)
					heartsH++;
			}
			if(Suit.SPADES.equals(suit)) {
				spades++;
				if(Rank.JACK.compareTo(rank) >= 0)
					spadesH++;
			}
		}
		
		if(clubs == 2 && clubsH == 2) return true;
		if(diamonds == 2 && diamondsH == 2) return true;
		if(hearts == 2 && heartsH == 2) return true;
		if(spades == 2 && spadesH == 2) return true;
		return false;
	}
	
	private boolean fourToAnInsideStraightWith1HighCard() {
		int tot = 0;
		if(fourToAnInsideStraight())
			for(Card card:hand)
				if(Rank.JACK.compareTo(card.getRank()) >= 0)
					tot++;	
		return tot == 1;
	}
	
	private boolean threeToAFlushWith1HighCard() {
		int clubs = 0;
		int clubsH = 0;
		int diamonds = 0;
		int diamondsH = 0;
		int hearts = 0;
		int heartsH = 0;
		int spades = 0;
		int spadesH = 0;
		
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();
			
			if(Suit.CLUBS.equals(suit)) {
				clubs++;
				if(Rank.JACK.compareTo(rank) >= 0)
					clubsH++;
			}
			if(Suit.DIAMONDS.equals(suit)) {
				diamonds++;
				if(Rank.JACK.compareTo(rank) >= 0)
					diamondsH++;
			}
			if(Suit.HEARTS.equals(suit)) {
				hearts++;
				if(Rank.JACK.compareTo(rank) >= 0)
					heartsH++;
			}
			if(Suit.SPADES.equals(suit)) {
				spades++;
				if(Rank.JACK.compareTo(rank) >= 0)
					spadesH++;
			}
		}
		
		if(clubs == 3 && clubsH == 1) return true;
		if(diamonds == 3 && diamondsH == 1) return true;
		if(hearts == 3 && heartsH == 1) return true;
		if(spades == 3 && spadesH == 1) return true;
		return false;
	}
	
	private boolean qjUnsuited() {
		int queen = rankHits.get(Rank.QUEEN);
		int jack = rankHits.get(Rank.JACK);
		return (queen == 1 && jack == 1);
	}
	
	private boolean kqUnsuited() {
		int king = rankHits.get(Rank.KING);
		int queen = rankHits.get(Rank.QUEEN);
		return (queen == 1 && king == 1);
	}
	private boolean kjUnsuited() {
		int king = rankHits.get(Rank.KING);
		int jack = rankHits.get(Rank.JACK);
		return (king == 1 && jack == 1);
	}
	
	private boolean ace() {
		return rankHits.get(Rank.ACE) == 1;
	}
	
	private boolean fourToAnInsideStraightWithNoHighCards() {
		if(fourToAnInsideStraight())
			for(Card card:hand)
				if(Rank.JACK.compareTo(card.getRank()) >= 0)
					return false;
		return true;
	}
	
	private boolean threeToAFlushWithNoHighCards() {
		int clubs = 0;
		int clubsH = 0;
		int diamonds = 0;
		int diamondsH = 0;
		int hearts = 0;
		int heartsH = 0;
		int spades = 0;
		int spadesH = 0;
		
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();
			
			if(Suit.CLUBS.equals(suit)) {
				clubs++;
				if(Rank.JACK.compareTo(rank) >= 0)
					clubsH++;
			}
			if(Suit.DIAMONDS.equals(suit)) {
				diamonds++;
				if(Rank.JACK.compareTo(rank) >= 0)
					diamondsH++;
			}
			if(Suit.HEARTS.equals(suit)) {
				hearts++;
				if(Rank.JACK.compareTo(rank) >= 0)
					heartsH++;
			}
			if(Suit.SPADES.equals(suit)) {
				spades++;
				if(Rank.JACK.compareTo(rank) >= 0)
					spadesH++;
			}
		}
		
		if(clubs == 3 && clubsH == 0) return true;
		if(diamonds == 3 && diamondsH == 0) return true;
		if(hearts == 3 && heartsH == 0) return true;
		if(spades == 3 && spadesH == 0) return true;
		return false;
	}
	
	private boolean ktSuited() {
		// C,D,H,S
		int[] king = {0,0,0,0};
		int[] ten = {0,0,0,0};
		Rank rank = null;
		Suit suit = null;
		
		for(Card card:hand) {
			rank = card.getRank();
			suit = card.getSuit();

			if(Rank.KING.equals(rank) && Suit.CLUBS.equals(suit)) ++king[0];
			if(Rank.KING.equals(rank) && Suit.DIAMONDS.equals(suit)) ++king[1];
			if(Rank.KING.equals(rank) && Suit.HEARTS.equals(suit)) ++king[2];
			if(Rank.KING.equals(rank) && Suit.SPADES.equals(suit)) ++king[3];
			
			if(Rank.TEN.equals(rank) && Suit.CLUBS.equals(suit)) ++ten[0];
			if(Rank.TEN.equals(rank) && Suit.DIAMONDS.equals(suit)) ++ten[1];
			if(Rank.TEN.equals(rank) && Suit.HEARTS.equals(suit)) ++ten[2];
			if(Rank.TEN.equals(rank) && Suit.SPADES.equals(suit)) ++ten[3];	
		}
		
		if(king[0] == 1 && ten[0] == 1) return true;
		if(king[1] == 1 && ten[1] == 1) return true;
		if(king[2] == 1 && ten[2] == 1) return true;
		if(king[3] == 1 && ten[3] == 1) return true;
		
		return false;
	}
	
	private boolean jack() {
		return rankHits.get(Rank.JACK) == 1;
	}
	private boolean king() {
		return rankHits.get(Rank.JACK) == 1;
	}
	
	private boolean queen() {
		return rankHits.get(Rank.QUEEN) == 1; 
	}
	
	// ---------------------
	public boolean[] remove() {
		if(straightFlush() && fourToARoyalFlush()) {
			return new boolean[] {false,false,false,false,false};
		}
	}
	*/
}
