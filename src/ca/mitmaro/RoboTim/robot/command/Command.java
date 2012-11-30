package ca.mitmaro.RoboTim.robot.command;

import java.util.logging.Logger;

import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.command.exception.InvalidCommand;

public interface Command {
	public void run(Robot robot) throws InvalidCommand;
	public Logger getLogger();
}
