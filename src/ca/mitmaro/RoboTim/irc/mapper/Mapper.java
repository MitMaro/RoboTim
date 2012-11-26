package ca.mitmaro.RoboTim.irc.mapper;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

public interface Mapper {
	
	public Command mapMessage(Message message) throws InvalidCommandException;
	
}
