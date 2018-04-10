package tj.chat.client.view;

import javax.swing.JFrame;

import tj.chat.client.controller.Client;

public class ChatFrame extends JFrame
{
	private Client controller;
	
	private ChatPanel basePanel;
	
	public ChatFrame(Client controller)
	{
		this.controller = controller;
		
		this.setTitle("TJ Chat Version 1.0");
		basePanel = new ChatPanel(controller);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setResizable(false);
		this.setSize(800, 600);
		this.setTitle("TJ Chat Client");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public ChatPanel getPanel()
	{
		return basePanel;
	}
}
