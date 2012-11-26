package ca.mitmaro.RoboTim.irc.mapper;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.command.numeric.Numeric004;
import ca.mitmaro.RoboTim.irc.message.Message;

public class Numeric004Mapper extends AbstractMapper {

	@Override
	public Command mapMessage(Message message) throws InvalidCommandException {
		
		if (!message.getCommand().equals("004")) {
			return null;
		}
		
		return new Numeric004();
	}
}
