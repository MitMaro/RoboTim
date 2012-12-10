package ca.mitmaro.RoboTim.irc.command.commands;


public class Quit extends AbstractCommand {
	
	private String message;
	
	public Quit() {
		super("QUIT");
	}
	
	public Quit(String message) {
		super("QUIT");
		
		this.message = message;
	}

	@Override
	public String getMessage() {
		if (this.message == null) {
			return "QUIT";
		}
		return String.format("QUIT :%s", this.message);
	}

}
