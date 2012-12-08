package ca.mitmaro.RoboTim.irc.message;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.mitmaro.RoboTim.irc.message.exception.InvalidMessage;

public class MessageTest {

	@Test(expectedExceptions = {InvalidMessage.class})
	public void Message_null_message() throws InvalidMessage {
		new Message(null);
	}

	@Test(expectedExceptions = {InvalidMessage.class})
	public void Message_empty_message() throws InvalidMessage {
		new Message("");
	}

	@Test(expectedExceptions = {InvalidMessage.class})
	public void Message_too_many_params() throws InvalidMessage {
		new Message("Command p1 p2 p3 p4 p5 p6 p7 p8 p9 p10 p11 p12 p13 p14 p15 p16");
	}
	
	@Test
	public void Message_command_only() throws InvalidMessage {
		Message m = new Message("Command");
		
		Assert.assertEquals(m.getCommand(), "command");
		
	}
	
	@Test
	public void Message_command_trailing() throws InvalidMessage {
		Message m = new Message("Command :trailing param");
		
		Assert.assertEquals(m.getCommand(), "command");
		Assert.assertEquals(m.getTrailing(), "trailing param");
		Assert.assertEquals(m.getParameter(14), "trailing param");
		
	}

	@Test
	public void Message_all_params() throws InvalidMessage {
		Message m = new Message("Command p1 p2 p3 p4 p5 p6 p7 p8 p9 p10 p11 p12 p13 p14 p15");
		
		Assert.assertEquals(m.getCommand(), "command");
		Assert.assertEquals(m.getParameter(0), "p1");
		Assert.assertEquals(m.getParameter(1), "p2");
		Assert.assertEquals(m.getParameter(2), "p3");
		Assert.assertEquals(m.getParameter(3), "p4");
		Assert.assertEquals(m.getParameter(4), "p5");
		Assert.assertEquals(m.getParameter(5), "p6");
		Assert.assertEquals(m.getParameter(6), "p7");
		Assert.assertEquals(m.getParameter(7), "p8");
		Assert.assertEquals(m.getParameter(8), "p9");
		Assert.assertEquals(m.getParameter(9), "p10");
		Assert.assertEquals(m.getParameter(10), "p11");
		Assert.assertEquals(m.getParameter(11), "p12");
		Assert.assertEquals(m.getParameter(12), "p13");
		Assert.assertEquals(m.getParameter(13), "p14");
		Assert.assertEquals(m.getParameter(14), "p15");
		
	}
	
	@Test
	public void Message_all_params_trailing() throws InvalidMessage {
		Message m = new Message("Command p1 p2 p3 p4 p5 p6 p7 p8 p9 p10 p11 p12 p13 p14 :trailing param");
		
		Assert.assertEquals(m.getCommand(), "command");
		Assert.assertEquals(m.getParameter(0), "p1");
		Assert.assertEquals(m.getParameter(1), "p2");
		Assert.assertEquals(m.getParameter(2), "p3");
		Assert.assertEquals(m.getParameter(3), "p4");
		Assert.assertEquals(m.getParameter(4), "p5");
		Assert.assertEquals(m.getParameter(5), "p6");
		Assert.assertEquals(m.getParameter(6), "p7");
		Assert.assertEquals(m.getParameter(7), "p8");
		Assert.assertEquals(m.getParameter(8), "p9");
		Assert.assertEquals(m.getParameter(9), "p10");
		Assert.assertEquals(m.getParameter(10), "p11");
		Assert.assertEquals(m.getParameter(11), "p12");
		Assert.assertEquals(m.getParameter(12), "p13");
		Assert.assertEquals(m.getParameter(13), "p14");
		Assert.assertEquals(m.getParameter(14), "trailing param");
		Assert.assertEquals(m.getTrailing(), "trailing param");
		
	}
}
