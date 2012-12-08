package ca.mitmaro.RoboTim.irc.message;

import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public class Message {
	
	private String prefix = null;
	private String command = null;
	private String[] parameters = {
		null, null, null, null, null,
		null, null, null, null, null,
		null, null, null, null, null
	};
	
	public Message(String message) throws InvalidMessage {
		
		int prefix_end = -1;
		int trailing_start;
		String[] tmp;
		
		String trailing = null;
		
		if (message == null || message.length() < 1) {
			throw new InvalidMessage("<<empty message>>");
		}
		
		// grab the prefix
		if (message.charAt(0) == ':') {
			prefix_end = message.indexOf(" ");
			this.prefix = message.substring(1, prefix_end);
		}
		
		// grab the trail, if it exists
		trailing_start = message.indexOf(" :");
		if (trailing_start >= 0) {
			trailing = message.substring(trailing_start + 2);
		}
		else {
			trailing_start = message.length();
		}
		
		// split the command and other params
		tmp = message.substring(prefix_end + 1, trailing_start).split("\\s");
		
		// too many parameters
		if (tmp.length > 16) {
			throw new InvalidMessage("To many parameters: " + message);
		}
		
		this.command = tmp[0].toLowerCase();
		
		for (int i = 1; i < tmp.length; i++) {
			this.parameters[i - 1] = tmp[i];
		}
		
		// add trailing parameter is it exists
		if (trailing != null) {
			this.parameters[14] = trailing;
		}
		
	}

	public String getPrefix() {
		return this.prefix;
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public String getParameter(int i) {
		return this.parameters[i];
	}
	
	public String getTrailing() {
		return this.parameters[14];
	}
	
	public String toString() {
		
		String rtn = "";
		if (this.prefix != null) {
			rtn += "Prefix: '" + this.prefix + "' ";
		}
		
		rtn += "Command: '" + this.command + "' ";
		
		for (String p: this.parameters) {
			if (p != null) {
				rtn += "Param: '" + p + "' ";
			}
		}
		
		return rtn;
		
	}
	
}
