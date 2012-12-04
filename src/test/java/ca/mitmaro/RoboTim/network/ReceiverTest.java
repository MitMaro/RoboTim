package ca.mitmaro.RoboTim.network;

import java.io.IOException;

import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import org.testng.Assert;

import ca.mitmaro.RoboTim.network.exception.InvalidLine;

public class ReceiverTest {

	@Test
	public void run_ok() throws IOException, InvalidLine {
		Connection c = mock(Connection.class);
		when(c.waitForResponse()).thenReturn("Hello There").thenReturn(null);
		
		ReceiverHandler rh = mock(ReceiverHandler.class);
		
		Receiver r = new Receiver(c);
		r.addHandler(rh);
		
		r.run();
		
		verify(rh).handle("Hello There");
	}
	
	@Test
	public void run_ioexception() throws IOException {
		
		IOException e = new IOException();
		Connection c = mock(Connection.class);
		when(c.waitForResponse()).thenThrow(e);
		
		ReceiverHandler rh = mock(ReceiverHandler.class);
		
		Receiver r = new Receiver(c);
		r.addHandler(rh);
		r.run();
		
		Assert.assertTrue(r.hasError());
		Assert.assertEquals(r.getLastError(), e);
	}
}
