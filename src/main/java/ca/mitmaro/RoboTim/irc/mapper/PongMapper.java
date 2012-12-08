package ca.mitmaro.RoboTim.irc.mapper;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Pong;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

public class PongMapper extends AbstractMapper {

	@Override
	public Command mapMessage(Message message) throws InvalidCommandException {
		
		if (!message.getCommand().equals("pong")) {
			return null;
		}
		
		String server;
		String server2;
		
		server = message.getParameter(0);
		
		server2 = message.getTrailing();
		
		if (server2 == null) {
			server2 = message.getParameter(1);
		}
		
		return new Pong(server, server2);
	}
}
