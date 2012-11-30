package ca.mitmaro.RoboTim.robot.responder;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.command.Command;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public abstract class AbstractResponder implements ResponderHandler {

	public abstract void handle(Robot robot, Command command) throws InvalidResponse;
	
	public abstract Logger getLogger();

}
