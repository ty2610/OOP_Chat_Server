import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class Driver
{
	public static void main(String[] args)throws IOException
	{

		Socket connection;
		String to;
		String from;
		String message;
		String name;
		User user;
		Socket socket;
		ServerAddress serverAddress;

		//user = new User("Tyler");
		//serverAddress = new ServerAddress("10.55.2.120:8081");
		//serverAddress.getSocket();
		//System.out.println(serverAddress.getPort());
		//socket = new Socket(8080);


		//TranscriptServer transcriptServer;
		//transcriptServer = new TranscriptServer(8080);
		//transcriptServer.startServer();
		//for(int i = 0; i<)

        //AddressBook addressBook;

		from = "Tyler Green(140.209.121.58)";
		//from = "Tyler'sComputer(10.55.2.120)";
		//connection = new Socket("140.209.123.248",8080);
		//connection = new Socket("140.209.121.58",8080);

        //addressBook = AddressBook.getInstance();
		//addressBook.add("Tyler","140.209.121.58");
		//addressBook.add("Josh","140.209.123.248");
		//addressBook.add("Trap","140.209.121.78");
		ServerAddress transcriptServerAddress;
		//transcriptServerAddress = new TranscriptServerAddress("140.209.121.78",8080);
		//System.out.println(transcriptServerAddress.getAsString());
		//transcriptServerAddress.getLocalAddress(8080);

		while(true)
		{
			try
			{
				//name = getName();
				//to = addressBook.getIP(name);
				to = "10.55.2.120";
				if(to==null)
				{
					to = getIP();
					//addressBook.add(name,to);
				}

				message = getMessage();
				sendMessage(from,to,message);

			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}


		/*while(true)
		{
			try
			{
				name = getName();
				to = addressBook.getIP(name);
				if(to==null)
				{
					to = getIP();
					addressBook.add(name,to);
				}

				message = getMessage();
				sendMessage(from,to,message);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}*/

		/*Demo[] demo;
		demo = new Demo[4];
		demo[0] = new Demo("Hi",250);
		demo[1] = new Demo("Hello",500);
		demo[2] = new Demo("Goodbye",2000);
		demo[3] = new Demo("Bye",750);
		for(int i=0; i<demo.length;i++)
		{
			new Thread(demo[i]).start();
		}*/
	}//main

	private static void sendMessage(String from, String to, String message)throws IOException
	{
		Socket connection;
		PrintWriter out;

		connection = new Socket(to,65000);
		out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
		out.println(from + " says " + message);
		out.close();
		connection.close();

	}

	private static String getName()throws IOException
	{
		return askTheUser("Enter a name: ");
	}

	private static String askTheUser(String message)throws IOException
	{
		System.out.print(message);
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	private static String getIP()throws IOException
	{
		return askTheUser("Enter IP#: ");
	}

	private static String getMessage()throws IOException
	{
		return askTheUser("Enter the message: ");
	}


}//class Driver