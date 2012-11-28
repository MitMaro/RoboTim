package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;
import ca.mitmaro.RoboTim.network.ReceiverHandler;
import ca.mitmaro.RoboTim.network.exception.InvalidLine;

public class MessageReceiver implements ReceiverHandler {
	
	private List<MessageHandler> handlers;
	
	private Logger logger;
	
	public MessageReceiver() {
		this.handlers = new ArrayList<MessageHandler>();
		this.logger = Logger.getLogger(this.getClass().getName());
	}
	
	public void addHandler(MessageHandler handler) {
		this.logger.finest(String.format("Adding message handler: %s", handler.toString()));
		this.handlers.add(handler);
	}

	@Override
	public void handle(String line) throws InvalidLine {
		Message m;
		try {
			m = new Message(line);
			
			this.logger.finest(String.format("Handling message: %s", m.toString()));
			
			for(MessageHandler h: this.handlers) {
				h.handle(m);
			}
			
		} catch (InvalidMessage e) {
			this.logger.warning(String.format("invalid message for line: %s", line));
			throw new InvalidLine(e);
		}
	}
}
