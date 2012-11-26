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


/**
 * A Chatter Server
 * 
 * @author Tim Oram <toram@mun.ca>
 */
public class Receiver implements Runnable {
	
	private Connection connection;
	
	private Logger logger;
	
	private List<ReceiverHandler> handlers;
	
	public Receiver (Connection connection, Logger logger) {
		
		this.logger = logger;
		
		this.handlers = new LinkedList<ReceiverHandler>();
		
		this.connection = connection;
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
					r.handle(line);
				}
				
				Thread.sleep(20);
			}
			catch(InterruptedException e) {
				this.logger.info("Interupted");
				return;
			}
			catch (SocketTimeoutException e) {
				this.logger.fine("Timeout");
				// this is ok
				continue;
			}
			catch (IOException e) {
				this.logger.severe("IOException Occured");
				e.printStackTrace();
				return;
			} catch (InvalidLineException e) {
				
			}
		}

	}
	
}