package userinterface.graphichandler;

import java.util.logging.Level;

import application.Main;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * @author Michele Franceschetti
 */

public class Axis 
{
	private static final double AXES_RADIUS = 1;

	private static final double AXES_LENGTH = 250;

	private Object3D parent = new Object3D();
	
	/**
	 * The constructor.
	 * 
	 * @param parent The parent Transform of the axis.
	 */
	public Axis(Object3D parent) 
    {
		this.parent = parent;
		buildAxis();
    }
		
	/**
	 * Returns the parent transform of the axis.
	 * 
	 * @return The parent Transform of the axis.
	 */
	public Object3D getParent() 
	{
		return parent;
	}

	/**
	 * Sets the parent transform of the axis.
	 * 
	 * @param parent The parent Transform of the axis to set
	 */
	public void setParent(Object3D parent) 
	{
		this.parent = parent;
	}

	/**
	 * Creates the hierarchy of the axis and builds the meshes.
	 */
	public void buildAxis()
	{
        Main.LOG.log(Level.INFO, "buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXES_LENGTH, AXES_RADIUS, AXES_RADIUS);
        final Box yAxis = new Box(AXES_RADIUS, AXES_LENGTH, AXES_RADIUS);
        final Box zAxis = new Box(AXES_RADIUS, AXES_RADIUS, AXES_LENGTH);
        
        final Box xAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);
        final Box yAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);
        final Box zAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        xAxisEnd.setMaterial(redMaterial);
        yAxisEnd.setMaterial(greenMaterial);
        zAxisEnd.setMaterial(blueMaterial);
        
        xAxisEnd.setTranslateX(-AXES_LENGTH / 2);
        yAxisEnd.setTranslateY(-AXES_LENGTH / 2);
        zAxisEnd.setTranslateZ(-AXES_LENGTH / 2);
        
        parent.getChildren().addAll(xAxis, yAxis, zAxis, xAxisEnd, yAxisEnd, zAxisEnd);
    }
	
	/**
	 * Switches the visibility of the axis.
	 */
	public void switchVisibility()
	{
		parent.setVisible(!parent.isVisible());
	}
}
