package ca.mitmaro.RoboTim.robot.responder;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Ping;
import ca.mitmaro.RoboTim.irc.command.commands.Pong;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public class PingResponder extends AbstractResponder {
	
	private Logger logger = LoggerFactory.getLogger(PingResponder.class);
	
	@Override
	public void handle(Robot robot, Command command) throws InvalidResponse {
		
		if (!(command instanceof Ping)) {
			return;
		}
		
		Ping ping = (Ping)command;
		
		Pong pong;
		
		try {
			this.logger.debug("PONGing");
			pong = new Pong(ping.getServer(), ping.getServer2());
			robot.sendMessage(pong.getMessage());
		} catch (InvalidCommandException e) {
			this.logger.warn("Invalid command for command: {}", command);
			throw new InvalidResponse(e);
		} catch (IOException e) {
			this.logger.warn("IOException for command: {}", command, e);
			throw new InvalidResponse(e);
		}
	}

}
