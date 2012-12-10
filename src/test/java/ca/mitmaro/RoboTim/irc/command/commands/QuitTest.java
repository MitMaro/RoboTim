package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

public class QuitTest {

	@Test
	public void getMessage_no_message() {
		Quit q = new Quit();
		
		Assert.assertEquals(q.getMessage(), "QUIT");
		
	}
	
	@Test
	public void getMessage_message() {
		Quit q = new Quit("message");
		
		Assert.assertEquals(q.getMessage(), "QUIT :message");
		
	}
}
