import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class ToAddress extends Address implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class makes a super call to the super class and creates the ToAddress with a user and serverAddress

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

	Constructors:

		public ToAddress(User user, ServerAddress serverAddress)
			constructor that makes super call with passed user and serverAddress

*/
	private final static long serialVersionUID=1;

	public ToAddress(User user, ServerAddress serverAddress)
	{
		//constructor that makes super call with passed user and serverAddress
		super(user,serverAddress);
	}//ToAddress(User user, ServerAddress serverAddress)

}//class ToAddress