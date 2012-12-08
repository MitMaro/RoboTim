package ca.mitmaro.RoboTim.irc.mapper;

import org.testng.Assert;

import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.command.commands.Ping;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

import static org.mockito.Mockito.*;

public class PingMapperTest {

	@Test
	public void mapMessage_not_ping() throws InvalidCommandException {
		
		PingMapper pm = new PingMapper();
		
		Message m = mock(Message.class);

		when(m.getCommand()).thenReturn("not_ping");
		
		Assert.assertNull(pm.mapMessage(m));

	}

	@Test
	public void mapMessage_valid() throws InvalidCommandException {
		
		PingMapper pm = new PingMapper();
		
		Message m = mock(Message.class);

		when(m.getCommand()).thenReturn("ping");
		when(m.getParameter(0)).thenReturn("server1");
		when(m.getParameter(1)).thenReturn("server2");
		when(m.getTrailing()).thenReturn(null);
		
		Ping p = (Ping)pm.mapMessage(m);

		Assert.assertEquals(p.getServer(), "server1");
		Assert.assertEquals(p.getServer2(), "server2");
		
	}

	@Test
	public void mapMessage_valid_with_trailing() throws InvalidCommandException {
		
		PingMapper pm = new PingMapper();
		
		Message m = mock(Message.class);

		when(m.getCommand()).thenReturn("ping");
		when(m.getParameter(0)).thenReturn("leading");
		when(m.getTrailing()).thenReturn("trailing");
		
		Ping p = (Ping)pm.mapMessage(m);

		Assert.assertEquals(p.getServer(), "leading");
		Assert.assertEquals(p.getServer2(), "trailing");
		
	}
	
}
