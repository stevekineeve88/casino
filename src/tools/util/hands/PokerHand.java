package tools.util.hands;

import java.util.ArrayList;
import java.util.Hashtable;

import tools.util.*;
import tools.util.abstracts.Hand;

/**
 * Class FCHand
 * Object representing five card poker hand.
 */
public class PokerHand extends Hand
{
	public static final String ROYAL_FLUSH = "Royal Flush";
	public static final String STRAIGHT_FLUSH = "Straight Flush";
	public static final String QUAD = "Quad";
	public static final String FULL_HOUSE = "Full House";
	public static final String FLUSH = "Flush";
	public static final String STRAIGHT = "Straight";
	public static final String TRIPLE = "Triple";
	public static final String TWO_PAIR = "Two Pair";
	public static final String PAIR = "Pair";
	public static final String HIGH_CARD = "High Card";
	private ArrayList<PokerHandRank> P_HAND_TABLE = new ArrayList<PokerHandRank>();
	
	/**
	 * FCHand Constructor
	 */
	public PokerHand()
	{
		super();
		init();
	}
	
	/**
	 * FCHand Constructor
	 * @param hand
	 */
	public PokerHand(ArrayList<Card> hand)
	{
		super(hand);
		init();
	}
	
	@Override
	public boolean isCompleteHand() 
	{
		return this.hand.size() == 5;
	}
	
	/**
	 * Get highest ranked hand
	 * @return PokerHandRank
	 * @throws CloneNotSupportedException 
	 */
	public PokerHandRank getHigherHand()
	{
		Hashtable<String, Boolean> statuses = new Hashtable<String, Boolean>();
		statuses.put(PokerHand.ROYAL_FLUSH, this.isRoyalFlush());
		statuses.put(PokerHand.STRAIGHT_FLUSH, this.isStraightFlush());
		statuses.put(PokerHand.QUAD, this.isQuad());
		statuses.put(PokerHand.FULL_HOUSE, this.isFullHouse());
		statuses.put(PokerHand.FLUSH, this.isFlush());
		statuses.put(PokerHand.STRAIGHT, this.isStraight());
		statuses.put(PokerHand.TRIPLE, this.isTriple());
		statuses.put(PokerHand.TWO_PAIR, this.isTwoPair());
		statuses.put(PokerHand.PAIR, this.isPair());
		statuses.put(PokerHand.HIGH_CARD, true);
		int highestRank = 0;
		PokerHandRank highestObj = null;
		for(PokerHandRank phr: this.P_HAND_TABLE)
		{
			if(phr.getRank() > highestRank)
			{
				highestObj = phr;
				highestRank = phr.getRank();
			}
		}
		return highestObj;
	}

	/**
	 * Is a pair
	 * @return boolean
	 */
	public boolean isPair() 
	{
		for(int i = 0; i < this.hand.size(); i++)
		{
			String currentSymbol = this.hand.get(i).getSymbol();
			int next = i + 1;
			while(true)
			{
				if(next >= this.hand.size())
				{
					next = 0;
				}
				if(i == next)
				{
					break;
				}
				if(currentSymbol.equals(this.hand.get(next).getSymbol()))
				{
					return true;
				}
				next++;
			}
		}
		return false;
	}

	/**
	 * Is a triple
	 * @return boolean
	 */
	public boolean isTriple() 
	{
		for(int i = 0; i < this.hand.size(); i++)
		{
			String currentSymbol = this.hand.get(i).getSymbol();
			int dups = 0;
			int next = i + 1;
			while(true)
			{
				if(next >= this.hand.size())
				{
					next = 0;
				}
				if(i == next)
				{
					break;
				}
				if(currentSymbol.equals(this.hand.get(next).getSymbol()))
				{
					dups++;
				}
				if(dups == 2)
				{
					return true;
				}
				next++;
			}
		}
		return false;
	}

	/**
	 * Is a quad
	 * @return boolean
	 */
	public boolean isQuad() 
	{
		for(int i = 0; i < this.hand.size(); i++)
		{
			String currentSymbol = this.hand.get(i).getSymbol();
			int dups = 0;
			int next = i + 1;
			while(true)
			{
				if(next >= this.hand.size())
				{
					next = 0;
				}
				if(i == next)
				{
					break;
				}
				if(currentSymbol.equals(this.hand.get(next).getSymbol()))
				{
					dups++;
				}
				if(dups == 3)
				{
					return true;
				}
				next++;
			}
		}
		return false;
	}

	/**
	 * Is a two pair
	 * @return boolean
	 */
	public boolean isTwoPair() 
	{
		ArrayList<String> pairs = new ArrayList<String>();
		for(int i = 0; i < this.hand.size(); i++)
		{
			String currentSymbol = this.hand.get(i).getSymbol();
			int next = i + 1;
			while(true)
			{
				if(next >= this.hand.size())
				{
					next = 0;
				}
				if(i == next)
				{
					break;
				}
				if(currentSymbol.equals(this.hand.get(next).getSymbol()) && !pairs.contains(currentSymbol))
				{
					pairs.add(currentSymbol);
					break;
				}
				next++;
			}
		}
		return pairs.size() == 2;
	}

	/**
	 * Is a full house
	 * @return boolean
	 */
	public boolean isFullHouse() 
	{
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < this.hand.size(); i++)
		{
			String currentSymbol = this.hand.get(i).getSymbol();
			int dups = 0;
			int next = i + 1;
			while(true)
			{
				if(next >= this.hand.size())
				{
					next = 0;
				}
				if(i == next)
				{
					break;
				}
				if(currentSymbol.equals(this.hand.get(next).getSymbol()))
				{
					dups++;
				}
				next++;
			}
			if((dups == 1 || dups == 2) && !items.contains(dups))
			{
				items.add(dups);
			}
		}
		return items.size() == 2;
	}

	/**
	 * Is a straight
	 * @return boolean
	 * @throws CloneNotSupportedException 
	 */
	public boolean isStraight()
	{
		Boolean[] aceHighs = {true, false};
		for(Boolean aceHigh: aceHighs)
		{
			this.orderBySymbolOrder(aceHigh);
			ArrayList<Card> cards = this.getHand();
			boolean isStraight = true;
			for(int i = 1; i < 5 && i < cards.size(); i++)
			{
				if(cards.get(i).getSymbolOrder(aceHigh) != cards.get(i-1).getSymbolOrder(aceHigh)+1)
				{
					isStraight = false;
					break;
				}
			}
			if(isStraight)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Is a flush
	 * @return boolean
	 */
	public boolean isFlush() 
	{
		ArrayList<Card> hand = this.getHand();
		for(int i = 1; i < 5 && i < hand.size(); i++)
		{
			if(!hand.get(i).getSuit().equals(hand.get(i-1).getSuit()))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Is a straight flush
	 * @return boolean
	 */
	public boolean isStraightFlush() 
	{
		Boolean[] aceHighs = {true, false};
		for(Boolean aceHigh: aceHighs)
		{
			this.orderBySymbolOrder(aceHigh);
			ArrayList<Card> cards = this.getHand();
			boolean isStraightFlush = true;
			for(int i = 1; i < 5 && i < cards.size(); i++)
			{
				Card current = cards.get(i);
				Card previous = cards.get(i - 1);
				if(current.getSymbolOrder(aceHigh) != previous.getSymbolOrder(aceHigh)+1
				   || !current.getSuit().equals(previous.getSuit()))
				{
					isStraightFlush = false;
					break;
				}
			}
			if(isStraightFlush)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Is a royal flush
	 * @return
	 */
	public boolean isRoyalFlush() 
	{
		boolean isStraightFlush = this.isStraightFlush();
		this.orderBySymbolOrder(true);
		ArrayList<Card> hand = this.getHand();
		return isStraightFlush && hand.get(hand.size()-1).getSymbol().equals(Deck.ACE);
	}
	
	/**
	 * Initialize Poker Ranks
	 */
	private void init()
	{
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.ROYAL_FLUSH, 10));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.STRAIGHT_FLUSH, 9));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.QUAD, 8));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.FULL_HOUSE, 7));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.FLUSH, 6));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.STRAIGHT, 5));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.TRIPLE, 4));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.TWO_PAIR, 3));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.PAIR, 2));
		P_HAND_TABLE.add(new PokerHandRank(PokerHand.HIGH_CARD, 1));
	}
}
