package Model;
import java.time.Year;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author alon_Geller and Jonahatan_Aaron
 * This class represent a client
 *
 */

public class Client implements Serializable
{
	private static final long serialVersionUID=23l;
	private String fName; // First name
	private String lName; // Family name
	private String password; // Password
	private String email; // Email
	private int ID; // ID number
	private Year driverLicense; // The year he obtained his Driver license
	private LocalDate birthDate; // His birth date
	private ArrayList<Car> cars; // ArrayList of the clients cars.
	/**
	 * Class constructor
	 * @param fName - String
	 * @param lName - String
	 * @param password - String
	 * @param email - String
	 * @param id - int
	 * @param driverLicense - Year
	 * @param date - LocalDate
	 */
	public Client(String fName, String lName, String password, String email, int id, Year driverLicense,LocalDate date )
	{
		this.fName = fName; // Client first name
		this.lName = lName; // Client first name
		this.password = password; // Client password
		this.email = email; // Client email
		this.ID = id; // Client ID
		this.driverLicense = driverLicense; // The year the client obtained his driver license 
		this.birthDate = date; // Client birth date
		this.cars = new ArrayList<Car>(); //Initialize Client cars list
	}
/**
 * fName getter
 * @return fName - String
 */
	public String getfName() {
		return fName;
	}
	/**
	 * lName getter
	 * @return lName - String
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * password getter
	 * @return password - String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * email getter
	 * @return email - String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * ID getter
	 * @return ID - int
	 */
	public int getID() {
		return ID;
	}
	/**
	 * driverLicense getter
	 * @return driverLicense - Year
	 */
	public Year getDriverLicense() {
		return driverLicense;
	}
	/**
	 * birthDate getter
	 * @return birthDate - LocalDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	/**
	 * cars getter
	 *@return cars - ArrayList Car
	 */
	public ArrayList<Car> getCars() {
		return cars;
	}
	/**
	 *fName Setter
	 *@param fName - String
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * lName Setter
	 * @param lName - String
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	/**
	 * password Setter
	 * @param password - String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * email Setter
	 * @param email - String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * iD Setter
	 * @param iD - int
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * driverLicense Setter
	 * @param driverLicense - Year
	 */
	public void setDriverLicense(Year driverLicense) {
		this.driverLicense = driverLicense;
	}
	/**
	 * birthDate Setter
	 * @param birthDate - LocalDate
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
/**
 * Cars Setter
 *@param cars - ArrayList Car
 */
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
/**
 * Class hashCode
 */
	@Override
	public int hashCode() {
		return Objects.hash(ID, birthDate, cars, driverLicense, email, fName, lName, password);
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
		Client other = (Client) obj;
		return ID == other.ID && Objects.equals(birthDate, other.birthDate) && Objects.equals(cars, other.cars)
				&& Objects.equals(driverLicense, other.driverLicense) && Objects.equals(email, other.email)
				&& Objects.equals(fName, other.fName) && Objects.equals(lName, other.lName)
				&& Objects.equals(password, other.password);
	}
/**
 * Class toString
 */
	@Override
	public String toString() {
		return "Client [fName=" + fName + ", lName=" + lName + ", password=" + password + ", email=" + email + ", ID="
				+ ID + ", driverLicense=" + driverLicense + ", birthDate=" + birthDate + ", cars=" + cars.toString() + "]";
	}
	
}
