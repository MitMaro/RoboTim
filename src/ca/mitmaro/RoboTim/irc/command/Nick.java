package ca.mitmaro.RoboTim.irc.command;

public class Nick extends AbstractCommand {

	private String nick;
	
	public Nick(String nick) {
		super("NICK");
		this.nick = nick;
	}

	@Override
	public String getMessage() {
		return String.format("NICK :%s", this.nick);
	}

}
