package tj.chat.server.view;

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

import tj.chat.server.controller.ServerMain;

public class StartPanel extends JPanel
{	
	private SpringLayout baseLayout;
	
	private JLabel titleLabel;
	private JLabel versionLabel;
	private JLabel portLabel;
	
	private JTextField portField;
	
	private JButton startButton;
	
	public StartPanel()
	{
		baseLayout = new SpringLayout();
		
		titleLabel = new JLabel("TJ Chat Server");
		versionLabel = new JLabel("Version 1.0");
		portLabel = new JLabel("Port Number");
		
		portField = new JTextField();
		
		startButton = new JButton("Start Server");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		
		this.add(titleLabel);
		this.add(versionLabel);
		this.add(portLabel);
		
		this.add(portField);
		
		this.add(startButton);
	}
	
	private void setupLayout()
	{
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, titleLabel, 82, SpringLayout.NORTH, this);
		portField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		baseLayout.putConstraint(SpringLayout.NORTH, portField, 6, SpringLayout.SOUTH, portLabel);
		baseLayout.putConstraint(SpringLayout.WEST, portField, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, startButton, 6, SpringLayout.SOUTH, portField);
		baseLayout.putConstraint(SpringLayout.WEST, startButton, 64, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, startButton, 41, SpringLayout.SOUTH, portField);
		baseLayout.putConstraint(SpringLayout.EAST, startButton, -55, SpringLayout.EAST, titleLabel);
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		baseLayout.putConstraint(SpringLayout.SOUTH, portField, 41, SpringLayout.SOUTH, portLabel);
		baseLayout.putConstraint(SpringLayout.EAST, portField, 0, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, titleLabel, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, versionLabel, 0, SpringLayout.WEST, titleLabel);
		portLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		baseLayout.putConstraint(SpringLayout.SOUTH, portLabel, 54, SpringLayout.SOUTH, versionLabel);
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.NORTH, portLabel, 27, SpringLayout.SOUTH, versionLabel);
		baseLayout.putConstraint(SpringLayout.WEST, portLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, portLabel, 0, SpringLayout.EAST, titleLabel);
		versionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseLayout.putConstraint(SpringLayout.NORTH, versionLabel, 6, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, versionLabel, 41, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, versionLabel, 0, SpringLayout.EAST, titleLabel);
	}
	
	private void setupListeners()
	{
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Integer.parseInt(portField.getText());
					ServerMain.startServer(Integer.parseInt(portField.getText()));
				}
				catch(Exception exception)
				{
					JOptionPane.showMessageDialog(null, "Port must be a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
