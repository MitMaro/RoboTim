package ca.mitmaro.RoboTim.network;

import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.net.Socket;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mitmaro.RoboTim.network.exception.Exception;

public class Connection {
	
	/** The connected socket */
	protected Socket socket;
	
	/** For reading from the server */
	protected BufferedReader in;
	
	/** For writing to the server */
	protected Writer out;
	
	protected Logger logger = LoggerFactory.getLogger(Connection.class);
	
	/**
	 * Associates the given Socket to this Client
	 * 
	 * @param Socket client The socket connection
	 * @param int timeout The timeout for the connection
	 * @throws Exception 
	 */
	public Connection(Socket socket, int timeout) throws IOException {
		this(
			socket,
			new PrintWriter(socket.getOutputStream(), true),
			new BufferedReader(new InputStreamReader(socket.getInputStream())),
			timeout
		);
	}
	
	/**
	 * Creates a socket connection for the given host and port
	 * 
	 * @param Socket client The socket connection
	 * @param int timeout The timeout for the connection
	 */
	public Connection(String host, int port, int timeout) throws IOException {
		this(new Socket(InetAddress.getByName(host).getHostName(), port), timeout);
	}
	
	/**
	 * Associates the given Socket to this Client
	 * 
	 * @param Socket client The socket connection
	 * @param int timeout The timeout for the connection
	 * @throws Exception 
	 */
	public Connection(Socket socket, Writer out, BufferedReader in, int timeout) throws IOException {
		this.socket = socket;
		this.socket.setSoTimeout(timeout);
		this.out = out;
		this.in = in;
	}
	
	public Writer getWriter() {
		return this.out;
	}
	
	public BufferedReader getReader() {
		return this.in;
	}
	
	/**
	 * Wait for this client to respond
	 *
	 * @return String The response from the connected socket
	 */
	public String waitForResponse() throws IOException {
		
		this.logger.debug("Waiting For Response");
		String msg = this.in.readLine();
		this.logger.info("Server Response: {}", msg);
		return msg;
	}
	
	/**
	 * Send a message to the connected socket
	 */
	public void sendMessage(String msg) throws IOException {
		this.logger.info("Sending Message: {}", msg);
		this.out.write(msg);
		this.out.flush();
	}
	
	public void shutdown() throws IOException {
		this.logger.info("IO Shutting Down");
		this.socket.shutdownInput();
		this.socket.shutdownOutput();
	}
	
	/**
	 * Disconnect the socket connection
	 */
	public void disconnect() throws IOException {
		this.logger.info("Disconnecting Server Connection");
		this.socket.close();
	}
}