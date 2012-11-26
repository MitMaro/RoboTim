package ca.mitmaro.RoboTim.irc.command.numeric;

import ca.mitmaro.RoboTim.irc.command.AbstractCommand;
import ca.mitmaro.RoboTim.irc.command.InvalidCommandException;

public class Numeric001 extends AbstractCommand {

	private String nick;
	private String user;
	private String host;
	
	public Numeric001(String nick, String user, String host) throws InvalidCommandException {
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
		
		// this command should not be sent by the client
		return "";
	}

}
