import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class ServerAddress implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class creates an object from and IP and port to start up a server; allows the retreval of ip, hostname, localhost, port, socket, and InetSocketAddress from created server

	Variables:

		address
			an InetSocketAddress instance variable to be used to gain information about the created server

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

	Constructors:


		public ServerAddress(String ipNumberAndPortNumber)
			initializes address with a string of the IP and port

		public ServerAddress(String ipNumber, int portNumber)
			initializes address with a string of the IP and a int of the portnumber

		public ServerAddress(InetSocketAddress address)
			initializes address with a InetSocketAddress

		public ServerAddress(ServerAddress serverAddress)
			initializes address with a ServerAddress

	Methods:

		public static ServerAddress getLocalAddress(int portNumber)
			returns the ServerAddress of the machine it is called in

		public InetSocketAddress getAddress()
			accessor to the address instance variable

		public String getAsString()
			returns address as a string

		public String getHostName()
			returns the hostname of address

		public String getIP()
			returns the IP of address

		public int getPort()
			returns the port number of address

		public String toString()
			tester methods for getAsString()

		public Socket getSocket()
			returns the socket of address

		public static boolean isValidIP(String ipNumber)
			returns a boolean of if the IP is a valid set of numbers

		public boolean equals(ServerAddress serverAddress)
			overrides equals and returns true if two InetSocketAddresses are equal

*/
	private InetSocketAddress address;
	private final static long serialVersionUID=1;

	public ServerAddress(String ipNumberAndPortNumber)
	{
		//initializes address with a string of the IP and port
		if(ipNumberAndPortNumber.indexOf(':')==-1)
		{
			throw new IllegalArgumentException("the passed ipNumberAndPortNumber to method:ServerAddress(String ipNumberAndPortNumber) has no :");
		}
		System.out.println(ipNumberAndPortNumber);
		String[] storage;
		int portAsInteger;
		InetAddress holder;
		portAsInteger = -1;
		Logger logger;
		logger = Logger.getInstance();
		if(ipNumberAndPortNumber == null)
		{
			throw new IllegalArgumentException("The passed ipNumberAndPortNumber to method:TranscriptServerAddress(String ipNumberAndPortNumber) is null");
		}//if
		storage = ipNumberAndPortNumber.split(":");
		//System.out.println(storage[0]);
		//System.out.println(storage[1]);
		try
		{
			portAsInteger = Integer.parseInt(storage[1].trim());
		}//try
		catch(NumberFormatException nfe)
		{
			System.out.println(nfe.getMessage());
		}//catch
		if(portAsInteger <= 0)
		{
			throw new IllegalArgumentException("the passed Port number to method:ServerAddress(String ipNumberAndPortNumber) is less or equal to 0");
		}//if
		if(portAsInteger > 65536)
		{
			throw new IllegalArgumentException("the passed Port number to method:ServerAddress(String ipNumberAndPortNumber) is greater than 65536");
		}//if
		if(!isValidIP(storage[0]))
		{
			throw new IllegalArgumentException("the passed IP number to method:ServerAddress(String ipNumberAndPortNumber) is not valid");
		}//if

		try
		{
			holder = InetAddress.getByName(storage[0].trim());
			//System.out.println(holder);
			this.address = new InetSocketAddress(holder,portAsInteger);
		}//try
		catch(Exception e)
		{
			logger.writeLog(e.getMessage());
		}//catch

	}//ServerAddress(String ipNumberAndPortNumber)

	public ServerAddress(String ipNumber, int portNumber)
	{
		//initializes address with a string of the IP and a int of the portnumber
		InetAddress holder;
		Logger logger;
		logger = Logger.getInstance();
		holder = null;
		try
		{
			holder = InetAddress.getByName(ipNumber);
		}//try
		catch(Exception e)
		{
			logger.writeLog(e.getMessage());
		}//catch
		if(holder == null)
		{
			throw new IllegalArgumentException("The local variable holder in method:TranscriptServerAddress(String ipNumber, int portNumber) is null");
		}//if
		if(ipNumber == null)
		{
			throw new IllegalArgumentException("The passed ipNumber to method:TranscriptServerAddress(String ipNumber, int portNumber) is null");
		}//if
		if(portNumber <= 0)
		{
			throw new IllegalArgumentException("The passed portNumber to method:TranscriptServerAddress(String ipNumber, int portNumber) is less or equal to 0");
		}//if
		if(portNumber > 65536)
		{
			throw new IllegalArgumentException("The passed portNumber to method:TranscriptServerAddress(String ipNumber, int portNumber) is greater than 65536");
		}//if
		if(!isValidIP(ipNumber))
		{
			throw new IllegalArgumentException("the passed IP number to method:ServerAddress(String ipNumber, int portNumber) is not valid");
		}//if

		this.address = new InetSocketAddress(holder, portNumber);

	}//ServerAddress(String ipNumber, int portNumber)

	public ServerAddress(InetSocketAddress address)
	{
		//initializes address with a InetSocketAddress
		if(address == null)
		{
			throw new IllegalArgumentException("The passed InetSocketAddress to method:TranscriptServerAddress(InetSocketAddress address) is null");
		}//if
		if(address.isUnresolved())
		{
			throw new IllegalArgumentException("The passed InetSocketAddress to method:TranscriptServerAddress(InetSocketAddress address) has an unresolved address");
		}//if
		if(!isValidIP(address.getAddress().getHostAddress()))
		{
			throw new IllegalArgumentException("the passed ip to method:ServerAddress(InetSocketAddress address) is not valid");
		}//if
		this.address = address;
	}//ServerAddress(InetSocketAddress address)

	public ServerAddress(ServerAddress serverAddress)
	{
		//initializes address with a ServerAddress
		if(serverAddress == null)
		{
			throw new IllegalArgumentException("The passed ServerAddress to method:ServerAddress(ServerAddress serverAddress) is null");
		}//if
		this.address = new InetSocketAddress(serverAddress.getIP(),serverAddress.getPort());

	}//ServerAddress(ServerAddress serverAddress)

	public static ServerAddress getLocalAddress(int portNumber)throws UnknownHostException, SocketException
	{
		//returns the ServerAddress of the machine it is called in
		if(portNumber <= 0)
		{
			throw new IllegalArgumentException("The passed portNumber to method:TranscriptServerAddress(String ipNumber, int portNumber) is less or equal to 0");
		}//if
		if(portNumber > 65536)
		{
			throw new IllegalArgumentException("The passed portNumber to method:TranscriptServerAddress(String ipNumber, int portNumber) is greater than 65536");
		}//if
		return new ServerAddress (InetAddress.getLocalHost().getHostAddress(), portNumber);

    }//ServerAddress getLocalAddress(int portNumber)

	public InetSocketAddress getAddress()
	{
		//accessor to the address instance variable
		return this.address;
	}//getAddress()

	public String getAsString()
	{
		//returns address as a string
		return getAddress().toString();
	}//getAsString()

	public String getHostName()
	{
		//returns the hostname of address
		return getAddress().getHostName();
	}//getHostName()

	public String getIP()
	{
	//returns the IP of address
		String[] holder;
		String temp;
		temp = getAsString();

		holder = temp.split(":");
		temp = holder[0].substring(1);
		return temp;
	}//getIP()

	public int getPort()
	{
		//returns the port number of address
		return getAddress().getPort();
	}//getPort()

	public String toString()
	{
		//tester methods for getAsString()
		return this.getAsString();
	}//toString()

	public Socket getSocket()throws IOException
	{
		//returns the socket of address
		Socket socket;
		socket = new Socket(this.getAddress().getAddress(),getPort());
		return socket;
	}//getSocket()

	public static boolean isValidIP(String ipNumber)
	{
		//returns a boolean of if the IP is a valid set of numbers
		boolean result;
		String[] part;
		int x;
		if(ipNumber == null)
		{
			throw new IllegalArgumentException("The passed String to method:isValidIP(String ipNumber) is null");
		}//if
		part = ipNumber.split("\\.");
		result = (part.length == 4) || (part.length == 6);
		for(int i=0;i<part.length && result;i++)
		{
			try
			{
				x = Integer.parseInt(part[i]);
				result = ((x>0) && (x<256));
			}//try
			catch(NumberFormatException nfe){result = false;}
		}//for
		return result;
	}//isValidIP(String ipNumber)

	public boolean equals(ServerAddress serverAddress)
	{
		//overrides equals and returns true if two InetSocketAddresses are equal
		if(serverAddress == null)
		{
			throw new IllegalArgumentException("The passed serverAddress to method:equals(ServerAddress serverAddress) is null");
		}//if
		return this.getAddress().equals(serverAddress.getAddress());
	}//equals(ServerAddress serverAddress)

}//class ServerAddress