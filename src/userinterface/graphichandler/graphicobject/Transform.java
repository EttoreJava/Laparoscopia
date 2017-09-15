package userinterface.graphichandler.graphicobject;

/**
 * @author Michele Franceschetti
 */

public interface Transform //extends Group
{
	/**
     * Sets the translation from the three axis.
     * 
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     * @param z The translation from the z axis.
     */
    public void setPosition(double x, double y, double z); 
    
    /**
     * Sets the translation from the x and y axis.
     * 
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     */
    public void setPosition(double x, double y);
    
    /**
     * Sets the rotation around the three axis.
     * 
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    public void setRotation(double x, double y, double z);
    
    /**
     * Sets the scale factor for the three axis.
     * 
     * @param scaleFactor The scale factor.
     */
    public void setScale(double scaleFactor);
    
    /**
     * Sets the scale for the three axis.
     * 
     * @param x The scale for the x axis.
     * @param y The scale for the y axis.
     * @param z The scale for the z axis.
     */
    public void setScale(double x, double y, double z);
   
    /**
     * Resets the position to the origin of the axis, the rotation parallel to the axis 
     * and the scale to the unitary values.
     */
    public void reset();
    
    /**
     * Returns the string representation of this transform.
     */
    @Override 
    public String toString(); 
}
