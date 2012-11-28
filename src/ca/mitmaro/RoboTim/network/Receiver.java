/*
Chatter
            By: Tim Oram
Student Number: #########
           For: CS-3715 Assignment #1
*/
package ca.mitmaro.RoboTim.network;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import ca.mitmaro.RoboTim.network.exception.InvalidLine;


/**
 * A Chatter Server
 * 
 * @author Tim Oram <toram@mun.ca>
 */
public class Receiver implements Runnable {
	
	private Connection connection;
	
	private Logger logger;
	
	private List<ReceiverHandler> handlers;
	
	private Exception last_excpetion = null;
	
	public Receiver (Connection connection) {
		this.logger = Logger.getLogger(this.getClass().getName());
		this.handlers = new LinkedList<ReceiverHandler>();
		this.connection = connection;
		logger.setParent(this.connection.getLogger());
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	public boolean hasError() {
		return this.last_excpetion != null;
	}
	
	public Exception getLastError() {
		return this.last_excpetion;
	}
	
	public void addHandler(ReceiverHandler r) {
		this.logger.finer(String.format("Adding reciever handler: %s", r.toString()));
		this.handlers.add(r);
	}
	
	public void run() {
		String line = "";
		
		while (line != null) {

			try {
				line = this.connection.waitForResponse();
				
				this.logger.finest(String.format("Read Line: %s", line));
				
				for(ReceiverHandler r: this.handlers) {
					try {
						r.handle(line);
					} catch (InvalidLine e) {
						this.logger.severe(String.format("InvalidLine for handler: %s", r.getClass()));
						// still not sure how to handle this....
						e.printStackTrace();
					}
				}
				
				Thread.sleep(20);
			}
			catch(InterruptedException e) {
				this.logger.fine("Connection read was interupted");
				return;
			}
			catch (SocketTimeoutException e) {
				// this is ok, it's supposed to happen eventually
				this.logger.finest("Socket timed out");
				continue;
			}
			catch (IOException e) {
				this.logger.warning(String.format("IOException Occured: %s", e.getMessage()));
				this.last_excpetion = e;
				return;
			}
		}

	}
	
}