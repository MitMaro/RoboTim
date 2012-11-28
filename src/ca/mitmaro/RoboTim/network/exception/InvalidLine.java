package ca.mitmaro.RoboTim.network.exception;

public class InvalidLine extends Exception {

	private static final long serialVersionUID = -3833489424408829310L;

	public InvalidLine(String message) {
		super(message);
	}

	public InvalidLine(Throwable cause) {
		super(cause);
	}

	public InvalidLine(String message, Throwable cause) {
		super(message, cause);
	}
}
