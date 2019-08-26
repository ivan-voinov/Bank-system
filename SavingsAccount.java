
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
import java.util.Random;

/**
 * The purpose of this class: to accumulate money on a bank account according to a monthly interest rate
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 **/
public class SavingsAccount extends BankAccount
{
	
	private double interestRate;
	private double minBalance;
	private Random rnd;
	
	/**
	 * Initializes the interest rate with a value between 0 and 1
	 */
	public SavingsAccount()
	{
		generateRandomInterestRate();
	}
	
	/**
	 * Creates a savings account with the specified information
	 * @param accHolder The client object which includes full name, email and phone
	 * @param balance The balance of the bank account
	 * @param accNumber The unique number of the bank account
	 * @param minBalance The minimum balance required to accumulate money
	 */
	public SavingsAccount(Client accHolder, double balance, long accNumber, double minBalance)
	{
		super(accHolder, balance, accNumber);
		this.minBalance = minBalance;
		
		generateRandomInterestRate();
	}
	
	/**
	 * Generates a random interest rate between 0 and 1
	 */
	public void generateRandomInterestRate()
	{
		rnd = new Random();
		
		//Generates a random interest rate between 0 and 1
		interestRate = rnd.nextDouble();
	}
	
	/**
	 * Tries to read the savings bank account information from the file
	 * @param fInput The file to read from
	 */
	@Override
	public void addBankAccountFromFile(Scanner fInput)
	{
		super.addBankAccountFromFile(fInput);
		
		double interestRate = fInput.nextDouble();
		double minBalance = fInput.nextDouble();
		
		this.minBalance = minBalance;
		this.interestRate = interestRate;
	}
	
	/**
	 * Prints the information about the bank account and the minimum balance to file
	 * and moves to the next line
	 */
	@Override
	public void printRecordsToFile(Formatter fOutput)
	{
		super.printRecordsToFile(fOutput);
		
		fOutput.format(" %.2f%n", minBalance);
	}
	
	/**
	 * Displays all the information about the savings bank account
	 * @return The concatenated string separated by spaces
	 * which includes all the information about the savings bank account
	 */
	@Override
	public String toString()
	{
		DecimalFormat df = new DecimalFormat();
		
		//Displays all the information about the bank account,
		//the minimum balance and the interest rate
		return super.toString() + 
		" Minimum balance: " + minBalance +
		" Interest rate: "   + df.format(interestRate);
	}
	
	/**
	 * Increases the balance by the monthly interest rate and returns the results of the update
	 * @return Message which includes the account number, success or fail of the update, and
	 * if not successful, the reason of failure
	 */
	@Override
	public String monthlyAccountUpdate()
	{
		if (balance > minBalance)
		{
			balance *= 1 + interestRate;
			return "Account " + accNumber + ": monthly update SUCCESSFUL";
		}
		else
			return "Account " + accNumber + 
				": monthly update FAILED: balance is not more than the minimum balance";
	}
}
