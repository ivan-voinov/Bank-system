
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 5
 * Date: March 3, 2019
 */

import java.text.DecimalFormat;

/**
 * The purpose of this class: to apply monthly fees to a bank account
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 **/
public class ChequingAccount extends BankAccount
{
	
	private double fee;
	
	/**
	 * Prompts the user for the general bank account information and the monthly fee
	 * @return True if the chequing account was added successfully
	 */
	@Override
	public boolean addBankAccount()
	{
		super.addBankAccount();
		
		//Inputs fee
		fee = Assign1.inputValidDouble("Enter monthly fee : ", "Invalid fee : ");
		
		return true;
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
	 * Reduces the balance by the monthly fee
	 */
	@Override
	public void monthlyAccountUpdate()
	{
		if (balance > fee)
			balance -= fee;
		else 
			System.out.println("Account " + accNumber + " error : not enough funds ");
	}
}
