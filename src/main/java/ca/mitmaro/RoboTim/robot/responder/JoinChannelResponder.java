package ca.mitmaro.RoboTim.robot.responder;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Join;
import ca.mitmaro.RoboTim.irc.command.commands.Numeric004;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public class JoinChannelResponder extends AbstractResponder {

	private String[] channels;
	private String[] keys;
	private Logger logger = LoggerFactory.getLogger(JoinChannelResponder.class);

	public JoinChannelResponder(String channel, String key) {
		this(new String[] {channel}, new String[] {key});
	}
	
	public JoinChannelResponder(String channel) {
		this(new String[] {channel}, new String[] {});
	}
	
	public JoinChannelResponder(String[] channels, String[] keys) {
		this.channels = channels;
		this.keys = keys;
	}
	
	public JoinChannelResponder(String[] channels) {
		this(channels, new String[] {});
	}
	
	@Override
	public void handle(Robot robot, Command command) throws InvalidResponse {
		
		if (!(command instanceof Numeric004)) {
			return;
		}
		
		this.logger.info("Joining channels: <{}>", (Object)this.channels);

		Join j = new Join(this.channels, this.keys);
		try {
			robot.sendMessage(j.getMessage());
		} catch (IOException e) {
			this.logger.warn("IOException for JOIN: {}", j, e);
			throw new InvalidResponse(e);
		}
	}
}
