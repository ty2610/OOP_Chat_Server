import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class User implements Serializable
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	this class creates a User object from a passed name, it overrides equals and hashcode

	Variables:

		serialVersionUID
			a static long initialized to 1 to be used in the serializable interface

		userName
			a String instance variable that holds the name of a user

	Constructors:

		public User(String userName)
			initializes userName to the passed name

	Methods:

		public String getName()
			accessor to the userName instance variable

		public boolean equals(Object other)
			casts the passed object as a User, if no exception use user equals, if exception, use string equals

		private boolean equals(String other)
			compares the passed Strings

		private boolean equals(User other)
			compares the passed users

		public int hashCode()
			overrides the HashCode into the String HashCode

		public String toString()
			tester method for getName()

*/
	private final static long serialVersionUID=1;
	private String userName;

	public User(String userName)
	{
		//initializes userName to the passed name
		if(userName == null)
		{
			throw new IllegalArgumentException("The passed String to method:User(String userName) is null");
		}//if
		this.userName = userName;
	}//User(String userName)

	public String getName()
	{
		//accessor to the userName instance variable
		return this.userName;
	}//getName()

	public boolean equals(Object other)
	{
		//casts the passed object as a User, if no exception use user equals, if exception, use string equals
		boolean result;
		try
		{
			result = this.equals((User)other);
		}//try
		catch(ClassCastException cce)
		{
			result = this.equals(other.toString());
		}//catch

		return result;
	}//equals(Object other)

	private boolean equals(String other)
	{
		//compares the passed Strings
		return(other != null && (this.getName().equals(other)));//(this.getName() == other);
	}//equals(String other)

	private boolean equals(User other)
	{
		//compares the passed users
		return(other != null && ((other.getName() == other.getName())));//|| this.equals(other)));
	}//equals(User other)

	public int hashCode()
	{
		//overrides the HashCode into the String HashCode
		return this.getName().hashCode();
	}//hashCode()

	public String toString()
	{
		//tester method for getName()
		return this.getName();
	}//toString()

}//class User