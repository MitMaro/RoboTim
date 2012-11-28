package ca.mitmaro.RoboTim.irc.command.commands;

public class Numeric004 extends AbstractCommand {

	private String server_name;
	private String version;
	private String user_modes;
	private String channel_modes; 
	
	public Numeric004(String server_name, String version, String user_modes, String channel_modes) {
		super("004");
		this.server_name = server_name;
		this.version = version;
		this.user_modes = user_modes;
		this.channel_modes = channel_modes;
		
	}
	
	public String getServerName() {
		return this.server_name;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public String getUserModes() {
		return this.user_modes;
	}
	
	public String getChannelModes() {
		return this.channel_modes;
	}
	
	@Override
	public String getMessage() {
		// Only supporting clients for the time being
		throw new UnsupportedOperationException("001 messages must not be sent by a client.");
	}

}
