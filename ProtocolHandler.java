import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public interface ProtocolHandler
{
/*
	Tyler A. Green
	Cisc 230
	Patrick L. Jarvis
	12-15-2015

	public interface for the protocolhandlers to use execute
*/
	public void execute(Socket socket);
}