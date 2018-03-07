import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class ChatServerAddress extends ServerAddress implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class is all super class calls to the constructors in ServerAddress, extends ServerAddress and implements Serializable

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

	Constructors:

		public ChatServerAddress(String ipNumberAndPortNumber)
			creates an ChatServerAddress with ip and port in the same String

		public ChatServerAddress(String ipNumber, int portNumber)
			creates an ChatServerAddress with a ip string and a portnumber int

		public ChatServerAddress(InetSocketAddress address)
			creates an ChatServerAddress with a InetSocketAddress

		public ChatServerAddress(ServerAddress serverAddress)
			creates an ChatServerAddress with a ServerAddress

*/
	private final static long serialVersionUID=1;

	public ChatServerAddress(String ipNumberAndPortNumber)
	{
		//creates an ChatServerAddress with ip and port in the same String
		super(ipNumberAndPortNumber);
	}//ChatServerAddress(String ipNumberAndPortNumber)

	public ChatServerAddress(String ipNumber, int portNumber)
	{
		//creates an ChatServerAddress with a ip string and a portnumber int
		super(ipNumber,portNumber);
	}//ChatServerAddress(String ipNumber, int portNumber)

	public ChatServerAddress(InetSocketAddress address)
	{
		//creates an ChatServerAddress with a InetSocketAddress
		super(address);
	}//ChatServerAddress(InetSocketAddress address)

	public ChatServerAddress(ServerAddress serverAddress)
	{
		//creates an ChatServerAddress with a ServerAddress
		super(serverAddress);
	}//ChatServerAddress(ServerAddress serverAddress)

}//class ChatServerAddress