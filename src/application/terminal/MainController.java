package application.terminal;

import java.io.IOException;
import java.util.Hashtable;
import application.terminal.games.*;
import application.terminal.util.*;
import application.terminal.util.interfaces.Execution;

public class MainController {

	public static void main(String[] args) throws IOException 
	{
		Input commands = new Input();
		Command blackjack = new Command("-blackjack", "Play Blackjack");
		blackjack.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				BlackJack game = new BlackJack();
				game.play(1);
			}
		});
		Command cardWorkout = new Command("-cardwo", "Card Workout");
		cardWorkout.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				CardWorkout game = new CardWorkout();
				game.play(1);
			}
		});
		commands.addCommand(blackjack);
		commands.addCommand(cardWorkout);
		try
		{
			commands.forceInput(args[0], new Hashtable<String, Object>());
		}
		catch(Exception e)
		{
			Display.instructions(commands);
		}
	}
}
