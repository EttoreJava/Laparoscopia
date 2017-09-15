package userinterface.userinputshandlers;

import java.util.logging.Level;

import application.Main;
import application.Simulation;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import userinterface.graphichandler.View;

/**
 * @author Michele Franceschetti
 */

public class MouseInputsHandler
{
	//Mouse buttons
	
	private boolean leftMouseButton;
	private boolean rightMouseButton;
	
	//Mouse position
	
	private double[] mousePosition = new double[2];
	private double[] lastMousePosition = new double[2];
	private double[] deltaMousePosition = new double[2];
	private double mouseWheelScroll;
	
	/**
	 * Returns whether the left mouse button is pressed or not.  
	 * 
	 * @return True if the left mouse button is pressed, false otherwise.
	 */
	public boolean isLeftButtonPressed()
	{
		return leftMouseButton;
	}
	
	/**
	 * Sets whether the left mouse button is pressed or not.
	 * 
	 * @param leftMouseButton True if the left mouse button is pressed, false otherwise.
	 */
	public void setLeftButtonPressed(boolean leftMouseButton)
	{
		this.leftMouseButton = leftMouseButton;
	}

	/**
	 * Returns whether the right mouse button is pressed or not.  
	 * 
	 * @return True if the right mouse button is pressed, false otherwise.
	 */
	public boolean isRightButtonPressed()
	{
		return rightMouseButton;
	}

	/**
	 * Sets whether the right mouse button is pressed or not.
	 * 
	 * @param rightMouseButton True if the right mouse button is pressed, false otherwise.
	 */
	public void setRightButtonPressed(boolean rightMouseButton) 
	{
		this.rightMouseButton = rightMouseButton;
	}
	
	/**
	 * Returns the x position of the cursor on the screen.
	 * 
	 * @return The x position of the cursor on the screen.
	 */
	public double getX()
	{
		return mousePosition[0];
	}
	
	/**
	 * Returns the y position of the cursor on the screen.
	 * 
	 * @return The y position of the cursor on the screen.
	 */
	public double getY()
	{
		return mousePosition[1];
	}
	
	/**
	 * Returns a value representing the rotation of the mouse wheel.
	 * 
	 * @return A value representing the rotation of the mouse wheel.
	 */
	public double getMouseWheelScroll()
	{
		return mouseWheelScroll;
	}

	/**
	 * Handles the mouse's events.
	 * 
	 * @param scene The 3D scene.
	 * @param camera The camera of the scene.
	 */
	public void handleMouse(Scene scene, View camera) 
	{
        scene.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
        	/**
        	 * Sets the commands when a mouse button is pressed.
        	 * 
        	 * @param event The mouse's event.
        	 */
            @Override 
            public void handle(MouseEvent event) 
            {
                updateInputs(event, scene);
                
                if(event.isPrimaryButtonDown() && Simulation.LEFT_ARM.getSelectedTool() != null)
                {
                	Main.LOG.log(Level.SEVERE, Simulation.LEFT_ARM.getSelectedTool().act());
	            }
               
                if(event.isSecondaryButtonDown() && Simulation.RIGHT_ARM.getSelectedTool() != null)
                {
                	Main.LOG.log(Level.SEVERE, Simulation.RIGHT_ARM.getSelectedTool().act());
                }
            }
        });
        
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
        	/**
        	 * Sets the commands when the mouse is dragged.
        	 * 
        	 * @param event The mouse's event.
        	 */
            @Override 
            public void handle(MouseEvent event) 
            {
                updateInputs(event, scene);
                
                if(event.isMiddleButtonDown())
                {
                	camera.translateCamera(deltaMousePosition[0]);
                	camera.rotateCamera(deltaMousePosition[1]);
                }
            }
        });
        
        scene.setOnScroll(new EventHandler<ScrollEvent>() 
        {
        	/**
        	 * Sets the commands when the mouse wheel is scrolled.
        	 * 
        	 * @param event The mouse's event.
        	 */
            @Override
            public void handle(ScrollEvent event) 
            {
            	mouseWheelScroll= event.getDeltaY() / event.getMultiplierY();
                
                camera.zoomCamera(mouseWheelScroll);
            }
        });

    }
	
	/*
	 * Updates the mouse inputs according to the mouse's events.	
	 */
	private void updateInputs(MouseEvent event, Scene scene)
	{
		lastMousePosition[0] = mousePosition[0];
		lastMousePosition[1] = mousePosition[1];
		
        mousePosition[0] = scene.getWidth() / 2 - event.getSceneX();
        mousePosition[1] = event.getSceneY() - scene.getHeight() / 2;
        
        deltaMousePosition[0] = mousePosition[0] - lastMousePosition[0]; 
        deltaMousePosition[1] = mousePosition[1] - lastMousePosition[1];
        
        if(event.isPrimaryButtonDown())
		{
			setLeftButtonPressed(true);
		}
        else
        {
        	setLeftButtonPressed(false);
        }
		
		if(event.isSecondaryButtonDown())
		{
			setRightButtonPressed(true);
		}
		else
		{
			setRightButtonPressed(false);
		}
	}
}
