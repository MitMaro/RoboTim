package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.CommandHandler;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.mapper.Mapper;
import ca.mitmaro.RoboTim.irc.message.InvalidMessageException;
import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;

public class CommandMapper implements MessageHandler {
	
	private List<Mapper> mappers;
	
	private List<CommandHandler> handlers;
	
	public CommandMapper() {
		this.mappers = new ArrayList<Mapper>();
		this.handlers = new ArrayList<CommandHandler>();
	}
	
	public void registerMapper(Mapper command) {
		this.mappers.add(command);
	}
	
	public void registerCommandHandler(CommandHandler handler) {
		this.handlers.add(handler);
	}
	
	public Command mapMessageToCommand(Message message) throws InvalidCommandException {
		Command c;
		for(Mapper mapper: this.mappers) {
			 c = mapper.mapMessage(message);
			 
			 if (c != null) {
				 return c;
			 }
		}
		return null;
	}

	@Override
	public void handle(Message message) throws InvalidMessageException {
		try {
			Command c = this.mapMessageToCommand(message);
			
			if (c != null) {
				for (CommandHandler h: this.handlers) {
					h.handle(c);
				}
			}
			
		} catch (InvalidCommandException e) {
			throw new InvalidMessageException(e);
		}
	}
}
