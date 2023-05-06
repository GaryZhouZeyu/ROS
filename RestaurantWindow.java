package ROSarchitecture;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;    
    
 
public class RestaurantWindow extends JFrame implements ActionListener{
	
	public RestaurantWindow(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
//#################################################################################
import javax.swing.*;		//Needed for Swing classes
import java.awt.*;		//Needed for BorderLayout class
import java.awt.event.*;

public class RestaurantWindow extends JFrame
{
	final int WINDOW_WIDTH = 500;	//Window width
	final int WINDOW_HEIGHT = 500;	//Window height
	private JPanel panel;
	private JLabel messageLabel;
	private Container contentPane;
	private JButton managerButton; 
	private JButton customerButton;
	
	/**
	 * Constructor
	 */
	
	public RestaurantWindow() {
		
		//Set the title.
		setTitle("Restaurant");
		
		//Set the size of the window.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//Specify what happens when the close button is clicked.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Get contentPane and set layout
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		//Build the panel and add it to the frame.
		buildPanel();
		
		//Add the panel to the frame's content pane.
		add(panel); //, BorderLayout.CENTER);
		
		//Create a label holding message and add to centerPane
		//Create a label to display instruction.
		messageLabel = new JLabel("                                          " + 
									"This is the home window of the restaurant");
		add(messageLabel, BorderLayout.NORTH);
		
		//Display the window.
		setVisible(true);

	}
	
	private void buildPanel() {
		
		//Create two buttons
		managerButton = new JButton("Manager Window");
		managerButton.setActionCommand("manager");
		managerButton.addActionListener(new ButtonListener());

		
		customerButton = new JButton("Customer Window");
		customerButton.setActionCommand("customer");
		customerButton.addActionListener(new ButtonListener());

		
	    //Add buttons to panel
		panel = new JPanel();
		panel.add(managerButton);
		panel.add(customerButton);
	    
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	
	/**
	 * main method
	 */
	
	public static void main(String[] args) 
	{
		new RestaurantWindow();
	}

}

}
}
