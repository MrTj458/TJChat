package tj.chat.server.controller;

import tj.chat.server.view.StartFrame;

public class ServerMain
{
	private static StartFrame frame;
	
	public static void main(String[] args)
	{
		frame = new StartFrame();
	}
	
	public static void startServer(int port)
	{
		frame.dispose();
		Server server = new Server(port);
		server.start();
	}
}
