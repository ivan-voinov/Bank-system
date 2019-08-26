
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * The purpose of this class: to show the information about all the bank accounts in a separate window
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class PrintAccountsFrame extends BankAppFrame
{
	private JTextArea accountsInformationTextArea;
	private JScrollPane scrollPane;
	
	/**
	 * Initializes and sets up all the GUI
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	public PrintAccountsFrame(Bank bank)
	{
		super("Information about the accounts");
		
		accountsInformationTextArea = new JTextArea(30, 70);
		scrollPane = new JScrollPane(accountsInformationTextArea,
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		accountsInformationTextArea.setEditable(false);
		
		add(scrollPane);
	}
	
	/**
	 * Updates the frame 
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	@Override
	public void loadFrame(Bank bank)
	{
		accountsInformationTextArea.setText(bank.getAccountDetails().toString());
	}
}
