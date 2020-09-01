package application.terminal.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import application.terminal.util.interfaces.Execution;

/**
 * Class Input
 * Object for handling input commands.
 */
public class Input 
{
	private ArrayList<Command> commands = new ArrayList<Command>();
	private Scanner scanner;
	
	//Commands
	private static final String INSTR = "I";
	private static final String CLEAR = "CLEAR";
	
	/**
	 * Input Constructor
	 */
	public Input()
	{
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Add command
	 * @param command
	 * @param description
	 */
	public void addCommand(Command command)
	{
		commands.add(command);
	}
	
	public ArrayList<Command> getCommands()
	{
		return this.commands;
	}
	
	/**
	 * Return any input.
	 * @param prompt
	 * @return String
	 */
	public String input(String prompt)
	{
		System.out.print(prompt);
		return this.scanner.nextLine();
	}
	
	/**
	 * Input prompt.
	 * @param prompt
	 * @return
	 * @throws Exception 
	 */
	public Command input(String prompt, Hashtable<String, Object> options) throws Exception
	{
		while(true)
		{	
			System.out.print(prompt);
			String input = this.scanner.nextLine();
			for(Command command: this.commands)
			{
				if(input.toLowerCase().equals(command.getCommand().toLowerCase()))
				{
					command.execute(options);
					return command;
				}
			}
		}
	}
	
	public Command forceInput(String input, Hashtable<String, Object> options) throws Exception
	{
		for(Command command: this.commands)
		{
			if(input.toLowerCase().equals(command.getCommand().toLowerCase()))
			{
				command.execute(options);
				return command;
			}
		}
		throw new Exception("Command not found.");
	}

	public void addClearCommand()
	{
		Command clear = new Command(Input.CLEAR, "clear input");
		clear.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				Display.clear();
			}
		});
		this.addCommand(clear);
	}
	
	public void addInstructionsCommand()
	{
		Input input = this;
		Command instructions = new Command(Input.INSTR, "Instructions");
		instructions.setRunnable(new Execution() {
			public void run(Hashtable<String, Object> options) throws Exception {
				Display.instructions(input);
			}
		});
		this.addCommand(instructions);
	}
	
	/**
	 * Close scanner
	 */
	public void close()
	{
		this.scanner.close();
	}
}
