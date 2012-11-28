package ca.mitmaro.RoboTim.robot.command;

import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.command.exception.InvalidCommand;

public interface Command {
	public void run(Robot robot) throws InvalidCommand;
}
