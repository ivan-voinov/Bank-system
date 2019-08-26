
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException; 
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.lang.StringBuilder;

/**
 * The purpose of this class: to operate the bank accounts
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class Bank 
{
	private ArrayList<BankAccount> accounts;
	private int maxAccounts = 1000;
	private Scanner fInput;
	private Formatter fOutput;
	
	/**
	 * Initializes the bank accounts array with the default size of 1000
	 */
	public Bank()
	{
		accounts = new ArrayList<BankAccount>(maxAccounts);
	}
	
	/**
	 * Initializes the bank accounts array with the size passed to this constructor
	 * and sets it as a maximum size of the array
	 * @param numAccs The size of the bank accounts array
	 */
	public Bank(int numAccs)
	{
		accounts = new ArrayList<BankAccount>(numAccs);
		maxAccounts = numAccs;
	}
	
	/**
	 * Adds a new account to the bank accounts array if there is enough room
	 * @param account Bank account to add
	 * @return True if there is enough room for a new account, false otherwise
	 */
	public boolean addAccount(BankAccount account)
	{
		//Checks if there is enough room for a new account
		if (accounts.size() >= maxAccounts)
		{
			return false;
		}
		
		accounts.add(account);
		return true;
	}
	
	/**
	 * Check if the number of the account is unique
	 * @param accNumToCheck A number of the account to check
	 * @return True if the number of the account is unique, false otherwise
	 */
	public boolean numAccountIsUnique(long accNumToCheck)
	{
		for (int i = 0; i < accounts.size(); ++i)
			if (accounts.get(i).accNumber == accNumToCheck)
				return false;
		  
		return true;
	}
	
	/**
	 * Tries to find the account by its number. Returns its index if successful or -1 if not.
	 * @param accNumber The unique number of the bank account
	 * @return The index of the bank account
	 */
	public int findAccount(long accNumber)
	{
		for (int i = 0; i < accounts.size(); ++i)
			if (accounts.get(i).accNumber == accNumber)
				return i;

		return -1;
	}
	
	/**
	 * Displays all the information about the requested bank account 
	 * @param accNumber The unique number of the bank account
	 * @return Information about the requested 
	 * bank account or error message if the account was not found
	 */
	public String displayAccount(long accNumber)
	{
		int accNum = findAccount(accNumber);
		
		if (accNum == -1)
			return("Error: account does not exist ");
		else
			return accounts.get(accNum).toString();
	}
	
	/**
	 * Gets all the information about all bank accounts
	 * @return The information about all bank accounts separated by new line characters
	 */
	public StringBuilder getAccountDetails()
	{
		StringBuilder accountDetails = new StringBuilder();
		
		accountDetails.append("Banking System" + System.lineSeparator());
		accountDetails.append("********************" + System.lineSeparator());
		accountDetails.append("Number of Account holders: " +
											accounts.size() + 
											System.lineSeparator());
		
		for (int i = 0; i < accounts.size(); ++i)
			accountDetails.append(accounts.get(i) + System.lineSeparator());
		
		return accountDetails;
	}
	
	/**
	 * Tries to withdraw the requested amount of money from the requested bank account
	 * @param accNumber The unique number of the bank account
	 * @param amt The requested amount of money to withdraw
	 * @return True if the transaction was successful, false otherwise
	 */
	public boolean withdraw(long accNumber, double amt)
	{
		int accountNumber = findAccount(accNumber);
		
		if (accountNumber == -1)
			return false;
		
		else
		{
			accounts.get(accountNumber).withdraw(amt);
			return true;
		}
	}
	
	/**
	 * Tried to deposit the requested amount of money to the requested bank account
	 * @param accNumber The unique number of the bank account
	 * @param amt The requested amount of money to deposit
	 * @return True if the transaction was successful, false otherwise
	 */
	public boolean deposit(long accNumber, double amt)
	{
		int accountNumber = findAccount(accNumber);
		
		if (accountNumber == -1)
			return false;
		
		else
		{
			accounts.get(accountNumber).deposit(amt);
			return true;
		}
	}
	
	/**
	 * Performs a monthly update for every bank account and returns the results of it
	 * @return Information about successful and failed updates
	 */
	public StringBuilder processMonthlyUpdate()
	{
		StringBuilder updateResults = new StringBuilder();
		
		if (accounts.size() == 0)
			updateResults.append("No accounts to update");
		else
		{
			for (int i = 0; i < accounts.size(); ++i)
				updateResults.append(accounts.get(i).monthlyAccountUpdate() + System.lineSeparator());
		}
		
		return updateResults;
	}
	
	/**
	 * Tries to open input file with the name "bankData.txt"
	 * @throws IOException If an input or output exception occurred
	 */
	public void openInputFile() throws IOException
	{
		String fileName = "bankData.txt";
		fInput = new Scanner(Paths.get(fileName));
	}
	
	/**
	 * Goes through all the records and creates a BankAccount object (savings or chequing)
	 * with the information read from the file
	 * @throws IllegalStateException If the file was closed
	 * @throws NoSuchElementException If file improperly formed 
	 * @throws NullPointerException If file was not initialized correctly
	 */
	public void readRecords() throws IllegalStateException, NoSuchElementException, NullPointerException
	{
		for (int i = accounts.size(); fInput.hasNext(); ++i)
		{	
			char accType = fInput.next().toLowerCase().charAt(0);
				
			switch (accType)
			{
				case 'c' :
				{
					accounts.add(new ChequingAccount());
					break;
				}
				case 's' : 
				{
					accounts.add(new SavingsAccount());
					break;
				}
			}
			accounts.get(i).addBankAccountFromFile(fInput);
		}
	}
	
	/**
	 * Closes the input file if it wasn't null
	 */
	public void closeInputFile()
	{
		if (fInput != null)
			fInput.close();
	}
	
	/**
	 * Tries to open the output file with the name "bankotput.txt"
	 * @throws FileNotFoundException If the file was not found
	 * @throws SecurityException If there is a security violation
	 * @throws FormatterClosedException If the formatter was closed
	 */
	public void openOutputFile() throws FileNotFoundException,
										SecurityException,
										FormatterClosedException
	{
		String fileName = "bankoutput.txt";
		fOutput = new Formatter(fileName);
	}
	
	/**
	 * Prints the information about every bank account to the file
	 * @throws NullPointerException If the file was not initialized correctly
	 */
	public void printRecordsToFile() throws NullPointerException
	{
		for (int i = 0; i < accounts.size(); ++i)
		{
			accounts.get(i).printRecordsToFile(fOutput);
		}
	}
	
	/**
	 * Closes the output file if it wasn't null
	 */
	public void closeOutputFile()
	{
		if (fOutput != null)
			fOutput.close();
	}
}
