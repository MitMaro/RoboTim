package ca.mitmaro.RoboTim.robot.responder;

import java.io.IOException;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.commands.Join;
import ca.mitmaro.RoboTim.irc.command.commands.Numeric004;
import ca.mitmaro.RoboTim.robot.Robot;

public class JoinChannelResponder extends AbstractResponder {

	private String[] channels;
	private String[] keys;

	public JoinChannelResponder(String channel, String key) {
		this.channels = new String[] {channel};
		this.keys = new String[] {key};
	}
	
	public JoinChannelResponder(String channel) {
		this.channels = new String[] {channel};
		this.keys = new String[] {};
	}
	
	public JoinChannelResponder(String[] channels, String[] keys) {
		this.channels = channels;
		this.keys = keys;
	}
	
	public JoinChannelResponder(String[] channels) {
		this.channels = channels;
		this.keys = new String[] {};;
	}
	
	@Override
	public void handle(Robot robot, Command command) throws InvalidResponse {
		
		if (!(command instanceof Numeric004)) {
			return;
		}
		
		try {
			Join j = new Join(this.channels, this.keys);
			robot.sendMessage(j.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
