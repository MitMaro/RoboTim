package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.command.exception.InvalidCommandException;

public class PingTest {

	@Test(expectedExceptions = {InvalidCommandException.class})
	public void Ping_server_null() throws InvalidCommandException {
		new Ping(null);
	}

	@Test(expectedExceptions = {InvalidCommandException.class})
	public void Ping_server_server2_null() throws InvalidCommandException {
		new Ping(null, null);
	}
	
	@Test
	public void getMessage_server() throws InvalidCommandException {
		Ping p = new Ping("server");
		
		Assert.assertEquals(p.getMessage(), "PING server");
	}
	
	@Test
	public void getMessage_server_server2() throws InvalidCommandException {
		Ping p = new Ping("server", "server2");
		
		Assert.assertEquals(p.getMessage(), "PING server server2");
	}
	
	@Test
	public void getMessage_server2() throws InvalidCommandException {
		Ping p = new Ping(null, "server2");
		
		Assert.assertEquals(p.getMessage(), "PING :server2");
	}
}
