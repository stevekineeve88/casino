package tools.util;

/**
 * Class PokerHandRank
 * Object describes a poker hand rank.
 */
public class PokerHandRank 
{
	private String hand;
	private int rank;
	
	/**
	 * PokerHandRank Constructor
	 * @param hand
	 * @param rank
	 */
	public PokerHandRank(String hand, int rank)
	{
		this.hand = hand;
		this.rank = rank;
	}
	
	/**
	 * Get hand name
	 * @return String
	 */
	public String getHand()
	{
		return this.hand;
	}
	
	/**
	 * Get hand rank
	 * @return int
	 */
	public int getRank()
	{
		return rank;
	}
}
