package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public class PongTest {

	@Test(expectedExceptions = {InvalidCommandException.class})
	public void Pong_no_server() throws InvalidCommandException {
		new Pong(null);
	}

	@Test(expectedExceptions = {InvalidCommandException.class})
	public void Pong_no_server_no_server2() throws InvalidCommandException {
		new Pong(null, null);
	}

	@Test
	public void getMessage_server() throws InvalidCommandException {
		Pong p = new Pong("server");
		Assert.assertEquals(p.getMessage(), "PONG server");
	}

	@Test
	public void getMessage_server_server2() throws InvalidCommandException {
		Pong p = new Pong("server", "server2");
		Assert.assertEquals(p.getMessage(), "PONG server server2");
	}

	@Test
	public void getMessage_server2() throws InvalidCommandException {
		Pong p = new Pong(null, "server2");
		Assert.assertEquals(p.getMessage(), "PONG :server2");
	}
}
