package ca.mitmaro.RoboTim.robot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mitmaro.RoboTim.irc.command.CommandHandler;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.network.Connection;
import ca.mitmaro.RoboTim.robot.command.Command;
import ca.mitmaro.RoboTim.robot.command.exception.InvalidCommand;
import ca.mitmaro.RoboTim.robot.responder.InvalidResponse;
import ca.mitmaro.RoboTim.robot.responder.ResponderHandler;

public class Robot implements CommandHandler {
	
	private List<Command> startup_commands;
	private List<Command> shutdown_commands;
	private List<ResponderHandler> responders;
	
	private Connection connection;
	
	public Robot(Connection conn) {
		this.startup_commands = new ArrayList<Command>();
		this.shutdown_commands = new ArrayList<Command>();
		this.responders = new ArrayList<ResponderHandler>();
		this.connection = conn;
	}
	
	public void sendMessage(String message) throws IOException {
		this.connection.sendMessage(message + "\r\n");
	}
	
	public void sendMessage(String[] messages) throws IOException {
		for (String m: messages) {
			this.sendMessage(m);
		}
	}
	
	public void registerShutdownCommand(Command command) {
		this.shutdown_commands.add(command);
	}
	
	public void registerStartupCommand(Command command) {
		this.startup_commands.add(command);
	}
	
	public void registerResponder(ResponderHandler handler) {
		this.responders.add(handler);
	}
	
	public void startup() {
		for (Command c: this.startup_commands) {
			try {
				c.run(this);
			} catch (InvalidCommand e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		for (Command c: this.shutdown_commands) {
			try {
				c.run(this);
			} catch (InvalidCommand e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handle(ca.mitmaro.RoboTim.irc.command.Command command) throws InvalidCommandException {
		for (ResponderHandler h: this.responders) {
			try {
				h.handle(this, command);
			} catch (InvalidResponse e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
