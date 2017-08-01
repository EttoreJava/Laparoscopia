package userinterface.userinputshandlers;

import content.Arm;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import userinterface.graphichandler.Axis;

/**
 * @author Michele Franceschetti
 */

public class KeyboardInputsHandler
{
	private static final int DEFAULT_VALUE = 0;
	
	//Left arm
	private int leftXAxis;
	private int leftYAxis;
	private int leftZAxis;
	
	//Right arm
	private int rightXAxis;
	private int rightYAxis;
	private int rightZAxis;
	
	//Other commands
	
	private boolean escape;
	private boolean reset;

	/**
	 * Default constructor.
	 */
	public KeyboardInputsHandler()
	{
		reset();
	}
	
	/**
	 * Returns the left x axis.
	 * 
	 * @return The left x axis.
	 */
	public int getLeftXAxis() 
	{
		return leftXAxis;
	}

	/**
	 * Returns the left y axis.
	 * 
	 * @return The left y axis.
	 */
	public int getLeftYAxis() 
	{
		return leftYAxis;
	}

	/**
	 * Returns the left z axis.
	 * 
	 * @return The left z axis.
	 */
	public int getLeftZAxis() 
	{
		return leftZAxis;
	}

	/**
	 * Returns the right x axis.
	 * 
	 * @return The right x axis.
	 */
	public int getRightXAxis()
	{
		return rightXAxis;
	}

	/**
	 * Returns the right y axis.
	 * 
	 * @return The right y axis.
	 */
	public int getRightYAxis() 
	{
		return rightYAxis;
	}

	/**
	 * Returns the right z axis.
	 * 
	 * @return The right z axis.
	 */
	public int getRightZAxis() 
	{
		return rightZAxis;
	}

	/**
	 * Returns whether the 'escape' key is pressed or not.
	 * 
	 * @return True if the 'escape' key is pressed, false otherwise.
	 */
	public boolean isEscape()
	{
		return escape;
	}
	
	/**
	 * Returns whether the reset key is pressed or not.
	 * 
	 * @return True if the reset key is pressed, false otherwise.
	 */
	public boolean isReset() 
	{
		return reset;
	}

	/**
	 * Handles the keyboard's events.
	 * 
	 * @param scene The 3D scene.
	 * @param leftArm The left arm.
	 * @param rightArm The right arm.
	 * @param axis The 3D axis mesh.
	 */
	public void handleKeyboard(Scene scene, Arm leftArm, Arm rightArm, Axis axis) 
	{
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	
        	/**
        	 * Sets the commands.
        	 * 
        	 * @param event The keyboard's event.
        	 */
            @Override
            public void handle(KeyEvent event) 
            {
                switch (event.getCode()) {
                    case ESCAPE:
                    	escape = true;
                    	Stage window = (Stage)scene.getWindow();
                    	window.close();
                        break;
                    case R:
                    	reset = true;
                        break;
                    case X:
                    	axis.switchVisibility();
                    	break;
                    case W:
                    	leftYAxis = 1;
                        break;
                    case A:
                    	leftXAxis = -1;
                        break;
                    case S:
                    	leftYAxis = -1;
                        break;
                    case D:
                    	leftXAxis = 1;
                        break;
                    case Q:
                    	leftZAxis = -1;
                    	break;
                    case E:
                    	leftZAxis = 1;
                    	break;
                    case I:
                    	rightYAxis = 1;
                        break;
                    case J:
                    	rightXAxis = -1;
                        break;
                    case K:
                    	rightYAxis = -1;
                        break;
                    case L:
                    	rightXAxis = 1;
                        break;
                    case U:
                    	rightZAxis = -1;
                    	break;
                    case O:
                    	rightZAxis = 1;
                    	break;
                    default:
                    	event.consume();
                    	reset();
                    	break;
                }
                
                leftArm.moveTarget(leftXAxis, leftYAxis, leftZAxis);
                rightArm.moveTarget(rightXAxis, rightYAxis, rightZAxis);
                
                reset();
            }
        });
    }
	
	/*
	 * Resets all the attributes to the DEFAULT_VALUE.
	 */
	private void reset()
	{
		leftXAxis = DEFAULT_VALUE;
		leftYAxis = DEFAULT_VALUE;
		leftZAxis = DEFAULT_VALUE;
		
		rightXAxis = DEFAULT_VALUE;
		rightYAxis = DEFAULT_VALUE;
		rightZAxis = DEFAULT_VALUE;
		
		escape = false;
		reset = false;
	}
}
