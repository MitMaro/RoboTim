package ca.mitmaro.RoboTim.robot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.irc.command.CommandHandler;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.network.Connection;
import ca.mitmaro.RoboTim.robot.command.Command;
import ca.mitmaro.RoboTim.robot.command.exception.InvalidCommand;
import ca.mitmaro.RoboTim.robot.responder.ResponderHandler;
import ca.mitmaro.RoboTim.robot.responder.exception.InvalidResponse;

public class Robot implements CommandHandler {
	
	private List<Command> startup_commands;
	private List<Command> shutdown_commands;
	private List<ResponderHandler> responders;
	
	private Connection connection;
	
	private Logger logger = LoggerFactory.getLogger(Robot.class);
	
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
			this.logger.debug("Sending Message: {}", m);
			this.sendMessage(m);
		}
	}
	
	public void registerShutdownCommand(Command command) {
		this.logger.debug("Adding Shutdown Command: {}", command);
		this.shutdown_commands.add(command);
	}
	
	public void registerStartupCommand(Command command) {
		this.logger.debug("Adding Startup Command: {}", command);
		this.startup_commands.add(command);
	}
	
	public void registerResponder(ResponderHandler handler) {
		this.logger.debug("Adding Responder Command: {}", handler);
		this.responders.add(handler);
	}
	
	public void startup() throws InvalidCommand {
		this.logger.info("Robot starting up");
		for (Command c: this.startup_commands) {
			c.run(this);
		}
	}
	
	public void shutdown() throws InvalidCommand {
		this.logger.info("Robot shutting down");
		for (Command c: this.shutdown_commands) {
			c.run(this);
		}
	}

	public void handle(ca.mitmaro.RoboTim.irc.command.Command command) throws InvalidCommandException {
		this.logger.debug("Handling command: {}", command);
		for (ResponderHandler h: this.responders) {
			try {
				h.handle(this, command);
			} catch (InvalidResponse e) {
				this.logger.warn("InvalidResponse from response handler: {}", h, e);
				// not happy with this handling because a handler can break others
				throw new InvalidCommandException(e);
			}
		}
	}
	
}
