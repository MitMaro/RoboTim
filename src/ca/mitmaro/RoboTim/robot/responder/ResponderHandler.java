package ca.mitmaro.RoboTim.robot.responder;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.robot.Robot;

public interface ResponderHandler {
	public abstract void handle(Robot robot, Command command) throws InvalidResponse;
}
