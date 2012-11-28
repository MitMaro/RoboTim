package ca.mitmaro.RoboTim.irc.command;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public interface CommandHandler {
	
	public void handle(Command command) throws InvalidCommandException;
	
}
