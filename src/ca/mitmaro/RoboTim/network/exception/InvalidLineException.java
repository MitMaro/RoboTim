package ca.mitmaro.RoboTim.network.exception;

public class InvalidLineException extends Exception {

	private static final long serialVersionUID = -3833489424408829310L;

	public InvalidLineException(String message) {
		super(message);
	}

	public InvalidLineException(Throwable cause) {
		super(cause);
	}

	public InvalidLineException(String message, Throwable cause) {
		super(message, cause);
	}
}
