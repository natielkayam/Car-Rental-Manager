package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;

import Model.Branch;
import Model.Car;
import Model.Client;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the ClientNavigation window
 *
 */
public class clientNavigation extends JFrame {
	private static final long serialVersionUID=23l;
	private JButton branch; // Button
	private JButton car; // Button
	private JButton logOut; // Button
	// GUI items
	private ArrayList <Branch> b;
	private ArrayList <Car> temp;
	/**
	 * Class constructor
	 * @param c - Client, represent current Client
	 */
	public clientNavigation(Client c)
	{
		super("Navigation");
		branch = new JButton("Search for a branch");
		car = new JButton("Search for a car");
		logOut =new JButton("logOut"); 
		b = new ArrayList <Branch>();
		/**
		 * branch on click event
		 */
		branch.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{	
					read(0); // Read branch info
					showBranch frame = new showBranch(); // Create branch window
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
					read(1);
					showCar frame = new showCar(c); // Create showCar window
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
	/**
	 * Read from file function
	 * @param flag - int, 0 indicates we read Branches and 1 we read Cars
	 */
	public void read(int flag)
	{
		String FileName;
		if(flag == 0)
			FileName = "Branch.ser";
		else
			FileName = "Car.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(FileName);
			in = new ObjectInputStream(fis);
			if(flag == 0)
				b = (ArrayList<Branch>) in.readObject();
			else
				temp = (ArrayList<Car>) in.readObject();
			in.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex2)
		{
			ex2.printStackTrace();
		}
	}
}

