package userinterface.graphichandler.graphicobject;

import java.net.URL;
import java.util.logging.Level;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import application.Main;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * @author Michele Franceschetti
 * @author Manuel Gallina
 */
public class Object3D extends Group implements Transform, ObjImporter 
{
	/**
	 * The obj mesh importer.	
	 */
	public static final ObjModelImporter OBJ_IMPORTER = new ObjModelImporter();	
	
	/**
	 * Conversion factor: radiant into degrees.
	 */
	public static final double RAD_TO_DEG = 180.0 / Math.PI;
	
	private Translate position  = new Translate(0,0,0); 
	private Rotation rotation = new Rotation();
    private Scale scale = new Scale();
    
    /**
     * Default constructor.
     */
    public Object3D() 
    { 
        super(); 
        getTransforms().addAll(position, rotation.getRotateX(), rotation.getRotateY(), rotation.getRotateZ(), scale); 
        reset();
    }
    
    /**
     * Returns the Translate object for the position.
     * 
     * @return The Translate object for the position.
     */
    public Translate getPosition()
    {
    	return this.position;
    }
    
    /**
     * Returns the Rotation object for the rotation.
     * 
     * @return The Rotation object for the rotation.
     */
    public Rotation getRotation()
    {
    	return this.rotation;
    }
    
    /**
     * Returns the Scale object for the scale.
     * 
     * @return The Scale object for the scale.
     */
    public Scale getScale()
    {
    	return this.scale;
    }
    
    /**
     * Sets the translation from the three axis.
     * 
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     * @param z The translation from the z axis.
     */
    public void setPosition(double x, double y, double z) 
    {
        position.setX(x);
        position.setY(y);
        position.setZ(z);
    }

    /**
     * Sets the translation from the x and y axis.
     * 
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     */
    public void setPosition(double x, double y)
    {
    	position.setX(x);
    	position.setY(y);
    }

    /**
     * Sets the rotation around the three axis.
     * 
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    public void setRotation(double x, double y, double z)
    {
    	rotation.setRotation(x, y, z);
    }
    
    /**
     * Sets the scale factor for the three axis.
     * 
     * @param scaleFactor The scale factor.
     */
    public void setScale(double scaleFactor)
    {
        scale.setX(scaleFactor);
        scale.setY(scaleFactor);
        scale.setZ(scaleFactor);
    }

    
    /**
     * Sets the scale for the three axis.
     * 
     * @param x The scale for the x axis.
     * @param y The scale for the y axis.
     * @param z The scale for the z axis.
     */
    public void setScale(double x, double y, double z) 
    {
        scale.setX(x);
        scale.setY(y);
        scale.setZ(z);
    }

    /**
     * Resets the position to the origin of the axis, the rotation parallel to the axis 
     * and the scale to the unitary values.
     */
    public void reset() 
    {
        position.setX(0.0);
        position.setY(0.0);
        position.setZ(0.0);
        
        rotation.setX(0.0);
        rotation.setY(0.0);
        rotation.setZ(0.0);
        
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
    }

    /**
     * Returns the distance between two positions.
     * 
     * @param a First position.
     * @param b Second position.
     * @return The distance between a and b.
     */
    public static double distance(Translate a, Translate b)
    {
    	return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + 
						(a.getY() - b.getY()) * (a.getY() - b.getY()) + 
						(a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));
    }

    /**
	 * Imports the mesh from a .obj file.
	 * 
	 * @param path The file path.
	 */
	public MeshView importObjMesh(URL path)
	{
		try
		{
			OBJ_IMPORTER.read(path);
		}
		catch(Exception e)
		{
			Main.LOG.log(Level.INFO, "IMPORT EXCEPTION: ", e);
			return null;
		}
	
		return OBJ_IMPORTER.getImport()[0];
	}

    /**
     * Returns the string representation of this transform.
     */
    @Override 
    public String toString() 
    {
        return "Transform [t = (" +
        				   position.getX() + ", " +
        				   position.getY() + ", " +
        				   position.getZ() + ")  " +
        				   rotation.toString() +
                           "s = (" +
                           scale.getX() + ", " + 
                           scale.getY() + ", " + 
                           scale.getZ() + ") ]";
    }
}
