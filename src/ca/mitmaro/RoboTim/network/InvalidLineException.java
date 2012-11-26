package ca.mitmaro.RoboTim.network;

@SuppressWarnings("serial")
public class InvalidLineException extends Exception {

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
