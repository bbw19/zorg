package zork;

import zork.Commands.*;

import java.util.*;

/**
 * Class Command - Part of the "Zork" game.
 * 
 * author: Michael Kolling version: 1.0 date: July 1999
 * 
 * This class holds information about a command that was issued by the user. A
 * command currently consists of two strings: a command word and a second word
 * (for example, if the command was "take map", then the two strings obviously
 * are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid command
 * words. If the user entered an invalid command (a word that is not known) then
 * the command word is <null>.
 * 
 * If the command had only one word, then the second word is <null>.
 * 
 * The second word is not checked at the moment. It can be anything. If this
 * game is extended to deal with items, then the second part of the command
 * should probably be changed to be an item rather than a String.
 */

public class Command {

	//All valid commands in a list
	private static final List<ICommandRunner> validCommands = new ArrayList<>();
	
	private String commandWord;
	private String secondWord;

	//Set all valid commands
	public static void InitialzeValidCommands(){
		validCommands.add(new CommandGo());
		validCommands.add(new CommandHelp());
		validCommands.add(new CommandQuit());
		//validCommands.add(new CommandTest());
		validCommands.add(new CommandMap());
		validCommands.add(new CommandUseKey());
		validCommands.add(new CommandPickup());
		validCommands.add(new CommandBack());
	}

	/**
	 * Create a command object. First and second word must be supplied, but
	 * either one (or both) can be null. The command word should be null to
	 * indicate that this was a command that is not recognised by this game.
	 */
	public Command(String firstWord, String secondWord) {
		this.commandWord = firstWord;
		this.secondWord = secondWord;
	}

	public void processCommand(){
		for (ICommandRunner validCommand : validCommands){
			if (validCommand.GetCommandName().equals(commandWord)){
				validCommand.RunCommand(this);
				return;
			}
		}

		System.out.println("Command not found!");
	}

	//String of all available commands
	public static String showAll() {
		String outputStr="";
		for (int i=0; i < validCommands.size(); i++) {
			outputStr  += validCommands.get(i).GetCommandName() + "  ";
		}
		return outputStr;
	}

	/**
	 * Return the command word (the first word) of this command. If the command
	 * was not understood, the result is null.
	 */
	public String getCommandWord() {
		return commandWord;
	}

	/**
	 * Return the second word of this command. Returns null if there was no
	 * second word.
	 */
	public String getSecondWord() {
		return secondWord;
	}

	/**
	 * Return true if this command was not understood.
	 */
	public boolean isUnknown() {
		return (commandWord == null);
	}

	/**
	 * Return true if the command has a second word.
	 */
	public boolean hasSecondWord() {
		return (secondWord != null);
	}
}
