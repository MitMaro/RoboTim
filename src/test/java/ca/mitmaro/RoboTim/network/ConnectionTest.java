package ca.mitmaro.RoboTim.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ConnectionTest {

	@Test
	public void Connection() throws IOException {
		Socket socket = mock(Socket.class);
		Writer w = mock(PrintWriter.class);
		BufferedReader r = mock(BufferedReader.class);
		
		Connection c = new Connection(socket, w, r, 100);
		Assert.assertEquals(c.getWriter(), w);
		Assert.assertEquals(c.getReader(), r);
		
		verify(socket).setSoTimeout(100);
	}

	@Test
	public void disconnect() throws IOException {

		Socket socket = mock(Socket.class);
		Writer w = mock(PrintWriter.class);
		BufferedReader r = mock(BufferedReader.class);
		
		Connection c = new Connection(socket, w, r, 100);
		c.disconnect();
		verify(socket).close();
	}

	@Test
	public void sendMessage() throws IOException {

		Socket socket = mock(Socket.class);
		Writer w = mock(PrintWriter.class);
		BufferedReader r = mock(BufferedReader.class);
		
		Connection c = new Connection(socket, w, r, 100);
		
		c.sendMessage("Test Message");
		verify(w).write("Test Message");
	}

	@Test
	public void shutdown() throws IOException {

		Socket socket = mock(Socket.class);
		Writer w = mock(PrintWriter.class);
		BufferedReader r = mock(BufferedReader.class);
		
		Connection c = new Connection(socket, w, r, 100);
		
		c.shutdown();
		verify(socket).shutdownInput();
		verify(socket).shutdownOutput();
	}

	@Test
	public void waitForResponse() throws IOException {

		Socket socket = mock(Socket.class);
		Writer w = mock(PrintWriter.class);
		BufferedReader r = mock(BufferedReader.class);
		
		when(r.readLine()).thenReturn("Test Message");
		
		Connection c = new Connection(socket, w, r, 100);
		
		Assert.assertEquals(c.waitForResponse(), "Test Message");
		
	}
}
