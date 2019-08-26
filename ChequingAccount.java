
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

/**
 * The purpose of this class: to apply monthly fees to a bank account
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 **/
public class ChequingAccount extends BankAccount
{
	
	private double fee;
	private Random rnd;
	
	/**
	 * Initializes the fee with a value between 5.0 and 10.0
	 */
	public ChequingAccount()
	{
		generateRandomFee();
	}
	
	/**
	 * Creates a chequing account with the specified information
	 * @param accHolder The client object which includes full name, email and phone
	 * @param balance The balance of the bank account
	 * @param accNumber The unique number of the bank account
	 */
	public ChequingAccount(Client accHolder, double balance, long accNumber)
	{
		super(accHolder, balance, accNumber);
		generateRandomFee();
	}
	
	/**
	 * Initializes the fee with a value between 5.0 and 10.0
	 */
	public void generateRandomFee()
	{
		rnd = new Random();
		
		//Generates a random fee between 5.0 and 10.0 
		fee = (10.0 - 5.0) * rnd.nextDouble() + 5.0;
	}
	
	/**
	 * Tries to read the bank account information from the file
	 * @param fInput The file to read from
	 */
	@Override
	public void addBankAccountFromFile(Scanner fInput)
	{
		super.addBankAccountFromFile(fInput);
		
		double fee = fInput.nextDouble();
		
		this.fee = fee;
	}
	
	/**
	 * Prints the information about the bank account to file and moves to the next line
	 * @param fOutput The file to print the information to
	 */
	@Override
	public void printRecordsToFile(Formatter fOutput)
	{
		super.printRecordsToFile(fOutput);
		fOutput.format("%n");
	}
	
	/**
	 * Displays all the information about the chequing bank account
	 * @return The concatenated string separated by spaces
	 * which includes all the information about the chequing bank account
	 */
	@Override
	public String toString()
	{
		DecimalFormat df = new DecimalFormat();
		
		//Displays all the information about the bank account
		//and the monthly fee 
		return super.toString() + " Fee: " + df.format(fee);
	}
	
	/**
	 * Reduces the balance by the monthly fee and returns the results of the update
	 * @return Message which includes the account number, success or fail of the update, and
	 * if not successful, the reason of failure
	 */
	@Override
	public String monthlyAccountUpdate()
	{
		if (balance > fee)
		{
			balance -= fee;
			return "Account " + accNumber + ": monthly update SUCCESSFUL";
		}
		else
			return "Account " + accNumber + ": monthly update FAILED: not enough funds";
	}
}
