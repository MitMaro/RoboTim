package ca.mitmaro.RoboTim.irc.command.commands;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JoinTest {

	@Test
	public void getMessage_channels() {
		
		Join j = new Join(new String[] {"#test1", "&test2"});
		
		Assert.assertEquals(j.getMessage(), "JOIN #test1,&test2");
		
	}

	@Test
	public void getMessage_channels_keys() {
		
		Join j = new Join(new String[] {"#test1", "&test2"}, new String[] {"foo1", "foo2"});
		
		Assert.assertEquals(j.getMessage(), "JOIN #test1,&test2 foo1,foo2");
		
	}
}
