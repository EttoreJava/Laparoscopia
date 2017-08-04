package userinterface.graphichandler.graphicobject;

import javafx.scene.transform.Rotate;

/**
 * @author Michele Franceschetti
 */

public class Rotation 
{
	private Rotate x = new Rotate();
    private Rotate y = new Rotate();
    private Rotate z = new Rotate();
    
    /**
     * Default Constructor.
     * 
     * Sets the rotation to (0,0,0).
     */
    public Rotation()
    {

        x.setAxis(Rotate.X_AXIS);
        y.setAxis(Rotate.Y_AXIS);
        z.setAxis(Rotate.Z_AXIS);
        
    	setRotation(0,0,0);
    }
    
    /** 
     * Returns the Rotate object for the X axis.
     * 
     * @return The Rotate object for the X axis.
     */
    public Rotate getRotateX()
    {
    	return this.x;
    }
    
    /** 
     * Returns the Rotate object for the Y axis.
     * 
     * @return The Rotate object for the Y axis.
     */
    public Rotate getRotateY()
    {
    	return this.y;
    }
    
    /** 
     * Returns the Rotate object for the Z axis.
     * 
     * @return The Rotate object for the Z axis.
     */
    public Rotate getRotateZ()
    {
    	return this.z;
    }
    
    /**
     * Sets the rotation of the transform around the three axis.
     * 
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    public void setRotation(double x, double y, double z)
    {
        this.x.setAngle(x);
        this.y.setAngle(y);
        this.z.setAngle(z);
    }
    
    /**
     * Sets the rotation around the x axis.
     * 
     * @param x The rotation around the x axis.
     */
    public void setX(double x) 
    { 
    	this.x.setAngle(x); 
    }
    
    /**
     * Sets the rotation around the y axis.
     * 
     * @param y The rotation around the y axis.
     */
    public void setY(double y) 
    { 
    	this.y.setAngle(y); 
    }
    
    /**
     * Sets the rotation around the z axis.
     * 
     * @param z The rotation around the z axis.
     */
    public void setZ(double z) 
    { 
    	this.z.setAngle(z); 
    }
    
    /**
     * Gets the rotation around the x axis.
     * 
     * @return The rotation around the x axis.
     */
    public double getX() 
    {
    	return this.x.getAngle();
    }
    
    /**
     * Gets the rotation around the y axis.
     * 
     * @return The rotation around the y axis.
     */
    public double getY() 
    {
    	return this.y.getAngle(); 
    }
    
    /**
     * Gets the rotation around the z axis.
     * 
     * @return The rotation around the z axis.
     */
    public double getZ()
    { 
    	return this.z.getAngle(); 
    }

    /**
     * Returns a string representation of the rotation.
     * 
     * @return The string representation.
     */
    @Override
    public String toString()
    {
	    return "r = (" +
	    x.getAngle() + ", " +
	    y.getAngle() + ", " +
	    z.getAngle() + ")  ";
    }
}
