package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.annotations.Test;

public class Numeric004Test {

	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void getMessage() {
		Numeric004 n4 = new Numeric004("server", "version", "user_modes", "channel_modes");
		n4.getMessage();
	}
}
