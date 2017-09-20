package content;

import application.Simulation;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * 
 * @author Ettore Gorni
 */
//Not yet implemented
public class Tissue 
{
	private static final String DEFAULT_NAME = "Default";
	private static final String DEFAULT_TYPE = "Unknown";
	private String name;
	private String type;
	
	private Object3D parent = new Object3D();
	
	public static final int X_VALUE = Simulation.PATIENT.X_VALUE;
	public static final int Y_VALUE = Simulation.PATIENT.Y_VALUE;
	public static final int Z_VALUE = Simulation.PATIENT.Z_VALUE;
	
	public static final int START_X_VALUE = 20; //20
	public static final int START_Y_VALUE = 10;  //10
	public static final int START_Z_VALUE = 10;  //10
	
	public static final PhongMaterial TISSUE_MATERIAL = new PhongMaterial(Color.BLACK);
	
	private int counter = 0;
	
	/**
	 * Constructor.
	 * 
	 * @param name The name of this tissue.
	 * @param type The type of this tissue.
	 */
	public Tissue(String name, String type)
	{
		
		this.name = name;
		this.type = type;
		
		Simulation.PATIENT.getModel();
		
	    for (int i=0; i<X_VALUE; i++){
			for (int j=0; j<Y_VALUE; j++){
				for (int k=0; k<Z_VALUE; k++){
						double equazione = (Math.pow(i-40, 2.0) + Math.pow(j-10, 2.0)+ Math.pow(k-20, 2.0));
						if( equazione < 25)	
						{
							if(Simulation.PATIENT.getModel()[i][j][k] == null)	{
								Simulation.PATIENT.getModel()[i][j][k] = new Sphere(Simulation.PATIENT.RADIUS);
								Simulation.PATIENT.getModel()[i][j][k].setTranslateX((i*2 - Simulation.PATIENT.X_OFFSET));
								Simulation.PATIENT.getModel()[i][j][k].setTranslateY((j*2 + Simulation.PATIENT.Y_OFFSET));
								Simulation.PATIENT.getModel()[i][j][k].setTranslateZ(k*2);
								counter++;
							}
								
							Simulation.PATIENT.getParent().getChildren().remove(Simulation.PATIENT.getModel()[i][j][k]);
							Simulation.PATIENT.getModel()[i][j][k].setMaterial(TISSUE_MATERIAL);
							parent.getChildren().addAll(Simulation.PATIENT.getModel()[i][j][k]);
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Default constructor.
	 */
	public Tissue()
	{
		this(DEFAULT_NAME, DEFAULT_TYPE);	
	}
	

	
	/** 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return object3D of the tissue
	 */
	public Object3D getParent() {
		return parent; 
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @author Ettore Gorni
	 * @return true if the tissue is complete separated from the patient
	 */
	public boolean isSeparated() {
		Sphere[][][] model = Simulation.PATIENT.getModel();
		for (int i=0; i<X_VALUE; i++){
			for (int j=0; j<Y_VALUE; j++){
				for (int k=0; k<Z_VALUE; k++){
					if(model[i][j][k]!=null && model[i][j][k].getMaterial() == TISSUE_MATERIAL &&  model[i][j][k].isVisible()) {
						if(model[i-1][j][k]!=null && model[i-1][j][k].isVisible() && i>0 && 
								model[i-1][j][k].getMaterial() == Simulation.PATIENT.PATIENT_COLOR)
							return false;
						if(i+1<Simulation.PATIENT.X_VALUE && model[i+1][j][k]!=null && model[i+1][j][k].isVisible() &&  
								model[i+1][j][k].getMaterial() == Simulation.PATIENT.PATIENT_COLOR)
							return false;					
						
						if(model[i][j-1][k]!=null && model[i][j-1][k].isVisible() && j>0 && 
								model[i][j-1][k].getMaterial() == Simulation.PATIENT.PATIENT_COLOR)
							return false;
						if(j+1<Simulation.PATIENT.Y_VALUE && model[i][j+1][k]!=null && model[i][j+1][k].isVisible() && 
								model[i][j+1][k].getMaterial() == Simulation.PATIENT.PATIENT_COLOR)
							return false;

						if(model[i][j][k-1]!=null && model[i][j][k-1].isVisible() && k>0 && 
								model[i][j][k-1].getMaterial() == Simulation.PATIENT.PATIENT_COLOR)
							return false;
						if(k+1<Simulation.PATIENT.Z_VALUE && model[i][j][k+1]!=null && model[i][j][k+1].isVisible() && 
								model[i][j][k+1].getMaterial() == Simulation.PATIENT.PATIENT_COLOR)
							return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return the tissue's volume
	 */
	public int getVolume(){
		return counter;
	}	
}
