package application;

import content.Arm;
import content.Cut;
import content.Patient;
import content.Tissue;
import javafx.scene.transform.Translate;

/**
 * @author Manuel Gallina
 *
 */
public final class Simulation
{
	/**
	 * The starting target position for the left arm.
	 */
	public static final Translate LEFT_TARGET = new Translate(-100, 100, 0);
    /**
     * The starting target position for the right arm.
     */
	public static final Translate RIGHT_TARGET = new Translate(100, 100, 0);
    
    /**
     * The left arm.
     */
    public static final Arm LEFT_ARM = new Arm(LEFT_TARGET, 200, 200);
    /**
     * The right arm.
     */
    public static final Arm RIGHT_ARM = new Arm(RIGHT_TARGET, 200, 200);
	
    /**
     * The patient.
     */
    public static final Patient PATIENT = new Patient(
    		new String[]{"Nome", "Mario", "Cognome", "Rossi", "Età", "50", "Altezza", "1.70", "Peso", "65"});         
    
    /**
     * The Tissue.
     */
    public static final Tissue TISSUE = new Tissue(
    		new String ("prova"), new String ("Tumore")); 
    
    /*
	 * A private constructor to override the default public one.
	 * This is useful to prevent the instantiation of this class.
	 */
    private Simulation(){}
}
