import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class AddressBookServerAddress extends ServerAddress implements Serializable
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

		public AddressBookServerAddress(String ipNumberAndPortNumber)
			creates an AddressBookServerAddress with ip and port in the same String

		public AddressBookServerAddress(String ipNumber, int portNumber)
			creates an AddressBookServerAddress with a ip string and a portnumber int

		public AddressBookServerAddress(InetSocketAddress address)
			creates an AddressBookServerAddress with a InetSocketAddress

		public AddressBookServerAddress(ServerAddress serverAddress)
			creates an AddressBookServerAddress with a ServerAddress

*/
	private final static long serialVersionUID=1;

	public AddressBookServerAddress(String ipNumberAndPortNumber)
	{
		//creates an AddressBookServerAddress with ip and port in the same String
		super(ipNumberAndPortNumber);
	}//ddressBookServerAddress(String ipNumberAndPortNumber)

	public AddressBookServerAddress(String ipNumber, int portNumber)
	{
		//creates an AddressBookServerAddress with a ip string and a portnumber int
		super(ipNumber,portNumber);
	}//AddressBookServerAddress(String ipNumber, int portNumber)

	public AddressBookServerAddress(InetSocketAddress address)
	{
		//creates an AddressBookServerAddress with a InetSocketAddress
		super(address);
	}//AddressBookServerAddress(InetSocketAddress address)

	public AddressBookServerAddress(ServerAddress serverAddress)
	{
		//creates an AddressBookServerAddress with a ServerAddress
		super(serverAddress);
	}//AddressBookServerAddress(ServerAddress serverAddress)

}//class AddressBookServerAddress