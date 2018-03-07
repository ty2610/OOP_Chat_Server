import java.io.*;
import java.util.*;
import java.lang.*;
public class Constants
{
/*
	Tyler A. Green
	CISC 230
	Patrick L. Jarvis
	11-12-2015

	This class takes in and stores the passed outputstream.

	Variables:

		logOutputStream
			Holds the passed outputstream.

	Constructors:

		private Constants()
			Initializes Constants.

	Methods:

		public static void setLogOutputStream(OutputStream logOutputStream)
			Initializes the instance variable to the passed outputstream.

		public static OutputStream getLogOutputStream()
			Accessor to the instance variable.

*/
	private static OutputStream logOutputStream = System.out;

	private Constants(){}

	public static void setLogOutputStream(OutputStream logOutputStream)
	{
		//Initializes the instance variable to the passed outputstream.
		if(logOutputStream == null)
		{
			throw new IllegalArgumentException("The out passed to Constants is null.");
		}//if
		Constants.logOutputStream = logOutputStream;
	}//setLogOutputStream(OutputStream logOutputStream)

	public static OutputStream getLogOutputStream()
	{
		//Accessor to the instance variable.
		return Constants.logOutputStream;
	}//getLogOutputStream()

}//class Constants