package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.*;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;

import Utils.Category;
import Model.Branch;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the showBranch window
 *
 */

public class showBranch extends JFrame implements Serializable{
	private static final long serialVersionUID=23l;
	private JButton back; // Button
	private JButton OK; // Button
	private JLabel num; // Label 
	private JLabel hello; // Label 
	private JLabel adressLabel; // Label 
	private JLabel opening; // Label 
	private JLabel closing; // Label 
	private Choice numbers; // Choice, branches numbers list
	// GUI items
	private static ArrayList <Branch> b;
	/**
	 * Class constructor
	 */
	public showBranch()
	{
		super("Search for a branch");
		hello = new JLabel("Choose a number from the list and press show to see the information, press back to return to this screen");
		back = new JButton("back");
		OK = new  JButton("Show");
		num = new JLabel();
		adressLabel = new JLabel();
		opening =  new JLabel();
		closing = new JLabel();
		numbers = new Choice();
		// Initialize GUI items
		b = new ArrayList <Branch>();
		read();
		getNumbers(b);
		/**
		 * back on click event
		 */
		back.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				getback();
			}
	});
		/**
		 * OK on click event
		 */
		OK.addActionListener(new ActionListener() 
	{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Branch temp = b.get(Integer.parseInt(numbers.getSelectedItem()) - 1);
				// Get chosen branch info
				hello.setVisible(false); 
				back.setVisible(true);
				OK.setVisible(false);
				num.setVisible(true);
				adressLabel.setVisible(true);
				opening.setVisible(true);
				closing.setVisible(true); 
				numbers.setVisible(false);	
				num.setText("The branch number is " + temp.getBranchNum());
				adressLabel.setText("The branch adress is " +temp.getLocation());
				opening.setText("The branch opening time is " + temp.getStartingTime());
				closing.setText("The branch closing time is " + temp.getClosingTime());
				// Change display and update labels text to show the information
			}		
	});	
		init();
	}
	/**
	 * This function adds values to the choice item(numbers)
	 *@param b - ArrayList Branch, the Branches list
	 */
	public void getNumbers(ArrayList <Branch> b) 
	{
		for(int i = 0; i < b.size(); i++)
		{
			numbers.add(String.valueOf(b.get(i).getBranchNum()));
		}
	}
	/**
	 * This function switches the display from showing info to showing branches number list
	 */
	public void getback()
	{
		hello.setVisible(true); 
		back.setVisible(false);
		OK.setVisible(true);
		num.setVisible(false);
		adressLabel.setVisible(false);
		opening.setVisible(false);
		closing.setVisible(false); 
		numbers.setVisible(true);
	}
	/**
	 * This function Creates a table like JPanel
	 * @return P - JPanel
	 */
	private JPanel addP()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,1));
		p.add(hello);
		p.add(num);
		p.add(adressLabel);
		p.add(opening);
		p.add(closing);
		p.add(numbers);
		hello.setVisible(true); 
		num.setVisible(false);
		adressLabel.setVisible(false);
		opening.setVisible(false);
		closing.setVisible(false); 
		numbers.setVisible(true);
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
		B.add(OK);
		B.add(back);
		back.setVisible(false);
		OK.setVisible(true);
		return B;
	}
	/**
	 * This function creates the window appearacne 
	 */
	public void init() 
	{
		setLayout(new BorderLayout());
		add(addP(), BorderLayout.NORTH);
		add(addB(), BorderLayout.SOUTH);
		pack();
	}
	/**
	 * read from file function
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
			b = (ArrayList<Branch>) in.readObject();
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
