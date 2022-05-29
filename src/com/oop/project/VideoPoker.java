package com.oop.project;

public class VideoPoker {

	public static void main(String[] args) {
		
		// Initializes a deck with 2 jokers. There are no repeated cards
		Deck deck = new Deck();
		
		// Prints the total number of cards in the deck.
		System.out.println(deck.countCards());
		
		// Adds BJOKER (black joker) and RJOKER (red joker).
		// It returns an array of the cards successfully added to the deck.
		// If the cards are in the deck, they aren't display in the return array.
		System.out.println(
				deck.addCards(new Card[] {new Card(Rank.BJOKER,Suit.BJOKER), new Card(Rank.RJOKER,Suit.RJOKER)})
				);
		
		// Prints the total number of cards in the deck.
		System.out.println(deck.countCards());
		
		// Removes the cards from the deck.
		// It's trying to remove BJOKER(black joker) and RJOKER(red joker).
		// Cards successfully removed are returned as an array.
		// Cards that aren't removed (already removed from the deck) don't show on the array.
		System.out.println(
				deck.removeCards(new Card[] {new Card(Rank.BJOKER,Suit.BJOKER), new Card(Rank.RJOKER,Suit.RJOKER)})
				);
		
		// Prints the total number of cards in the deck.
		System.out.println(deck.countCards());
		// This time the resulting array is empty since the jokers were already removed.
		System.out.println(
				deck.removeCards(new Card[] {new Card(Rank.BJOKER,Suit.BJOKER), new Card(Rank.RJOKER,Suit.RJOKER)})
				);
		
		System.out.println(deck.countCards());
		// Reads the jokers and they are displayed in the array.
		System.out.println(
				deck.addCards(new Card[] {new Card(Rank.BJOKER,Suit.BJOKER), new Card(Rank.RJOKER,Suit.RJOKER)})
				);
		
		// Will try to remove the same card twice.
		System.out.println(deck.countCards());
		System.out.println(
				deck.removeCards(new Card[] {new Card(Rank.A,Suit.SPADES)})
				);
		System.out.println(deck.countCards());
		System.out.println(
				deck.removeCards(new Card[] {new Card(Rank.A,Suit.SPADES)})
				);
		System.out.println(deck.countCards());
		
	}
}
