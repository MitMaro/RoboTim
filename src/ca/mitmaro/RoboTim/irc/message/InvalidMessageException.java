package ca.mitmaro.RoboTim.irc.message;

@SuppressWarnings("serial")
public class InvalidMessageException extends Exception {

	public InvalidMessageException(String message) {
		super(message);
	}

	public InvalidMessageException(Throwable cause) {
		super(cause);
	}

	public InvalidMessageException(String message, Throwable cause) {
		super(message, cause);
	}
}
