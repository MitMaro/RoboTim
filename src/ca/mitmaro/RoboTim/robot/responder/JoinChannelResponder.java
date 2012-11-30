package ca.mitmaro.RoboTim.robot.responder;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Join;
import ca.mitmaro.RoboTim.irc.command.commands.Numeric004;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public class JoinChannelResponder extends AbstractResponder {

	private String[] channels;
	private String[] keys;
	private Logger logger;

	public JoinChannelResponder(String channel, String key) {
		this(new String[] {channel}, new String[] {key});
	}
	
	public JoinChannelResponder(String channel) {
		this(new String[] {channel}, new String[] {});
	}
	
	public JoinChannelResponder(String[] channels, String[] keys) {
		this.channels = channels;
		this.keys = keys;
		this.logger = Logger.getLogger(this.getClass().getName());
	}
	
	public JoinChannelResponder(String[] channels) {
		this(channels, new String[] {});
	}
	
	@Override
	public Logger getLogger() {
		return this.logger;
	}
	
	@Override
	public void handle(Robot robot, Command command) throws InvalidResponse {
		
		if (!(command instanceof Numeric004)) {
			return;
		}
		
		this.logger.finer(String.format("Joining channels: %s", Arrays.toString(this.channels)));
		
		try {
			Join j = new Join(this.channels, this.keys);
			robot.sendMessage(j.getMessage());
		} catch (IOException e) {
			throw new InvalidResponse(e);
		}
	}
}
