package tj.chat.server.view;

import javax.swing.JFrame;

import tj.chat.server.controller.Server;

public class StartFrame extends JFrame
{
	private StartPanel basePanel;
	
	public StartFrame()
	{
		basePanel = new StartPanel();
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setResizable(false);
		this.setSize(250, 300);
		this.setTitle("TJ Chat Server");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
