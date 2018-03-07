import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class ChatServer extends Server implements ProtocolHandler
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class creates a server of itself, implements it's own ProtocolHandler, and has access to the transcriptServerAddress and the addressBookServerAddress

	Variables:

		addressBookServerAddress
			an AddressBookServerAddress instance variable

		transcriptServerAddress
			a TranscriptServerAddress instance variable

	Constructors:

		public ChatServer(ChatServerAddress serverAddress, AddressBookServerAddress addressBookServerAddress, TranscriptServerAddress transcriptServerAddress)
			constructor that makes a call to the super class and then initializes transcriptServerAddress and addressBookServerAddress

	Methods:

		public AddressBookServerAddress getAddressBookServerAddress()
			accessor to addressBookServerAddress instance variable

		public TranscriptServerAddress getTranscriptServerAddress()
			accessor to transcriptServerAddress instance variable

		public ProtocolHandler getProtocolHandler()
			returns the protocol handler, which is this class

		public void execute(Socket socket)
			executes the functionality of this specific server,getting a inputstream and casting it as a Message type and then calling to the processMessage, closes all necessary things


*/
	public static void main(String[] args)throws IOException
	{
		//Starts up ChatServer
		ChatServer chatServer;
		chatServer = new ChatServer(new ChatServerAddress(ServerAddress.getLocalAddress(65002)),new AddressBookServerAddress(ServerAddress.getLocalAddress(65001)),new TranscriptServerAddress(ServerAddress.getLocalAddress(65000)));
		chatServer.startServer();
	}//main

	private AddressBookServerAddress addressBookServerAddress;
	private TranscriptServerAddress transcriptServerAddress;

	public ChatServer(ChatServerAddress serverAddress, AddressBookServerAddress addressBookServerAddress, TranscriptServerAddress transcriptServerAddress)throws IOException
	{
		//constructor that makes a call to the super class and then initializes transcriptServerAddress and addressBookServerAddress
		super(serverAddress);
		this.addressBookServerAddress = addressBookServerAddress;
		this.transcriptServerAddress = transcriptServerAddress;
	}//ChatServer(ChatServerAddress serverAddress, AddressBookServerAddress addressBookServerAddress, TranscriptServerAddress transcriptServerAddress)

	public AddressBookServerAddress getAddressBookServerAddress()
	{
		//accessor to addressBookServerAddress instance variable
		return this.addressBookServerAddress;
	}//getAddressBookServerAddress()

	public TranscriptServerAddress getTranscriptServerAddress()
	{
		//accessor to transcriptServerAddress instance variable
		return this.transcriptServerAddress;
	}//getTranscriptServerAddress()

	public ProtocolHandler getProtocolHandler()
	{
		//returns the protocol handler, which is this class
		return this;
	}//getProtocolHandler()

	public void execute(Socket socket)
	{
		//executes the functionality of this specific server,getting a inputstream and casting it as a Message type and then calling to the processMessage, closes all necessary things
		try
		{
			Message message;
			ObjectInputStream input;
			input = new ObjectInputStream(socket.getInputStream());
			message = (Message)input.readObject();
			message.processMessage(this);
			try
			{
				input.close();
				socket.close();
			}//try
			catch(Exception e){}
		}//try
		catch(Exception e)
		{
			writeLogMessage(e.getMessage());
		}//catch

	}//execute(Socket socket)

}//class ChatServer