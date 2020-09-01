package tools.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import tools.util.*;

public class CardsTest 
{
	static Deck deck;
	
	@BeforeAll
	static void init()
	{
		deck = new Deck();
	}
	
	@BeforeEach
	void initTest()
	{
		deck.shuffle();
	}
	
	@AfterAll
	static void cleanUp()
	{
		System.out.println("Cleaning up");
	}
	
	@Test
	void test_shuffle_returnsFullDeck() throws Exception
	{
		int fullDeck = deck.getDeckSize();
		deck.draw();
		deck.shuffle();
		assertEquals(fullDeck, deck.getDeckSize());
	}
	
	@Test
	void test_drawCard_getSuitValue() throws Exception
	{
		Card card = drawCard(Deck.ACE, Deck.SPADES);
		assertEquals(1, card.getSuitValue());
	}
	
	@Test
	void test_drawAceLow_getSymbolOrder() throws Exception
	{
		Card card = drawCard(Deck.ACE);
		assertEquals(1, card.getSymbolOrder(false));
	}
	
	@Test
	void test_drawAceHigh_getSymbolOrder() throws Exception
	{
		Card card = drawCard(Deck.ACE);
		assertEquals(14, card.getSymbolOrder(true));
	}
	
	@Test
	void test_drawFaceCard_getSymbolValue() throws Exception
	{
		Card card = drawCard(Deck.KING);
		assertEquals(10, card.getSymbolValue());
	}
	
	@Test
	void test_drawAceLow_getSymbolValue() throws Exception
	{
		Card card = drawCard(Deck.ACE);
		assertEquals(1, card.getSymbolValue(false));
	}
	
	@Test
	void test_drawAceHigh_getSymbolValue() throws Exception
	{
		Card card = drawCard(Deck.ACE);
		assertEquals(11, card.getSymbolValue(true));
	}
	
	/**
	 * Draw a specific card by symbol.
	 * @param symbol
	 * @return Card
	 * @throws Exception
	 */
	private Card drawCard(String symbol) throws Exception
	{
		Card card = deck.draw();
		while(!card.getSymbol().equals(symbol))
		{
			card = deck.draw();
		}
		return card;
	}
	
	/**
	 * Draw a specific card by symbol and suit
	 * @param symbol
	 * @param suit
	 * @return Card
	 * @throws Exception
	 */
	private Card drawCard(String symbol, String suit) throws Exception
	{
		Card card = deck.draw();
		while(!card.getSymbol().equals(symbol) && !card.getSuit().equals(suit))
		{
			card = deck.draw();
		}
		return card;
	}
}
