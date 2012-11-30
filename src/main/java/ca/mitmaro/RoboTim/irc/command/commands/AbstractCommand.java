package ca.mitmaro.RoboTim.irc.command.commands;

import ca.mitmaro.RoboTim.irc.command.Command;

public abstract class AbstractCommand implements Command {
	
	private String command_name;
	
	public AbstractCommand(String command_name) {
		this.command_name = command_name;
	}
	
	public String getCommandName() {
		return this.command_name;
	}
	
	public abstract String getMessage() ;
}
