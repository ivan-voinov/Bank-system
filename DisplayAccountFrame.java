
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

/**
 * The purpose of this class: To implement GUI 
 * to display the information about the requested bank account
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class DisplayAccountFrame extends BankAppFrame
{
	private FrameController controller;
	
	private JTextField accountNumberTextField;
	private JTextField accountInformationTextField;
	
	private JLabel accountNumberLabel;
	private JLabel accountInformationLabel;
	
	private JButton backButton;
	private JButton displayAccountButton;
	
	/**
	 * Initializes and sets up all the GUI
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 * @param controller Frame manager which is used to switch frames
	 */
	public DisplayAccountFrame(Bank bank, FrameController controller)
	{
		super("Frame display");
		
		
		this.controller = controller;
		
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		
		accountNumberLabel = new JLabel("Account number");
		accountNumberTextField = new JTextField();
		accountInformationLabel = new JLabel("Account information");
		accountInformationTextField = new JTextField();
		backButton = new JButton("Back");
		displayAccountButton = new JButton("Display");
		
		
		accountInformationTextField.setEditable(false);
		
		
		displayAccountButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Displays the information about the account or 
					 * the error message if the input is incorrect or account is not found
					 * @param event Event triggered when the button is clicked
					 */
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						try
						{
							long accountNumber = Long.parseLong(accountNumberTextField.getText());
							
							if ((Long.toString(accountNumber).length() > 8) || (accountNumber < 0))
								throw new NumberFormatException();
							
							accountInformationTextField.setText(bank.displayAccount(accountNumber));
						}
						catch(java.lang.NumberFormatException numberFormatException)
						{
							JOptionPane.showMessageDialog
								(DisplayAccountFrame.this, "The input is incorrect");
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
						DisplayAccountFrame.this.setVisible(false);
						DisplayAccountFrame.this.controller.showFrame(FrameNames.MENU);
					}
				});
		
		
		GridBagConstraints accountNumberLabelC = new GridBagConstraints();
		GridBagConstraints accountNumberTextFieldC = new GridBagConstraints();
		GridBagConstraints accountInformationLabelC = new GridBagConstraints();
		GridBagConstraints accountInformationTextFieldC = new GridBagConstraints();
		GridBagConstraints backButtonC = new GridBagConstraints();
		GridBagConstraints displayAccountButtonC = new GridBagConstraints();
		
		
		//Set up the gridbag constraints
		accountNumberLabelC.gridx = 1;
		accountNumberLabelC.gridy = 0;
		accountNumberLabelC.gridwidth = 1;
		accountNumberLabelC.gridheight = 1;
		accountNumberLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		accountNumberTextFieldC.gridx = 1;
		accountNumberTextFieldC.gridy = 1;
		accountNumberTextFieldC.gridwidth = 1;
		accountNumberTextFieldC.gridheight = 1;
		accountNumberTextFieldC.fill = GridBagConstraints.HORIZONTAL;
		
		accountInformationLabelC.gridx = 1;
		accountInformationLabelC.gridy = 2;
		accountInformationLabelC.gridwidth = 1;
		accountInformationLabelC.gridheight = 1;
		accountInformationLabelC.fill = GridBagConstraints.HORIZONTAL;
		
		accountInformationTextFieldC.gridx = 1;
		accountInformationTextFieldC.gridy = 3;
		accountInformationTextFieldC.gridwidth = 1;
		accountInformationTextFieldC.weightx = 0.5;
		accountInformationTextFieldC.gridheight = 1;
		accountInformationTextFieldC.fill = GridBagConstraints.HORIZONTAL;
		
		backButtonC.gridx = 0;
		backButtonC.gridy = 4;
		backButtonC.gridwidth = 1;
		backButtonC.gridheight = 1;
		backButtonC.fill = GridBagConstraints.HORIZONTAL;
		
		displayAccountButtonC.gridx = 2;
		displayAccountButtonC.gridy = 4;
		displayAccountButtonC.gridwidth = 1;
		displayAccountButtonC.gridheight = 1;
		displayAccountButtonC.fill = GridBagConstraints.HORIZONTAL;
		
		
		//Add the components to the frame
		add(accountNumberLabel, accountNumberLabelC);
		add(accountNumberTextField, accountNumberTextFieldC);
		add(accountInformationLabel, accountInformationLabelC);
		add(accountInformationTextField, accountInformationTextFieldC);
		add(backButton, backButtonC);
		add(displayAccountButton, displayAccountButtonC);
	}
	
	/**
	 * Clears the text of all text fields
	 */
	public void clearTextFields()
	{
		accountInformationTextField.setText("");
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
