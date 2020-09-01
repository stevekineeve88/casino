package application.terminal.util;

import java.io.IOException;
import java.util.ArrayList;

import tools.util.Card;
import tools.util.abstracts.Hand;
import tools.util.hands.BJHand;

public class Display 
{
	public static void card(Card card)
	{
		System.out.println(">>"+card.getSymbol()+"-"+card.getSuit().substring(0, 3));
	}
	
	public static void hand(Hand hand)
	{
		for(Card card: hand.getHand())
		{
			Display.card(card);
		}
	}
	
	public static void blackJackScore(BJHand hand)
	{
		ArrayList<Integer> scores = hand.getScores();
		String scoreDisplay = "";
		for(Integer score: scores)
		{
			scoreDisplay += score+"/";
		}
		System.out.println(">>"+scoreDisplay.substring(0, scoreDisplay.length()-1));
	}
	
	public static void instructions(Input input) throws IOException
	{
		ArrayList<Command> commands = input.getCommands();
		System.out.println("-----------------------");
		for(Command command: commands)
		{
			System.out.println(command.getCommand()+" > "+command.getDescription());
		}
		System.out.println("-----------------------");
	}
	
	public static void clear()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
