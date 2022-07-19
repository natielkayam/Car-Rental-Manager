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
import java.time.Year;
import java.util.ArrayList;

import javax.swing.*;

import Model.Client;

/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the SignIn window
 * Manager info: manager@manager.com, Manager
 *
 */

public class SignIn  extends JFrame implements Serializable{
	private static final long serialVersionUID=23l;
	private JButton submit; // Button
	private JButton clear; // Button
	private JButton signUp; // Button
	private JTextField passwordText; // Text
	private JTextField emailText; // Text
	private JLabel label_password; // Label
	private JLabel label_email; // Label
	private JLabel label_problem; //Label
	// GUI items, 
	ArrayList <Client> clients; // Clients list
	Container con; // Container for design purposes
	
	/**
	 * SignIn constructor
	 */
	public SignIn()
	{
		super("Sign up");
		submit = new JButton("Submit");
		clear = new JButton("clear");
		signUp = new JButton("sign Up");
		passwordText = new JTextField(20); 
		emailText = new JTextField(20); 
		label_password = new JLabel("your password:");
		label_email = new JLabel("email");
		label_problem = new JLabel();
		// Initialized all the window items
		clients = new ArrayList<Client>();
		con = this.getContentPane();
		/**
		 * Submit button on click event
		 */
		submit.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					read(); // Read client list
					Client c = null; // Create temp client
					for(Client t : clients) // Iterate through the clients list
					{
						if(t.getEmail() == emailText.getText()) // If we found a client with this email save him
						{
							c = t;
							break;
						}
					}
					if(checkLogin() == false) // Check info
						return;
					// Check if the manager info was inserted
					if(emailText.getText().equals("manager@manager.com") && passwordText.getText().equals("Manager"))
					{
						dispose();
						ManagerNavigation f1 = new ManagerNavigation();
						f1.setVisible(true);
						// If so create a navigation window for him and close this one
					}
					else
					{
						// If not create a client navigation window for this client and close this one
						dispose();
						clientNavigation frame1 = new clientNavigation(c);
						frame1.setVisible(true);
					}
					
				}
		});
		/**
		 * signUp button on click event
		 */
		signUp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				SignUp sign = new SignUp();
				sign.setVisible(true);
				// Open signUp window and Dispose this one
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
			// Clear textFields
		}
	});
		init();
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
		con.setBackground(Color.yellow);
	}
	/**
	 * This function Creates a table like JPanel
	 * @return P - JPanel
	 */
	private JPanel addP()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(label_email);
		p.add(emailText);
		p.add(label_password);
		p.add(passwordText);
		label_problem.setVisible(false);
		p.add(label_problem);
		p.setBackground(Color.pink);
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
		B.add(signUp);
		B.setBackground(Color.blue);
		return B;
	}
	/**
	 * This file checks login information
	 * @return boolean value based on the validation
	 */
	public boolean checkLogin()
	{
		boolean checkemail = false; // Indicates if this email exists
		if(clients == null) // If we dont have any clients you cant login
		{
			label_problem.setVisible(true);
			label_problem.setText("No Clients were found");
			return false;
		}
		else
		{
			for(Client check: clients)
			{
				if(check.getEmail().equals(emailText.getText()) && check.getPassword().equals(passwordText.getText()) == false)
				{
					// If mail is correct but password not you cant login
					checkemail = true;
					label_problem.setVisible(true);
					label_problem.setText("Incorrect password");
					return false;
				}
				else if(check.getEmail().equals(emailText.getText()) && check.getPassword().equals(passwordText.getText()))
				{
					// Can login
					checkemail = true;
					return true;
				}
			}
		}
		if(checkemail == false)
		{
			// We dont have a client with this email
			label_problem.setVisible(true);
			label_problem.setText("No client with this email exist in the system");
			return false;
		}
		return false;
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
