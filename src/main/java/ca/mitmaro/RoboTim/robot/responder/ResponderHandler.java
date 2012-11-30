package ca.mitmaro.RoboTim.robot.responder;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public interface ResponderHandler {
	public void handle(Robot robot, Command command) throws InvalidResponse;
}
