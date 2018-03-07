import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class FromAddress extends Address implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class extends Address and implements Serializable, it makes a suepr call passing user and serverAddress

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

	Constructors:

		public FromAddress(User user, ServerAddress serverAddress)
			constructor that makes a super classes class with the passed user and serverAddress

*/
	private final static long serialVersionUID=1;

	public FromAddress(User user, ServerAddress serverAddress)
	{
		//constructor that makes a super classes class with the passed user and serverAddress
		super(user,serverAddress);
	}//FromAddress(User user, ServerAddress serverAddress)

}//class FromAddress