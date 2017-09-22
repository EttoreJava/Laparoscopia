package content.tool;

import java.net.URL;

import javafx.geometry.Point3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

/**
 * @author Manuel Gallina
 */
public class VacuumCleaner extends Tool
{	
	private static final double SCALE = 0.6;
	private static final double OFFSET_X_CORRECTION = 50.0;
	private static final double DISTANCE =  OFFSET_X_CORRECTION / 2.0;
	private static final String ACTION = "Vacuuming";
	
	/**
	 * The file path is relative to the bin folder.
	 */
	private static final String MESH_PATH = "/models/VacuumCleaner.obj";
	
	/**
	 * Constructor for the Forceps class.
	 * 
	 * @param type The tool type.
	 */
	public VacuumCleaner(String type)
	{
		super(type);
		
		setAction(ACTION);
		
		URL objFileUrl = this.getClass().getResource(MESH_PATH);
		
		this.setShape(getParent().importObjMesh(objFileUrl));

		this.getParent().setScale(SCALE);
		this.getParent().setRotation(0.0, 0.0, 270.0);
		this.getParent().setPosition(30, 0, 0);
		
		this.getParent().getChildren().add(this.getShape());
	}
	
	/**
	 * @author Ettore Gorni
	 * @param sphere
	 * @return true if the vacuum cleaner is close to the sphere
	 */
	public boolean closeToModel(Sphere sphere) 
	{
		Point3D sphereCenter= new Point3D(sphere.getTranslateX(),sphere.getTranslateY(),sphere.getTranslateZ());
		Translate position = this.getParent().getPosition();
			Point3D center = new Point3D(position.getX()-OFFSET_X_CORRECTION, position.getY(), position.getZ());
			if(sphereCenter.distance(this.getParent().localToScene(center))<(DISTANCE))
				return true;

		return false;

	}
	
	@Override
	public String act() 
	{
		return super.act();
	}
}