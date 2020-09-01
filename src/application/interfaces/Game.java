package application.interfaces;

/**
 * Abstract Class Game
 * Class for basic setup and playing a specified game.
 */
public interface Game 
{	
	/**
	 * Play the game
	 * @throws Exception 
	 */
	public abstract void play(int players) throws Exception;
	
	/**
	 * Clear the game.
	 */
	public abstract void clear();
	
	
	/*
	 * Setup all players and other necessary data.
	 */
	public void setup(int players) throws Exception;
}
