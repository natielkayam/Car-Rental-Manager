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
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.*;

import Model.Client;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the Signup window
 *
 */

public class SignUp extends JFrame implements Serializable
{ 
	private static final long serialVersionUID=23l;
	private JButton submit; // Button
	private JButton clear; // Button
	private Choice day; // Choice, days list
	private Choice month; // Choice, months list
	private Choice year;  // Choice, years list
	private JLabel label_password; // Label
	private JLabel label_lname; // Label
	private JLabel label_fname;  // Label
	private JLabel label_email;  // Label
	private JLabel label_liscene;  // Label
	private JLabel label_ID;  // Label
	private JLabel label_Year;  // Label
	private JLabel label_Month;  // Label
	private JLabel label_Day;  // Label
	private JLabel label_Problem;  // Label
	private JLabel label_Invalid;  // Label
	private JTextField passwordText; // Text
	private JTextField lastNameText; // Text
	private JTextField emailText; // Text
	private JTextField liscene; // Text
	private JTextField ID; // Text
	private JTextField firstNameText; // Text
	// GUI items, for the window design
	private ArrayList <Client> clients; // Clients ArrayList
	/**
	 * SignUp constructor
	 */
	public SignUp()
	{
		super("Sign up");
		submit = new JButton("Submit");
		clear = new JButton("clear");
		day = new Choice();
		month = new Choice();
		year = new Choice();
		ID = new JTextField(20);
		liscene	= new JTextField(20);
		emailText = new JTextField(20);
		lastNameText =new JTextField(20);
		passwordText = new JTextField(20);
		firstNameText =  new JTextField(20); 
		label_password = new JLabel("your password:");
		label_lname = new JLabel("your Last name:") ;
		label_fname = new JLabel("your First name:");
		label_email =new JLabel("your email:") ;
		label_liscene = new JLabel("Year Obtained license:");
		label_ID = new JLabel("your ID:");
		label_Year = new JLabel("your Year of Birth:");
		label_Month = new JLabel("your Month of Birth:");
		label_Day = new JLabel("your Day of Birth:");
		label_Problem = new JLabel("h");
		label_Invalid = new JLabel("your siging up process was not sucsseful due to :");
		// Initialized all the window items
		clients = new ArrayList <Client>();
		/**
		 * Submit button on click event
		 */
		submit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				read(); // Read the current clients situation
				if(validateClient() == false) // Check data
					return; // If false cant register the new client
				if(clients != null) // If we already have clients check if someone with this info exists
					if(checkInfo(clients) == false)  // If so cant register
						return;
				Client newClient = new Client(firstNameText.getText(), lastNameText.getText(), passwordText.getText(), emailText.getText(), 
				Integer.parseInt(ID.getText()), Year.of(Integer.parseInt(liscene.getText())), 
			   	LocalDate.of(Integer.parseInt(year.getSelectedItem()), 
				Month.of(Integer.parseInt(month.getSelectedItem())), Integer.parseInt(day.getSelectedItem())));
				// Created new Client
				clients.add(newClient); // Add him
				write(clients); // Write new situation
				SignIn frame = new SignIn(); // Create new SignIn window
				frame.setVisible(true); // Make him visible
				dispose(); // Dispose the current window
			}
			
		});
		/**
		 * clear button on click event
		 */
		clear.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				emailText.setText("");
				passwordText.setText("");
				ID.setText("");
				liscene.setText("");
				emailText.setText("");
				lastNameText.setText("");
				passwordText.setText("");
				firstNameText.setText("");
				// Clear all textFields
			}
		});
		setDate(); // Call setDate
		intialize(); // Call initialize 
	}
	/**
	 * This function creates the window appearacne 
	 */
	public void intialize()
	{
		setLayout(new BorderLayout()); // Chose layOut
		
		add(addP(), BorderLayout.NORTH); // Add panels
		add(addY(), BorderLayout.CENTER);
		add(addB(),BorderLayout.SOUTH);
		pack(); // Pack
	}
	/**
	 * This function enters values to the choices objects that let Client choose his birthday
	 */
	private void setDate()
	{
		for(int i = 1; i < 32; i++) // Days
		{
			day.add(String.valueOf(i));
		}
		for(int i = 1; i < 13; i++) // Months
		{
			month.add(String.valueOf(i));
		}
		String now = String.valueOf(Year.now()); // Get current year
		int current =  Integer.parseInt(now); 
		for(int i = current - 18; i > current - 121; i--) // Iterate backword, all clients must be at least 18 years old
		{
		
			year.add(String.valueOf(i));
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
		p.add(label_fname);
		p.add(firstNameText);
		p.add(label_lname);
		p.add(lastNameText);
		p.add(label_ID);
		p.add(ID);
		p.add(label_liscene);
		p.add(liscene);
		p.add(label_email);
		p.add(emailText);
		p.add(label_password);
		p.add(passwordText);
		p.add(label_Invalid);
		label_Invalid.setVisible(false); // This label shows up only when something is wrong
		label_Invalid.setForeground(Color.blue);
		p.add(label_Problem);
		label_Problem.setVisible(false); // This label shows up only when something is wrong
		label_Problem.setForeground(Color.blue);
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
		B.add(submit);
		B.add(clear);
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
		Y.add(label_Day);
		Y.add(day);
		Y.add(label_Month);
		Y.add(month);
		Y.add(label_Year);
		Y.add(year);
		return Y;
	}
	/**
	 * This function make the labels that indicate the problem in the registering process appear
	 */
	private void error() 
	{
		label_Problem.setVisible(true);
		label_Invalid.setVisible(true);
		
	}
	/**
	 * This function validate the Client registration info
	 * @return boolean type based on good info or bad
	 */
	public boolean validateClient() 
	{
		String fName = firstNameText.getText(); 
		String pass = passwordText.getText(); 
		String lName = lastNameText.getText(); 
		String email = emailText.getText(); 
		String lisceneYear = liscene.getText();
		String Identity = ID.getText();
		String month_chosen = month.getSelectedItem();
		String day_chosen = day.getSelectedItem();
		String year_chosen = year.getSelectedItem();
		if(fName.isBlank()) // Check if this field is empty
		{
			label_Problem.setText("Please enter a valid First name");
			error();
			return false;
		}
		if(lName.isBlank()) // Check if this field is empty
		{
			label_Problem.setText("Please enter a valid Last name");
			error();
			return false;
		}
		if(pass.isBlank()) // Check if this field is empty
		{
			label_Problem.setText("Please enter a valid password");
			error();
			return false;
		}
		if(pass.length() < 5) // Check if this field is at least 5 char long
		{
			label_Problem.setText("Please enter at least 6 digits in your password password");
			error();
			return false;
		}
		try // Check if this field is numeric
		{
			Integer.parseInt(lisceneYear);
			if(Integer.parseInt(lisceneYear) <1920 || lisceneYear.isBlank()) // Check if the date is logical
			{
				label_Problem.setText("Please enter a valid year in the the obtain driver liscene field");
				error();
				return false;
			}
		}
		catch(NumberFormatException e1) // Not numeric
		{ 
			label_Problem.setText("Please enter a valid year in the the obtain driver liscene field");
			error();
			return false;
		}
		try // Check if this field is numeric
		{
			Integer.parseInt(Identity);
			if(Identity.isBlank())
			{
				label_Problem.setText("Please enter a valid ID number");
				error();
				return false;
			}
		}
		catch(NumberFormatException e2)  // Not numeric
		{ 
			label_Problem.setText("Please enter a valid ID number");
			error();
			return false;
		}
		//Check the email
		if(email.contains("@") == false || email.contains(".") == false || email.lastIndexOf(".") <email.lastIndexOf("@"))
		{
			label_Problem.setText("Please enter a valid email adress");
			error();
			return false;
		}
	
		switch(month_chosen) // check if the birth date is logical
		{
			case "2": // February
			{
				if((Integer.parseInt(day_chosen) == 29 && Integer.parseInt(year_chosen) % 4 !=0) || Integer.parseInt(day_chosen) > 29)
				{
					label_Problem.setText("Please enter a valid birth date, the day you chose wasnt exist in your birth year february");
					error();
					return false;
				}
				break;
			}
			// Months with 30 days
			case "4":
			case "6":
			case "9":
			case "11":
			{
				if(Integer.parseInt(day_chosen) == 31)
				{
					label_Problem.setText("Please enter a valid day in your birth date");
					error();
					return false;
				}
				break;
			}
		}
		return true;
	}
	/**
	 * This function checks if there is already a client with this info
	 *@param c - ArrayList Client
	 * @return boolean type based on if the info already exists
	 */
	public boolean checkInfo(ArrayList <Client> c)
	{
		for(Client temp : c) // Iterate throguh client list
		{
			if(temp.getID() == Integer.parseInt(ID.getText())) // Check if this ID exists
			{
				label_Problem.setText("This unique ID value already exists in our system");
				error();
				return false;
			}
			if(temp.getEmail().equals(emailText.getText())) // Check if this email exists
			{
				label_Problem.setText("This unique email value already exists in our system");
				error();
				return false;
			}
		}
		return true;
	}
	/**
	 * this is write to file function
	 *@param clients - ArrayList Client the Clients list to write
	 */
	public void write(ArrayList <Client> clients)
	{
		String FileName = "Clients.ser";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(FileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(clients);
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * this is read from file function
	 */
	public void read()
	{
		String FileName = "Clients.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(FileName);
			in = new ObjectInputStream(fis);
			clients = (ArrayList<Client>) in.readObject();
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