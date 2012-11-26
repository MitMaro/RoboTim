package ca.mitmaro.RoboTim.irc.command;

public abstract class AbstractCommand implements Command {
	
	private String name;
	
	public AbstractCommand(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract String getMessage();
}
