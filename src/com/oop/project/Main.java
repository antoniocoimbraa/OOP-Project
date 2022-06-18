package com.oop.project;

//Source: https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.9

public class Main {
	
	public static void main(String[] args) {
		
		/*
		System.out.println("b".compareTo("a"));
		
		Integer count = 0;
		count = null;
		System.out.println(count);
		
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(Rank.ACE,Suit.CLUBS));
		cards.add(new Card(Rank.ACE,Suit.DIAMONDS));
		cards.add(new Card(Rank.ACE,Suit.SPADES));
		cards.add(new Card(Rank.ACE,Suit.HEARTS));
		cards.add(new Card(Rank.ACE,Suit.CLUBS));

		PokerHand pokerHand1 = new PokerHand(cards.toArray());
		System.out.println(pokerHand1);
		
		for(Map.Entry<Rank,Integer> entry:pokerHand1.getRankHits().entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
		
		for(Map.Entry<Suit,Integer> entry:pokerHand1.getSuitHits().entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
		
		pokerHand1.hold(1, new Card(Rank.NINE,Suit.DIAMONDS));
		System.out.println(pokerHand1);
		
		for(Map.Entry<Rank,Integer> entry:pokerHand1.getRankHits().entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
		
		for(Map.Entry<Suit,Integer> entry:pokerHand1.getSuitHits().entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
		*/
		
		/*
		// Jack or better
		List<Card> card = new LinkedList<Card>();
		PokerHand pokerHand = null;
		card.add(new Card(Rank.JACK,Suit.CLUBS));
		card.add(new Card(Rank.KING,Suit.DIAMONDS));
		card.add(new Card(Rank.QUEEN,Suit.SPADES));
		card.add(new Card(Rank.TEN,Suit.HEARTS));
		card.add(new Card(Rank.NINE,Suit.CLUBS));
		pokerHand = new PokerHand(card.toArray());
		System.out.println(Arrays.toString(pokerHand.check()));
		
		//card = Card.newDeck("JD KD QD TD ND");
		
		card = new LinkedList<Card>();
		card.add(new Card(Rank.JACK,Suit.DIAMONDS));
		card.add(new Card(Rank.KING,Suit.DIAMONDS));
		card.add(new Card(Rank.QUEEN,Suit.DIAMONDS));
		card.add(new Card(Rank.TEN,Suit.DIAMONDS));
		card.add(new Card(Rank.NINE,Suit.DIAMONDS));
		pokerHand = new PokerHand(card.toArray());
		System.out.println(Arrays.toString(pokerHand.check()));
		
		
		card = new LinkedList<Card>();
		card.add(new Card(Rank.JACK,Suit.DIAMONDS));
		card.add(new Card(Rank.KING,Suit.DIAMONDS));
		card.add(new Card(Rank.QUEEN,Suit.DIAMONDS));
		card.add(new Card(Rank.ACE,Suit.DIAMONDS));
		card.add(new Card(Rank.TEN,Suit.DIAMONDS));
		pokerHand = new PokerHand(card.toArray());
		System.out.println(Arrays.toString(pokerHand.check()));
		
		card = new LinkedList<Card>();
		card.add(new Card(Rank.THREE,Suit.DIAMONDS));
		card.add(new Card(Rank.KING,Suit.DIAMONDS));
		card.add(new Card(Rank.QUEEN,Suit.DIAMONDS));
		card.add(new Card(Rank.ACE,Suit.DIAMONDS));
		card.add(new Card(Rank.TWO,Suit.DIAMONDS));
		pokerHand = new PokerHand(card.toArray());
		System.out.println(Arrays.toString(pokerHand.check()));
		*/
		
		Machine machine = new Machine(args);
		System.out.println(machine);
		machine.play();
	}
}
