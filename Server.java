import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.atomic.*;

abstract public class Server implements Runnable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	abstract public super class that implements Runnable; has methods pertaining to start, stop, run, connection manager, and get port. All designed to work for every server

	Variables:

		serverIsRunning
			a AtomicBoolean instance variable meant to tell if the server is runnning

		serverSocket
			a ServerSocket instance variable touching the socket of the created server


	Constructors:

		public Server(ServerAddress serverAddress)
			initializes a serversocket for the server

	Methods:

		abstract public ProtocolHandler getProtocolHandler();
			abstract methods for the protocolHandler

		private Runnable getConnectionManager(Socket socket)
			returns a Runnable of a new ConnectionManager

		public int getPort()
			returns the port of the server

		public void startConnectionManager(Runnable connectionManager)
			starts a new thread from the given connectionManager

		public boolean isRunning()
			returns if the server is running

		private void isRunning(boolean running)
			mutator for the ServerIsRunning instance variable

		public synchronized void startServer()
			starts the server

		public synchronized void stopServer()
			stops the server

		public void writeLogMessage(String message)
			writes the message to the system

		public void run()
			is the canstant running of the server, accepts connections and starts connection managers

		public ServerAddress getAddress()
			returns the ServerAddress of the server

*/
	AtomicBoolean serverIsRunning;
	ServerSocket serverSocket;

	public Server(ServerAddress serverAddress)
	{
		//initializes a serversocket for the server
		serverSocket = null;

		serverIsRunning = new AtomicBoolean();
		try
		{
			this.serverSocket = new ServerSocket(serverAddress.getPort(),0,serverAddress.getAddress().getAddress());
		}//try
		catch(Exception e)
		{
			writeLogMessage(e.getMessage());
		}//catch

	}//Server(ServerAddress serverAddress)

	abstract public ProtocolHandler getProtocolHandler();//abstract methods for the protocolHandler

	private Runnable getConnectionManager(Socket socket)
	{
		//returns a Runnable of a new ConnectionManager
		return new ConnectionManager(socket,getProtocolHandler());
	}//getConnectionManager(Socket socket)

	public int getPort()
	{
		//returns the port of the server
		return this.serverSocket.getLocalPort();
	}//getPort()

	public void startConnectionManager(Runnable connectionManager)
	{
		//starts a new thread from the given connectionManager
		new Thread(connectionManager).start();
	}//startConnectionManager(Runnable connectionManager)

	public boolean isRunning()
	{
		//returns if the server is running
		return this.serverIsRunning.get();
	}//isRunning()

	private void isRunning(boolean running)
	{
		//mutator for the ServerIsRunning instance variable
		this.serverIsRunning.set(running);
	}//isRunning(boolean running)

	public synchronized void startServer()
	{
		//starts the server
		this.run();
		while(!isRunning())
		{
			try
			{
				Thread.sleep(1);
			}//try
			catch(Exception e){}
		}//while
		isRunning(true);
	}//startServer()

	public synchronized void stopServer()throws IOException
	{
		//stops the server
		isRunning(false);
		this.serverSocket.close();
	}//stopServer()

	public void writeLogMessage(String message)
	{
		//writes the message to the system
		Logger logger;
		logger = Logger.getInstance();
		logger.writeLog(message);
	}//writeLogMessage(String message)

	public void run()
	{
		//is the canstant running of the server, accepts connections and starts connection managers
		Socket clientSocket;
		Runnable connectionManager;
		try
		{
			System.out.println(this.getClass().getName() + " " + this.getAddress());
		}//try
		catch(Exception e)
		{
			writeLogMessage(e.getMessage());
		}//catch
		isRunning(true);
		while(this.isRunning())
		{
			try
			{
				clientSocket = this.serverSocket.accept();
				connectionManager = this.getConnectionManager(clientSocket);
				this.startConnectionManager(connectionManager);
			}//try
			catch(Exception e)
			{
				this.writeLogMessage(e.getMessage());
			}//catch
		}//while
	}//run()

	public ServerAddress getAddress()throws IOException
	{
		//returns the ServerAddress of the server
		return new ServerAddress(new InetSocketAddress(serverSocket.getInetAddress(),this.getPort()));
	}//getAddress()



	private class ConnectionManager implements Runnable
	{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	private class that is the protocolHandler for all servers

	Variables:

		protocolHandler
			instance variable that holds ProtocolHandler

		socket
			Socket instance variable

	Constructors:

		public ConnectionManager(Socket socket, ProtocolHandler protocolHandler)
			initializes the protocolHandler and socket instance variables


	Methods:

		public void run()
			runs the protocolHandler execute method when necessary for the specific server

*/
		private ProtocolHandler protocolHandler;
		private Socket socket;

		public ConnectionManager(Socket socket, ProtocolHandler protocolHandler)
		{
			//initializes the protocolHandler and socket instance variables
			this.protocolHandler = protocolHandler;
			this.socket = socket;
		}//ConnectionManager(Socket socket, ProtocolHandler protocolHandler)

		public void run()
		{
			//runs the protocolHandler execute method when necessary for the specific server
			try
			{
				this.protocolHandler.execute(this.socket);
			}//try
			catch(Exception e)
			{
				writeLogMessage(e.getMessage());
			}//catch

			finally
			{
				try
				{
					this.socket.getInputStream().close();
				}//try
				catch(Exception e){}
				try
				{
					this.socket.getOutputStream().close();
				}//try
				catch(Exception e){}
				try
				{
					this.socket.close();
				}//try
				catch(Exception e){}
			}//finally

		}//run()

	}//class ConnectionManager

}//class Server