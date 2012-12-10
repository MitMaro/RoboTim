package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrivmsgTest {

	@Test
	public void getMessage() {
		Privmsg pm = new Privmsg("target", "message message");
		
		Assert.assertEquals(pm.getMessage(), "PRIVMSG target :message message");
	}
}
