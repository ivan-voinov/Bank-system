
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

/**
 * The purpose of this class: to create exceptions with the information about a transaction
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class TransactionIllegalArgumentException extends IllegalArgumentException
{
	private long accNumber;
	private double amtOfTransaction;
	private String problemDescription;
	
	/**
	 * Initializes the number of a bank account, the amount of transaction and the problem description
	 * variables with values
	 * @param accNumber The unique number of a bank account
	 * @param amtOfTransaction The amount of money involved in a transaction
	 * @param problemDescription The description of what went wrong in a transaction
	 */
	public TransactionIllegalArgumentException(long accNumber,
											   double amtOfTransaction,
											   String problemDescription)
	{
		this.accNumber = accNumber;
		this.amtOfTransaction = amtOfTransaction;
		this.problemDescription = problemDescription;
	}
	
	/**
	 * Returns the string with the information about the failed transaction
	 * @return The display of the problem description, the account number and the amount of transaction
	 */
	public String toString()
	{
		return (problemDescription + 
				"\nAccount number: " + accNumber + 
				"\nAmount of transaction: " + amtOfTransaction);
	}
}
