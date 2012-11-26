package ca.mitmaro.RoboTim.network;

public interface ReceiverHandler {
	
	public void handle(String line) throws InvalidLineException;
	
}
