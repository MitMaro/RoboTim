package ca.mitmaro.RoboTim.irc.mapper;

import org.testng.annotations.Test;
import org.testng.Assert;

import ca.mitmaro.RoboTim.irc.command.commands.Pong;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

import static org.mockito.Mockito.*;

public class PongMapperTest {

	@Test
	public void mapMessage_not_pong() throws InvalidCommandException {
		PongMapper pm = new PongMapper();
		
		Message m = mock(Message.class);

		when(m.getCommand()).thenReturn("not_pong");
		
		Assert.assertNull(pm.mapMessage(m));
		
	}
	
	@Test
	public void mapMessage_valid() throws InvalidCommandException {
		
		PongMapper pm = new PongMapper();
		
		Message m = mock(Message.class);

		when(m.getCommand()).thenReturn("pong");
		when(m.getParameter(0)).thenReturn("server1");
		when(m.getParameter(1)).thenReturn("server2");
		when(m.getTrailing()).thenReturn(null);
		
		Pong p = (Pong)pm.mapMessage(m);

		Assert.assertEquals(p.getServer(), "server1");
		Assert.assertEquals(p.getServer2(), "server2");
		
	}

	@Test
	public void mapMessage_valid_with_trailing() throws InvalidCommandException {
		
		PongMapper pm = new PongMapper();
		
		Message m = mock(Message.class);

		when(m.getCommand()).thenReturn("pong");
		when(m.getParameter(0)).thenReturn("leading");
		when(m.getTrailing()).thenReturn("trailing");
		
		Pong p = (Pong)pm.mapMessage(m);

		Assert.assertEquals(p.getServer(), "leading");
		Assert.assertEquals(p.getServer2(), "trailing");
		
	}
}
