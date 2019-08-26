
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Formatter;

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
	protected Client accHolder;
	
	/**
	 * The current balance of the account
	 */
	protected double balance;
	
	/**
	 * The unique non-negative account number up to 8 digits long
	 */
	protected long accNumber; 
	
	/**
	 * Default empty constructor
	 */
	public BankAccount()
	{}
	
	/**
	 * Creates a bank account with the specified information
	 * @param accHolder The client object which includes full name, email and phone
	 * @param balance The balance of the bank account
	 * @param accNumber The unique number of the bank account
	 */
	public BankAccount(Client accHolder, double balance, long accNumber)
	{
		this.accHolder = accHolder;
		this.balance = balance;
		this.accNumber = accNumber;
	}
	
	/**
	 * Tries to read the bank account information from the file
	 * @param fInput The file to read from
	 */
	public void addBankAccountFromFile(Scanner fInput)
	{
		long accNumber = fInput.nextLong();
		String firstName = fInput.next();
		String lastName = fInput.next();
		long phoneNumber = fInput.nextLong();
		String email = fInput.next();
		double balance = fInput.nextDouble();
		
		this.accHolder = new Client(firstName, lastName, email, phoneNumber);
		this.accNumber = accNumber;
		this.balance = balance;
	}
	
	/**
	 * Prints the information about the bank account to the file
	 * @param fOutput The file to print the information to
	 */
	public void printRecordsToFile(Formatter fOutput)
	{
		if (this instanceof ChequingAccount)
			fOutput.format("%c ", 'C');
		else
			fOutput.format("%c ", 'S');
		
		fOutput.format("%s %d %s %.2f", 
					   accHolder.getName(),
					   accNumber,
					   accHolder.getEmail(),
					   balance);
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
					   " Email Address: " + accHolder.getEmail()    + 
					   " Balance: "       + df.format(balance)      +
					   "$";
		
		return print;
	}
	
	/**
	 * Increases the balance by the amount of money and 
	 * displays an error if the amount is negative
	 * @param amt The amount of money to deposit
	 * @throws TransactionIllegalArgumentException If the amount to deposit is negative
	 */
	public void deposit(double amt) throws TransactionIllegalArgumentException
	{
		if (amt < 0)
			throw new TransactionIllegalArgumentException(accNumber, amt, "Error: amount is negative");
		else
			balance += amt;
	}
	
	/**
	 * Reduces the balance by the amount of money,
	 * and displays an error if the amount to withdraw is negative or greater than the balance
	 * @param amt The amount of money to withdraw
	 * @throws TransactionIllegalArgumentException 
	 * If the amount to withdraw is negative or greater than balance
	 */
	public void withdraw(double amt) throws TransactionIllegalArgumentException
	{
		if (amt < 0)
			throw new TransactionIllegalArgumentException
				(accNumber, amt, "Error: amount is negative");
		else
			if (amt > balance)
				throw new TransactionIllegalArgumentException
					(accNumber, amt, "Error: amount is greater than balance");
		else
			balance -= amt;
	}
	
	/**
	 * Applies changes to balance and recalculates it. Returns the results of the update
	 * @return The information about the results of the update
	 */
	public abstract String monthlyAccountUpdate();
}
