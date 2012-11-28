package ca.mitmaro.RoboTim.irc.message.exception;

public class Exception extends java.lang.Exception {

	private static final long serialVersionUID = 8431725499685174704L;

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
