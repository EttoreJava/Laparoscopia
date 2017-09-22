package content.tool;

import java.net.URL;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

/**
 * @author Manuel Gallina
 * @author Ettore Gorni
 */
public class Scalpel extends Tool
{	
	private static final double SCALE = 2.0;
	private static final String ACTION = "Cutting";
	
	private static final double RADIUS=1.5;
	private static final double OFFSET_X=18.0;
	private static final double OFFSET_Y=0.7;
	private static final double OFFSET_Z=6.5;
	private static final double OFFSET_X_CORRECTION = 50.0;
	
	private Sphere[] model = new Sphere[28];
	
	/**
	 * The file path is relative to the bin folder.
	 */
	private static final String MESH_PATH = "/models/Knife.obj";
	
	/**
	 * Constructor for the Forceps class. It also builds the model.
	 * 
	 * @param type The tool type.
	 */
	public Scalpel(String type)
	{
		super(type);
		
		setAction(ACTION);
		
		URL objFileUrl = this.getClass().getResource(MESH_PATH);
		
		this.setShape(getParent().importObjMesh(objFileUrl));
		
		this.getParent().setScale(SCALE);
		this.getParent().setRotation(0, 180.0, 0.0);
		this.getParent().setPosition(-5.0, 0.0, -5.9);
		
		this.getParent().getChildren().add(this.getShape());
		
		final PhongMaterial blackMaterial = new PhongMaterial();
	        blackMaterial.setDiffuseColor(Color.BLACK);
	        blackMaterial.setSpecularColor(Color.BLACK);
		
		for(int i=0; i<model.length; i++) {
			model[i] = new Sphere(RADIUS);
			model[i].setMaterial(blackMaterial);
		}
	}
	
	/**
	 * @param point which we want to know if is inside of the model
	 * @return true if the point is inside
	 * 
	 * -13, 0.7, 0.6 are parameter fount thought some experiments, I don't know why we need them
	 */
	public boolean inModel(Sphere sphere) 
	{
		Point3D sphereCenter= new Point3D(sphere.getTranslateX(),sphere.getTranslateY(),sphere.getTranslateZ());
		for(int i=(int)(model.length/2.0); i<model.length; i++)
		{
			Point3D center = new Point3D(model[i].getTranslateX()-OFFSET_X_CORRECTION,
					model[i].getTranslateY(),model[i].getTranslateZ() );
			if(sphereCenter.distance(model[i].localToScene(center))<(RADIUS+sphere.getRadius()))
				return true;
		}

		return false;

	}
	
	/**
	 * @return spheres model of Scalpel
	 */
	public Sphere[] getModel()
	{
		Translate origine = this.getParent().getPosition();
		model[0].setTranslateX(origine.getX() + OFFSET_X);
		model[0].setTranslateY(origine.getY() - OFFSET_Y);
		model[0].setTranslateZ(origine.getZ() + OFFSET_Z);
		
		for(int i=1; i<model.length; i++) 
		{
		model[i].setTranslateX(model[i-1].getTranslateX() + RADIUS);
		model[i].setTranslateY(origine.getY() - OFFSET_Y);
		model[i].setTranslateZ(origine.getZ() + OFFSET_Z);
		}
		
		return model;
	}
	
	@Override
	public String act() 
	{		
		return super.act();
	}

}
