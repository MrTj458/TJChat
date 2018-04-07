package tj.chat.server.view;

import javax.swing.JFrame;

import tj.chat.server.controller.Server;

public class ServerFrame extends JFrame
{
	private Server controller;
	
	private ServerPanel basePanel;
	
	public ServerFrame(Server controller)
	{
		this.controller = controller;
		
		this.setTitle("TJ Chat Version 1.0");
		basePanel = new ServerPanel(controller);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setResizable(false);
		this.setSize(800, 600);
		this.setTitle("TJ Chat Server");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public ServerPanel getPanel()
	{
		return basePanel;
	}
}
