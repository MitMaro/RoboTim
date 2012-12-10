package ca.mitmaro.RoboTim.irc;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;
import ca.mitmaro.RoboTim.network.exception.InvalidLine;

public class LineInputReceiverTest {

	@Test
	public void handle() throws InvalidLine, InvalidMessage {
		
		MessageHandler mh = Mockito.mock(MessageHandler.class);
		
		LineInputReceiver lir = new LineInputReceiver();
		
		lir.addHandler(mh);
		
		lir.handle("Commnad Param");
		
		Mockito.verify(mh).handle(Mockito.any(Message.class));
		
	}
	
	@Test(expectedExceptions = {InvalidLine.class})
	public void handle_invalid_line() throws InvalidLine {
		
		MessageHandler mh = Mockito.mock(MessageHandler.class);
		
		LineInputReceiver lir = new LineInputReceiver();
		
		lir.addHandler(mh);
		
		lir.handle(null);
		
	}
}
