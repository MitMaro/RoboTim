package ca.mitmaro.RoboTim.irc.command.commands;


public class Privmsg extends AbstractCommand {
	
	private String target;
	private String message;
	
	public Privmsg(String target, String message) {
		super("PRIVMSG");
		
		this.target = target;
		this.message = message;
	}

	public String getMsg() {
		return this.message;
	}
	
	public String getTarget() {
		return this.target;
	}

	@Override
	public String getMessage() {
		return String.format("PRIVMSG %s :%s", this.target, this.message);
	}
}
