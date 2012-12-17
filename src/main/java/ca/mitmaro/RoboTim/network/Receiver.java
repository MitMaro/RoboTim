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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.network.exception.InvalidLine;


/**
 * A Chatter Server
 * 
 * @author Tim Oram <toram@mun.ca>
 */
public class Receiver implements Runnable {
	
	private Connection connection;
	
	private Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	private List<ReceiverHandler> handlers;
	
	private Exception last_excpetion = null;
	
	public Receiver (Connection connection) {
		this.handlers = new LinkedList<ReceiverHandler>();
		this.connection = connection;
	}
	
	public boolean hasError() {
		return this.last_excpetion != null;
	}
	
	public Exception getLastError() {
		return this.last_excpetion;
	}
	
	public void addHandler(ReceiverHandler handler) {
		this.logger.debug("Adding reciever handler: {}", handler);
		this.handlers.add(handler);
	}
	
	public void run() {
		String line = "";
		
		while (line != null) {

			try {
				line = this.connection.waitForResponse();
				
				this.logger.debug("Read Line: {}", line);
				
				for(ReceiverHandler r: this.handlers) {
					try {
						r.handle(line);
					} catch (InvalidLine e) {
						// still not sure how to handle this....
						this.logger.error("InvalidLine for handler: {}", r, e);
					}
				}
				
				Thread.sleep(20);
			}
			catch(InterruptedException e) {
				this.logger.info("Connection read was interupted");
				return;
			}
			catch (SocketTimeoutException e) {
				// this is ok, it's supposed to happen eventually
				this.logger.trace("Socket timed out");
				continue;
			}
			catch (IOException e) {
				this.logger.warn("IOException Occured", e);
				this.last_excpetion = e;
				return;
			}
		}

	}
	
}