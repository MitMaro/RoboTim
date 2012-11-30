package ca.mitmaro.RoboTim.robot.command.exception;

public class InvalidCommand extends Exception {
	
	private static final long serialVersionUID = -2208705371223710793L;

	public InvalidCommand(String message) {
		super(message);
	}

	public InvalidCommand(Throwable e) {
		super(e);
	}

	public InvalidCommand(String message, Throwable e) {
		super(message, e);
	}

}
