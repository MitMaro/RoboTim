package ca.mitmaro.RoboTim.irc.command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.mapper.Mapper;
import ca.mitmaro.RoboTim.irc.message.Message;

public class CommandMapper {
	
	private List<Mapper> mappers;
	
	private Logger logger = LoggerFactory.getLogger(CommandMapper.class);
	
	public CommandMapper() {
		this.mappers = new ArrayList<Mapper>();
	}
	
	public void registerMapper(Mapper command) {
		this.logger.debug("Adding mapper: {}", command);
		this.mappers.add(command);
	}
	
	public Command mapMessageToCommand(Message message) throws InvalidCommandException {
		Command c;
		this.logger.debug("Mapping message: {}", message);
		for(Mapper mapper: this.mappers) {
			 c = mapper.mapMessage(message);
			 if (c != null) {
				 this.logger.debug("Command mapped using: {}", mapper);
				 return c;
			 }
		}
		return null;
	}
}
