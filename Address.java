import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class Address implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	This class takes in a User and ServerAddress and has accessors for the User and ServerAddress.

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

		user
			Is a passed User object

		serverAddress
			Is a passed ServerAddress object

	Constructors:

		public Address(User user,ServerAddress serverAddress)
			initializes the user and serverAddress instance variables

	Methods:

		public User getUser()
			accessor to user instance variable

		public ServerAddress getAddress()
			accessor to serverAddress instance variable

*/
	private final static long serialVersionUID=1;
	private User user;
	private ServerAddress serverAddress;

	public Address(User user,ServerAddress serverAddress)
	{
		//initializes the user and serverAddress instance variables
		this.user = user;
		this.serverAddress = serverAddress;
	}//Address(User user,ServerAddress serverAddress)

	public User getUser()
	{
		//accessor to user instance variable
		return this.user;
	}//User getUser()

	public ServerAddress getAddress()
	{
		//accessor to serverAddress instance variable
		return this.serverAddress;
	}//getAddress()

}//class Address