package Model;

import Utils.Category;
import java.time.Year;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This class represent a car
 *
 */

public class Car  implements Serializable{
	private static final long serialVersionUID=23l;
	private int carNum; // Car number
	private Year manufacturingYear; // The year the car was made
	private float dailyPrice; // The car price for a day
	private String gearBox; // The type of gearbox
	private Category category; // Car category
	private String model; // Car model
	private String subModel; // Car sub model
	private int branchNumber; // The branch number
	private boolean rented; // Tells if the car has been booked in this times
	private ArrayList <LocalDate []> dates; // The car dates diary
	/**
	 * Car class constructor
	 * @param num - int
	 * @param year - Year
	 * @param price - float
	 * @param gear - String
	 * @param c - Category
	 * @param model - String
	 * @param sub - String
	 * @param branch - int
	 */
	public Car(int num, Year year, float price, String gear, Category c, String model, String sub,int branch)
	{
		this.carNum = num;
		this.category = c;
		this.dailyPrice = price;
		this.gearBox = gear;
		this.manufacturingYear = year;
		this.model = model;
		this.subModel = sub;
		this.branchNumber = branch;
		this.rented = false;
		this.dates = new ArrayList <LocalDate []>();
	}
	/**
	 * This function checks the car availability and updates the date diary
	 * @param start - LocalDate 
	 * @param end - LocalDate
	 * @param flag -int
	 */
	public void datesRent(LocalDate start, LocalDate end, int flag)
	{
		LocalDate [] temp = {start,end}; // Create an array
		if(this.dates.size() == 0) // If the diary is empty
		{
			if(flag == 1) // If flag is 1 add date to diary
				this.dates.add(temp);
			else
				this.rented = true; // if flag is 0 check if available
		}
		else
		{
			if(this.dates.get(dates.size() -1)[1].isBefore(start)) // Check if the wanted Starting date is later than all of the ending dates
			{
				if(flag == 1)
					this.dates.add(temp); // If so add at the end
				else
					this.rented = true; // If so available 
			}
			else if(this.dates.get(0)[0].isAfter(end)) // Check if the wanted dates end before any existing one stars
			{
				if(flag == 1)
				{
					this.dates.add(this.dates.get(this.dates.size() -1));
					sort(temp,0); // If so add at the start
				}
				else
					this.rented = true; // If so available 
			}
			else
			{
				for(LocalDate[] check : this.dates) // Check if the date fits in between 2 existing diary enters
				{
					if(check[1].isBefore(start) && dates.get(this.dates.indexOf(check)+1)[0].isBefore(end))
						// The date is in between those 2 dates
					{
						if(flag == 1)
						{
							this.dates.add(this.dates.get(this.dates.size() -1));
							// Add the date using sort
							sort(temp, dates.indexOf(check));
						}
						else
							this.rented = true; // If so available 
					}
				}
			}	
		}
	}
	/**
	 * Class hashCode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(branchNumber, carNum, category, dailyPrice, dates, gearBox, manufacturingYear, model,
				rented, subModel);
	}
	/**
	 * Class equals
	 * @param obj - Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return branchNumber == other.branchNumber && carNum == other.carNum && category == other.category
				&& Float.floatToIntBits(dailyPrice) == Float.floatToIntBits(other.dailyPrice)
				&& Objects.equals(dates, other.dates) && Objects.equals(gearBox, other.gearBox)
				&& Objects.equals(manufacturingYear, other.manufacturingYear) && Objects.equals(model, other.model)
				&& rented == other.rented && Objects.equals(subModel, other.subModel);
	}
	/**
	 * CarNum getter
	 * @return carNum
	 */
	public int getCarNum() {
		return carNum;
	}
	/**
	 * manufacturingYear getter
	 * @return manufacturingYear
	 */
	public Year getManufacturingYear() {
		return manufacturingYear;
	}
	/**
	 * dailyPrice getter
	 * @return dailyPrice
	 */
	public float getDailyPrice() {
		return dailyPrice;
	}
	/**
	 * gearBox getter
	 * @return gearBox
	 */
	public String getGearBox() {
		return gearBox;
	}
	/**
	 * category getter
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * model getter
	 * @return model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * subModel getter
	 * @return subModel
	 */
	public String getSubModel() {
		return subModel;
	}
	/**
	 * branchNumber getter
	 * @return branchNumber
	 */
	public int getBranchNumber() {
		return branchNumber;
	}
	/**
	 * rented getter
	 * @return rented
	 */
	public boolean isRented() {
		return rented;
	}
	/**
	 * getDates getter
	 * @return getDates
	 */
	public ArrayList<LocalDate[]> getDates() {
		return dates;
	}
	/**
	 * carNum setter
	 * @param carNum - int 
	 */
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	/**
	 * manufacturingYear setter
	 * @param manufacturingYear - Year 
	 */
	public void setManufacturingYear(Year manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}
	/**
	 * dailyPrice setter
	 * @param dailyPrice - float 
	 */
	public void setDailyPrice(float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	/**
	 * gearBox setter
	 * @param gearBox - String 
	 */
	public void setGearBox(String gearBox) {
		this.gearBox = gearBox;
	}
	/**
	 * category setter
	 * @param category - Category 
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * String setter
	 * @param model - String 
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * subModel setter
	 * @param subModel - String 
	 */
	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}
	/**
	 * branchNumber setter
	 * @param branchNumber - int 
	 */
	public void setBranchNumber(int branchNumber) {
		this.branchNumber = branchNumber;
	}
	/**
	 * rented setter
	 * @param rented - boolean 
	 */
	public void setRented(boolean rented) {
		this.rented = rented;
	}
	/**
	 * dates setter
	 * @param dates - ArrayList LocalDate[]
	 */
	public void setDates(ArrayList<LocalDate[]> dates) {
		this.dates = dates;
	}
	/**
	 * This function sort the dates diary
	 * @param check - The date to add
	 * @param position - The index to insert him in
	 */
	public void sort(LocalDate[] check, int position)
	{
		LocalDate[] t1 = new LocalDate[2]; // temporary array
		LocalDate[] t2 = new LocalDate[2]; // temporary array
		LocalDate[] t3 = new LocalDate[2]; // temporary array
		for(int i = dates.size() - 2; i >position; i--) // Iterate until you reach the place
		{
			// Swap
			t1 = dates.get(i);
			t2 = dates.get(i-1);
			t1 = t2;
		}
		// Add in the wanted place
		t3 = dates.get(position);
		t3 = check;
	}
	/**
	 * Class toString
	 */
	@Override
	public String toString() {
		return "Car [carNum=" + carNum + ", manufacturingYear=" + manufacturingYear + ", dailyPrice=" + dailyPrice
				+ ", gearBox=" + gearBox + ", category=" + category + ", model=" + model + ", subModel=" + subModel
				+ ", branchNumber=" + branchNumber + ", rented=" + rented + ", dates=" + dates.toString() + "]";
	}

}