package ca.mitmaro.RoboTim.irc.mapper;

import org.testng.annotations.Test;
import org.testng.Assert;

import ca.mitmaro.RoboTim.irc.command.commands.Numeric004;
import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.message.Message;

import static org.mockito.Mockito.*;

public class Numeric004MapperTest {

	@Test
	public void mapMessage_not_004() throws InvalidCommandException {
		Numeric004Mapper mp = new Numeric004Mapper();
		
		Message m = mock(Message.class);
		
		when(m.getCommand()).thenReturn("not_004");
		
		Assert.assertNull(mp.mapMessage(m));
		
	}

	@Test
	public void mapMessage() throws InvalidCommandException {
		Numeric004Mapper mp = new Numeric004Mapper();
		
		Message m = mock(Message.class);
		
		when(m.getCommand()).thenReturn("004");
		when(m.getParameter(0)).thenReturn("server_name");
		when(m.getParameter(1)).thenReturn("version");
		when(m.getParameter(2)).thenReturn("user_modes");
		when(m.getParameter(3)).thenReturn("channel_modes");
		
		Numeric004 n004 = (Numeric004)mp.mapMessage(m);

		Assert.assertEquals(n004.getCommandName(), "004");
		Assert.assertEquals(n004.getServerName(), "server_name");
		Assert.assertEquals(n004.getUserModes(), "user_modes");
		Assert.assertEquals(n004.getChannelModes(), "channel_modes");
		
	}
	
}
