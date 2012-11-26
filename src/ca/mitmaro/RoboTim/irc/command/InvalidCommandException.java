package ca.mitmaro.RoboTim.irc.command;

@SuppressWarnings("serial")
public class InvalidCommandException extends Exception {

	public InvalidCommandException(String message) {
		super(message);
	}

}
