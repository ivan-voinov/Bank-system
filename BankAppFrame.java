
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JFrame;

/**
 * The purpose of this class: an abstract base class for the frames
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 **/
public abstract class BankAppFrame extends JFrame
{
	/**
	 * Assigns the window a new title
	 * @param title The title of the window
	 */
	public BankAppFrame(String title) 
	{
		super(title);
	}
	
	/**
	 * Updates the frame 
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	public abstract void loadFrame(Bank bank);
	
}
