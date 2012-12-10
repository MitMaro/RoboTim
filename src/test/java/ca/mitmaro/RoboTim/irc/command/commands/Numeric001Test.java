package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.annotations.Test;

public class Numeric001Test {

	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void getMessage() {
		Numeric001 n1 = new Numeric001("nick", "user", "host");
		n1.getMessage();
	}
}
