package View;
import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.*;

import Model.Branch;
import Model.Car;
import Model.Client;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

import Utils.Category;

/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This class represent a GUI window of the car Leasing company search function
 *
 */
public class showCar extends JFrame implements Serializable{
	private static final long serialVersionUID=23l;
	private JButton back;

	private JButton search; // Button
	private JLabel yearLabel; // Label
	private JLabel categoryLabel;  // Label
	private JLabel modelLabel;  // Label
	private JLabel submodelLabel;  // Label
	private JLabel gearboxLabel;  // Label
	private JLabel priceLabel;  // Label
	private JLabel branchLabel;  // Label
	private JLabel hello;  // Label
	private JLabel label_Year_start;  // Label
	private JLabel label_Month_start;  // Label
	private JLabel label_Day_start;  // Label
	private JLabel label_Year_end;  // Label
	private JLabel label_Month_end;  // Label
	private JLabel label_Day_end;  // Label
	private JLabel label_Problem;  // Label
	private Choice year;
	private Choice category;
	private JTextField modelText; // Text
	private JTextField submodelText; // Text
	private JTextField gearboxText; // Text
	private JTextField priceText; // Text
	private Choice branch; // Choice branchs list
	private Choice month_start; // Choice months list
	private Choice day_start; // Choice days list
	private Choice yearRent_start; // Choice years list
	private Choice month_end; // Choice years list
	private Choice day_end; // Choice years list
	private Choice yearRent_end; // Choice years list
	// GUI items
	private ArrayList <Car> temp ;
	private static ArrayList <Branch> b;
	private static ArrayList <Car> c;
	/**
	 * Class constructor
	 * @param cc - Client , current connected client
	 */
	public showCar(Client cc)
	{
		hello = new JLabel("Enter the desired information and than press search, press back to return to this screen");
		back= new JButton("back");
		search = new JButton("serach");
		yearLabel = new JLabel("choose year of manufucture");
		categoryLabel  = new JLabel("choose car category");
		modelLabel  = new JLabel("Enter car model");
		submodelLabel  = new JLabel("Enter car sub model");
		gearboxLabel  = new JLabel("Enter the type of gearbox");
		priceLabel  = new JLabel("Enter the max car price");
		branchLabel =  new JLabel("Enter the car branch number");
		label_Year_start =new JLabel();
		label_Problem = new JLabel();
		label_Month_start = new JLabel();
		label_Day_start = new JLabel();
		label_Year_end =new JLabel();
		label_Month_end = new JLabel();
		label_Day_end = new JLabel();
		year = new Choice();
		category = new Choice();
		modelText = new JTextField(20);
		submodelText = new JTextField(20);
		gearboxText = new JTextField(20);
		priceText = new JTextField(20);
		branch = new Choice(); 
		day_start = new Choice();
		month_start = new Choice();
		yearRent_start = new Choice();
		day_end = new Choice();
		month_end = new Choice();
		yearRent_end = new Choice();
		//Initialize GUI items
		temp = new ArrayList <Car>();
		b = new ArrayList <Branch>();
		c = new ArrayList <Car>();
		/*
		 * back on click event
		 */
		back.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					clientNavigation frame = new clientNavigation(cc);
					frame.setVisible(true);
					dispose();
					// Go back to the navigation window
				}
		});
		/**
		 * search on click event
		 */
		search.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					 LocalDate [] datesrent = getRentDate();
					 // Get dates
					if(checkDates(datesrent[0],datesrent[1]) == false) // Check them
						return;
					if(showCars(c) == false) // Check if a car that answers those demands exists
						return;	
					CarsWindow frame = new CarsWindow((int) ChronoUnit.DAYS.between(datesrent[0],datesrent[1]), temp, datesrent[0], datesrent[1], cc);
					frame.setVisible(true);
					dispose();
					// Dispose current window and go to CarsWindow frame
				}
		});
		read("Branch.ser",0);
		read("Car.ser",1);
		// read situation
		getNumbers(b);
		createYear();
		createCategory();
		setDate();
		init();
	}
	private boolean checkDates(LocalDate start, LocalDate end)
	{
		if(end.isBefore(start))
		{
			label_Problem.setVisible(true);
			label_Problem.setText("The dates you chose is not valid, the ending date is before the starting date");
			return false;
		}
		if(start.isBefore(LocalDate.now()))
		{
			label_Problem.setVisible(true);
			label_Problem.setText("The starting date you chose is not valid, the date has already pass");
			return false;
		}
			return true;
}
	/**
	 * this function returns the dates the client chose
	 * @return date - LocalDate []  an array that holds the strating date at 0 index and ending date at 1 index
	 */
	private LocalDate [] getRentDate()
	{
		String month_Start = month_start.getSelectedItem();
		String day_Start = day_start.getSelectedItem();
		String year_Start = yearRent_start.getSelectedItem();
		String month_End = month_end.getSelectedItem();
		String day_End = day_end.getSelectedItem();
		String year_End = yearRent_end.getSelectedItem();
		LocalDate Start = null;
		LocalDate End = null;
		try
		{
		 Start = LocalDate.of(Integer.parseInt(year_Start),Month.of(Integer.parseInt(month_Start)) ,Integer.parseInt(day_Start));
		}
		catch(DateTimeException e)
		{
			label_Problem.setVisible(true);
			label_Problem.setText(String.valueOf(e));
		}
		try
		{
			 End = LocalDate.of(Integer.parseInt(year_End),Month.of(Integer.parseInt(month_End)) ,Integer.parseInt(day_End));
		}
		catch(DateTimeException e1)
		{
			label_Problem.setVisible(true);
			label_Problem.setText(String.valueOf(e1));
		}
		LocalDate [] date = {Start,End};
		return date;
	}
	/**
	 * this function add values to the choice(year)
	 */
	private void createYear() 
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
	private void createCategory()
	{
		Category[] c  = Utils.Category.values();
		for (Category c1 : c)
			category.add(String.valueOf(c1));
	}
	/**
	 * this function add values to the choice(branch)
	 * @param b - ArrayList Branch
	 */
	public void getNumbers(ArrayList <Branch> b) 
	{
		for(Branch b1: b)
			branch.add(String.valueOf(b1.getBranchNum()));
	}
	/**
	 * This is a filtering function, bases on client requests
	 * @param c - The cars list
	 * @return - boolean value based on if there are cars that fit demands 
	 */
	private boolean showCars(ArrayList <Car> c)
	{
		String yeartemp = year.getSelectedItem();
		String categorytemp = category.getSelectedItem();
		String model = modelText.getText();
		String submodel = submodelText.getText();
		String gearbox = gearboxText.getText();
		String price = priceText.getText();
		// Get the values the client enterd
		try
		{
			Float.parseFloat(price);
		}
		catch(NumberFormatException e2)
		{ 
			label_Problem.setText("Please enter a valid price number");
			label_Problem.setVisible(true);
			return false;
		}
		// Check if price is numeric
		String branchtemp = branch.getSelectedItem();
		LocalDate [] daterent = getRentDate();
		//Get values before for easier use
		for(Car ctemp : c) // Iterate through the cars list
		{
			ctemp.datesRent(daterent[0],daterent[1],0); // First check if the car is available in those dates
			if((String.valueOf(ctemp.getBranchNumber()) == branchtemp && (ctemp.getModel() == model || model.isBlank())
				&&(ctemp.getSubModel()== submodel || submodel.isBlank()) &&
				(ctemp.getGearBox() == gearbox || gearbox.isBlank())
				&&
			String.valueOf(ctemp.getManufacturingYear()) == yeartemp && String.valueOf(ctemp.getCategory()) == categorytemp
			   && Float.parseFloat(price) >= ctemp.getDailyPrice() && ctemp.isRented() == true))
				System.out.print(ctemp.isRented());
			//Check and find only cars that fit the client wishes
				temp.add(ctemp); // if so insert it to the temp ArrayList
		}
		if(temp.isEmpty())
		{
			label_Problem.setVisible(true);
			label_Problem.setText("We couldnt find a car for you in the requsted dates");
			return false;	
			//If temp is empty it means we couldnt find a car that fit the client wishes
		}
		else
			return true;
		// We found at least 1 car
	}
	/**
	 * This function enters values to the choices objects that let Client choose his starting and ending renting dates
	 */
	private void setDate()
	{
		JPanel b = new JPanel (new FlowLayout());
		for(int i = 1; i < 32; i++)
		{
			day_start.add(String.valueOf(i));
			day_end.add(String.valueOf(i));
		}
		for(int i = 1; i < 13; i++)
		{
			month_start.add(String.valueOf(i));
			month_end.add(String.valueOf(i));
		}
		String now = String.valueOf(Year.now());
		int current =  Integer.parseInt(now);
		for(int i = current ; i < current + 2; i++)
		{
			yearRent_end.add(String.valueOf(i));
			yearRent_start.add(String.valueOf(i));
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
		p.add(label_Problem);
		label_Problem.setVisible(false);
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
		B.add(back);
		back.setVisible(false);
		B.add(search);
		return B;
	}
	/**
	 * This function Creates a FlowLayout like JPanel
	 * @return Y - JPanel
	 */
	private JPanel addY()
	{
		JPanel Y = new JPanel();
		Y.setLayout(new FlowLayout());
		label_Day_start.setText("Starting day");
		Y.add(label_Day_start);
		Y.add(day_start);
		label_Month_start.setText("Starting month");
		Y.add(label_Month_start);
		Y.add(month_start);
		label_Year_start.setText("Starting year");
		Y.add(label_Year_start);
		Y.add(yearRent_start);
		return Y;
	}
	/**
	 * This function Creates a FlowLayout like JPanel
	 * @return S - JPanel
	 */
	private JPanel addS()
	{
		JPanel S = new JPanel();
		S.setLayout(new FlowLayout());
		label_Day_end.setText("Ending day");
		S.add(label_Day_end);
		S.add(day_end);
		label_Month_end.setText("Ending month");
		S.add(label_Month_end);
		S.add(month_end);
		label_Year_end.setText("Ending year");
		S.add(label_Year_end);
		S.add(yearRent_end);
		return S;
	}
	/**
	 * This function Creates a table like JPanel
	 * @return V - JPanel
	 */
	private JPanel addV()
	{
		JPanel V = new JPanel();
		V.setLayout(new GridLayout(0,1));
		V.add(addS());
		V.add(addY());
		return V;
	}
	/**
	 * This function creates the window appearacne 
	 */
	public void init() 
	{
		setLayout(new BorderLayout());
		add(hello, BorderLayout.NORTH);
		add(addP(), BorderLayout.NORTH);
		add(addV(), BorderLayout.CENTER);
		add(addB(), BorderLayout.SOUTH);
		pack();
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
}
