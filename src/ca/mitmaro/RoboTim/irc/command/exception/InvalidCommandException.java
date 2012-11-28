package ca.mitmaro.RoboTim.irc.command.exception;

public class InvalidCommandException extends Exception {

	private static final long serialVersionUID = 3795971797627262562L;

	public InvalidCommandException(String message) {
		super(message);
	}

	public InvalidCommandException(Throwable cause) {
		super(cause);
	}

	public InvalidCommandException(String message, Throwable cause) {
		super(message, cause);
	}

}
