package ca.mitmaro.RoboTim.robot.responder;

import java.io.IOException;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.command.Ping;
import ca.mitmaro.RoboTim.irc.command.Pong;
import ca.mitmaro.RoboTim.robot.Robot;

public class PingResponder extends AbstractResponder {
	
	@Override
	public void handle(Robot robot, Command command) throws ResponderException {
		
		if (!(command instanceof Ping)) {
			return;
		}
		
		Ping ping = (Ping)command;
		
		Pong pong;
		try {
			pong = new Pong(ping.getServer(), ping.getServer2());
			robot.sendMessage(pong.getMessage());
		} catch (InvalidCommandException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
