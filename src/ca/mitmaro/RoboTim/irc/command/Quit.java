package ca.mitmaro.RoboTim.irc.command;

public class Quit extends AbstractCommand {
	
	private String message;
	
	public Quit(String message) {
		super("QUIT");
		
		this.message = message;
	}

	@Override
	public String getMessage() {
		if (this.message == null) {
			return String.format("QUIT");
		}
		
		return String.format("QUIT :%s", this.message);
	}

}
