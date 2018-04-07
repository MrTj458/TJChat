package tj.chat.server.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import tj.chat.server.controller.Server;

public class ServerPanel extends JPanel
{
	private Server controller;
	
	private SpringLayout baseLayout;
	
	private JTextArea connectedClients;
	private JScrollPane connectedClientsPane;
	
	private JTextArea textArea;
	private JScrollPane textAreaPane;
	
	private JTextField textField;
	
	private JButton submitButton;
	
	public ServerPanel(Server controller)
	{
		this.controller = controller;
		
		baseLayout = new SpringLayout();
		
		connectedClients = new JTextArea("Connected clients: 0\n");
		connectedClientsPane = new JScrollPane(connectedClients);
		
		textArea = new JTextArea();
		textAreaPane = new JScrollPane(textArea);
		
		textField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		
		connectedClients.setEditable(false);
		textArea.setEditable(false);
		
		this.add(connectedClientsPane);
		this.add(textAreaPane);
		this.add(textField);
		this.add(submitButton);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, connectedClientsPane, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, connectedClientsPane, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, connectedClientsPane, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, connectedClientsPane, 152, SpringLayout.WEST, this);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		baseLayout.putConstraint(SpringLayout.EAST, textField, 494, SpringLayout.EAST, connectedClientsPane);
		textField.setBackground(Color.LIGHT_GRAY);
		baseLayout.putConstraint(SpringLayout.NORTH, submitButton, 6, SpringLayout.SOUTH, textAreaPane);
		baseLayout.putConstraint(SpringLayout.WEST, submitButton, 6, SpringLayout.EAST, textField);
		baseLayout.putConstraint(SpringLayout.SOUTH, submitButton, 0, SpringLayout.SOUTH, connectedClientsPane);
		baseLayout.putConstraint(SpringLayout.EAST, submitButton, 0, SpringLayout.EAST, textAreaPane);
		connectedClients.setBackground(Color.LIGHT_GRAY);
		baseLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, textAreaPane);
		baseLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, connectedClientsPane);
		baseLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, connectedClientsPane);
		textArea.setBackground(Color.LIGHT_GRAY);
		baseLayout.putConstraint(SpringLayout.SOUTH, textAreaPane, 505, SpringLayout.NORTH, connectedClientsPane);
		baseLayout.putConstraint(SpringLayout.NORTH, textAreaPane, 0, SpringLayout.NORTH, connectedClientsPane);
		baseLayout.putConstraint(SpringLayout.WEST, textAreaPane, 6, SpringLayout.EAST, connectedClientsPane);
		baseLayout.putConstraint(SpringLayout.EAST, textAreaPane, -10, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				submit();
			}
		});
		
		textField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					submit();
				}
			}
		});
	}
	
	private void submit()
	{
		if(textArea.getText().length() > 0)
		{
			controller.serverCommand(textField.getText());
		}
		textField.setText("");
	}
	
	public void addMainMessage(String message)
	{
		textArea.append(message + "\n");
	}
	
	public void setCurrentClients(String clients)
	{
		connectedClients.setText(clients);
	}
}
