import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class TranscriptServer extends Server implements ProtocolHandler
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class creates a server of itself and implements its own ProtocolHandler

	Constructors:

		public TranscriptServer(TranscriptServerAddress serverAddress)
			constructor that makes a super call with a serverAddress

	Methods:

		public ProtocolHandler getProtocolHandler()
			returns itself since it is a protocolhandler

		public void startConnectionManager(Runnable connectionManager)
			starts the ConnectionManager

		public void execute(Socket socket)
			reads in the passed message and prints it

		public TranscriptServerAddress getAddress()
			returns the TranscriptServerAddress of the server

*/

	public TranscriptServer(TranscriptServerAddress serverAddress)throws IOException
	{
		//constructor that makes a super call with a serverAddress
		super(serverAddress);
	}//TranscriptServer(TranscriptServerAddress serverAddress)

	public static void main(String[] args)throws IOException
	{
		//starts up TranscriptServer
		TranscriptServer transcriptServer;
		transcriptServer = new TranscriptServer(new TranscriptServerAddress(ServerAddress.getLocalAddress(65000)));
		transcriptServer.startServer();
	}//main

	public ProtocolHandler getProtocolHandler()
	{
		//returns itself since it is a protocolhandler
		return this;
	}//getProtocolHandler()

	public void startConnectionManager(Runnable connectionManager)
	{
		//starts the ConnectionManager
		connectionManager.run();
	}//startConnectionManager(Runnable connectionManager)

	public void execute(Socket socket)
	{
		//reads in the passed message and prints it
		try
		{
			BufferedReader in;
			String record;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//System.out.println(socket);
			record = in.readLine();
			for(int i=0;record!=null;i++)
			{
				System.out.println(i + ": " + record);
				record = in.readLine();
			}//for
			try
			{
				in.close();
				socket.close();
			}//try
			catch(Exception e){}
		}//try
		catch(Exception e)
		{
			super.writeLogMessage(e.getMessage());
		}//catch

	}//execute(Socket socket)

	public TranscriptServerAddress getAddress()throws IOException
	{
		//returns the TranscriptServerAddress of the server
		return new TranscriptServerAddress(super.getAddress());
	}//TranscriptServerAddress getAddress()

}//class TranscriptServer