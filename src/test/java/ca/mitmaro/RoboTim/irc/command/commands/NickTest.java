package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NickTest {

	@Test
	public void getMessage() {
		Nick n = new Nick("myNick");
		Assert.assertEquals(n.getMessage(), "NICK :myNick");
	}
}
