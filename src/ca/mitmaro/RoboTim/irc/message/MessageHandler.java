package ca.mitmaro.RoboTim.irc.message;

public interface MessageHandler {
	
	public void handle(Message message) throws InvalidMessageException;
	
}
