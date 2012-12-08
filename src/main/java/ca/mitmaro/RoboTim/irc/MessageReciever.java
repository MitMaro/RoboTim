package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.CommandHandler;
import ca.mitmaro.RoboTim.irc.command.CommandMapper;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public class MessageReciever implements MessageHandler {
	
	private CommandMapper mapper;
	
	private List<CommandHandler> handlers;
	
	private Logger logger = LoggerFactory.getLogger(CommandHandler.class);
	
	public MessageReciever(CommandMapper mapper) {
		this.mapper = mapper;
		this.handlers = new ArrayList<CommandHandler>();
	}
	
	public void registerCommandHandler(CommandHandler handler) {
		this.logger.debug("Adding command handler: {}", handler);
		this.handlers.add(handler);
	}
	
	public void handle(Message message) throws InvalidMessage {
		try {
			Command c = this.mapper.mapMessageToCommand(message);
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
