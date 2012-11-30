package ca.mitmaro.RoboTim.irc.message.exception;

public class InvalidMessage extends Exception {

	private static final long serialVersionUID = -1383047961514795737L;

	public InvalidMessage(String message) {
		super(message);
	}

	public InvalidMessage(Throwable cause) {
		super(cause);
	}

	public InvalidMessage(String message, Throwable cause) {
		super(message, cause);
	}
}
