package tools.util;

/**
 * Card Class
 * Object containing information about a specific card.
 */
public class Card
{
	protected String suit;
	protected String symbol;
	protected int suitScore;
	protected int highSymbolOrder;
	protected int lowSymbolOrder;
	protected int highSymbolScore;
	protected int lowSymbolScore;
	
	/**
	 * Card Constructor
	 * @param suit
	 * @param card
	 */
	public Card(String suit, String symbol)
	{
		this.suit = suit;
		this.symbol = symbol;
	}
	
	/**
	 * Compare symbol values of two cards.
	 * @param c2
	 * @param aceHigh
	 * @return int
	 */
	public int compareSymbolValueTo(Card c2, boolean aceHigh)
	{
		if(this.getSymbolValue(aceHigh) > c2.getSymbolValue(aceHigh))
		{
			return 1;
		}
		if(c2.getSymbolValue(aceHigh) > this.getSymbolValue(aceHigh))
		{
			return -1;
		}
		return 0;
	}
	
	/**
	 * Compare symbol order of two cards
	 * @param c2
	 * @param aceHigh
	 * @return int
	 */
	public int compareSymbolOrderTo(Card c2, boolean aceHigh)
	{
		if(this.getSymbolOrder(aceHigh) > c2.getSymbolOrder(aceHigh))
		{
			return 1;
		}
		if(c2.getSymbolOrder(aceHigh) > this.getSymbolOrder(aceHigh))
		{
			return -1;
		}
		return 0;
	}
	
	/**
	 * Get suit.
	 * @return String
	 */
	public String getSuit()
	{
		return this.suit;
	}
	
	/**
	 * Set suit score
	 * @param suitScore
	 */
	public void setSuitValue(int suitScore)
	{
		this.suitScore = suitScore;
	}
	
	/**
	 * Get suit score
	 * @return int
	 */
	public int getSuitValue()
	{
		return this.suitScore;
	}
	
	/**
	 * Get card.
	 * @return String
	 */
	public String getSymbol()
	{
		return this.symbol;
	}
	
	/**
	 * Set symbol order
	 * @param symbolOrder
	 */
	public void setSymbolOrders(Integer[] symbolOrder)
	{
		this.highSymbolOrder = symbolOrder.length > 1 ? symbolOrder[1] : symbolOrder[0];
		this.lowSymbolOrder = symbolOrder[0];
	}
	
	/**
	 * Get symbol order.
	 * @return int
	 */
	public int getSymbolOrder()
	{
		return this.lowSymbolOrder;
	}
	
	/**
	 * Get symbol order with aceHigh flag
	 * @param aceHigh
	 * @return int
	 */
	public int getSymbolOrder(boolean aceHigh)
	{
		if(!this.symbol.equals(Deck.ACE))
		{
			return this.getSymbolOrder();
		}
		if(aceHigh)
		{
			return this.highSymbolOrder;
		}
		return this.lowSymbolOrder;
	}
	
	/**
	 * Set symbol values.
	 * @param symbolScore
	 */
	public void setSymbolValues(Integer[] symbolScore)
	{
		this.highSymbolScore = symbolScore.length > 1 ? symbolScore[1] : symbolScore[0];
		this.lowSymbolScore = symbolScore[0];
	}
	
	/**
	 * Get symbol value.
	 * @return int
	 */
	public int getSymbolValue()
	{
		return this.lowSymbolScore;
	}
	
	/**
	 * Get symbol value with aceHigh flag
	 * @param aceHigh
	 * @return int
	 */
	public int getSymbolValue(boolean aceHigh)
	{
		if(!this.symbol.equals(Deck.ACE))
		{
			return this.getSymbolValue();
		}
		if(aceHigh)
		{
			return this.highSymbolScore;
		}
		return this.lowSymbolScore;
	}
}
