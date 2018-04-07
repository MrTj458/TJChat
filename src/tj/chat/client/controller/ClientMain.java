package tj.chat.client.controller;

import tj.chat.client.view.AddressFrame;

public class ClientMain
{
	private static AddressFrame frame;
	
	public static void main(String[] args)
	{
		frame = new AddressFrame();
	}
	
	public static void startClient( String name, String address, int port)
	{
		frame.dispose();
		Client client = new Client(name, address, port);
		client.start();
	}
}
