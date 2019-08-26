
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

/**
 * The purpose of this class: to keep the information about the account holder
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class Client
{
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNumber;
	
	/**
	 * Initializes the first name, the last name and the email
	 * @param firstName the first name of the account holder
	 * @param lastName the last name of the account holder
	 * @param email the email of the account holder (has a format of _@_._)
	 */
	public Client(String firstName,
				  String lastName,
				  String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	/**
	 * Initializes all the instance variables
	 * @param firstName the first name of the account holder
	 * @param lastName the last name of the account holder
	 * @param email the email of the account holder (has a format of _@_._)
	 * @param phoneNumber the phone number of the account holder
	 */
	public Client(String firstName,
				  String lastName,
				  String email,
				  long phoneNumber)
	{
		this(firstName, lastName, email);
		
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Returns the full name of the account holder
	 * @return the full (concatenated) name of the account holder separated by space
	 */
	public String getName()
	{
		return firstName + " " + lastName;
	}
	
	/**
	 * Returns the email of the account holder
	 * @return the email of the account holder
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Returns the phone of the account holder
	 * @return the phone of the account holder
	 */
	public long getPhone()
	{
		return phoneNumber;
	}
}
