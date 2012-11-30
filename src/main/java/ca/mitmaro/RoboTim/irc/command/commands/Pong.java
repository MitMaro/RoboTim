package ca.mitmaro.RoboTim.irc.command.commands;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public class Pong extends AbstractCommand {

	private String server;
	private String server2;
	
	public Pong(String server) throws InvalidCommandException {
		super("PONG");
		
		if (server == null) {
			throw new InvalidCommandException("Missing server parameter in PONG command");
		}
		
		this.server = server;
	}
	
	public Pong(String server, String server2) throws InvalidCommandException {
		super("PONG");
		
		if (server == null && server2 == null) {
			throw new InvalidCommandException("One of server or server2 parameter must be provided for the PONG command");
		}
		this.server = server;
		this.server2 = server2;
	}
	
	@Override
	public String getMessage() {
		
		// server 1 and server 2
		if (this.server != null && this.server2 != null) {
			return String.format("%s %s %s", this.getCommandName(), this.server, this.server2);
		}

		// server 1 but not server 2
		if (this.server != null && this.server2 == null) {
			return String.format("%s %s", this.getCommandName(), this.server);
		}
		
		// server 2 but not server 1
		if (this.server == null && this.server2 != null) {
			return String.format("%s :%s", this.getCommandName(), this.server2);
		}

		throw new RuntimeException("This state should not happen. There is probably a bug in a constructor.");
	}

}
