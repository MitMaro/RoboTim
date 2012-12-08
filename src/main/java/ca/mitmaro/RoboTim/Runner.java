package ca.mitmaro.RoboTim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ca.mitmaro.RoboTim.irc.LineInputReceiver;
import ca.mitmaro.RoboTim.irc.MessageReciever;
import ca.mitmaro.RoboTim.irc.command.CommandMapper;
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
		
		Connection c = new Connection("localhost", 6667, 60*1000);
		
		Receiver receiver = new Receiver(c);
		LineInputReceiver line_receiver = new LineInputReceiver();
		CommandMapper command_mapper = new CommandMapper();
		
		Robot tim = new Robot(c);
		
		command_mapper.registerMapper(new PingMapper());
		command_mapper.registerMapper(new PongMapper());
		command_mapper.registerMapper(new Numeric004Mapper());
		
		MessageReciever message_reciever = new MessageReciever(command_mapper);
		
		line_receiver.addHandler(message_reciever);
		receiver.addHandler(line_receiver);
		
		tim.registerStartupCommand(new AbstractCommand() {
			
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
		});
		
		tim.registerResponder(new PingResponder());
		tim.registerResponder(new JoinChannelResponder("#muncssociety"));

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
