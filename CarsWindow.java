package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.swing.JTable;

import javax.swing.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import Utils.Category;
import Model.Branch;
import Model.Car;
import Model.Client;
/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This is the CarsWindow window
 *
 */

public class CarsWindow extends JFrame implements Serializable{
	private static final long serialVersionUID=23l; // serial UID
	private JButton back; // JButton
	private JButton rent; // JButton
	private JLabel info; // JButton
	private Choice numbers; // Choice
	private JTable j;
	// GUI items
	
	/**
	 * Class constructor
	 *@param days - int
	 *@param temp - ArrayList Car
	 *@param start - LocalDate
	 *@param end - LocalDate
	 *@param c - Client
	 */
	public CarsWindow(int days, ArrayList <Car> temp, LocalDate start, LocalDate end, Client c)
	{
		super("Choose your car");
		back = new JButton("back");
		rent = new JButton("Rent");
		numbers = new Choice();
		info = new JLabel("choose the car number that you want from the list and press rent button to confirm, press back button to return");
		// Initialize GUI items
		getNumbers(temp);
		/**
		 * back on click event
		 */
		back.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				showCar frame = new showCar(c); // Create showCar window
				frame.setVisible(true);
				dispose();
			}
	});

		/**
		 * rent on click event
		 */
		rent.addActionListener(new ActionListener() 
	{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				for(Car chosen: temp)
				{
					if(chosen.getCarNum() == Integer.parseInt(numbers.getSelectedItem()))
					{
						chosen.datesRent(start, end, 1);
					}
					// Add the dates to the car dates diary
				}
				// Dispose current window and open a new one
			}
	});	
		init(temp, days);
	}
	/**
	 * This function adds values to the choice item(numbers)
	 *@param c - ArrayList Car cars list
	 */
	public void getNumbers(ArrayList <Car> c) 
	{
		for(Car c1: c)
			numbers.add(String.valueOf(c1.getCarNum()));
	}
	/**
	 * This function Creates a table like JPanel
	 * @return P - JPanel
	 */
	private JPanel addP(ArrayList <Car> temp, int days)
	{
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		String [] columnNames = {"Carnum","Car year", "Model", "Sub model","Gearbox", "Category", "Price", "Daily price", "Branch num"}; 
		// Create call names
		 Object[][] data = new  Object[temp.size()][9];
		 int count = 0;
		 for(Car t :temp)
		 {
			 data[count][0] = t.getCarNum();
			 data[count][1] = t.getManufacturingYear();
			 data[count][2] = t.getModel();
			 data[count][3] = t.getSubModel();
			 data[count][4] = t.getGearBox();
			 data[count][5] = t.getCategory();
			 data[count][6] = t.getDailyPrice() * days ;
			 data[count][7] = t.getDailyPrice();
			 data[count][8] = t.getBranchNumber();
			 count++;
			 // Insert the data for each column in the counter row and after that increase counter
		 }
		j =new JTable(data, columnNames); // Create the table
		p.add(info,  BorderLayout.NORTH);
		p.add((new JScrollPane(j)),  BorderLayout.SOUTH);
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
		B.add(rent);
		return B;
	}
	/**
	 * This function creates the window appearacne 
	 */
	public void init(ArrayList <Car> temp,int days) 
	{
		setLayout(new BorderLayout());
		add(addP(temp,days), BorderLayout.NORTH);
		add(numbers, BorderLayout.CENTER);
		add(addB(), BorderLayout.SOUTH);
		pack();
	}	
}
