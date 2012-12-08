package ca.mitmaro.RoboTim.irc.command;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;
import ca.mitmaro.RoboTim.irc.mapper.Mapper;
import ca.mitmaro.RoboTim.irc.message.Message;

public class CommandMapperTest {

	@Test
	public void mapMessageToCommand() throws InvalidCommandException {
		
		Mapper mer1 = Mockito.mock(Mapper.class);
		Mapper mer2 = Mockito.mock(Mapper.class);
		Mapper mer3 = Mockito.mock(Mapper.class);
		Message m = Mockito.mock(Message.class);
		Command c = Mockito.mock(Command.class);
		Command c_bad = Mockito.mock(Command.class);

		Mockito.when(mer1.mapMessage(m)).thenReturn(null);
		Mockito.when(mer2.mapMessage(m)).thenReturn(c);
		Mockito.when(mer3.mapMessage(m)).thenReturn(c_bad);
		
		CommandMapper cm = new CommandMapper();
		cm.registerMapper(mer1);
		cm.registerMapper(mer2);
		cm.registerMapper(mer3);
		
		Assert.assertEquals(cm.mapMessageToCommand(m), c);

		Mockito.verify(mer1).mapMessage(m);
		Mockito.verify(mer2).mapMessage(m);
		Mockito.verify(mer3, Mockito.never()).mapMessage(m);
		
		
		
	}
	
	@Test(expectedExceptions = {InvalidCommandException.class})
	public void mapMessageToCommand_invalid_command() throws InvalidCommandException {
		
		Mapper mer = Mockito.mock(Mapper.class);
		Message m = Mockito.mock(Message.class);

		Mockito.when(mer.mapMessage(m)).thenThrow(new InvalidCommandException("message"));
		
		CommandMapper cm = new CommandMapper();
		cm.registerMapper(mer);
		
		cm.mapMessageToCommand(m);
	}
}
