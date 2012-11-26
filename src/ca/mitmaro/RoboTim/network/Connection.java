package ca.mitmaro.RoboTim.network;

import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.InetAddress;
import java.util.logging.Logger;

public class Connection {
	
	/** The connected socket */
	protected Socket socket;
	
	/** For reading from the server */
	protected BufferedReader in;
	
	/** For writing to the server */
	protected PrintWriter out;
	
	/** The port of */
	protected int port;
	
	/** The host of */
	protected String host;
	
	/** Simple logger */
	protected Logger log;
	
	/**
	 * Associates the given Socket to this Client
	 * 
	 * @param Socket client The socket connection
	 * @param int timeout The timeout for the connection
	 */
	public Connection(Socket socket, int timeout, Logger logger) throws IOException {
		this.socket = socket;
		try {
			this.socket.setSoTimeout(timeout);
		}
		catch(SocketException e) {} // rare error, won't handle this for now
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.log = logger;
	}
	
	/**
	 * Creates a socket connection for the given host and port
	 * 
	 * @param Socket client The socket connection
	 * @param int timeout The timeout for the connection
	 */
	public Connection(String host, int port, int timeout, Logger logger) throws IOException {
		this(new Socket(InetAddress.getByName(host).getHostName(), port), timeout, logger);
	}
	
	public boolean canRead() throws IOException {
		return this.in.ready();
	}
	
	/**
	 * Wait for this client to respond
	 *
	 * @return String The response from the connected socket
	 */
	public String waitForResponse() throws IOException {
		this.log.info("Waiting For Response");
		String msg = this.in.readLine();
		this.log.info(String.format("Client Response: %s", msg));
		return msg;
	}
	
	/**
	 * Set the host and port for this client. Also sets the Unique ID.
	 *
	 * @param String host The host name
	 * @param int post The port number
	 */
	public void setHostAndPort(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Send a message to the connected socket
	 */
	public void sendMessage(String msg) throws IOException {
		
		this.log.info(String.format("Sending Message: %s", msg));
		this.out.print(msg);
		if (this.out.checkError()){
			throw new IOException ("Error Sending Message");
		}
	}
	
	public void shutdown() throws IOException {
		this.socket.shutdownInput();
	}
	
	/**
	 * Disconnect the socket connection
	 */
	public void disconnect() {
		this.log.info("Disconnecting Client");
		try {
			this.socket.close();
		}
		catch (IOException e) {
			this.log.warning(String.format("Error Disconnecting Client: %s"));
		}
	}
	
}