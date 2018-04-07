package tj.chat.client.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import tj.chat.client.controller.ClientMain;
import tj.chat.server.controller.ServerMain;

public class AddressPanel extends JPanel
{
	private SpringLayout baseLayout;
	
	private JLabel titleLabel;
	private JLabel versionLabel;
	
	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel portLabel;
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField portField;
	
	private JButton connectButton;
	
	public AddressPanel()
	{
		baseLayout = new SpringLayout();
		
		titleLabel = new JLabel("TJ Chat Client");
		versionLabel = new JLabel("Version 1.0");
		
		nameLabel = new JLabel("Name");
		addressLabel = new JLabel("Address");
		portLabel = new JLabel("Port Number");

		
		nameField = new JTextField();
		addressField = new JTextField();
		portField = new JTextField();
		
		connectButton = new JButton("Connect");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		
		this.add(titleLabel);
		this.add(versionLabel);
		
		this.add(nameLabel);
		this.add(addressLabel);
		this.add(portLabel);
		
		this.add(nameField);
		this.add(addressField);
		this.add(portField);
		
		this.add(connectButton);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, portField, 6, SpringLayout.SOUTH, portLabel);
		baseLayout.putConstraint(SpringLayout.WEST, portField, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, portField, 0, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, portLabel, 6, SpringLayout.SOUTH, addressField);
		baseLayout.putConstraint(SpringLayout.SOUTH, portLabel, 29, SpringLayout.SOUTH, addressField);
		baseLayout.putConstraint(SpringLayout.NORTH, addressField, 6, SpringLayout.SOUTH, addressLabel);
		baseLayout.putConstraint(SpringLayout.WEST, addressField, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, addressField, 0, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, addressLabel, 29, SpringLayout.SOUTH, nameField);
		baseLayout.putConstraint(SpringLayout.NORTH, addressLabel, 6, SpringLayout.SOUTH, nameField);
		baseLayout.putConstraint(SpringLayout.NORTH, nameField, 6, SpringLayout.SOUTH, nameLabel);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, nameField, 0, SpringLayout.EAST, titleLabel);
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.WEST, portLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, portLabel, 0, SpringLayout.EAST, titleLabel);
		portLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.WEST, addressLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, addressLabel, 0, SpringLayout.EAST, titleLabel);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		baseLayout.putConstraint(SpringLayout.SOUTH, nameLabel, 29, SpringLayout.SOUTH, versionLabel);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.NORTH, nameLabel, 6, SpringLayout.SOUTH, versionLabel);
		baseLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, nameLabel, 0, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, versionLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, versionLabel, -10, SpringLayout.EAST, this);
		versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.NORTH, versionLabel, 6, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, titleLabel, -10, SpringLayout.EAST, this);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, titleLabel, 43, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, connectButton, 6, SpringLayout.SOUTH, portField);
		baseLayout.putConstraint(SpringLayout.WEST, connectButton, 61, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, connectButton, 188, SpringLayout.WEST, this);
	}
	
	private void setupListeners()
	{
		connectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				connect();
			}
		});
	}
	
	private void connect()
	{
		if(nameField.getText().length() > 0)
		{
			if(addressField.getText().length() > 0)
			{
				try
				{
					Integer.parseInt(portField.getText());
					ClientMain.startClient(nameField.getText(), addressField.getText(), Integer.parseInt(portField.getText()));
				}
				catch(Exception exception)
				{
					JOptionPane.showMessageDialog(null, "Port must be a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You must enter an address", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must enter a name", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
