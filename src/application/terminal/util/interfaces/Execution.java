package application.terminal.util.interfaces;

import java.util.Hashtable;

/**
 * Interface Execution
 * Interface for creating a runnable command.
 */
public interface Execution 
{
	/**
	 * Run command.
	 * @param options
	 * @throws Exception
	 */
	public void run(Hashtable<String, Object> options) throws Exception;
}
