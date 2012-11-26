package ca.mitmaro.RoboTim.irc.command;

public abstract class AbstractCommandHandler implements CommandHandler {

	@Override
	public abstract void handle(Command command) throws InvalidCommandException;

}
