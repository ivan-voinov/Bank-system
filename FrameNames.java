
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

/**
 * @author Ivan
 *
 */
public enum FrameNames
	{
		/**
		 * Frame implementing main menu
		 */
		MENU(0),
		
		
		/**
		 * Frame implementing adding a new account
		 */
		ADD_ACCOUNT(1),
		
		
		/**
		 * Frame implementing updating an account
		 */
		UPDATE_ACCOUNT(2),
		
		
		/**
		 * Frame implementing printing of all accounts
		 */
		PRINT_ACCOUNTS(3),
		
		
		/**
		 * Frame implementing displaying an account
		 */
		DISPLAY_ACCOUNT(4),
		
		
		/**
		 * Frame implementing monthly update
		 */
		MONTHLY_UPDATE(5);
		
		private final int INDEX;
		
		private FrameNames(int index)
		{
			this.INDEX = index;
		}
		
		/**
		 * Returns the index of the frame
		 * @return the index of the frame
		 */
		public int getIndex()
		{
			return INDEX;
		}
	}