
/*
 * Name: Ivan Voinov
 * Student id: 040935680
 * Course & section: CST8132 312
 * Assignment: Lab 9
 * Date: April 18, 2019
 */


/**
 * The purpose of this class: To launch the bank application
 * @author Ivan Voinov
 * @version 1.0
 * @since 1.8.0_201
 */
public class Assign1 
{	
	/**
	 * Initializes the frame controller, the bank, and sets up the frames
	 * @param args UNUSED command line arguments
	 */
	public static void main(String[] args)
	{		
		Bank bank = new Bank();
		FrameController controller = new FrameController();
		controller.initializeFrames(bank);
		controller.setFramesSize(800, 600);
		controller.showFrame(FrameNames.MENU);
	}
}
