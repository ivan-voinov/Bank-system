
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

import java.util.ArrayList;

import java.util.regex.Pattern;


/**
 * The purpose of this class: to implement GUI to add a new bank account
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class AddAccountFrame extends BankAppFrame
{
	private JTextField accNumberTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField balanceTextField;
	private JTextField minBalanceTextField;

	private ButtonGroup typeOfAccountGroup;

	private JRadioButton chequingAccountRadioButton;
	private JRadioButton savingsAccountRadioButton;

	private JButton backButton;
	private JButton createAccountButton;

	private JLabel accNumberLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	private JLabel balanceLabel;
	private JLabel minBalanceLabel;
	private JLabel typeOfAccountLabel;
	
	private ArrayList<JLabel> validationLabels;

	private FrameController controller;

	/**
	 * Initializes and sets up all the GUI
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 * @param controller Frame manager which is used to switch frames
	 */
	public AddAccountFrame(Bank bank, FrameController controller) 
	{
		super("New account");

		
		this.controller = controller;

		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		
		validationLabels = new ArrayList<JLabel>();
		
		
		accNumberTextField = new JTextField();
		firstNameTextField = new JTextField();
		lastNameTextField = new JTextField();
		emailTextField = new JTextField();
		balanceTextField = new JTextField();
		minBalanceTextField = new JTextField();
		typeOfAccountGroup = new ButtonGroup();
		chequingAccountRadioButton = new JRadioButton("Chequing account", true);
		savingsAccountRadioButton = new JRadioButton("Savings account");
		backButton = new JButton("Back");
		createAccountButton = new JButton("Create account");
		accNumberLabel = new JLabel("Number of account");
		firstNameLabel = new JLabel("First name");
		lastNameLabel = new JLabel("Last name");
		emailLabel = new JLabel("Email");
		balanceLabel = new JLabel("Balance");
		minBalanceLabel = new JLabel("Minimum balance");
		typeOfAccountLabel = new JLabel("Type of account");
		validationLabels.add(new JLabel("Must be a whole unique non-negative number up to 8 characters"));
		validationLabels.add(new JLabel("Must have a valid email format"));
		validationLabels.add(new JLabel("Must be a whole non-negative number"));
		validationLabels.add(new JLabel("Must be a whole non-negative number"));

		
		GridBagConstraints accountNumberTextFieldC = new GridBagConstraints();
		GridBagConstraints firstNameTextFieldC = new GridBagConstraints();
		GridBagConstraints lastNameTextFieldC = new GridBagConstraints();
		GridBagConstraints emailTextFieldC = new GridBagConstraints();
		GridBagConstraints balanceTextFieldC = new GridBagConstraints();
		GridBagConstraints minBalanceTextFieldC = new GridBagConstraints();
		GridBagConstraints chequingAccountRadioButtonC = new GridBagConstraints();
		GridBagConstraints savingsAccountRadioButtonC = new GridBagConstraints();
		GridBagConstraints backButtonC = new GridBagConstraints();
		GridBagConstraints createAccountButtonC = new GridBagConstraints();
		GridBagConstraints accNumberLabelC = new GridBagConstraints();
		GridBagConstraints firstNameLabelC = new GridBagConstraints();
		GridBagConstraints lastNameLabelC = new GridBagConstraints();
		GridBagConstraints emailLabelC = new GridBagConstraints();
		GridBagConstraints balanceLabelC = new GridBagConstraints();
		GridBagConstraints minBalanceLabelC = new GridBagConstraints();
		GridBagConstraints typeOfAccountLabelC = new GridBagConstraints();
		
		
		GridBagConstraints accNumberValidationLabelC = new GridBagConstraints();
		GridBagConstraints emailValidationLabelC = new GridBagConstraints();
		GridBagConstraints balanceValidationLabelC = new GridBagConstraints();
		GridBagConstraints minBalanceValidationLabelC = new GridBagConstraints();
		
		
		minBalanceTextField.setEditable(false);

		
		savingsAccountRadioButton.addActionListener(new ActionListener()
		{
			/**
			 * Makes minimum balance text field editable
			 * @param event Event triggered when the radio button is selected
			 */
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				minBalanceTextField.setEditable(true);
			}
		});

		chequingAccountRadioButton.addActionListener(new ActionListener()
		{
			/**
			 * Clears the minimum balance text field and makes it non-editable
			 * @param event Event triggered when the radio button is selected
			 */
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				minBalanceTextField.setText("");
				minBalanceTextField.setEditable(false);
			}
		});

		createAccountButton.addActionListener(new ActionListener()
		{
			/**
			 * Creates a new bank account or shows an error if the input is incorrect
			 * @param event Event triggered when the button is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				try 
				{
					String firstName = firstNameTextField.getText();
					String lastName = lastNameTextField.getText();
					String email = emailTextField.getText();
					double balance = Double.parseDouble(balanceTextField.getText());
					long accNumber = Long.parseLong(accNumberTextField.getText());
					
					//Validate account number (has to be up to 8 characters, non-negative, unique)
					if ((Long.toString(accNumber).length() > 8) || (accNumber < 0) || 
							!bank.numAccountIsUnique(accNumber)) 
					{
						validationLabels.get(0).setVisible(true);
						throw new NumberFormatException();
					}
					
					//Validate email
					if (!Pattern.matches(".+@.+\\..+", email))
					{
						validationLabels.get(1).setVisible(true);
						throw new NumberFormatException();
					}
					
					//Validate balance
					if (balance < 0)
					{
						validationLabels.get(2).setVisible(true);
						throw new NumberFormatException();
					}
					
					//Create client
					Client client = new Client(firstName, lastName, email);
					
					//Create chequing account if there is enough room
					if (chequingAccountRadioButton.isSelected())
					{
						ChequingAccount chequingAccount = 
								new ChequingAccount(client, balance, accNumber);

						if (bank.addAccount(chequingAccount))
							JOptionPane.showMessageDialog
								(AddAccountFrame.this, "Chequing account created successfully");
						else
							JOptionPane.showMessageDialog(AddAccountFrame.this, "Error: no room");
					}
					
					//Create savings account if there is enough room
					else 
					{
						double minBalance = Double.parseDouble(minBalanceTextField.getText());
						
						if (minBalance < 0)
						{
							validationLabels.get(3).setVisible(true);
							throw new NumberFormatException();
						}

						SavingsAccount savingsAccount = 
								new SavingsAccount(client, balance, accNumber, minBalance);

						if (bank.addAccount(savingsAccount))
							JOptionPane.showMessageDialog
								(AddAccountFrame.this, "Savings account added successfully");
						else
							JOptionPane.showMessageDialog(AddAccountFrame.this, "Error: no room");
					}
					
					clearTextFields();
				} 
				catch (java.lang.NumberFormatException numberFormatException) 
				{
					JOptionPane.showMessageDialog(AddAccountFrame.this, "The input is incorrect");
				}
			}
		});
		
		/**
		 * Clears all the text fields and switches to the main menu frame
		 * @param event Event triggered when the button is clicked
		 */
		backButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				clearTextFields();
				AddAccountFrame.this.setVisible(false);
				AddAccountFrame.this.controller.showFrame(FrameNames.MENU);
			}
		});
		
		//Set up the gridbag constraints
		accNumberLabelC.gridx = 1;
		accNumberLabelC.gridy = 0;
		accNumberLabelC.gridwidth = 1;
		accNumberLabelC.gridheight = 1;
		accNumberLabelC.fill = GridBagConstraints.HORIZONTAL;

		accountNumberTextFieldC.gridx = 1;
		accountNumberTextFieldC.gridy = 1;
		accountNumberTextFieldC.gridwidth = 1;
		accountNumberTextFieldC.gridheight = 1;
		accountNumberTextFieldC.fill = GridBagConstraints.HORIZONTAL;

		firstNameLabelC.gridx = 1;
		firstNameLabelC.gridy = 2;
		firstNameLabelC.gridwidth = 1;
		firstNameLabelC.gridheight = 1;
		firstNameLabelC.fill = GridBagConstraints.HORIZONTAL;

		firstNameTextFieldC.gridx = 1;
		firstNameTextFieldC.gridy = 3;
		firstNameTextFieldC.gridwidth = 1;
		firstNameTextFieldC.gridheight = 1;
		firstNameTextFieldC.fill = GridBagConstraints.HORIZONTAL;

		lastNameLabelC.gridx = 1;
		lastNameLabelC.gridy = 4;
		lastNameLabelC.gridwidth = 1;
		lastNameLabelC.gridheight = 1;
		lastNameLabelC.fill = GridBagConstraints.HORIZONTAL;

		lastNameTextFieldC.gridx = 1;
		lastNameTextFieldC.gridy = 5;
		lastNameTextFieldC.gridwidth = 1;
		lastNameTextFieldC.gridheight = 1;
		lastNameTextFieldC.fill = GridBagConstraints.HORIZONTAL;

		emailLabelC.gridx = 1;
		emailLabelC.gridy = 6;
		emailLabelC.gridwidth = 1;
		emailLabelC.gridheight = 1;
		emailLabelC.fill = GridBagConstraints.HORIZONTAL;

		emailTextFieldC.gridx = 1;
		emailTextFieldC.gridy = 7;
		emailTextFieldC.gridwidth = 1;
		emailTextFieldC.gridheight = 1;
		emailTextFieldC.fill = GridBagConstraints.HORIZONTAL;

		balanceLabelC.gridx = 1;
		balanceLabelC.gridy = 8;
		balanceLabelC.gridwidth = 1;
		balanceLabelC.gridheight = 1;
		balanceLabelC.fill = GridBagConstraints.HORIZONTAL;

		balanceTextFieldC.gridx = 1;
		balanceTextFieldC.gridy = 9;
		balanceTextFieldC.gridwidth = 1;
		balanceTextFieldC.gridheight = 1;
		balanceTextFieldC.fill = GridBagConstraints.HORIZONTAL;

		typeOfAccountLabelC.gridx = 1;
		typeOfAccountLabelC.gridy = 10;
		typeOfAccountLabelC.gridwidth = 1;
		typeOfAccountLabelC.gridheight = 1;
		typeOfAccountLabelC.fill = GridBagConstraints.HORIZONTAL;

		chequingAccountRadioButtonC.gridx = 1;
		chequingAccountRadioButtonC.gridy = 11;
		chequingAccountRadioButtonC.gridwidth = 1;
		chequingAccountRadioButtonC.gridheight = 1;
		chequingAccountRadioButtonC.fill = GridBagConstraints.HORIZONTAL;

		savingsAccountRadioButtonC.gridx = 1;
		savingsAccountRadioButtonC.gridy = 12;
		savingsAccountRadioButtonC.gridwidth = 1;
		savingsAccountRadioButtonC.gridheight = 1;
		savingsAccountRadioButtonC.fill = GridBagConstraints.HORIZONTAL;

		minBalanceLabelC.gridx = 1;
		minBalanceLabelC.gridy = 13;
		minBalanceLabelC.gridwidth = 1;
		minBalanceLabelC.gridheight = 1;
		minBalanceLabelC.fill = GridBagConstraints.HORIZONTAL;

		minBalanceTextFieldC.gridx = 1;
		minBalanceTextFieldC.gridy = 14;
		minBalanceTextFieldC.gridwidth = 1;
		minBalanceTextFieldC.gridheight = 1;
		minBalanceTextFieldC.fill = GridBagConstraints.HORIZONTAL;

		backButtonC.gridx = 0;
		backButtonC.gridy = 15;
		backButtonC.gridwidth = 1;
		backButtonC.gridheight = 1;
		backButtonC.fill = GridBagConstraints.HORIZONTAL;
		backButtonC.anchor = GridBagConstraints.LAST_LINE_START;

		createAccountButtonC.gridx = 2;
		createAccountButtonC.gridy = 15;
		createAccountButtonC.gridwidth = 1;
		createAccountButtonC.gridheight = 1;
		createAccountButtonC.fill = GridBagConstraints.HORIZONTAL;
		createAccountButtonC.anchor = GridBagConstraints.LAST_LINE_END;
		
		accNumberValidationLabelC.gridx = 2;
		accNumberValidationLabelC.gridy = 1;
		accNumberValidationLabelC.gridwidth = 1;
		accNumberValidationLabelC.gridheight = 1;
		accNumberValidationLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		emailValidationLabelC.gridx = 2;
		emailValidationLabelC.gridy = 7;
		emailValidationLabelC.gridwidth = 1;
		emailValidationLabelC.gridheight = 1;
		emailValidationLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		balanceValidationLabelC.gridx = 2;
		balanceValidationLabelC.gridy = 9;
		balanceValidationLabelC.gridwidth = 1;
		balanceValidationLabelC.gridheight = 1;
		balanceValidationLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		minBalanceValidationLabelC.gridx = 2;
		minBalanceValidationLabelC.gridy = 14;
		minBalanceValidationLabelC.gridwidth = 1;
		minBalanceValidationLabelC.gridheight = 1;
		minBalanceValidationLabelC.fill = GridBagConstraints.HORIZONTAL;

		
		//Add radio buttons to the group 
		typeOfAccountGroup.add(chequingAccountRadioButton);
		typeOfAccountGroup.add(savingsAccountRadioButton);

		
		//Add the components to the frame
		add(accNumberTextField, accountNumberTextFieldC);
		add(firstNameTextField, firstNameTextFieldC);
		add(lastNameTextField, lastNameTextFieldC);
		add(emailTextField, emailTextFieldC);
		add(balanceTextField, balanceTextFieldC);
		add(minBalanceTextField, minBalanceTextFieldC);
		add(chequingAccountRadioButton, chequingAccountRadioButtonC);
		add(savingsAccountRadioButton, savingsAccountRadioButtonC);
		add(backButton, backButtonC);
		add(createAccountButton, createAccountButtonC);
		add(accNumberLabel, accNumberLabelC);
		add(firstNameLabel, firstNameLabelC);
		add(lastNameLabel, lastNameLabelC);
		add(emailLabel, emailLabelC);
		add(balanceLabel, balanceLabelC);
		add(minBalanceLabel, minBalanceLabelC);
		add(typeOfAccountLabel, typeOfAccountLabelC);
		add(validationLabels.get(0), accNumberValidationLabelC);
		add(validationLabels.get(1), emailValidationLabelC);
		add(validationLabels.get(2), balanceValidationLabelC);
		add(validationLabels.get(3), minBalanceValidationLabelC);
	}
	
	/**
	 * Clears the text of all text fields
	 */
	public void clearTextFields()
	{
		minBalanceTextField.setText("");
		accNumberTextField.setText("");
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		emailTextField.setText("");
		balanceTextField.setText("");
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
