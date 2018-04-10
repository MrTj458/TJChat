package tj.chat.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import tj.chat.client.view.ChatFrame;

public class Client
{
	private String address;
	private int port;
	private String name;
	
	private Socket socket;
	
	private InputStream inStream;
	private OutputStream outStream;
	
	private int ID;
	
	private ChatFrame frame;
	
	public Client( String name, String address, int port)
	{
		this.name = name;
		this.address = address;
		this.port = port;
		
		frame = new ChatFrame(this);
	}
	
	public void start()
	{
		try
		{
			showMessage("Connecting to " + address + ":" + port);
			socket = new Socket(address, port);
			
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();
			outStream.flush();
			
			if(socket.isConnected()) showMessage("Connected!");
			
			sendMessage("/n/" + name);
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
			outStream.flush();
			showMessage("Sent message: " + message);
		}
		catch (IOException e)
		{
			showMessage("Unable to send message!");
		}
	}
	
	private void processMessage(String message)
	{
		if(message.startsWith("/s/check"))
		{
			showMessage("Sending reply");
			sendMessage("/r/" + Integer.toString(ID));
		}
		else if(message.contains("/s/id"))
		{
			ID = Integer.parseInt(message.substring(5,7));
			
			showMessage("Received id " + ID);
		}
		else
		{
			showMessage(message);
		}
	}
	
	private void showMessage(String message)
	{
		frame.getPanel().addMessage(message);
	}
}
