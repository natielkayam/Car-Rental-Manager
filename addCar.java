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

import javax.swing.*;

import Model.Branch;
import Model.Car;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;

import Utils.Category;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the AddCar window
 *
 */

public class addCar extends JFrame implements Serializable{
	private static final long serialVersionUID=23l;
	private JButton clear;
	private JButton add;
	private JLabel numLabel;
	private JLabel yearLabel;
	private JLabel categoryLabel;
	private JLabel modelLabel;
	private JLabel submodelLabel;
	private JLabel gearboxLabel;
	private JLabel priceLabel;
	private JLabel branchLabel;
	private JLabel problem;
	private JTextField numText;
	private Choice year;
	private Choice category;
	private JTextField modelText;
	private JTextField submodelText;
	private JTextField gearboxText;
	private JTextField priceText;
	private Choice branch;
	// GUI items
	private static ArrayList <Branch> b;
	private static ArrayList <Car> c;
	
	/**
	 * Class constructor
	 */
	public addCar() 
	{
		clear= new JButton("clear");
		add = new JButton("add");
		problem = new JLabel();
		numLabel = new JLabel("Enter car number");
		yearLabel = new JLabel("Enter year of manufucture");
		categoryLabel  = new JLabel("Enter car category");
		modelLabel  = new JLabel("Enter car model");
		submodelLabel  = new JLabel("Enter car sub model");
		gearboxLabel  = new JLabel("Enter the type of gearbox");
		priceLabel  = new JLabel("Enter the car price");
		branchLabel = new JLabel("Enter the car branch");
		numText = new JTextField(20);
		year = new Choice();
		category = new Choice();
		modelText = new JTextField(20);
		submodelText = new JTextField(20);
		gearboxText = new JTextField(20);
		priceText = new JTextField(20);
		branch = new Choice(); 
		// Initialize GUI items
		b = new ArrayList<Branch>();
		c = new ArrayList<Car>();
		/**
		 * add on click event
		 */
		add.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(checkInfo() == false) // Check data
						return;
					read("Car.ser", 1); //  if the data is good read the Car file
					Car addto = new Car(Integer.parseInt(numText.getText()), Year.of(Integer.parseInt(year.getSelectedItem())), 
							Float.parseFloat(priceText.getText()),  gearboxText.getText(), 
							Category.valueOf(category.getSelectedItem()), modelText.getText(), submodelText.getText(), Integer.parseInt(branch.getSelectedItem()));
					c.add(addto);
					//Created a new car and added him
					for(Branch t : b)
					{
						if(t.getBranchNum() == Integer.parseInt(branch.getSelectedItem()))
							t.getCars().add(addto);
						//Add the car to the Branch cars ArrayList
					}
					write("Car.ser");
					write("Branch.ser");
					// Write new situation
					dispose();
					ManagerNavigation frame= new ManagerNavigation();
					frame.setVisible(true);
					// Go back to Navigation window
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
					numText.setText("");
					modelText.setText(""); 
					submodelText.setText(""); 
					gearboxText.setText("");
					priceText.setText("");
					// clear textFields
				}
		});
		getNumbers();
		createYear();
		createCategory();
		init();
	}
	/**
	 * this function add values to the choice(year)
	 */
	public void createYear() 
	{
		String now = String.valueOf(Year.now());
		int current =  Integer.parseInt(now);
		for(int i = current; i > current - 121; i--)
		{
			year.add(String.valueOf(i));
		}
	}
	/**
	 * this function add values to the choice(category)
	 */
	public void createCategory()
	{
		Category[] c  = Utils.Category.values();
		for (Category c1 : c)
			category.add(String.valueOf(c1));
	}
	/**
	 * this function add values to the choice(branch)
	 */
	public void getNumbers() 
	{
		read("Branch.ser",0);
		for(Branch b1: b)
			branch.add(String.valueOf(b1.getBranchNum()));
	}
	/**
	 * This function Creates a table like JPanel
	 * @return P - JPanel
	 */
	private JPanel addP()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(numLabel);
		p.add(numText);
		p.add(yearLabel);
		p.add(year);
		p.add(categoryLabel);
		p.add(category);
		p.add(modelLabel);
		p.add(modelText);
		p.add(submodelLabel);
		p.add(submodelText);
		p.add(gearboxLabel);
		p.add(gearboxText);
		p.add(priceLabel);
		p.add(priceText);
		p.add(branchLabel);
		p.add(branch);
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
	 * write to file function
	 * @param filename - String, the file we want to read from
	 */
	public void write(String filename)
	{
		String FileName = filename;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(FileName);
			out = new ObjectOutputStream(fos);
			if(filename == "Car.ser")
				out.writeObject(c);
			else
				out.writeObject(b);
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * read from file function
	 * @param FileName - String, the file we want to read from
	 * @param flag - int, if 0 we read Branches information, if 1 we read Cars information
	 */
	public void read(String FileName, int flag)
	{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(FileName);
			in = new ObjectInputStream(fis);
			if(flag == 0)
			{
				b = (ArrayList<Branch>) in.readObject();
			}
			else
				c = (ArrayList<Car>) in.readObject();
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
		if(modelText.getText().isBlank())
		{
			problem.setText("Please enter the car model ");
			problem.setVisible(true);
			return false;
		}
		if(submodelText.getText().isBlank())
		{
			problem.setText("Please enter the submodel");
			problem.setVisible(true);
			return false;
		}
		if(gearboxText.getText().isBlank())
		{
			problem.setText("Please enter the gearbox info");
			problem.setVisible(true);
			return false;
		}
		// Check if all the text fields were field
		try
		{
			Integer.parseInt(numText.getText());
		}
		catch(NumberFormatException e1)
		{ 
			problem.setText("Please enter a numeric car plate number");
			problem.setVisible(true);
			return false;
		}
		try
		{
			Integer.parseInt(priceText.getText());
		}
		catch(NumberFormatException e1)
		{ 
			problem.setText("Please enter a numeric price");
			problem.setVisible(true);
			return false;
		}
		// Check if the fields that must be numeric are numeric
	return true;
	}
}
