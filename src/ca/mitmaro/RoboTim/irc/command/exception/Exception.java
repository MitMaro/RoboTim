package ca.mitmaro.RoboTim.irc.command.exception;

public class Exception extends java.lang.Exception {

	private static final long serialVersionUID = 8269661663891046408L;

	public Exception(String message) {
		super(message);
	}

	public Exception(Throwable cause) {
		super(cause);
	}

	public Exception(String message, Throwable cause) {
		super(message, cause);
	}

}
