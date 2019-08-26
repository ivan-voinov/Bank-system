
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The purpose of this class: to show the results of the monthly account update in a separate window
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class MonthlyUpdateFrame extends BankAppFrame
{
	private JTextArea monthlyUpdateResultsTextArea;
	private JScrollPane scrollPane;
	
	/**
	 * Initializes and sets up all the GUI
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	public MonthlyUpdateFrame(Bank bank)
	{
		super("Monthly update");
		
		monthlyUpdateResultsTextArea = new JTextArea(30, 70);
		scrollPane = new JScrollPane(monthlyUpdateResultsTextArea,
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		monthlyUpdateResultsTextArea.setEditable(false);
		
		add(scrollPane);
	}
	
	/**
	 * Updates the frame 
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	@Override
	public void loadFrame(Bank bank)
	{
		monthlyUpdateResultsTextArea.setText(bank.processMonthlyUpdate().toString());
	}
}
