package content;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * This class isn't been used yet.
 * 
 * @author Manuel Gallina
 * @author Michele Franceschetti
 * @author Ettore Gorni
 */
//Not yet implemented
public class Tissue 
{
	private static final String DEFAULT_TYPE = "Unknown";
	private String type;
	private Translate target;
	private float radius;
	private Object3D cancer = new Object3D();
	
	/**
	 * Default constructor.
	 */
	public Tissue()
	{
		this.type = DEFAULT_TYPE;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param name The name of this tissue.
	 * @param type The type of this tissue.
	 */
	public Tissue(String type, Translate target, float radius)
	{
		this.type = type;
		this.target= target;
		this.radius= radius;
		
		createCancer();
	}
	
	/**
	 * Creates the hierarchy of the arm.
	 */
	public void createCancer(){
		Sphere tissue = new Sphere(radius);
        tissue.setMaterial(new PhongMaterial(Color.GOLD));
        tissue.setDrawMode(DrawMode.FILL);
        tissue.setTranslateX(target.getX());
        tissue.setTranslateY(target.getY());
        tissue.setTranslateZ(target.getZ());
        
        cancer.getChildren().add(tissue);
        
	}
	
	/**
	 * @return The cancer.
	 */
	public Object3D getTissue()
	{
		return cancer;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
