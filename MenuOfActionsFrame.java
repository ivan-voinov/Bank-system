
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.awt.event.ActionEvent;

/**
 * The purpose of this class: To implement frame manager to set up and switch frames
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class MenuOfActionsFrame extends BankAppFrame
{
	private JLabel menuChoicesLabel;
	
	private JButton addAccountButton;
	private JButton readRecordsButton;
	private JButton updateButton;
	private JButton displayButton;
	private JButton printToScreenButton;
	private JButton printToFileButton;
	private JButton monthlyUpdateButton;
	private JButton quitButton;
	
	private FrameController controller;
	
	/**
	 * Initializes and sets up all the GUI
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 * @param controller Frame manager which is used to switch frames
	 */
	public MenuOfActionsFrame(Bank bank, FrameController controller)
	{
		super("Menu");
		
		
		this.controller = controller;
		
		
		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		
		
		menuChoicesLabel = new JLabel("Choose an action ", SwingConstants.CENTER);
		addAccountButton = new JButton("Add a new account");
		readRecordsButton = new JButton("Read the accounts' information from a file");
		updateButton = new JButton("Update the information about an account");
		displayButton = new JButton("Display the information about an account");
		printToScreenButton = new JButton("Print the information about the accounts to screen");
		printToFileButton = new JButton("Print the information about the accounts to file");
		monthlyUpdateButton = new JButton("Process a monthly update for all accounts");
		quitButton = new JButton("Quit");
		
		
		GridBagConstraints menuChoicesLabelC = new GridBagConstraints();
		GridBagConstraints addAccountButtonC = new GridBagConstraints();
		GridBagConstraints readRecordsButtonC = new GridBagConstraints();
		GridBagConstraints updateButtonC = new GridBagConstraints();
		GridBagConstraints displayButtonC = new GridBagConstraints();
		GridBagConstraints printToScreenButtonC = new GridBagConstraints();
		GridBagConstraints printToFileButtonC = new GridBagConstraints();
		GridBagConstraints monthlyUpdateButtonC = new GridBagConstraints();
		GridBagConstraints quitButtonC = new GridBagConstraints();
		
		menuChoicesLabelC.gridx = 0;
		menuChoicesLabelC.gridy = 0;
		menuChoicesLabelC.weightx = 0.5;
		
		addAccountButtonC.gridx = 0;
		addAccountButtonC.gridy = 1;
		addAccountButtonC.weightx = 0.5;
		
		readRecordsButtonC.gridx = 0;
		readRecordsButtonC.gridy = 2;
		readRecordsButtonC.weightx = 0.5;

		updateButtonC.gridx = 0;
		updateButtonC.gridy = 3;
		updateButtonC.weightx = 0.5;
		
		displayButtonC.gridx = 0;
		displayButtonC.gridy = 4;
		displayButtonC.weightx = 0.5;
		
		printToScreenButtonC.gridx = 0;
		printToScreenButtonC.gridy = 5;
		printToScreenButtonC.weightx = 0.5;
		
		printToFileButtonC.gridx = 0;
		printToFileButtonC.gridy = 6;
		printToFileButtonC.weightx = 0.5;
		
		monthlyUpdateButtonC.gridx = 0;
		monthlyUpdateButtonC.gridy = 7;
		monthlyUpdateButtonC.weightx = 0.5;
		
		quitButtonC.gridx = 0;
		quitButtonC.gridy = 8;
		quitButtonC.weightx = 0.5;
		
		
		addAccountButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Switches to the add account frame
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						MenuOfActionsFrame.this.setVisible(false);
						MenuOfActionsFrame.this.controller.showFrame(FrameNames.ADD_ACCOUNT);
					}
				});
		
		readRecordsButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Reads the information about the bank accounts from a file
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						try
						{
							bank.openInputFile();
							bank.readRecords();
							bank.closeInputFile();
							
							JOptionPane.showMessageDialog
							(MenuOfActionsFrame.this, "Accounts added successfully");
						}
						catch(IOException ioException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "Error opening file");
						}
						catch (NullPointerException nullPointerException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "Error: file was not initialized");
						}
						catch (NoSuchElementException elementException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "File improperly formed");
						}
						catch (IllegalStateException stateException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "Error reading from file");
						}
					}
				});
		
		updateButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Switches to the update account frame
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						MenuOfActionsFrame.this.setVisible(false);
						MenuOfActionsFrame.this.controller.showFrame(FrameNames.UPDATE_ACCOUNT);
					}
				});
		
		displayButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Switches to the display account frame
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						MenuOfActionsFrame.this.setVisible(false);
						MenuOfActionsFrame.this.controller.showFrame(FrameNames.DISPLAY_ACCOUNT);
					}
				});
		
		printToScreenButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Shows the print accounts frame
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						MenuOfActionsFrame.this.controller.showFrame(FrameNames.PRINT_ACCOUNTS);
						MenuOfActionsFrame.this.controller.
							getFrame(FrameNames.PRINT_ACCOUNTS).loadFrame(bank);
					}
				});
		
		printToFileButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Prints the information about the bank accounts to a file
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void
					actionPerformed(ActionEvent event) 
					{
						try
						{
							bank.openOutputFile();
							bank.printRecordsToFile();
							bank.closeOutputFile();
							
							JOptionPane.showMessageDialog
							(MenuOfActionsFrame.this, "Accounted printed successfully");
						}
						catch(SecurityException securityException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "Write permission denied");
						}
						catch (FileNotFoundException fileNotFoundException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "Error opening file: file not found");
						}
						catch (FormatterClosedException formatterClosedException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this,"Error opening file");
						}
						catch (NullPointerException nullPointerException)
						{
							JOptionPane.showMessageDialog
								(MenuOfActionsFrame.this, "Error: file was not initialized");
						}
					}
				});
		
		monthlyUpdateButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Shows the monthly update frame
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						MenuOfActionsFrame.this.controller.showFrame(FrameNames.MONTHLY_UPDATE);
						MenuOfActionsFrame.this.controller.
							getFrame(FrameNames.MONTHLY_UPDATE).loadFrame(bank);
					}
				});
		
		quitButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Quits the application
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						if (JOptionPane.showConfirmDialog
						(MenuOfActionsFrame.this, "Are you sure you want to quit?") == 0)
							MenuOfActionsFrame.this.controller.disposeAllFrames();
					}
				});
		
		getContentPane().add(menuChoicesLabel, menuChoicesLabelC);
		getContentPane().add(addAccountButton, addAccountButtonC);
		getContentPane().add(readRecordsButton, readRecordsButtonC);
		getContentPane().add(updateButton, updateButtonC);
		getContentPane().add(displayButton, displayButtonC);
		getContentPane().add(printToScreenButton, printToScreenButtonC);
		getContentPane().add(printToFileButton, printToFileButtonC);
		getContentPane().add(monthlyUpdateButton, monthlyUpdateButtonC);
		getContentPane().add(quitButton, quitButtonC);
	}

	/**
	 * Updates the frame 
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	@Override
	public void loadFrame(Bank bank) 
	{
		// TODO Auto-generated method stub
		
	}
}
