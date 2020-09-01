package tools.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;
import tools.util.*;
import tools.util.hands.BJHand;

class BlackJackTest 
{
	static Deck deck;
	static BJHand hand;
	
	@BeforeAll
	static void init()
	{
		deck = new Deck();
		hand = new BJHand();
	}
	
	@BeforeEach
	void initTest() throws Exception
	{
		hand.clear();
		deck.shuffle();
	}
	
	@Test
	void test_hand_isCompleteHand() throws Exception 
	{
		hand.addCard(deck.draw());
		hand.addCard(deck.draw());
		hand.addCard(deck.draw());
		assertTrue(hand.isCompleteHand());
	}
	
	@Test
	void test_hand_isHighScoreWithAce() throws Exception
	{
		drawHand(new String[]{Deck.ACE, Deck.KING});
		assertEquals(21, hand.getScores().get(1));
	}
	
	@Test
	void test_hand_isLowerScoreWithAce() throws Exception
	{
		drawHand(new String[]{Deck.ACE, Deck.KING});
		assertEquals(11, hand.getScores().get(0));
	}
	
	/**
	 * Draw a specific card by symbol.
	 * @param symbol
	 * @return Card
	 * @throws Exception
	 */
	private void drawHand(String[] symbols) throws Exception
	{
		List<String> symbolList = new ArrayList<String>(Arrays.asList(symbols));
		while(symbolList.size() != 0)
		{
			Card card = deck.draw();
			if(symbolList.contains(card.getSymbol()))
			{
				hand.addCard(card);
				symbolList.remove(card.getSymbol());
			}
		}
	}
}