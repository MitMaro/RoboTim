package ca.mitmaro.RoboTim.irc;

import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.message.Message;
import ca.mitmaro.RoboTim.irc.message.MessageHandler;
import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;
import ca.mitmaro.RoboTim.network.exception.InvalidLine;

import static org.mockito.Mockito.*;

public class MessageReceiverTest {

	@Test
	public void handle_ok() throws InvalidMessage, InvalidLine {
		
		LineInputReceiver mr = new LineInputReceiver();
		MessageHandler mh = mock(MessageHandler.class);
		
		mr.addHandler(mh);
		
		mr.handle("command arg");
		
		verify(mh).handle(any(Message.class));
	}
	
	@Test(expectedExceptions = {InvalidLine.class})
	public void handle_exception() throws InvalidLine {
		
		LineInputReceiver mr = new LineInputReceiver();
		MessageHandler mh = mock(MessageHandler.class);
		
		mr.addHandler(mh);
		
		mr.handle("");
		
	}
}
