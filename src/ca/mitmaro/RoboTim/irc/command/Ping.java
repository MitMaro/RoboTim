package ca.mitmaro.RoboTim.irc.command;

public class Ping extends AbstractCommand {

	private String server;
	private String server2;
	
	public Ping(String server) throws InvalidCommandException {
		super("PING");
		
		if (server == null) {
			throw new InvalidCommandException("Missing server parameter in PING command");
		}
		
		this.server = server;
	}
	
	public Ping(String server, String server2) throws InvalidCommandException {
		super("PING");
		
		if (server == null && server2 == null) {
			throw new InvalidCommandException("Missing both server and server2 parameter in PING command");
		}
		this.server = server;
		this.server2 = server2;
	}
	
	public String getServer() {
		return this.server;
	}
	
	public String getServer2() {
		return this.server2;
	}
	
	@Override
	public String getMessage() {
		
		// server 1 and server 2
		if (this.server != null && this.server2 != null) {
			return String.format("%s %s %s", this.getName(), this.server, this.server2);
		}

		// server 1 but not server 2
		if (this.server != null && this.server2 == null) {
			return String.format("%s %s", this.getName(), this.server);
		}
		
		// server 2 but not server 1
		if (this.server == null && this.server2 != null) {
			return String.format("%s :%s", this.getName(), this.server2);
		}
		
		return "";
	}

}
