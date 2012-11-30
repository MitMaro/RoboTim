package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.CommandHandler;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.mapper.Mapper;
import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public class CommandMapper implements MessageHandler {
	
	private List<Mapper> mappers;
	
	private List<CommandHandler> handlers;
	
	private Logger logger = LoggerFactory.getLogger(CommandMapper.class);
	
	public CommandMapper() {
		this.mappers = new ArrayList<Mapper>();
		this.handlers = new ArrayList<CommandHandler>();
	}
	
	public void registerMapper(Mapper command) {
		this.logger.debug("Adding mapper: {}", command);
		this.mappers.add(command);
	}
	
	public void registerCommandHandler(CommandHandler handler) {
		this.logger.debug("Adding command handler: {}", handler);
		this.handlers.add(handler);
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

	public void handle(Message message) throws InvalidMessage {
		try {
			Command c = this.mapMessageToCommand(message);
			if (c != null) {
				this.logger.debug("Handling Command: {}", c);
				for (CommandHandler h: this.handlers) {
					h.handle(c);
				}
			}
			
		} catch (InvalidCommandException e) {
			this.logger.warn("Invalid command for message: {}", message, e);
			throw new InvalidMessage(e);
		}
	}
}
