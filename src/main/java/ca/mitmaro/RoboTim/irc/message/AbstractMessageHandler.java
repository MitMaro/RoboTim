package ca.mitmaro.RoboTim.irc.message;

import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public abstract class AbstractMessageHandler implements MessageHandler {
	
	public abstract void handle(Message message) throws InvalidMessage;
	
}
