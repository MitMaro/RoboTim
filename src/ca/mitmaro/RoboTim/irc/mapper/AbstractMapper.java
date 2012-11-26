package ca.mitmaro.RoboTim.irc.mapper;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

public abstract class AbstractMapper implements Mapper {
	
	public abstract Command mapMessage(Message message) throws InvalidCommandException;
	
}
