package application.terminal.util;

import java.util.Hashtable;
import application.terminal.util.interfaces.Execution;

/**
 * Class Command
 * Object for storing information about a command.
 */
public class Command 
{
	String command;
	String description;
	Execution execution;
	
	/**
	 * Command Constructor.
	 * @param command
	 * @param description
	 */
	public Command(String command, String description)
	{
		this.command = command;
		this.description = description;
	}
	
	/**
	 * Get command
	 * @return String
	 */
	public String getCommand()
	{
		return this.command;
	}
	
	/**
	 * Get description
	 * @return String
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Set runnable command.
	 * @param execution
	 */
	public void setRunnable(Execution execution)
	{
		this.execution = execution;
	}
	
	/**
	 * Execute set command.
	 * @param options
	 * @throws Exception
	 */
	public void execute(Hashtable<String, Object> options) throws Exception
	{
		if(this.execution != null)
		{
			this.execution.run(options);
		}
	}
}
