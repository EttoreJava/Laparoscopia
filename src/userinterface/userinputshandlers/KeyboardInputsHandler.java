package userinterface.userinputshandlers;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import userinterface.graphichandler.Axis;
import userinterface.guihandler.WindowHandler;

/**
 * @author Michele Franceschetti
 * @author Ettore Gorni
 * @author Luca Benedetti
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
	private int patientCounter=0, tumorCounter=0;

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
	public void handleKeyboard1(Scene scene, Arm leftArm, Arm rightArm, Axis axis, Patient patient, Tissue tissue, VBox window) 
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
                    	fullReset(leftArm, rightArm, patient);
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
       
                window.getChildren().add(4,WindowHandler.percentage(patientCounter, tumorCounter));
                window.getChildren().remove(5);
                
                reset();
                
                //patientCounter = removed(patient, tissue)[0];
                //tumorCounter = removed(patient, tissue)[1];
            }
        });
    }
	
	private void fullReset(Arm leftArm, Arm rightArm, Patient patient) {
		rightArm.reset();
		leftArm.reset();
		patient.reset();
		patientCounter = 0;
		tumorCounter = 0;
	}
	
	/**
	 * @author Ettore Gorni
	 * This method manage that element that must be deleted by the vacuum cleaner
	 */
	private void vacuumCleanerManager(Patient patient, Arm arm) {
    	ArrayList<HashSet<Sphere>> pieces = patient.getPieces();
    	for(int h=0; h<pieces.size(); h++) {
    		if(pieces.get(h).size()<50 && CleanerCloseTo((VacuumCleaner)arm.getSelectedTool(), pieces.get(h))) {
    			Iterator<Sphere> it=pieces.get(h).iterator();
    		    while(it.hasNext()) {
    				it.next().setVisible(false);
    		    }
            }
        }
	}
	
	/**
	 * @author Ettore Gorni
	 * This method manage that element that must be deleted by the vacuum cleaner
	 */
	private void scalpelManager(Patient patient, Arm arm) {
		for (int i=0; i<patient.X_VALUE; i++){
        	for (int j=0; j<patient.Y_VALUE; j++){
        		for (int k=0; k<patient.Z_VALUE; k++){
        			if (!(patient.getModel()[i][j][k] == null)) {
            			if (((Scalpel)arm.getSelectedTool()).inModel(patient.getModel()[i][j][k])) {
            				if (patient.getModel()[i][j][k].isVisible()){
            					if (patient.getModel()[i][j][k].getMaterial().equals(patient.PATIENT_COLOR)) patientCounter++;
            					else tumorCounter++;
            				}
            				patient.getModel()[i][j][k].setVisible(false);
            			}
        			}
        		}
        	}
		}
	}
	
	/**
	 * @author Ettore Gorni 
	 * This method manage the tools, now are implemented for Scalpel and vacuumCleaner
	 */
	private void toolManager (Arm leftArm, Arm rightArm, Patient patient, Tissue tissue){
		// RIGHT ARM
        if(rightArm.getSelectedTool() instanceof Scalpel) {
        	scalpelManager(patient, rightArm);
        }
        else if(rightArm.getSelectedTool() instanceof VacuumCleaner) {
        	vacuumCleanerManager(patient, rightArm);
        }
		// LEFT ARM
        if(leftArm.getSelectedTool() instanceof Scalpel) {
        	scalpelManager(patient, leftArm);
        }
        else if(leftArm.getSelectedTool() instanceof VacuumCleaner) {
        	vacuumCleanerManager(patient, leftArm);
        }
	}
	
	/**
	 * @param cleaner
	 * @param patient
	 * @return true if the cleaner is close to one of the model spheres
	 */
	private boolean CleanerCloseTo(VacuumCleaner cleaner, HashSet<Sphere> piece) {
	    Iterator<Sphere> it=piece.iterator();

	    while(it.hasNext()) {
			if((cleaner.closeToModel(it.next())))
				return true;
	    }
		return false;
	}
	
	private int[] removed(Patient patient, Tissue tissue){
		int[] removed = {0};
		int pCounter = 0, tCounter = 0;
		for (int i=0; i<patient.X_VALUE; i++){
			for (int j=0; j<patient.Y_VALUE; j++){
				for (int k=0; k<patient.Z_VALUE; k++){
					if((patient.getModel()[i][j][k].getMaterial() == tissue.TISSUE_MATERIAL) && !(patient.getModel()[i][j][k].isVisible())) tCounter++;
					if((patient.getModel()[i][j][k].getMaterial() == patient.PATIENT_COLOR) && !(patient.getModel()[i][j][k].isVisible())) pCounter++;
				}
			}
		}
		removed[0] = pCounter;
		removed[1] = tCounter;
		return removed;
    }
	
	
	/**
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
