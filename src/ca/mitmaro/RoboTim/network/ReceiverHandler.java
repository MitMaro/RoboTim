package ca.mitmaro.RoboTim.network;

import ca.mitmaro.RoboTim.network.exception.InvalidLineException;

public interface ReceiverHandler {
	
	public void handle(String line) throws InvalidLineException;
	
}
