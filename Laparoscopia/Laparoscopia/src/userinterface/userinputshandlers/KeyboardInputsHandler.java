package userinterface.userinputshandlers;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import application.Simulation;
import content.Arm;
import content.Patient;
import content.Tissue;
import content.tool.Scalpel;
import content.tool.Tool;
import content.tool.VacuumCleaner;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import userinterface.graphichandler.Axis;

/**
 * @author Michele Franceschetti
 * @author Ettore Gorni
 */

public class KeyboardInputsHandler
{
	private static final int DEFAULT_VALUE = 0;
	
	//Left arm
	private int leftXAxis;
	private int leftYAxis;
	private int leftZAxis;
	
	private int leftRotation360;
	private int leftRotation180;
	
	//Right arm
	private int rightXAxis;
	private int rightYAxis;
	private int rightZAxis;
	
	private int rightRotation360;
	private int rightRotation180;
	
	//Other commands
	
	private boolean escape;
	private boolean reset;
	double x=0,y=0,z=0;

	/**
	 * Default constructor.
	 */
	public KeyboardInputsHandler()
	{
		reset();
	}
	
	/**
	 * @return right Wrist rotation in 360
	 */
	public int getRightRotation360()
	{
		return rightRotation360;
	}
	
	/**
	 * @return right Wrist rotation in 180
	 */
	public int getRightRotation180()
	{
		return rightRotation180;
	}
	
	/**
	 * @return left Wrist rotation in 360
	 */
	public int getLeftRotation360()
	{
		return leftRotation360;
	}
	
	/**
	 * @return left Wrist  rotation in 180
	 */
	public int getLeftRotation180()
	{
		return leftRotation180;
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
	 * @param toolList 
	 * @param axis The 3D axis mesh.
	 */
	public void handleKeyboard1(Scene scene, Arm leftArm, Arm rightArm, Axis axis, Patient patient, Tissue tissue) 
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
                    case T:
                    	leftRotation360 = -1;
                    	break;
                    case Y:
                    	leftRotation360 = 1;
                    	break;
                    case H:
                    	leftRotation180 = -1;
                    	break;
                    case G:
                    	leftRotation180 = 1;
                    	break;
                    case V:
                    	rightRotation360 = -1;
                    	break;
                    case B:
                    	rightRotation360 = 1;
                    	break;
                    case N:
                    	rightRotation180 = -1;
                    	break;
                    case M:
                    	rightRotation180 = 1;
                    	break;
                    default:
                    	event.consume();
                    	reset();
                    	break;
                }

                leftArm.moveTarget(leftXAxis, leftYAxis, leftZAxis);
                leftArm.moveHand(leftRotation180, leftRotation360);
                rightArm.moveTarget(rightXAxis, rightYAxis, rightZAxis);
                rightArm.moveHand(rightRotation180, rightRotation360);
                               
                toolManager(leftArm, rightArm, patient, tissue);
                
                reset();
            }
        });
    }
	
	/**
	 * This method manage the tools, now are implemented for Scalpel and vacuumCleaner
	 */
	private void toolManager (Arm leftArm, Arm rightArm, Patient patient, Tissue tissue){
        if(rightArm.getSelectedTool() instanceof Scalpel) {
            for (int i=0; i<40; i++){
            	for (int j=0; j<20; j++){
            		for (int k=0; k<20; k++){
            			if (((Scalpel)rightArm.getSelectedTool()).inModel(patient.getModel()[i][j][k])) 
            				patient.getModel()[i][j][k].setVisible(false);
            		}
            	}
            }
        }
        else if(rightArm.getSelectedTool() instanceof VacuumCleaner) {
        	if(tissue.isSeparated() && CleanerCloseTo((VacuumCleaner)rightArm.getSelectedTool(), patient) ) {
        		for (int i=Simulation.TISSUE.START_X_VALUE; i<Simulation.TISSUE.X_VALUE; i++)	{
        			for (int j=Simulation.TISSUE.START_Y_VALUE; j<Simulation.TISSUE.Y_VALUE; j++)	{
        				for (int k=Simulation.TISSUE.START_Z_VALUE; k<Simulation.TISSUE.Z_VALUE; k++)	{
                			patient.getModel()[i][j][k].setVisible(false);
                		}
                	}
                }
        	}
        }


        if(leftArm.getSelectedTool() instanceof Scalpel) {
            for (int i=0; i<40; i++){
            	for (int j=0; j<20; j++){
            		for (int k=0; k<20; k++){
            			if (((Scalpel)leftArm.getSelectedTool()).inModel(patient.getModel()[i][j][k])) 
            				patient.getModel()[i][j][k].setVisible(false);
            		}
            	}
            }
        }
        else if(leftArm.getSelectedTool() instanceof VacuumCleaner) {
        	if(tissue.isSeparated() && CleanerCloseTo((VacuumCleaner)leftArm.getSelectedTool(), patient)) {
        		for (int i=Simulation.TISSUE.START_X_VALUE; i<Simulation.TISSUE.X_VALUE; i++)	{
        			for (int j=Simulation.TISSUE.START_Y_VALUE; j<Simulation.TISSUE.Y_VALUE; j++)	{
        				for (int k=Simulation.TISSUE.START_Z_VALUE; k<Simulation.TISSUE.Z_VALUE; k++)	{
                			patient.getModel()[i][j][k].setVisible(false);
                		}
                	}
                }
        	}
        }
	}
	
	/**
	 * @param cleaner
	 * @param patient
	 * @return true if the cleaner is close to one of the model spheres
	 */
	private boolean CleanerCloseTo(VacuumCleaner cleaner, Patient patient) {
		for (int i=Simulation.TISSUE.START_X_VALUE; i<Simulation.TISSUE.X_VALUE; i++)	{
			for (int j=Simulation.TISSUE.START_Y_VALUE; j<Simulation.TISSUE.Y_VALUE; j++)	{
				for (int k=Simulation.TISSUE.START_Z_VALUE; k<Simulation.TISSUE.Z_VALUE; k++)	{
						if((cleaner.closeToModel(patient.getModel()[i][j][k])))
							return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Resets all the attributes to the DEFAULT_VALUE.
	 */
	private void reset()
	{
		leftXAxis = DEFAULT_VALUE;
		leftYAxis = DEFAULT_VALUE;
		leftZAxis = DEFAULT_VALUE;
		
		leftRotation360 = DEFAULT_VALUE;
		leftRotation180 = DEFAULT_VALUE;

		
		rightXAxis = DEFAULT_VALUE;
		rightYAxis = DEFAULT_VALUE;
		rightZAxis = DEFAULT_VALUE;
		
		rightRotation360 = DEFAULT_VALUE;
		rightRotation180 = DEFAULT_VALUE;
		
		
		
		escape = false;
		reset = false;
	}
}
