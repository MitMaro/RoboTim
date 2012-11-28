package ca.mitmaro.RoboTim.irc;

import java.util.ArrayList;
import java.util.List;

import ca.mitmaro.RoboTim.irc.message.InvalidMessageException;
import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.network.ReceiverHandler;
import ca.mitmaro.RoboTim.network.exception.InvalidLineException;

public class MessageReceiver implements ReceiverHandler {
	
	List<MessageHandler> handlers;
	
	public MessageReceiver() {
		this.handlers = new ArrayList<MessageHandler>();
	}
	
	public void addHandler(MessageHandler handler) {
		this.handlers.add(handler);
	}

	@Override
	public void handle(String line) throws InvalidLineException {
		Message m;
		try {
			m = new Message(line);
			
			for(MessageHandler h: this.handlers) {
				h.handle(m);
			}
			
		} catch (InvalidMessageException e) {
			throw new InvalidLineException(e);
		}
	}
}
