package ca.mitmaro.RoboTim.irc.mapper;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Numeric004;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

public class Numeric004Mapper extends AbstractMapper {

	@Override
	public Command mapMessage(Message message) throws InvalidCommandException {
		
		if (!message.getCommand().equals("004")) {
			return null;
		}

		String server_name = message.getParameter(0);
		String version = message.getParameter(1);
		String user_modes = message.getParameter(2);
		String channel_modes = message.getParameter(3);
		return new Numeric004(server_name, version, user_modes, channel_modes);
	}
}
