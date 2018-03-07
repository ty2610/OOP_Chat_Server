import java.io.*;
import java.net.*;

public class ChatClient
{
 /*
 	Tyler Green
    Patrick L. Jarvis
    December 6, 2015

*/

 public static void main ( String[] args ) throws Exception
 {

  AddressBookServerAddress    addressBookServerAddress;
  String                      ipAndPort;
  int                         loc;
  String                      name;
  String                      message;
  ServerAddress               priorAddress;
  String[]                    response;
  TranscriptServerAddress     transcriptServerAddress;
  User                        user;
  ServerAddress               userAddress;
  TextMessage				  textMessage;
  User 						  meUser;
  ToAddress					  toAddress;
  FromAddress				  fromAddress;
  ChatServer                  chatServer;
  ChatServerAddress			  chatServerAddress;

  addressBookServerAddress = new AddressBookServerAddress ( ServerAddress.getLocalAddress(65001) );
  transcriptServerAddress  = new TranscriptServerAddress  ( ServerAddress.getLocalAddress(65000) );
  chatServerAddress = new ChatServerAddress(ServerAddress.getLocalAddress(65002));
  meUser = new User("Tyler");



  while ( true)
  {
    message = ask("Please enter name_message> ");
    message = message.trim();

    loc     = (message+" ").indexOf(" ");
    name    = message.substring(0, loc).trim();
    message = message.substring(loc).trim();

    if ( name.length() < 1 ) tell("Error. Name was not entered.");
     else
     if ( message.length() < 1 )  tell("Error. Message was not entered.");
     else
     {
      // get ServerAddress for this person
      user        = new User ( name );
      userAddress = queryAddressBook ( user, addressBookServerAddress );

      if ( userAddress == null )
      {
       while ( userAddress == null )
       {
        ipAndPort   = ask("Enter IP:portNumber for " + user);
        try                                    { userAddress = new ServerAddress(ipAndPort); }
        catch ( IllegalArgumentException iae ) { tell("Error. Invalid IP or port number: " + ipAndPort ); }
       }  //  while ( ipAndPort == null )

         priorAddress = updateAddressBook ( user, userAddress, addressBookServerAddress);

         System.out.println("Debug message: ServerAddress for " + user + " was " + priorAddress + " and is now " + userAddress);


      }  // if ( userAddress == null )

      	   toAddress = new ToAddress(user,userAddress);
	  	   fromAddress = new FromAddress(meUser,chatServerAddress);
	       textMessage = new TextMessage(toAddress,fromAddress,message);
	       Socket chatSocket;
	       ObjectOutputStream output;
	       ServerAddress address;

	       //address = textMessage.getToAddress();

	       //chatSocket = address.getSocket();
		   chatSocket = chatServerAddress.getSocket();
	       output = new ObjectOutputStream(chatSocket.getOutputStream());
	       output.writeObject(textMessage);

	  	   output.close();
	       chatSocket.close();



     }  //if ... else



} // while (true)

 }



 private static ServerAddress queryAddressBook ( User user, AddressBookServerAddress addressBookServerAddress) throws Exception
 {
  ObjectInputStream  input;
  ObjectOutputStream output;
  ServerAddress      serverAddress = null;
  Socket             socket;

  socket = addressBookServerAddress.getSocket();

  output = new ObjectOutputStream ( socket.getOutputStream() );
  output.writeObject( AddressBookServer.Command.RequestServerAddress );
  output.writeObject( user );

  //  Must not open the ObjectInputStream before the ObjectOutputStream
  //  because the Server protocol at the other end does a read first. If
  //  we open the ObjectInputStream before the ObjectOutputStream, the
  //  program will hang.
  input         = new ObjectInputStream  ( socket.getInputStream() );
  serverAddress = (ServerAddress) input.readObject();

  input.close();
  output.close();
  socket.close();

  return serverAddress;
 }

 private static ServerAddress updateAddressBook ( User user, ServerAddress serverAddress, AddressBookServerAddress addressBookServerAddress) throws Exception
 {
  ObjectOutputStream output;
  ServerAddress      result;
  Socket             socket;

  socket = addressBookServerAddress.getSocket();

  output = new ObjectOutputStream ( socket.getOutputStream() );

  output.writeObject( AddressBookServer.Command.UpdateServerAddress );
  output.writeObject( user );
  output.writeObject( serverAddress );

  result = (ServerAddress) new ObjectInputStream(socket.getInputStream()).readObject();

  output.close();
  socket.close();

  return result;
 }


 private static String ask (String prompt) throws IOException
 {
  System.out.print(prompt + " ");
  return new BufferedReader(new InputStreamReader(System.in)).readLine();
 }

 private static void tell (String message) { System.out.println ( message ); }

}
