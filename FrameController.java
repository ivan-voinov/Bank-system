
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */

import javax.swing.JFrame;

import java.awt.Frame;
import java.util.ArrayList;

/**
 * The purpose of this class: To implement frame manager to set up and switch frames
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class FrameController 
{	
	private ArrayList<BankAppFrame> frames;
	
	/**
	 * Initializes all the frames and makes them invisible
	 * @param bank Bank object which is used to add data to or to retrieve data from
	 */
	public void initializeFrames(Bank bank)
	{
		frames = new ArrayList<BankAppFrame>();
		
		//Add all the frames
		frames.add(new MenuOfActionsFrame(bank, this));
		frames.add(new AddAccountFrame(bank, this));
		frames.add(new UpdateAccountFrame(bank, this));
		frames.add(new PrintAccountsFrame(bank));
		frames.add(new DisplayAccountFrame(bank, this));
		frames.add(new MonthlyUpdateFrame(bank));
		
		//Make all the frames invisible
		for (Frame frame : frames)
			frame.setVisible(false);
		
		//Set the default close operation
		frames.get(FrameNames.MENU.getIndex()).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.get(FrameNames.ADD_ACCOUNT.getIndex()).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.get(FrameNames.UPDATE_ACCOUNT.getIndex()).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.get(FrameNames.PRINT_ACCOUNTS.getIndex()).setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frames.get(FrameNames.DISPLAY_ACCOUNT.getIndex()).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.get(FrameNames.MONTHLY_UPDATE.getIndex()).setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	
	/**
	 * Sets the width and height of all frames to specified values
	 * @param width The width of the frames
	 * @param height The height of the frames
	 */
	public void setFramesSize(int width, int height)
	{
		for (JFrame frame : frames)
			frame.setSize(width, height);
	}
	
	/**
	 * Makes a specified frame visible
	 * @param frameName The name of the frame to make visible
	 */
	public void showFrame(FrameNames frameName)
	{
		frames.get(frameName.getIndex()).setVisible(true);
	}
	
	/**
	 * Returns a specified frame
	 * @param frameName The name of the frame to make visible
	 * @return The abstract base class for the frame
	 */
	public BankAppFrame getFrame(FrameNames frameName)
	{
		return frames.get(frameName.getIndex());
	}
	
	/**
	 * Disposes all the frames
	 */
	public void disposeAllFrames()
	{
		for (JFrame frame : frames)
			frame.dispose();
	}
}
