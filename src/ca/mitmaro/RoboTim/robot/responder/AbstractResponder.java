package ca.mitmaro.RoboTim.robot.responder;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.robot.Robot;

public abstract class AbstractResponder implements ResponderHandler {

	@Override
	public abstract void handle(Robot robot, Command command) throws InvalidResponse;
}
