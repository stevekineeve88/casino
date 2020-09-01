package tools.util.hands;

import java.util.ArrayList;
import java.util.Collections;

import tools.util.Card;
import tools.util.abstracts.Hand;

/**
 * Class BJHand
 * Extends Hand for a Black Jack Hand.
 */
public class BJHand extends Hand
{
	
	/**
	 * BJHand Constructor
	 */
	public BJHand()
	{
		super();
	}
	
	/**
	 * BJHand Constructor
	 * @param hand
	 */
	public BJHand(ArrayList<Card> hand) 
	{
		super(hand);
	}
	
	/**
	 * Get current score
	 * @return int
	 */
	public int getScore(boolean aceHigh)
	{
		int score = 0;
		for(Card card: this.hand)
		{
			score += card.getSymbolValue(aceHigh);
		}
		return score;
	}
	
	/**
	 * Get scores.
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getScores()
	{
		int combinations = (int) Math.pow(2, this.hand.size());
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for(int i = 0; i < combinations; i++)
		{
			String binaryString = Integer.toBinaryString(i);
			String mask = String.join("", Collections.nCopies(this.hand.size() - binaryString.length(), "0"));
			binaryString = mask + binaryString;
			int score = 0;
			for(int index = 0; index < binaryString.length(); index++)
			{
				score += this.hand.get(index).getSymbolValue(binaryString.charAt(index) == '1' ? true : false);
			}
			if(score <= 21 && !scores.contains(score))
			{
				scores.add(score);
			}
		}
		Collections.sort(scores);
		return scores;
	}

	@Override
	public boolean isCompleteHand() 
	{
		return this.hand.size() >= 2;
	}
	
}
