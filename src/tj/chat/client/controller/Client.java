package tj.chat.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
	private String address;
	private int port;
	private String name;
	
	private Socket socket;
	
	private InputStream inStream;
	private OutputStream outStream;
	
	private int ID;
	
	public Client( String name, String address, int port)
	{
		this.name = name;
		this.address = address;
		this.port = port;
	}
	
	public void start()
	{
		try
		{
			socket = new Socket(address, port);
			
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();
			
			sendMessage("n/" + name);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		waitForMessages();
	}
	
	private void waitForMessages()
	{
		new Thread("Messages")
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
	
	public void sendMessage(String message)
	{
		try
		{
			outStream.write(message.getBytes());
		}
		catch (IOException e)
		{}
	}
	
	private void processMessage(String message)
	{
		if(message.startsWith("/s/"))
		{
			if(message.contains("check"))
			{
				sendMessage(Integer.toString(ID));
			}
		}
		else if(message.contains("id"))
		{
			ID = Integer.parseInt(message.substring(6));
		}
		else
		{
			showMessage(message);
		}
	}
	
	private void showMessage(String message)
	{
		//TODO
	}
}
