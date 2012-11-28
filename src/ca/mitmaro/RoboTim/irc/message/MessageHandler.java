package ca.mitmaro.RoboTim.irc.message;

import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public interface MessageHandler {
	
	public void handle(Message message) throws InvalidMessage;
	
}
