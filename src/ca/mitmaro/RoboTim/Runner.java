package ca.mitmaro.RoboTim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.mitmaro.RoboTim.irc.CommandMapper;
import ca.mitmaro.RoboTim.irc.MessageReceiver;
import ca.mitmaro.RoboTim.irc.command.commands.Nick;
import ca.mitmaro.RoboTim.irc.command.commands.Privmsg;
import ca.mitmaro.RoboTim.irc.command.commands.Quit;
import ca.mitmaro.RoboTim.irc.command.commands.User;
import ca.mitmaro.RoboTim.irc.mapper.Numeric004Mapper;
import ca.mitmaro.RoboTim.irc.mapper.PingMapper;
import ca.mitmaro.RoboTim.irc.mapper.PongMapper;
import ca.mitmaro.RoboTim.network.Connection;
import ca.mitmaro.RoboTim.network.Receiver;
import ca.mitmaro.RoboTim.robot.Robot;
import ca.mitmaro.RoboTim.robot.command.AbstractCommand;
import ca.mitmaro.RoboTim.robot.command.exception.InvalidCommand;
import ca.mitmaro.RoboTim.robot.responder.JoinChannelResponder;
import ca.mitmaro.RoboTim.robot.responder.PingResponder;


public class Runner implements Runnable {

	private Connection conn;

	public Runner(Connection c) {
		this.conn = c;
	}

	public static void main(String[] args) throws Exception {
		
		
		Logger logger = Logger.getLogger("global");
		
		Connection c = new Connection("localhost", 6667, 60*1000);
		c.getLogger().setParent(logger);
		
		Receiver receiver = new Receiver(c);
		MessageReceiver message_receiver = new MessageReceiver();
		CommandMapper message_handler = new CommandMapper();
		
		Robot tim = new Robot(c);
		
		message_handler.registerMapper(new PingMapper());
		message_handler.registerMapper(new PongMapper());
		message_handler.registerMapper(new Numeric004Mapper());
		message_handler.registerCommandHandler(tim);
		
		message_receiver.addHandler(message_handler);
		receiver.addHandler(message_receiver);
		
		tim.registerStartupCommand(new AbstractCommand() {
			
			private Logger logger = Logger.getLogger("StartupLogger");
			
			@Override
			public void run(Robot robot) throws InvalidCommand {
				Nick nick = new Nick("RoboTim");
				User user = new User("robotim", 0, "Alter Tim");
				
				try {
					robot.sendMessage(nick.getMessage());
					robot.sendMessage(user.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public Logger getLogger() {
				return this.logger;
			}
		});
		
		tim.registerResponder(new PingResponder());
		tim.registerResponder(new JoinChannelResponder("#muncssociety"));

		logger.setLevel(Level.ALL);

		Thread reader_thread = new Thread(receiver);
		reader_thread.start();
		tim.startup();
		
		
		// simple command line interface
		Thread ui = new Thread(new Runner(c));
		ui.start();
		
		while (ui.isAlive()) {
			Thread.sleep(1000);
		}
		
		reader_thread.interrupt();
		
		// wait for reader to shutdown
		c.shutdown();
		while(reader_thread.isAlive()) {
			Thread.sleep(25);
		}
		c.disconnect();
		
	}

	public void run() {
		
		String line;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp;
		
		while (true) {
			
			try {
				line = in.readLine();
				
				if (line.startsWith("/quit")) {
					Quit quit = new Quit("Bye! Bye!");
					this.conn.sendMessage(quit.getMessage() + "\r\n");
					break;
				}
				
				
				if (line.startsWith("/r")) {
					this.conn.sendMessage(line.substring(2) + "\r\n");
				} else {
				
					tmp = line.split("\\s", 2);
					
					Privmsg message = new Privmsg(tmp[0], tmp[1]);
					
					this.conn.sendMessage(message.getMessage() + "\r\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
		
		System.out.println("Quitting");
		
		return;
		
	}

}
