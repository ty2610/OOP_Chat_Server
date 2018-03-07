import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

abstract public class Message implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class takes in the from and to, and have accessors to the User and ServerAddress of passed from and to

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

		toAddress
			a ToAddress instance variable

		fromAddress
			a FromAddress isntance variable

	Constructors:

		public Message(ToAddress toAddress, FromAddress fromAddress)
			initializes the toAddress and fromAddress instance variables

	Methods:

		public User getTo()
			returns the User of the toAddress

		public ServerAddress getToAddress()
			returns the ServerAddress of the toAddress

		public User getFrom()
			returns the User of the fromAddress

		public ServerAddress getFromAddress()
			returns the ServerAddress of the fromAddress

		public abstract void processMessage(ChatServer chatServer)
			abstract method for sub class

*/
	private final static long serialVersionUID=1;
	private ToAddress toAddress;
	private FromAddress fromAddress;

	public Message(ToAddress toAddress, FromAddress fromAddress)
	{
		//initializes the toAddress and fromAddress instance variables
		this.toAddress=toAddress;
		this.fromAddress = fromAddress;
	}//Message(ToAddress toAddress, FromAddress fromAddress)

	public User getTo()
	{
		//returns the User of the toAddress
		return this.toAddress.getUser();
	}//getTo()

	public ServerAddress getToAddress()
	{
		//returns the ServerAddress of the toAddress
		return this.toAddress.getAddress();
	}//getToAddress()

	public User getFrom()
	{
		//returns the User of the fromAddress
		return this.fromAddress.getUser();
	}//getFrom()

	public ServerAddress getFromAddress()
	{
		//returns the ServerAddress of the fromAddress
		return this.fromAddress.getAddress();
	}//getFromAddress()

	public abstract void processMessage(ChatServer chatServer)throws Exception;//abstract method for sub class

}//class Message