import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class AddressBookServer extends Server
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	This class extends Server creates its own server, protocolHandler, and initializes the Hashmap for <userServerAddress>

	Variables:

		addressBook
			a HashMap for given User and ServerAddress objects

	Constructors:

		public AddressBookServer(AddressBookServerAddress serverAddress)
			initializes addressBook and makes a call to the super class

	Methods:

		public ProtocolHandler getProtocolHandler()
			an accessor to the protocolHandler for this specific server

		public AddressBookServerAddress getAddress()
			gives back an AddressBookServerAddress object

*/
	public enum Command implements Serializable
	{
/*
	This Enum opens up an AddressBookServer server and does enum commands to RequestServerAddress and UpdateServerAddress

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

	Constructors:

		private void command
			default constructor

	Methods:

		public void processRequest(RequestProcessor requestProcessor)
			reads in a object and casts as User, then gets the ServerAddress from the HashMap, then writes of the received ServerAddress

		public void processRequest(RequestProcessor requestProcessor)
			reads in and casts an object as a User, then reads in and casts the object as a ServerAddress, stores old user in Hashmap then puts user and serverAddress in Hashmap and then writes out old HashMap

		public abstract void processRequest(RequestProcessor requestProcessor)
			abstract that is subclassed
*/
		RequestServerAddress
		{
			public void processRequest(RequestProcessor requestProcessor)
			{
				//reads in a object and casts as User, then gets the ServerAddress from the HashMap, then writes of the received ServerAddress
				User user;
				ServerAddress serverAddress;
				user = (User)(requestProcessor.readObject());//read user object
				serverAddress = requestProcessor.getFromAddressBook(user);//determine if in address book
				requestProcessor.writeObject(serverAddress);//write serveraddress object(could be null)
			}//processRequest(RequestProcessor requestProcessor)
		},

		UpdateServerAddress
		{
			public void processRequest(RequestProcessor requestProcessor)
			{
				//reads in and casts an object as a User, then reads in and casts the object as a ServerAddress, stores old user in Hashmap then puts user and serverAddress in Hashmap and then writes out old HashMap
				ServerAddress serverAddress;
				User user;
				ServerAddress pre;

				user = (User)(requestProcessor.readObject());
				serverAddress = (ServerAddress)(requestProcessor.readObject());
				pre = requestProcessor.getFromAddressBook(user);
				requestProcessor.putIntoAddressBook(user,serverAddress);
				requestProcessor.writeObject(pre);

			}//processRequest(RequestProcessor requestProcessor)
		};

		private final static long serialVersionUID=1;
		private void command(){}
		public abstract void processRequest(RequestProcessor requestProcessor);//abstract that is subclassed
	}//Enum Command


	private AddressBook<User,ServerAddress> addressBook;

	public static void main(String[] args)throws IOException
	{
		//opens up the AddressBookServer
		AddressBookServer addressBookServer;
		addressBookServer = new AddressBookServer(new AddressBookServerAddress(ServerAddress.getLocalAddress(65001)));
		addressBookServer.startServer();
	}//main

	public AddressBookServer(AddressBookServerAddress serverAddress)
	{
		//initializes addressBook and makes a call to the super class
			super(serverAddress);
			addressBook = new AddressBook<User,ServerAddress>();
	}//AddressBookServer(AddressBookServerAddress serverAddress)

	public ProtocolHandler getProtocolHandler()
	{
		//an accessor to the protocolHandler for this specific server
		return (new RequestProcessor());
	}//getProtocolHandler()

	public AddressBookServerAddress getAddress()throws IOException
	{
		//gives back an AddressBookServerAddress object
		return new AddressBookServerAddress(super.getAddress());
	}//getAddress()

	private class RequestProcessor implements ProtocolHandler
	{
	/*
		this is a private nested class to implement ProtocolHandler for this specific class

		Variables:

			input
				input is an ObjectInputStream to be written to

			output
				output is an ObjectOutputStream to write objects out with

		Constructors:

			RequestProcessor()
				A constructor that initializes input and output to null

		Methods:

			public Object readObject()
				reads in input and returns the object

			public void writeObject(Object object)
				writes out an object to the output

			public void execute(Socket socket)
				makes input and output equal to the inputstream and outputstream and makes a command object the trys to processes the command, finally closing the input, output, and socket

			public ServerAddress getFromAddressBook(User user)
				gets the serverAddress from the addressBook with a user for the key

			public void putIntoAddressBook(User user, ServerAddress serverAddress)
				places the key user with the value serverAddress in addressBook

	*/
		private ObjectInputStream input;
		private ObjectOutputStream output;

		public RequestProcessor()
		{
			//A constructor that initializes input and output to null
			input = null;
			output = null;
		}//RequestProcessor()

		public Object readObject()
		{
			//reads in input and returns the object
			Object object;
			object = null;
			try
			{
				object = input.readObject();
			}//try
			catch(Exception e)
			{
				writeLogMessage(e.getMessage());
			}//catch
			return object;
		}//readObject()

		public void writeObject(Object object)
		{
			//writes out an object to the output
			try
			{
				output.writeObject(object);
			}//try
			catch(Exception e)
			{
				writeLogMessage(e.getMessage());
			}//catch
		}//writeObject(Object object)

		public void execute(Socket socket)
		{
		//makes input and output equal to the inputstream and outputstream and makes a command object the trys to processes the command, finally closing the input, output, and socket
			AddressBookServer.Command command;

			try
			{
				this.input = new ObjectInputStream(socket.getInputStream());

				command = (AddressBookServer.Command)this.readObject();

				this.output = new ObjectOutputStream(socket.getOutputStream());

				command.processRequest(this);
			}//try
			catch(Exception e)
			{
				writeLogMessage(e.getMessage());
			}//catch

			finally
			{
				try
				{
					this.input.close();
				}//try
				catch(Exception e){}
				try
				{
					this.output.close();
				}//try
				catch(Exception e){}
				try
				{
					socket.close();
				}//try
				catch(Exception e){}

			}//finally

		}//execute(Socket socket)

		public ServerAddress getFromAddressBook(User user)
		{
			//gets the serverAddress from the addressBook with a user for the key
			return addressBook.getAddressFor(user);
		}//getFromAddressBook(User user)

		public void putIntoAddressBook(User user, ServerAddress serverAddress)
		{
			//places the key user with the value serverAddress in addressBook
			addressBook.putAddressFor(user,serverAddress);
		}//putIntoAddressBook(User user, ServerAddress serverAddress)

	}//class RequestProcessor

}//class AddressBookServer