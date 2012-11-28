package ca.mitmaro.RoboTim.robot.responder;

public class InvalidResponse extends Exception {
	
	private static final long serialVersionUID = 1244787259032538311L;

	public InvalidResponse(String message) {
		super(message);
	}

	public InvalidResponse(Throwable cause) {
		super(cause);
	}

	public InvalidResponse(String message, Throwable cause) {
		super(message, cause);
	}

}
