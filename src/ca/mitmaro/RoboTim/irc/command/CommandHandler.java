package ca.mitmaro.RoboTim.irc.command;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public interface CommandHandler {
	
	public void handle(Command command) throws InvalidCommandException;
	
	public Logger getLogger();
	
}
