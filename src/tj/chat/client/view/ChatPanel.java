package tj.chat.client.view;

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

import tj.chat.client.controller.Client;

public class ChatPanel extends JPanel
{
	private Client controller;
	
	private SpringLayout baseLayout;
	
	private JTextField textField;
	
	private JButton sendButton;
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public ChatPanel(Client controller)
	{
		this.controller = controller;
		baseLayout = new SpringLayout();
		
		textField = new JTextField();
		sendButton = new JButton("Send");
		
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		
		textArea.setEditable(false);
		
		this.add(textField);
		this.add(sendButton);
		this.add(scrollPane);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, textField, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, textField, 646, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, sendButton, -1, SpringLayout.NORTH, textField);
		baseLayout.putConstraint(SpringLayout.WEST, sendButton, 6, SpringLayout.EAST, textField);
		baseLayout.putConstraint(SpringLayout.EAST, sendButton, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, textField);
		baseLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, textField);
		baseLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, sendButton);
	}
	
	private void setupListeners()
	{
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
		
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				submit();
			}
		});
	}
	
	private void submit()
	{
		if(textArea.getText().length() > 0)
		{
			controller.sendMessage(textField.getText());
		}
		textField.setText("");
	}
	
	public void addMessage(String message)
	{
		textArea.append(message + "\n");
	}
}
