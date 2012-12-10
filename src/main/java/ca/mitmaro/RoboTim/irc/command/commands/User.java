package ca.mitmaro.RoboTim.irc.command.commands;


public class User extends AbstractCommand {

	private String user;
	private int mode;
	private String name;
	
	public User(String user, int mode, String name) {
		super("USER");
		this.user = user;
		this.mode = mode;
		this.name = name;
	}

	@Override
	public String getMessage() {
		return String.format("USER %s %d * :%s", this.user, this.mode, this.name);
	}

}
