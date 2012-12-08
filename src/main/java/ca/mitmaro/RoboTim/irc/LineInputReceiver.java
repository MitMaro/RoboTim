package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;
import ca.mitmaro.RoboTim.network.ReceiverHandler;
import ca.mitmaro.RoboTim.network.exception.InvalidLine;

public class LineInputReceiver implements ReceiverHandler {
	
	private List<MessageHandler> handlers;
	
	private Logger logger = LoggerFactory.getLogger(LineInputReceiver.class);
	
	public LineInputReceiver() {
		this.handlers = new ArrayList<MessageHandler>();
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	public void addHandler(MessageHandler handler) {
		this.logger.debug("Adding message handler: {}", handler);
		this.handlers.add(handler);
	}

	public void handle(String line) throws InvalidLine {
		Message m;
		try {
			m = new Message(line);
			
			this.logger.debug("Handling message: {}", m);
			
			for(MessageHandler h: this.handlers) {
				h.handle(m);
			}
			
		} catch (InvalidMessage e) {
			this.logger.warn("Invalid message for line: {}", line, e);
			throw new InvalidLine(e);
		}
	}
}
