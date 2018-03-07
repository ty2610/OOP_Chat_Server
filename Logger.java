import java.io.*;
import java.util.*;
import java.lang.*;
public class Logger
{
/*
	Tyler A. Green
	CISC 230
	Patrick L. Jarvis
	11-12-2015

	This class uses the Singleton Pattern, it throws an error if the passed outputstream creates an exception.

	Variables:

		instance
			Is the object of Logger.

		logFile
			Holds the outputstream from constants.

	Constructors:

		private Logger()
			Trys to initializes logFile, or throws an IllegalArgumentException.

	Methods:

		public void write(String message)
			Prints the instance variable.

		public void close()
			Pushes all the memory still in the buffer.

		public static Logger getInstance()
			Accessor to the instance variable.

		public static Logger newInstance()
			Accessor to the Accessor of the instance variable.

*/
	private static Logger instance = null;
	private PrintWriter logFile;

	private Logger()
	{
		//Trys to initializes logFile, or throws an IllegalArgumentException.
		try
		{
			this.logFile = new PrintWriter(Constants.getLogOutputStream(),true);
		}//try
		catch(Exception e)
		{
			throw new IllegalArgumentException("Logger.constructor " + e.getMessage());
		}//catch

	}//Logger()


	public synchronized void writeLog(String message)
	{
		//Prints the instance variable.
		this.logFile.println(message);
	}//write(String message)

	public void close()
	{
		//Pushes all the memory still in the buffer.
		this.logFile.flush();
	}//close()

	public static Logger getInstance()
	{
		//Accessor to the instance variable.
		if(Logger.instance==null)
		{
			Logger.instance = new Logger();
		}
		return Logger.instance;
	}//getInstance()

	public static Logger newInstance()
	{
		//Accessor to the Accessor of the instance variable.
		return Logger.getInstance();
	}//newInstance()

}//class Logger