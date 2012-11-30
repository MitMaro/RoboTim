package ca.mitmaro.RoboTim.irc.message;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public interface MessageHandler {
	
	public void handle(Message message) throws InvalidMessage;
	
	public Logger getLogger();
	
}
