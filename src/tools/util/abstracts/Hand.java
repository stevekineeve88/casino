package tools.util.abstracts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import tools.util.Card;

/**
 * Class Hand
 * Abstract class for defining a player hand.
 */
public abstract class Hand 
{
	protected ArrayList<Card> hand;
	
	/**
	 * Hand Constructor
	 * @param hand
	 */
	public Hand(ArrayList<Card> hand)
	{
		this.hand = hand;
	}
	
	/**
	 * Hand Constructor
	 */
	public Hand()
	{
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * Add card to hand.
	 * @param card
	 */
	public void addCard(Card card)
	{
		this.hand.add(card);
	}
	
	/**
	 * Clear hand.
	 */
	public void clear()
	{
		this.hand.clear();
	}
	
	/**
	 * Get hand.
	 * @return ArrayList<Card>
	 */
	public ArrayList<Card> getHand()
	{
		return this.hand;
	}
	
	/**
	 * Sort hand based on symbol order.
	 * @param aceHigh
	 */
	public void orderBySymbolOrder(boolean aceHigh)
	{
		Collections.sort(this.hand, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) 
			{
				return c1.compareSymbolOrderTo(c2, aceHigh);
			}
		});
	}
	
	/**
	 * Sort hand based on symbol value.
	 * @param aceHigh
	 */
	public void orderBySymbolValue(boolean aceHigh)
	{
		Collections.sort(this.hand, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) 
			{
				return c1.compareSymbolValueTo(c2, aceHigh);
			}
		});
	}
	
	/**
	 * Whether hand is complete or not.
	 * @return boolean
	 */
	public abstract boolean isCompleteHand();
}
