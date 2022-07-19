package Model;

import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This class represent a branch of the car Leasing company
 *
 */
public class Branch implements Serializable
{
	private static final long serialVersionUID=23l;
	private int branchNum; // The branch number
	private LocalTime startingTime; // Opening Hour
	private LocalTime closingTime; // Closing hour
	private String location; // The branch location
	private ArrayList <Car> cars; // Cars in the branch
	
	/**
	 * Branch class constructor
	 * @param open -LocalTime
	 * @param close -LocalTime
	 * @param adress -String
	 */
	public Branch(LocalTime open, LocalTime close, String adress)
	{
		this.branchNum = 0;
		this.startingTime = open;
		this.location = adress;
		this.closingTime = close;
		cars = new ArrayList <Car>();
	}

	/**
	 * branchNum getter
	 * @return branchNum - int
	 */
	public int getBranchNum() 
	{
		return branchNum;
	}
	/**
	 * startingTime getter
	 * @return startingTime - LocalTime
	 */
	public LocalTime getStartingTime() {
		return startingTime;
	}
	/**
	 * closingTime getter
	 * @return closingTime - LocalTime
	 */
	public LocalTime getClosingTime() {
		return closingTime;
	}
	/**
	 * location getter
	 * @return location - String
	 */
	public String getLocation() {
		return location;
	}
/**
 * Cars getter
 *@return cars - ArrayList Car
 */
	public ArrayList<Car> getCars() {
		return cars;
	}
	/**
	 * branchNum setter 
	 *@param branchNum - int
	 */
	public void setBranchNum(int branchNum) {
		this.branchNum = branchNum;
	}
	/**
	 * startingTime setter
	 *@param startingTime - LocalTime
	 */
	public void setStartingTime(LocalTime startingTime) {
		this.startingTime = startingTime;
	}
	/**
	 * closingTime setter
	 *@param closingTime - LocalTime
	 */
	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}
	/**
	 * location setter
	 *@param location - String
	 */
	public void setLocation(String location) {
		this.location = location;
	}
/**
 * Cars setter
 *@param cars - ArrayList Car
 */
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
/**
 * class hashcodes
 */
	@Override
	public int hashCode() {
		return Objects.hash(branchNum, cars, closingTime, location, startingTime);
	}
	/**
	 * Class toString
	 */
@Override
public String toString() {
	return "Branch [branchNum=" + branchNum + ", startingTime=" + startingTime + ", closingTime=" + closingTime
			+ ", location=" + location + ", cars=" + cars.toString() + "]";
}


/**
 * Class equals
 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		return branchNum == other.branchNum && Objects.equals(cars, other.cars)
				&& Objects.equals(closingTime, other.closingTime) && Objects.equals(location, other.location)
				&& Objects.equals(startingTime, other.startingTime);
	}

}
