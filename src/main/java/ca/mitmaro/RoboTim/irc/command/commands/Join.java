package ca.mitmaro.RoboTim.irc.command.commands;


public class Join extends AbstractCommand {

	private String[] channels;
	private String[] keys;

	public Join(String[] channels, String[] keys) {
		super("JOIN");
		this.channels = channels;
		this.keys = keys;
	}
	public Join(String[] channels) {
		super("JOIN");
		this.channels = channels;
		this.keys = new String[] {};
	}

	@Override
	public String getMessage() {
		
		String channel = "";
		String key = "";
		
		for (int i = 0; i < this.channels.length - 1; i++) {
			channel += this.channels[i] + ",";
		}
		if (this.channels.length > 0) {
			channel += this.channels[this.channels.length - 1];
		}
		
		for (int i = 0; i < this.keys.length - 1; i++) {
			key += this.keys[i] + ",";
		}
		if (this.keys.length > 0) {
			key += this.keys[this.keys.length - 1];
		}
		
		if (key.isEmpty()) {
			return String.format("JOIN %s", channel);
		}
		return String.format("JOIN %s %s", channel, key);
	}

}
