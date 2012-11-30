package ca.mitmaro.RoboTim.irc.command;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public abstract class AbstractCommandHandler implements CommandHandler {

	public abstract void handle(Command command) throws InvalidCommandException;
	
	public abstract Logger getLogger();

}
