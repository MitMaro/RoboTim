package ca.mitmaro.RoboTim.network;

import ca.mitmaro.RoboTim.network.exception.InvalidLine;

public interface ReceiverHandler {
	
	public void handle(String line) throws InvalidLine;
	
}
