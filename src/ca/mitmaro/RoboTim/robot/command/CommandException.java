package ca.mitmaro.RoboTim.robot.command;

@SuppressWarnings("serial")
public class CommandException extends Exception {

	public CommandException() {
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable e) {
		super(e);
	}

	public CommandException(String message, Throwable e) {
		super(message, e);
	}

}
