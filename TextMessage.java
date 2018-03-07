import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class TextMessage extends Message implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class takes in a to, from, and message and processes it to send the message to the correct to and from

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

		message
			a String instance variable meant to hold the message that is wanted to be sent or recieved

	Constructors:

		public TextMessage(ToAddress toAddress, FromAddress fromAddress, String message)
			makes a super call with toAddress and fromAddress and initializes message

	Methods:

		public void processMessage(ChatServer chatServer)
			processes with a given chatServer, decides where the message needs to go and updates addressbook and receiving a message

*/
	private final static long serialVersionUID=1;
	String message;

	public TextMessage(ToAddress toAddress, FromAddress fromAddress, String message)
	{
		//makes a super call with toAddress and fromAddress and initializes message
		super(toAddress,fromAddress);
		this.message = message;
	}//TextMessage(ToAddress toAddress, FromAddress fromAddress, String message)

	public void processMessage(ChatServer chatServer)throws Exception
	{
		//processes with a given chatServer, decides where the message needs to go and updates addressbook and receiving a message
		boolean itsFromUs;
		itsFromUs = this.getFromAddress().equals(chatServer.getAddress());
		if(itsFromUs)
		{
			Socket tranSocket;
			PrintWriter out;
			ObjectOutputStream objectOut;
			Socket chatSocket;
			tranSocket = chatServer.getTranscriptServerAddress().getSocket();
			out = new PrintWriter(new OutputStreamWriter(tranSocket.getOutputStream()));
			out.println("I said: " + this.message);
			out.close();
			tranSocket.close();

			if(!this.getToAddress().equals(chatServer.getAddress()))
			{
				chatSocket = super.getToAddress().getSocket();
				objectOut = new ObjectOutputStream(chatSocket.getOutputStream());
				objectOut.writeObject(this);
				objectOut.close();
				chatSocket.close();
			}//if

		}//if
		else
		{
			Socket socket;
			PrintWriter out;
			User user;
			ServerAddress serverAddress;
			ObjectOutputStream output;
			ServerAddress result;

			socket = chatServer.getTranscriptServerAddress().getSocket();
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			out.println(getFrom() + " said: " + this.message);
			out.close();
			socket.close();

			socket = chatServer.getAddressBookServerAddress().getSocket();
			user = super.getFrom();
			serverAddress = super.getFromAddress();
			output = new ObjectOutputStream(socket.getOutputStream());

			output.writeObject(AddressBookServer.Command.UpdateServerAddress);
			output.writeObject(user);
  			output.writeObject(serverAddress);
  			result = (ServerAddress) new ObjectInputStream(socket.getInputStream()).readObject();

  			output.close();
  			socket.close();

		}//else

	}//processMessage(ChatServer chatServer)

}//class TextMessage