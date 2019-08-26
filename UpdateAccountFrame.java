
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The purpose of this class: to implement GUI to update a bank account
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 **/
public class UpdateAccountFrame extends BankAppFrame
{
	private JLabel transactionTypeLabel;
	private JLabel amountLabel;
	private JLabel accNumberLabel;
	
	private JTextField amountTextField;
	private JTextField accountNumberTextField;
	
	private ButtonGroup transactionTypeGroup;
	
	private JRadioButton depositRadioButton;
	private JRadioButton withdrawRadioButton;
	
	private JButton backButton;
	private JButton processTransactionButton;
	
	private FrameController controller;
	
	/**
	 * Initializes and sets up all the GUI
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 * @param controller Frame manager which is used to switch frames
	 */
	public UpdateAccountFrame(Bank bank, FrameController controller)
	{
		super("Update account");
		
		
		this.controller = controller;
		
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		
		transactionTypeLabel = new JLabel("Type of transaction");
		amountLabel = new JLabel("Amount of money");
		accNumberLabel = new JLabel("Account number");
		amountTextField = new JTextField();
		accountNumberTextField = new JTextField();
		transactionTypeGroup = new ButtonGroup();
		depositRadioButton = new JRadioButton("Deposit", true);
		withdrawRadioButton = new JRadioButton("Withdraw", false);
		backButton = new JButton("Back");
		processTransactionButton = new JButton("Process transaction");
		
		
		GridBagConstraints transactionTypeLabelC = new GridBagConstraints();
		GridBagConstraints depositRadioButtonC = new GridBagConstraints();
		GridBagConstraints withdrawRadioButtonC = new GridBagConstraints();
		GridBagConstraints accountNumberLabelC = new GridBagConstraints();
		GridBagConstraints accountNumberTextFieldC = new GridBagConstraints();
		GridBagConstraints amountLabelC = new GridBagConstraints();
		GridBagConstraints amountTextFieldC = new GridBagConstraints();
		GridBagConstraints backButtonC = new GridBagConstraints();
		GridBagConstraints createAccountButtonC = new GridBagConstraints();
		
		
		processTransactionButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Processes the transaction or shows an error message
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						try
						{
							long accountNumber = Long.parseLong(accountNumberTextField.getText());
							long amount = Long.parseLong(amountTextField.getText());
							
							if ((Long.toString(accountNumber).length() > 8) || (accountNumber < 0))
								throw new NumberFormatException();
							
							if (amount < 0)
								throw new NumberFormatException();
								
							if (depositRadioButton.isSelected())
							{
								if (!bank.deposit(accountNumber, amount))
									JOptionPane.showMessageDialog(UpdateAccountFrame.this,
										"Account not found");
								else
									JOptionPane.showMessageDialog
										(UpdateAccountFrame.this, "Transaction successful");
							}
							else
							{
								if (!bank.withdraw(accountNumber, amount))
									JOptionPane.showMessageDialog(UpdateAccountFrame.this,
										"Account not found");
								else
									JOptionPane.showMessageDialog
										(UpdateAccountFrame.this, "Transaction successful");
							}
						}
						catch (java.lang.NumberFormatException numberFormatException)
						{
							JOptionPane.showMessageDialog(UpdateAccountFrame.this,
									"The input is incorrect");
						}
						catch (TransactionIllegalArgumentException transactionIllegalArgumentException)
						{
							JOptionPane.showMessageDialog
								(UpdateAccountFrame.this, transactionIllegalArgumentException);
						}
					}
				});
		
		backButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Clears all the text fields and switches to the main menu frame
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						clearTextFields();
						UpdateAccountFrame.this.setVisible(false);
						UpdateAccountFrame.this.controller.showFrame(FrameNames.MENU);
					}
				});
		
		
		transactionTypeLabelC.gridx = 0;
		transactionTypeLabelC.gridy = 0;
		transactionTypeLabelC.gridwidth = 1;
		transactionTypeLabelC.gridheight = 1;
		transactionTypeLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		depositRadioButtonC.gridx = 0;
		depositRadioButtonC.gridy = 1;
		depositRadioButtonC.gridwidth = 1;
		depositRadioButtonC.gridheight = 1;
		depositRadioButtonC.fill = GridBagConstraints.HORIZONTAL;
		
		withdrawRadioButtonC.gridx = 0;
		withdrawRadioButtonC.gridy = 2;
		withdrawRadioButtonC.gridwidth = 1;
		withdrawRadioButtonC.gridheight = 1;
		withdrawRadioButtonC.fill = GridBagConstraints.HORIZONTAL;
		
		accountNumberLabelC.gridx = 0;
		accountNumberLabelC.gridy = 3;
		accountNumberLabelC.gridwidth = 1;
		accountNumberLabelC.gridheight = 1;
		accountNumberLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		accountNumberTextFieldC.gridx = 0;
		accountNumberTextFieldC.gridy = 4;
		accountNumberTextFieldC.gridwidth = 1;
		accountNumberTextFieldC.gridheight = 1;
		accountNumberTextFieldC.fill = GridBagConstraints.HORIZONTAL;
		
		amountLabelC.gridx = 0;
		amountLabelC.gridy = 5;
		amountLabelC.gridwidth = 1;
		amountLabelC.gridheight = 1;
		amountLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		amountTextFieldC.gridx = 0;
		amountTextFieldC.gridy = 6;
		amountTextFieldC.gridwidth = 1;
		amountTextFieldC.gridheight = 1;
		amountTextFieldC.fill = GridBagConstraints.HORIZONTAL;
		
		backButtonC.gridx = 0;
		backButtonC.gridy = 7;
		backButtonC.gridwidth = 1;
		backButtonC.gridheight = 1;
		backButtonC.fill = GridBagConstraints.HORIZONTAL;
		
		createAccountButtonC.gridx = 0;
		createAccountButtonC.gridy = 8;
		createAccountButtonC.gridwidth = 1;
		createAccountButtonC.gridheight = 1;
		createAccountButtonC.fill = GridBagConstraints.HORIZONTAL;
		
		
		transactionTypeGroup.add(depositRadioButton);
		transactionTypeGroup.add(withdrawRadioButton);
		
		
		add(transactionTypeLabel, transactionTypeLabelC);
		add(depositRadioButton, depositRadioButtonC);
		add(withdrawRadioButton, withdrawRadioButtonC);
		add(accNumberLabel, accountNumberLabelC);
		add(accountNumberTextField, accountNumberTextFieldC);
		add(amountLabel, amountLabelC);
		add(amountTextField, amountTextFieldC);		
		add(backButton, backButtonC);
		add(processTransactionButton, createAccountButtonC);
	}
	
	/**
	 * Clears the text of all text fields
	 */
	public void clearTextFields()
	{
		amountTextField.setText("");
		accountNumberTextField.setText("");
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
