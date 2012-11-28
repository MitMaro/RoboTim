package ca.mitmaro.RoboTim.irc.command.commands;


public class Numeric001 extends AbstractCommand {

	private String nick;
	private String user;
	private String host;
	
	public Numeric001(String nick, String user, String host) {
		super("001");
		this.nick = nick;
		this.user = user;
		this.host = host;
		
	}
	
	public String getNick() {
		return this.nick;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getHost() {
		return this.host;
	}
	
	@Override
	public String getMessage() {
		throw new UnsupportedOperationException("Message 001 must not be sent as a client.");
	}

}
