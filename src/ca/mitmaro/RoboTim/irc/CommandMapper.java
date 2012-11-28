package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	
	private Logger logger;
	
	public CommandMapper() {
		this.mappers = new ArrayList<Mapper>();
		this.handlers = new ArrayList<CommandHandler>();
		this.logger = Logger.getLogger(this.getClass().getName());
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	public void registerMapper(Mapper command) {
		this.logger.finer(String.format("Adding mapper: %s", command.toString()));
		this.mappers.add(command);
	}
	
	public void registerCommandHandler(CommandHandler handler) {
		this.logger.finer(String.format("Adding command handler: %s", handler.toString()));
		this.handlers.add(handler);
	}
	
	public Command mapMessageToCommand(Message message) throws InvalidCommandException {
		Command c;
		this.logger.finer(String.format("Mapping: %s", message.toString()));
		for(Mapper mapper: this.mappers) {
			 c = mapper.mapMessage(message);
			 if (c != null) {
				 this.logger.finest(String.format("Command mapper using: %s", mapper.toString()));
				 return c;
			 }
		}
		return null;
	}

	@Override
	public void handle(Message message) throws InvalidMessage {
		try {
			Command c = this.mapMessageToCommand(message);
			if (c != null) {
				this.logger.finer(String.format("Handling Command: %s", c.getCommandName()));
				for (CommandHandler h: this.handlers) {
					h.handle(c);
				}
			}
			
		} catch (InvalidCommandException e) {
			this.logger.warning(String.format("Invalid command for message: %s", message.toString()));
			throw new InvalidMessage(e);
		}
	}
}
