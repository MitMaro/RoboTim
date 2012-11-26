package ca.mitmaro.RoboTim.irc.mapper;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.command.Ping;
import ca.mitmaro.RoboTim.irc.message.Message;

public class PingMapper extends AbstractMapper {

	@Override
	public Command mapMessage(Message message) throws InvalidCommandException {
		
		if (!message.getCommand().equals("ping")) {
			return null;
		}
		
		String server;
		String server2;
		
		server = message.getParameter(0);
		server2 = message.getTrailing();
		
		if (server2 == null) {
			server2 = message.getParameter(1);
		}
		
		return new Ping(server, server2);
	}
}
