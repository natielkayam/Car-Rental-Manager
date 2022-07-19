package Controller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Model.Branch;
import Model.Car;
import Model.Client;
import View.*;

/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the main class
 *
 */

public class Main implements Serializable
{
	private static ArrayList <Branch> branch; // The company branches
	private static	ArrayList <Car> car; // The company cars
	private static ArrayList <Client> client; // The company clients
	private static final long serialVersionUID=23l; // Serializable number
	public static void main(String[] args)
	{ 
		branch = new ArrayList <Branch>(); 
		client = new ArrayList <Client>();
		car = new ArrayList <Car>();
		read("Branch.ser",0); // Read branches
		read("Car.ser",1); // read Cars
		// Use Nimbus for design 
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		{
	        if ("Nimbus".equals(info.getName())) {
	            try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
	            break;
	        }
	    }
		SignIn frame = new SignIn(); // Open a GUI window
		frame.setVisible(true); // Make him visable
	}
	/**
	 * this function reads information saved using serializable
	 * @param FileName - String
	 * @param flag - int
	 */
		static void read(String FileName, int flag)
		{
			FileInputStream fis = null;
			ObjectInputStream in = null;
			try
			{
				fis = new FileInputStream(FileName);
				in = new ObjectInputStream(fis);
				if(flag == 0) // If flag is 0 read branches
				{
					branch = (ArrayList<Branch>) in.readObject();
				}
				else // Read cars
					car = (ArrayList<Car>) in.readObject();
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
