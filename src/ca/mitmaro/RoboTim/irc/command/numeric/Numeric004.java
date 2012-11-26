package ca.mitmaro.RoboTim.irc.command.numeric;

import ca.mitmaro.RoboTim.irc.command.AbstractCommand;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;

public class Numeric004 extends AbstractCommand {

	public Numeric004() throws InvalidCommandException {
		super("004");
		
	}
	
	@Override
	public String getMessage() {
		
		// this command should not be sent by the client
		return "";
	}

}
