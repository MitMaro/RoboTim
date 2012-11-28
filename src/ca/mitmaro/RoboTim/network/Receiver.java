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

import ca.mitmaro.RoboTim.network.exception.InvalidLineException;


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
		this.handlers.add(r);
	}
	
	public void run() {
		String line = "";
		
		while (line != null) {

			try {
				line = this.connection.waitForResponse();
				
				for(ReceiverHandler r: this.handlers) {
					try {
						r.handle(line);
					} catch (InvalidLineException e) {
						// still not sure how to handle this....
						e.printStackTrace();
					}
				}
				
				Thread.sleep(20);
			}
			catch(InterruptedException e) {
				this.logger.fine("Interupted");
				return;
			}
			catch (SocketTimeoutException e) {
				// this is ok, it's supposed to happen eventually
				this.logger.finest("Timeout");
				continue;
			}
			catch (IOException e) {
				this.logger.warning("IOException Occured");
				this.last_excpetion = e;
				return;
			}
		}

	}
	
}