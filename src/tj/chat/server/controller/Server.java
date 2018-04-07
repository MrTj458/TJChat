package tj.chat.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import tj.chat.server.model.ServerClient;
import tj.chat.server.model.UniqueIdentifier;
import tj.chat.server.view.ServerFrame;

public class Server
{
	private Server thisServer;
	
	private int port;
	private ServerSocket serverSocket;
	
	private ArrayList<ServerClient> clients;
	private ArrayList<Integer> responses;
	
	//UI
	private ServerFrame uiFrame;
	
	public Server(int port)
	{
		thisServer = this;
		
		this.port = port;
		clients = new ArrayList<ServerClient>();
		responses = new ArrayList<Integer>();
		
		uiFrame = new ServerFrame(this);
	}
	
	public void start()
	{
		try
		{
			serverSocket = new ServerSocket(port);
			showMessage("Server started on port: " + port);
		}
		catch (IOException e)
		{
			showMessage("ERROR: Unable to start server!");
		}
		
		checkForConnections();
		checkOnClients();
	}
	
	private void checkForConnections()
	{
		new Thread("NewConnectionChecking")
		{
			public void run()
			{
				try
				{
					while(true)
					{
						Socket clientSocket = serverSocket.accept();
						showMessage("Client connected from: " + clientSocket.getInetAddress().getHostAddress() + ":" + Integer.toString(clientSocket.getPort()));
						
						ServerClient client = new ServerClient(clientSocket, UniqueIdentifier.getIdentifier(), "New User", thisServer);
						sendToClient(client.getID(), "/s/id " + client.getID());
						clients.add(client);
						updateClientList();
						Thread t = new Thread(client);
						t.start();
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void checkOnClients()
	{
		new Thread("DisconnectChecking")
		{
			public void run()
			{
				while(true)
				{
					try
					{
						serverBroadcast("/s/check");
						Thread.sleep(2000);
						for(int i = 0; i < clients.size(); i++)
						{
							if(!responses.contains(clients.get(i).getID()))
							{
								clients.get(i).addAttempt();
								showMessage("User " + clients.get(i).getName() + "(" + clients.get(i).getID() + ") Not responding. Attempt: " + clients.get(i).getAttempts());
								
								if(clients.get(i).getAttempts() > 4)
								{
									showMessage("User " + clients.get(i).getName() + "(" + clients.get(i).getID() + ") Lost connection");
									disconnectClient(clients.get(i).getID());
								}
							}
						}
						responses.clear();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void broadcastMessage(String sendersName, String message)
	{
		for(int i = 0; i < clients.size(); i++)
		{
			clients.get(i).sendMessage(sendersName + ": " + message + "\n");
		}
		showMessage(sendersName + ": " + message);
	}
	
	private void serverBroadcast(String message)
	{
		for(int i = 0; i < clients.size(); i++)
		{
			clients.get(i).sendMessage(message + "\n");
		}
		if(!message.startsWith("/s/")) showMessage(message);
	}
	
	private void sendToClient(int id, String message)
	{
		for(int i = 0; i < clients.size(); i++)
		{
			if(clients.get(i).getID() == id)
			{
				clients.get(i).sendMessage(message);
			}
		}
	}
	
	public void disconnectClient(int id)
	{
		for(int i = 0; i < clients.size(); i++)
		{
			if(clients.get(i).getID() == id)
			{
				broadcastMessage(clients.get(i).getName(), "Has Disconnected");
				
				UniqueIdentifier.addID(clients.get(i).getID());
				clients.remove(i);
				updateClientList();
				break;
			}
		}
	}
	
	public void addresponse(int id)
	{
		responses.add(id);
	}
	
	private void showMessage(String message)
	{
		uiFrame.getPanel().addMainMessage(message);
	}
	
	public void updateClientList()
	{
		String clientString = "Connected Clients: " + clients.size() + "\n";
		
		for(int i = 0; i < clients.size(); i++)
		{
			clientString += clients.get(i).getName() + " (" + clients.get(i).getID() + ")\n";
		}
		
		uiFrame.getPanel().setCurrentClients(clientString);
	}
	
	public void serverCommand(String command)
	{
		if(command.startsWith("say "))
		{
			serverBroadcast("Server: " + command.substring(4));
		}
		else if(command.startsWith("help"))
		{
			showMessage("HELP:");
			showMessage("help - shows this dialogue");
			showMessage("say [message] - sends a message to all clients");
		}
		else
		{
			showMessage("Unknown command");
		}
	}
}
