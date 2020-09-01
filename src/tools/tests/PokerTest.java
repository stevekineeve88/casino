package tools.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

import tools.util.Card;
import tools.util.Deck;
import tools.util.hands.PokerHand;

class PokerTest 
{
	static Deck deck;
	static PokerHand hand;
	
	@BeforeAll
	static void init()
	{
		deck = new Deck();
		hand = new PokerHand();
	}
	
	@BeforeEach
	void initTest()
	{
		hand.clear();
		deck.shuffle();
	}

	@Test
	void test_hand_isPair() throws Exception 
	{
		drawHand(new String[] {
			Deck.KING,
			Deck.QUEEN,
			Deck.ACE,
			Deck.JACK,
			Deck.ACE
		});
		assertTrue(hand.isPair());
	}
	
	@Test
	void test_hand_isTriple() throws Exception 
	{
		drawHand(new String[] {
			Deck.KING,
			Deck.QUEEN,
			Deck.ACE,
			Deck.ACE,
			Deck.ACE
		});
		assertTrue(hand.isTriple());
	}
	
	@Test
	void test_hand_isQuad() throws Exception 
	{
		drawHand(new String[] {
			Deck.KING,
			Deck.ACE,
			Deck.ACE,
			Deck.ACE,
			Deck.ACE
		});
		assertTrue(hand.isQuad());
	}
	
	@Test
	void test_hand_isTwoPair() throws Exception 
	{
		drawHand(new String[] {
			Deck.KING,
			Deck.KING,
			Deck.QUEEN,
			Deck.ACE,
			Deck.ACE
		});
		assertTrue(hand.isTwoPair());
	}
	
	@Test
	void test_hand_isFullHouse() throws Exception 
	{
		drawHand(new String[] {
			Deck.KING,
			Deck.KING,
			Deck.KING,
			Deck.ACE,
			Deck.ACE
		});
		assertTrue(hand.isFullHouse());
	}
	
	@Test
	void test_hand_isStraightAceLow() throws Exception 
	{
		drawHand(new String[] {
			Deck.ACE,
			"2",
			"4",
			"5",
			"3"
		});
		assertTrue(hand.isStraight());
	}
	
	@Test
	void test_hand_isStraightAceHigh() throws Exception
	{
		drawHand(new String[] {
			Deck.ACE,
			"10",
			Deck.KING,
			Deck.QUEEN,
			Deck.JACK
		});
		assertTrue(hand.isStraight());
	}
	
	@Test
	void test_hand_isflush() throws Exception 
	{
		drawHand(new String[][] {
			new String[] {Deck.ACE, Deck.SPADES},
			new String[] {Deck.JACK, Deck.SPADES},
			new String[] {Deck.QUEEN, Deck.SPADES},
			new String[] {Deck.KING, Deck.SPADES},
			new String[] {"5", Deck.SPADES}
		});
		assertTrue(hand.isFlush());
	}
	
	@Test
	void test_hand_isStraightFlushAceLow() throws Exception 
	{
		drawHand(new String[][] {
			new String[] {Deck.ACE, Deck.SPADES},
			new String[] {"4", Deck.SPADES},
			new String[] {"2", Deck.SPADES},
			new String[] {"5", Deck.SPADES},
			new String[] {"3", Deck.SPADES}
		});
		assertTrue(hand.isStraightFlush());
	}
	
	@Test
	void test_hand_isRoyalFlush() throws Exception 
	{
		drawHand(new String[][] {
			new String[] {Deck.ACE, Deck.SPADES},
			new String[] {Deck.KING, Deck.SPADES},
			new String[] {"10", Deck.SPADES},
			new String[] {Deck.JACK, Deck.SPADES},
			new String[] {Deck.QUEEN, Deck.SPADES}
		});
		assertTrue(hand.isRoyalFlush());
	}
	
	@Test
	void test_hand_isOrderedByValueAceLow() throws Exception
	{
		drawHand(new String[] {"2", "4", "3", "5", Deck.ACE});
		hand.orderBySymbolValue(false);
		ArrayList<Card> cards = hand.getHand();
		for(int i = 1; i < cards.size(); i++)
		{
			int previous = cards.get(i-1).getSymbolValue(false);
			assertEquals(cards.get(i).getSymbolValue(false), previous+1);
		}
	}
	
	@Test
	void test_hand_isOrderedByValueAceHigh() throws Exception
	{
		drawHand(new String[] {Deck.JACK, "10", Deck.ACE, Deck.QUEEN, Deck.KING});
		hand.orderBySymbolValue(true);
		ArrayList<Card> cards = hand.getHand();
		assertEquals(cards.get(4).getSymbol(), Deck.ACE);
	}
	
	@Test
	void test_hand_isOrderedByOrderAceLow() throws Exception
	{
		drawHand(new String[] {"2", "4", "3", "5", Deck.ACE});
		hand.orderBySymbolOrder(false);
		ArrayList<Card> cards = hand.getHand();
		for(int i = 1; i < cards.size(); i++)
		{
			int previous = cards.get(i-1).getSymbolOrder(false);
			assertEquals(cards.get(i).getSymbolOrder(false), previous+1);
		}
	}
	
	@Test
	void test_hand_isOrderedByOrderAceHigh() throws Exception
	{
		drawHand(new String[] {Deck.JACK, "10", Deck.ACE, Deck.QUEEN, Deck.KING});
		hand.orderBySymbolOrder(true);
		ArrayList<Card> cards = hand.getHand();
		for(int i = 1; i < cards.size(); i++)
		{
			int previous = cards.get(i-1).getSymbolOrder(true);
			assertEquals(cards.get(i).getSymbolOrder(true), previous+1);
		}
	}
	
	/**
	 * Create hand by symbols.
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
	
	/**
	 * Create hand by suits and symbols.
	 * @param cards
	 * @throws Exception
	 */
	private void drawHand(String[][] cards) throws Exception
	{
		List<String[]> cardList = new ArrayList<String[]>(Arrays.asList(cards));
		while(cardList.size() != 0)
		{
			Card card = deck.draw();
			for(int i = 0; i < cardList.size(); i++)
			{
				if(card.getSymbol().equals(cardList.get(i)[0]) && card.getSuit().equals(cardList.get(i)[1]))
				{
					hand.addCard(card);
					cardList.remove(i);
					break;
				}
			}
		}
	}
}
