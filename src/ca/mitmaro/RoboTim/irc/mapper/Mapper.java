package ca.mitmaro.RoboTim.irc.mapper;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

public interface Mapper {
	
	public Command mapMessage(Message message) throws InvalidCommandException;
	
	public Logger getLogger();
	
}
