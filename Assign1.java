
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 5
 * Date: March 3, 2019
 */

import java.util.Scanner;

/**
 * The purpose of this class: To process input of the user of the bank simulator
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class Assign1 
{
	/**
	 * Scanner instance shared by all the classes in the assignment
	 */
	public static Scanner input = new Scanner(System.in);
	
	/**
	 * Ensures the user enters a positive integer number
	 * @param inputMessage Message which prompts the user to enter a value
	 * @param errorMessage Message which informs the user about the incorrect input
	 * @return The validated positive integer 
	 */
	public static int inputValidInt(String inputMessage, String errorMessage)
	{
		int num = 0;
		do
		{
			boolean inputIncorrect = true;
			
			//Ensures the input is an integer
			do
			{
				System.out.println(inputMessage);
				
				if (Assign1.input.hasNextInt())
				{
					num = Assign1.input.nextInt();
					inputIncorrect = false;
				}
				else 
				{
					System.out.println(errorMessage);
				}
				Assign1.input.nextLine();
				
			} while(inputIncorrect);
			
			if (num <= 0)
				System.out.println(errorMessage);
			
		} while(num <= 0);
		
		return num;
	}
	
	/**
	 * Ensures the user enters a valid non-negative double
	 * @param inputMessage Message which prompts the user to enter a value
	 * @param errorMessage Message which informs the user about the incorrect input
	 * @return The validated non-negative double
	 */
	public static double inputValidDouble(String inputMessage, String errorMessage)
	{
		double num = 0;
		do
		{
			boolean inputIncorrect = true;
			
			//Ensures the input is a double
			do
			{
				System.out.println(inputMessage);
				
				if (Assign1.input.hasNextDouble())
				{
					num = Assign1.input.nextDouble();
					inputIncorrect = false;
				}
				else 
				{
					System.out.println(errorMessage);
				}
				Assign1.input.nextLine();
				
			} while(inputIncorrect);
			
			if (num < 0)
				System.out.println(errorMessage);
			
		} while(num < 0);
		
		return num;
	}
	
	/**
	 * Simulates managing Bank customers' Bank Accounts
	 * @param args UNUSED command line arguments
	 */
	public static void main(String[] args)
	{
		Bank bank = new Bank();
		boolean keepLooping = true;
		
		do
		{
			char charInput;
			String stringInput;
			
			System.out.println("a: Add new account \n" + 
							   "u: Update an account \n" + 
							   "d: Display an account \n" + 
							   "p: Print all accounts \n" +
							   "m: Run monthly update \n" +
							   "q: Quit \n" + 
							   "Enter your option : ");
			
			//Checks if the input is not empty and contains exactly 1 symbol
			stringInput = input.nextLine();
			if (!stringInput.equals("") && stringInput.length() == 1)
			{
				charInput = stringInput.toLowerCase().charAt(0);
				switch (charInput)
				{
					case 'a' : //Adds a new account
					{
						bank.addAccount();
						break;
					}
					case 'u' : //Updates (withdraws/deposits some amount) an account
					{
						bank.updateAccount();
						break;
					}
					case 'd' : //Displays information about the requested account
					{
						System.out.println(bank.displayAccount());
						break;
					}
					case 'p' : //Prints information about all accounts
						{
							bank.printAccountDetails();
							break;
						}
					case 'm' : //Processes a monthly update for all accounts
					{
						bank.monthlyUpdate();
						break;
					}
					case 'q' : //Terminates the program
						{
							keepLooping = false;
							break;
						}
				}
			}
		} while (keepLooping);
		
		input.close();
	}
}
