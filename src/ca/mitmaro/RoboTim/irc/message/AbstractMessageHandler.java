package ca.mitmaro.RoboTim.irc.message;

public abstract class AbstractMessageHandler implements MessageHandler {
	
	public abstract void handle(Message message) throws InvalidMessageException;
	
}
