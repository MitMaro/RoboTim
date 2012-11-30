package ca.mitmaro.RoboTim.robot.responder;

import java.io.IOException;
import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Ping;
import ca.mitmaro.RoboTim.irc.command.commands.Pong;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public class PingResponder extends AbstractResponder {
	
	private Logger logger;
	
	public PingResponder() {
		this.logger = Logger.getLogger(this.getClass().getName());
	}

	@Override
	public Logger getLogger() {
		return this.logger;
	}
	
	@Override
	public void handle(Robot robot, Command command) throws InvalidResponse {
		
		if (!(command instanceof Ping)) {
			return;
		}
		
		Ping ping = (Ping)command;
		
		Pong pong;
		
		try {
			this.logger.finer("PONGing");
			pong = new Pong(ping.getServer(), ping.getServer2());
			robot.sendMessage(pong.getMessage());
		} catch (InvalidCommandException e) {
			this.logger.finer(String.format("Invalid command for command: %s", command.getCommandName()));
			throw new InvalidResponse(e);
		} catch (IOException e) {
			this.logger.finer(String.format("IOException: %s", e.getMessage()));
			throw new InvalidResponse(e);
		}
	}

}
