
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 5
 * Date: March 3, 2019
 */

/**
 * The purpose of this class: to keep the information about the account holder
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class Person
{
	private String firstName;
	private String lastName;
	private long phoneNum;
	private String email;
	
	/**
	 * Initializes all the instance variables
	 * @param firstName the first name of the account holder
	 * @param lastName the last name of the account holder
	 * @param phoneNum the phone number of the account holder (10 digits)
	 * @param email the email of the account holder (has a format of _@_._)
	 */
	public Person(String firstName,
				  String lastName,
				  long phoneNum,
				  String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.email = email;
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
	 * Returns the phone number of the account holder
	 * @return the phone number of the account holder
	 */
	public long getPhoneNum()
	{
		return phoneNum;
	}
	
	/**
	 * Returns the email of the account holder
	 * @return the email of the account holder
	 */
	public String getEmail()
	{
		return email;
	}
}
