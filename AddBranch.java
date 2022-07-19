package View;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalTime;
import javax.swing.*;

import Model.Branch;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the AddBranch window
 *
 */

public class AddBranch extends JFrame implements Serializable{
	private static final long serialVersionUID=23l;
	private JButton clear;  // Button 
	private JButton add;  // Button 
	private JLabel adressLabel;  // Label
	private JLabel openingHourLabel;  // Label
	private JLabel openingMinuteLabel;  // Label
	private JLabel closingHourLabel;  // Label
	private JLabel closingMinuteLabel;  // Label
	private JLabel problem; // Label
	private JTextField adressText; // Text
	private Choice Hour_Start; // Choice, Hours list
	private Choice Minute_Start;  // Choice, Minutes list
	private Choice Hour_End; //Choice, Hours list
	private Choice Minute_End; // Choice, Minutes list
	// GUI items
	private static ArrayList <Branch> branch;
	private LocalTime start;
	private LocalTime end;
	// Class attributes
	/**
	 * Class constructor
	 */
	public AddBranch()
	{
		clear= new JButton("clear");
		add = new JButton("add");
		adressLabel = new JLabel("Enter branch adress");
		openingHourLabel = new JLabel("Choose opening time hour");
		openingMinuteLabel = new JLabel("Choose opening time mintues");
		closingHourLabel = new JLabel("Choose closing time hour");
		closingMinuteLabel = new JLabel("Choose closing time mintues");
		problem = new JLabel();
		adressText =  new JTextField(20);
		Hour_Start =  new Choice();
		Minute_Start = new Choice();
		Hour_End =  new Choice();
		Minute_End = new Choice();
		//Initialize GUI items
		branch = new ArrayList <Branch>();
		end = LocalTime.of(0,0);
		start =  LocalTime.of(0,0);
		// Initialize class attributes
		/**
		 * add on click event
		 */
		add.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					read(); // Read
					if(checkInfo() == false) // Check if info is validate 
						return;
					Branch temp = new Branch(start, end, adressText.getText()); // New Branch
					branch.add(temp); // Add
					temp.setBranchNum(branch.size()); // Set Branch num
					write(); // Write new situation
					dispose(); // Dispose window
					ManagerNavigation frame = new ManagerNavigation();
					frame.setVisible(true);
					// Get back to Navigation
					
				}
		});
		/**
		 * clear on click event
		 */
		clear.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					adressText.setText("");
					// Clear textField
				}
		});
		createTime();
		init();
	}
	/**
	 * This function add the dates values to the choices
	 */
	public void createTime()
	{
		for(int i = 0; i <60; i++)
		{
			Minute_End.add(String.valueOf(i));
			Minute_Start.add(String.valueOf(i));
		}
		for(int i = 0; i <25; i++)
		{
			Hour_End.add(String.valueOf(i));
			Hour_Start.add(String.valueOf(i));
		}
	}
	/**
	 * This function Creates a table like JPanel
	 * @return P - JPanel
	 */
	private JPanel addP()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(adressLabel);
		p.add(adressText);
		problem.setVisible(false);
		p.add(problem);
		return p;
	}
	/**
	 * This function Creates a FlowLayout like JPanel
	 * @return B - JPanel
	 */
	private JPanel addB()
	{
		JPanel B = new JPanel();
		B.setLayout(new FlowLayout());
		B.add(add);
		B.add(clear);
		return B;
	}
	/**
	 * This function Creates a FlowLayout like JPanel
	 * @return O - JPanel
	 */
	private JPanel addO()
	{
		JPanel O = new JPanel();
		O.setLayout(new FlowLayout());
		O.add(openingHourLabel);
		O.add(Hour_Start);
		O.add(openingMinuteLabel);
		O.add(Minute_Start);
		O.add(closingHourLabel);
		O.add(Hour_End);
		O.add(closingMinuteLabel);
		O.add(Minute_End);
		return O;
	}
	/**
	 * This function creates the window appearacne 
	 */
	public void init()
	{
		setLayout(new BorderLayout());
		add(addP(), BorderLayout.NORTH);
		add(addO(), BorderLayout.CENTER);
		add(addB(), BorderLayout.SOUTH);
		pack();
	}
	/**
	 * write function
	 */
	public void write()
	{
		String FileName = "Branch.ser";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(FileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(branch);
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * Read function
	 */
	public void read()
	{
		String FileName = "Branch.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(FileName);
			in = new ObjectInputStream(fis);
			branch = (ArrayList<Branch>) in.readObject();
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
	/**
	 * this function checks the info
	 * @return boolean value based on the validity 
	 */
	public boolean checkInfo()
	{
		start = LocalTime.of(Integer.parseInt(Hour_Start.getSelectedItem()), Integer.parseInt(Minute_Start.getSelectedItem()));
		end = LocalTime.of(Integer.parseInt(Hour_End.getSelectedItem()), Integer.parseInt(Minute_End.getSelectedItem()));
		// Creates the times variables
		if(end.isBefore(start)) // Check if the times are valid
		{
			problem.setVisible(true);
			problem.setText("Ending hours must come after opening hours");
			return false;
		}
		if(adressText.getText().isBlank()) // Check if a location was entered 
		{
			problem.setVisible(true);
			problem.setText("Enter location");
			return false;
		}
	return true;
	}
}
