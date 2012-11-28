package ca.mitmaro.RoboTim.irc.command;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public abstract class AbstractCommandHandler implements CommandHandler {

	@Override
	public abstract void handle(Command command) throws InvalidCommandException;

}
