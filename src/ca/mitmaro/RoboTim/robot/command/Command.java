package ca.mitmaro.RoboTim.robot.command;

import ca.mitmaro.RoboTim.robot.Robot;

public interface Command {
	public void run(Robot robot) throws CommandException;
}
