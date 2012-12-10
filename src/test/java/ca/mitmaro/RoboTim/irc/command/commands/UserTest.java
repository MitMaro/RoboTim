package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

	@Test
	public void getMessage() {
		User u = new User("user", 1, "name");
		
		Assert.assertEquals(u.getMessage(), "USER user 1 * :name");
	}
}
