
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 5
 * Date: March 3, 2019
 */

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * The purpose of this class: an abstract base class for bank accounts
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 **/
public abstract class BankAccount 
{
	/**
	 * The owner of the bank account
	 */
	protected Person accHolder;
	
	/**
	 * The current balance of the account
	 */
	protected double balance;
	
	/**
	 * The unique account number up to 8 digits long
	 */
	protected int accNumber;
	
	/**
	 * Gets the current balance of the bank account
	 * @return Current balance of the bank account
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * Prompts the user for the bank account information and creates an account
	 * @return True if the account was created successfully
	 */
	public boolean addBankAccount()
	{
		//Inputs account number and 
		//ensures the user inputs a correct number which is greater than zero and up to 8 digits
		do
		{
			accNumber = Assign1.inputValidInt("Enter account number : ", "Invalid account number : ");
			
			if (!Bank.numAccountIsUnique(accNumber))
				System.out.println("This account number already exists");
			
			if (Integer.toString(accNumber).length() > 8)
				System.out.println("Invalid account number : ");
			
		} while (!Bank.numAccountIsUnique(accNumber) || Integer.toString(accNumber).length() > 8);
		
		//Inputs first name
		System.out.println("Enter first name of account holder : ");
		String firstName = Assign1.input.nextLine();
		
		//Inputs last name
		System.out.println("Enter last name of account holder : ");
		String lastName = Assign1.input.nextLine();
		
		//Inputs phone number
		long phoneNum = 0;
		do
		{
			boolean inputIncorrect = true;
			
			//Ensures the input is a long
			do
			{
				System.out.println("Enter phone number : ");
				
				if (Assign1.input.hasNextLong())
				{
					phoneNum = Assign1.input.nextLong();
					inputIncorrect = false;
				}
				else 
				{
					System.out.println("Invalid phone number : ");
				}
				Assign1.input.nextLine();
					
			} while(inputIncorrect);
				
			//Checks if the phone is exactly 10 digits
			if (Long.toString(phoneNum).length() != 10)
				System.out.println("Invalid phone number : ");
				
		} while(phoneNum < 0 || Long.toString(phoneNum).length() != 10);
		
		//Inputs email and ensures it is valid (has a format of _@_._)
		String email;
		boolean emailInvalid = true;
		
		do
		{
			System.out.println("Enter Email address : ");
			email = Assign1.input.nextLine();
			//"." = any character, "+" = one or many, \\ = quote the character
			emailInvalid = !Pattern.matches(".+@.+\\..+", email);
			
		} while (emailInvalid);
		
		//Inputs balance
		balance = Assign1.inputValidDouble("Enter opening balance : ", "Invalid opening balance : ");
		
		//Creates a new person with the data the user entered
		accHolder = new Person(firstName, lastName, phoneNum, email);
		
		//Account created successfully
		return true;
	}
	
	/**
	 * Displays all the information about the bank account
	 * @return The concatenated string separated by spaces
	 * which includes all the information about the bank account
	 */
	public String toString()
	{
		DecimalFormat df = new DecimalFormat();
		
		//Displays all the information about the bank account
		String print = "AccountNumber: "  + accNumber               + 
					   " Name: "          + accHolder.getName()     +
					   " Phone Number: "  + accHolder.getPhoneNum() + 
					   " Email Address: " + accHolder.getEmail()    + 
					   " Balance: "       + df.format(balance)      +
					   "$";
		
		return print;
	}
	
	/**
	 * Updates the balance of the bank account by the passed amount
	 * @param amt The amount to deposit (if positive) or withdraw (if negative)
	 */
	public void updateBalance(double amt)
	{
		balance += amt;
	}
	
	/**
	 * Applies changes to balance and recalculates it
	 */
	public abstract void monthlyAccountUpdate();
}
