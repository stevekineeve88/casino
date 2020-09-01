package application.terminal.games;

import java.util.Enumeration;
import java.util.Hashtable;

import application.interfaces.Game;
import application.terminal.util.Display;
import application.terminal.util.Input;
import tools.util.Card;
import tools.util.Deck;

public class CardWorkout implements Game 
{
	Deck deck;
	Input prompter;
	Hashtable<String, String> workouts = new Hashtable<String, String>();
	String aceWorkout;
	
	@Override
	public void play(int players) throws Exception 
	{
		this.clear();
		this.setup(players);
		while(this.deck.getDeckSize() > 0)
		{
			Card card = this.deck.draw();
			Display.card(card);
			String workout = card.getSymbolValue()+" "+this.workouts.get(card.getSuit());
			if(card.getSymbol().equals(Deck.ACE))
			{
				workout = aceWorkout;
			}
			this.prompter.input(workout);
		}
		this.prompter.close();
		System.out.println("You made it!!!");
	}

	@Override
	public void clear() 
	{
		workouts.clear();
		aceWorkout = null;
	}

	@Override
	public void setup(int players) throws Exception 
	{
		this.prompter = new Input();
		this.deck = new Deck();
		Enumeration<String> suits = this.deck.getSuits();
		while(suits.hasMoreElements())
		{
			String suit = suits.nextElement();
			this.workouts.put(suit, this.prompter.input("Enter workout for "+suit+": "));
		}
		System.out.print("Enter Ace Workout: ");
		this.aceWorkout = this.prompter.input("Enter Ace Workout: ");
	}
}
