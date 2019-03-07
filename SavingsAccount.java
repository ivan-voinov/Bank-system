
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 5
 * Date: March 3, 2019
 */

import java.text.DecimalFormat;

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
	
	/**
	 * Prompts the user for the general bank account information,
	 * the interest rate and the minimum balance
	 * @return True if the savings account was added successfully
	 */
	@Override
	public boolean addBankAccount()
	{
		super.addBankAccount();
		
		//Inputs minBalance
		minBalance = Assign1.inputValidDouble("Enter the minimum balance : ", "Invalid minimum balance : ");
		
		//Inputs interest rate
		do
		{
			interestRate = Assign1.inputValidDouble("Enter interest rate(should be a number in (0,1)): ",
													"Invalid interest rate : ");
			
			if (interestRate >= 1 || interestRate == 0)
				System.out.println("Invalid interest rate : ");
			
		} while (interestRate >= 1 || interestRate == 0);
		
		return true;
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
	 * Increases the balance by the monthly interest rate if the it is more than the minimum balance
	 */
	@Override
	public void monthlyAccountUpdate()
	{
		if (balance > minBalance)
			balance *= 1 + interestRate;
		else
			System.out.println("Account " + accNumber + " error : balance is not more than minimum balance ");
	}
}
