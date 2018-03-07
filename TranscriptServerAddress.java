import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class TranscriptServerAddress extends ServerAddress implements Serializable
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

		public TranscriptServerAddress(String ipNumberAndPortNumber)
			creates an TranscriptServerAddress with ip and port in the same String

		public TranscriptServerAddress(String ipNumber, int portNumber)
			creates an TranscriptServerAddress with a ip string and a portnumber int

		public TranscriptServerAddress(InetSocketAddress address)
			creates an TranscriptServerAddress with a InetSocketAddress

		public TranscriptServerAddress(ServerAddress serverAddress)
			creates an TranscriptServerAddress with a ServerAddress

*/
	private final static long serialVersionUID=1;

	public TranscriptServerAddress(String ipNumberAndPortNumber)
	{
		//creates an TranscriptServerAddress with ip and port in the same String
		super(ipNumberAndPortNumber);
	}//TranscriptServerAddress(String ipNumberAndPortNumber)

	public TranscriptServerAddress(String ipNumber, int portNumber)
	{
		//creates an TranscriptServerAddress with a ip string and a portnumber int
		super(ipNumber,portNumber);
	}//TranscriptServerAddress(String ipNumber, int portNumber)

	public TranscriptServerAddress(InetSocketAddress address)
	{
		//creates an TranscriptServerAddress with a InetSocketAddress
		super(address);
	}//TranscriptServerAddress(InetSocketAddress address)

	public TranscriptServerAddress(ServerAddress serverAddress)
	{
		//creates an TranscriptServerAddress with a ServerAddress
		super(serverAddress);
	}//TranscriptServerAddress(ServerAddress serverAddress)

}//class TranscriptServerAddress