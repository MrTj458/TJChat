package tj.chat.client.view;

import javax.swing.JFrame;

public class AddressFrame extends JFrame
{
	private AddressPanel basePanel;
	
	public AddressFrame()
	{
		basePanel = new AddressPanel();
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setResizable(false);
		this.setSize(250, 300);
		this.setTitle("TJ Chat");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
