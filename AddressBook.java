import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class AddressBook<Key,Value>
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	This class is the accessor and updator for the HashMap

	Variables:

		addressBook
			an instance variable that is a HashMap for Key and Value

	Constructors:

		public AddressBook()
			initializes the HashMap instance variable

	Methods:

		public Value getAddressFor(Key key)
			gets the value from a given key

		public void putAddressFor(Key key, Value value)
			places the key and value into the HashMap


*/
	private HashMap<Key,Value> addressBook;

	public AddressBook()
	{
		//initializes the HashMap instance variable
		this.addressBook = new HashMap<Key,Value>();
	}//AddressBook()

	public synchronized Value getAddressFor(Key key)
	{
		//gets the value from a given key
		return this.addressBook.get(key);
	}//getAddressFor(Key key)

	public synchronized void putAddressFor(Key key, Value value)
	{
		//places the key and value into the HashMap
		this.addressBook.put(key,value);
	}//putAddressFor(Key key, Value value)

}//class AddressBook