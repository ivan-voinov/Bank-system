
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 5
 * Date: March 3, 2019
 */

/**
 * The purpose of this class: to operate the bank accounts
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class Bank 
{
	private static BankAccount[] accounts;
	private static int numAccounts = 0;
	private int sizeBank = 100;
	
	/**
	 * Initializes the bank accounts array with the default size of 1000
	 */
	public Bank()
	{
		accounts = new BankAccount[sizeBank];
	}
	
	/**
	 * Initializes the bank accounts array with the size passed to this constructor
	 * @param numAccs The size of the bank accounts array
	 */
	public Bank(int numAccs)
	{
		accounts = new BankAccount[numAccs];
	}
	
	/**
	 * Prompts the user to enter the information about the new account and adds it to the array
	 * @return True if a new account was successfully created or false if there is not enough room
	 */
	public boolean addAccount()
	{	
		//Checks if there is enough room for a new account
		if (numAccounts >= sizeBank)
		{
			System.out.println("Error: no room ");
			return false;
		}
		
		//Tells the user to enter details about the new account
		System.out.println("Enter details of account holder " + (numAccounts + 1));
		System.out.println("==================================");
		
		//Inputs the type of the account
		boolean inputIncorrect = true;
		char accType = '0';
		do
		{
			String userInput;
			System.out.println("Enter account type (s for savings, c for checking): ");
			
			//Checks if the input is not empty and contains exactly 1 symbol
			userInput = Assign1.input.nextLine();
			if (!userInput.equals("") && userInput.length() == 1)
			{
				accType = userInput.toLowerCase().charAt(0);
				if (accType == 'c' || accType == 's')
					inputIncorrect = false;
			}
			
		} while (inputIncorrect);
		
		switch (accType)
		{
			case 'c' :
			{
				accounts[numAccounts] = new ChequingAccount();
				break;
			}
			case 's' : 
			{
				accounts[numAccounts] = new SavingsAccount();
				break;
			}
		}
		
		//Tries to add the account
		if (accounts[numAccounts].addBankAccount())
		{
			numAccounts++;
			return true;
		}
		else 
		{
			System.out.println("Error: account not added ");
			return false;
		}
	}
	
	/**
	 * Check if the number of the account is unique
	 * @param accNumToCheck A number of the account to check
	 * @return True if the number of the account is unique, false otherwise
	 */
	public static boolean numAccountIsUnique(int accNumToCheck)
	{
		for (int i = 0; i < numAccounts; ++i)
		{
			if (accounts[i].accNumber == accNumToCheck)
				return false;
		}
		return true;
	}
	
	/**
	 * Prompts the user for the number of the account to find and tries to find it
	 * @return Index of the account if found, -1 otherwise
	 */
	public int findAccount()
	{
		int accNumber;
		
		//Inputs account number and 
		//ensures the user inputs a correct number which is greater than zero
		accNumber = Assign1.inputValidInt("Enter account number : ", "Invalid account number : ");
		
		for (int i = 0; i < numAccounts; ++i)
			if (accounts[i].accNumber == accNumber)
				return i;
		return -1;
	}
	
	/**
	 * Displays all the information about the requested account 
	 * @return Information about the requested 
	 * bank account or error message if the account was not found
	 */
	public String displayAccount()
	{
		int accNum = findAccount();
		
		if (accNum == -1)
			return("Error: account does not exist ");
		else
			return accounts[accNum].toString();
	}
	
	/**
	 * Prints the total number of the bank accounts and 
	 * the information about every bank account in the array
	 */
	public void printAccountDetails()
	{
		System.out.println("Baking System");
		System.out.println("********************");
		System.out.println("Number of Account holders: " + numAccounts);
		
		for (int i = 0; i < numAccounts; ++i)
			System.out.println(accounts[i]);
	}
	
	/**
	 * Performs a deposit/withdraw operation for the selected bank account
	 * or displays an error message if the account was not found
	 */
	public void updateAccount()
	{
		int accNum = findAccount();
		
		double amt = 0;

		boolean inputIncorrect = true;
			
		//Ensures the input is an integer
		do
		{
			System.out.println("Enter amount to deposit / withdraw " + 
					 		   "(positive number to deposite, negative number to withdraw) : ");
			
			if (Assign1.input.hasNextDouble())
			{
				amt = Assign1.input.nextDouble();
				inputIncorrect = false;
			}
			else 
			{
				System.out.println("Invalid amount: ");
			}
			Assign1.input.nextLine();
			
		} while(inputIncorrect);
		
		if (accNum == -1)
			System.out.println("Error: account does not exist ");
		else
			//Checks if there is enough funds
			if (amt < 0 && java.lang.Math.abs(amt) > accounts[accNum].balance)
				System.out.println("Error: not enough funds ");
			else 
				accounts[accNum].updateBalance(amt);
	}
	
	/**
	 * Performs a monthly update for every bank account
	 */
	public void monthlyUpdate()
	{
		for (int i = 0; i < numAccounts; ++i)
			accounts[i].monthlyAccountUpdate();
	}
}
