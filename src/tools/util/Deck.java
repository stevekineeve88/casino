package tools.util;

import java.util.*;

/**
 * Deck class
 * Object representing a deck of cards with methods for determining card properties.
 */
public class Deck 
{
	public static final String CLUBS = "Clubs";
	public static final String DIAMONDS = "Diamonds";
	public static final String HEARTS = "Hearts";
	public static final String SPADES = "Spades";
	private Hashtable<String, Integer> SUITS_TABLE = new Hashtable<String, Integer>();
	
	public static final String ACE = "Ace";
	public static final String KING = "King";
	public static final String QUEEN = "Queen";
	public static final String JACK = "Jack";
	private Hashtable<String, Integer[]> SYMBOL_SCORES = new Hashtable<String, Integer[]>();
	private Hashtable<String, Integer[]> SYMBOL_ORDER = new Hashtable<String, Integer[]>();
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	
	/**
	 * Deck Constructor
	 * @throws Exception 
	 */
	public Deck()
	{
		this.SUITS_TABLE.put(CLUBS, 4);
		this.SUITS_TABLE.put(DIAMONDS, 3);
		this.SUITS_TABLE.put(HEARTS, 2);
		this.SUITS_TABLE.put(SPADES, 1);
		
		this.SYMBOL_SCORES.put(ACE, new Integer[] {1, 11});
		this.SYMBOL_SCORES.put(KING, new Integer[] {10});
		this.SYMBOL_SCORES.put(QUEEN, new Integer[] {10});
		this.SYMBOL_SCORES.put(JACK, new Integer[] {10});
		
		this.SYMBOL_ORDER.put(ACE, new Integer[] {1, 14});
		this.SYMBOL_ORDER.put(KING, new Integer[] {13});
		this.SYMBOL_ORDER.put(QUEEN, new Integer[] {12});
		this.SYMBOL_ORDER.put(JACK, new Integer[] {11});
		for(int i = 2; i <= 10; i++)
		{
			Integer[] values = new Integer[] {i};
			String symbol = Integer.toString(i);
			this.SYMBOL_SCORES.put(symbol, values);
			this.SYMBOL_ORDER.put(symbol, values);
		}
		shuffle();
	}
	
	/**
	 * Draw a card.
	 * @return Card
	 * @throws Exception
	 */
	public Card draw() throws Exception
	{
		if(this.cards.size() == 0)
		{
			throw new Exception("Deck is empty");
		}
		return this.cards.remove(0);
	}
	
	/**
	 * Get current deck size.
	 * @return int
	 */
	public int getDeckSize()
	{
		return this.cards.size();
	}
	
	/**
	 * Get suits.
	 * @return Enumeration<String>
	 */
	public Enumeration<String> getSuits()
	{
		return this.SUITS_TABLE.keys();
	}
	
	/**
	 * Shuffle the cards.
	 */
	public void shuffle()
	{
		this.cards.clear();
		Set<String> suitKeys = this.SUITS_TABLE.keySet();
		Set<String> symbolKeys = this.SYMBOL_SCORES.keySet();
		for(String suitKey: suitKeys)
		{
			for(String symbolKey: symbolKeys)
			{
				Card card = new Card(suitKey, symbolKey);
				card.setSuitValue(this.SUITS_TABLE.get(suitKey));
				card.setSymbolOrders(this.SYMBOL_ORDER.get(symbolKey));
				card.setSymbolValues(this.SYMBOL_SCORES.get(symbolKey));
				this.cards.add(card);
			}
		}
		Collections.shuffle(this.cards, new Random());
	}
}
