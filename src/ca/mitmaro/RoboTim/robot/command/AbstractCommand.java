package ca.mitmaro.RoboTim.robot.command;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.command.exception.InvalidCommand;

public abstract class AbstractCommand implements Command {
	public abstract void run(Robot robot) throws InvalidCommand;
	
	public abstract Logger getLogger();
}
