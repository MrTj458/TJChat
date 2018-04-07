package tj.chat.server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import tj.chat.server.controller.Server;

public class ServerClient implements Runnable
{
	private Socket clientSocket;
	
	private InputStream inStream;
	private OutputStream outStream;
	
	private int ID;
	
	private String name;
	
	private Server controller;
	
	private int attempts;
	
	public ServerClient(Socket clientSocket, int ID, String name, Server controller)
	{
		this.clientSocket = clientSocket;
		
		try
		{
			inStream = clientSocket.getInputStream();
			outStream = clientSocket.getOutputStream();
		}
		catch (IOException e)
		{
			System.out.println("Unable to set up streams with client. ID: " + ID);
		}
		
		this.ID = ID;
		this.name = name;
		this.controller = controller;
	}
	
	@Override
	public void run()
	{
		handleClientSocket();
	}
	
	private void handleClientSocket()
	{	
		new Thread("ClientSocket" + ID)
		{
			public void run()
			{
				try
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
					while(true)
					{	
						String line;
						while((line = reader.readLine()) != null)
						{	
							processMessage(line);
						}
					}
				}
				catch(IOException e)
				{}
			}
		}.start();
	}
	
	private void processMessage(String message) throws IOException
	{
		//Check if incoming message is a command;
		if(message.startsWith("/"))
		{
			if(message.contains("quit"))
			{
				controller.disconnectClient(ID);
				clientSocket.close();
			}
		}
		else if(message.startsWith("n/"))
		{
			this.name = message.substring(2);
			controller.updateClientList();
			controller.broadcastMessage(name, " Has Connected");
		}
		else
		{
			controller.broadcastMessage(this.name, message);
		}
	}
	
	public void sendMessage(String message)
	{
		try
		{
			outStream.write(message.getBytes());
		}
		catch (IOException e)
		{}
	}
	
	public void addAttempt()
	{
		attempts++;
	}
	
	public int getAttempts()
	{
		return attempts;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public String getName()
	{
		return this.name;
	}
}













