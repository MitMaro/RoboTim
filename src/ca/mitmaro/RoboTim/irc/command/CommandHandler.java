package ca.mitmaro.RoboTim.irc.command;

public interface CommandHandler {
	
	public void handle(Command command) throws InvalidCommandException;
	
}
