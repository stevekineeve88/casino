package application.terminal.games;

import java.util.ArrayList;
import java.util.Hashtable;
import application.interfaces.Game;
import application.terminal.util.*;
import application.terminal.util.interfaces.*;
import application.util.Result;
import tools.util.*;
import tools.util.hands.BJHand;

/**
 * Class BlackJack
 * For playing game of Blackjack
 */
public class BlackJack implements Game
{
	private Deck deck;
	private ArrayList<BJHand> hands;
	private BJHand dealer;
	private Input prompter;
	
	//Commands
	private static final String HIT = "H";
	private static final String STAY = "S";
	private static final String HAND = "HAND";
	private static final String SCORE = "SCORE";
	
	//Options
	private static final String CURRENT_HAND = "Hand";
	private static final String DECK = "Deck";
	private static final String ROUND = "Round";
	
	/**
	 * BlackJack Constructor.
	 */
	public BlackJack()
	{
		this.deck = new Deck();
		this.hands = new ArrayList<BJHand>();
		this.dealer = new BJHand();
		this.prompter = new Input();
		this.prompter.addClearCommand();
		this.prompter.addInstructionsCommand();
	}
	
	@Override
	public void play(int players) throws Exception 
	{
		try
		{
			this.clear();
			this.setup(players);
			System.out.println("---START---");
			for(int i = 0; i < this.hands.size(); i++)
			{
				BJHand currentHand = this.hands.get(i);
				Result round = new Result();
				round.setStatus(false);
				ArrayList<Integer> scores = new ArrayList<Integer>();
				Display.hand(currentHand);
				Display.blackJackScore(currentHand);
				Hashtable<String, Object> options = new Hashtable<String, Object>();
				options.put(BlackJack.CURRENT_HAND, currentHand);
				options.put(BlackJack.DECK, this.deck);
				options.put(BlackJack.ROUND, round);
				while(!round.getStatus()) 
				{
					scores = currentHand.getScores();
					if(scores.isEmpty() || scores.get(scores.size() - 1) == 21)
					{
						break;
					}
					this.prompter.input("Press i for instructions: ", options);
				}
				System.out.println(this.displayPlayerRound(scores));
			}
			this.prompter.close();
		}
		catch(Exception e)
		{
			System.out.println("Whoops...something went wrong");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void clear() 
	{
		this.hands.clear();
		this.deck.shuffle();
	}
	
	@Override
	public void setup(int players) throws Exception 
	{
		this.deck.shuffle();
		for(int i = 0; i < players; i++)
		{
			BJHand hand = new BJHand();
			hand.addCard(this.deck.draw());
			hand.addCard(this.deck.draw());
			this.hands.add(hand);
		}
		this.dealer.addCard(this.deck.draw());
		this.dealer.addCard(this.deck.draw());
		Command hit = new Command(BlackJack.HIT, "Hit me with a card");
		hit.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				BJHand hand = (BJHand) options.get(BlackJack.CURRENT_HAND);
				Deck deck = (Deck) options.get(BlackJack.DECK);
				hand.addCard(deck.draw());
				Display.hand(hand);
			}
		});
		Command stay = new Command(BlackJack.STAY, "Stay with your hand");
		stay.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				Result round = (Result) options.get(BlackJack.ROUND);
				round.setStatus(true);
			}
		});
		Command hand = new Command(BlackJack.HAND, "Display Hand");
		hand.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				BJHand hand = (BJHand) options.get(BlackJack.CURRENT_HAND);
				Display.hand(hand);
			}
		});
		Command score = new Command(BlackJack.SCORE, "Display score");
		score.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				BJHand hand = (BJHand) options.get(BlackJack.CURRENT_HAND);
				Display.blackJackScore(hand);
			}
		});
		this.prompter.addCommand(hit);
		this.prompter.addCommand(stay);
		this.prompter.addCommand(hand);
		this.prompter.addCommand(score);
	}
	
	/**
	 * Print score UI
	 * @param hand
	 * @return
	 */
	private String displayPlayerRound(ArrayList<Integer> scores)
	{
		if(scores.isEmpty())
		{
			return "BUST!!";
		}
		Integer score = scores.get(scores.size() - 1);
		if(score == 21)
		{
			return "21!!!";
		}
		if(score > 18)
		{
			return "Nice! you scored a " + score;
		}
		if(score > 15)
		{
			return "Good! you scored a " + score;
		}
		return "You scored a " + score;
	}
}