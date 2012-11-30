package ca.mitmaro.RoboTim.irc.mapper;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

public abstract class AbstractMapper implements Mapper {
	
	protected Logger logger = Logger.getLogger(AbstractMapper.class.getName());
	
	public abstract Command mapMessage(Message message) throws InvalidCommandException;
	
	public Logger getLogger() {
		return this.logger;
	}
	
}
