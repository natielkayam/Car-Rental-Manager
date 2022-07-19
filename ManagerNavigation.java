package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import Model.Branch;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the ManagerNavigation window
 *
 */
public class ManagerNavigation extends JFrame implements Serializable {
	private static final long serialVersionUID=23l; // Serial number
	private JButton branch; // Button
	private JButton car; // Button
	private JButton logOut; // Button
	// GUI items
	/**
	 * Constructor
	 */
	public ManagerNavigation()
	{
		super("Navigation");
		branch = new JButton("Add new branch");
		car = new JButton("Add new car");
		logOut = new JButton("logOut");
		/**
		 * branch on click event
		 */
		branch.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// Open AddBranch frame
					AddBranch frame = new AddBranch(); 
					frame.setVisible(true);
					dispose();
				}
		});
		/**
		 * car on click event
		 */
		car.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// Open addCar frame
					addCar frame = new addCar(); 
					frame.setVisible(true);
					dispose();
				}
		});
		/**
		 * logOut on click event
		 */
		logOut.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					SignIn frame = new SignIn(); // Open a GUI window
					frame.setVisible(true); // Make him visable
					dispose();
				}
		});
		init();
	}
	/**
	 * This function creates the window appearacne 
	 */
	public void init()
	{
		setLayout(new FlowLayout());
		add(branch);
		branch.setBackground(Color.orange);
		add(car);
		car.setBackground(Color.magenta);
		add(logOut);
		logOut.setBackground(Color.blue);
		pack();
	}
}
