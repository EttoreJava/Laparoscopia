package content.tool;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * @author Manuel Gallina
 */

public abstract class Tool 
{
	private static final String DEFAULT_ACTION = "Action";
	private static final String DEFAULT_TYPE = "Default";
	private static final Sphere DEFAULT_SHAPE = new Sphere(10);
	
	private String type;
	private MeshView shape;
	private String action;
	
	private Object3D parent = new Object3D();

	//Constructors
	/**
	 * Default constructor for the Tool class.
	 */
	public Tool()
	{
		this.action = DEFAULT_ACTION;
		this.type = DEFAULT_TYPE;
		
		this.getParent().getChildren().add(DEFAULT_SHAPE);	
	}
	
	/**
	 * Default constructor for the Tool class.
	 * 
	 * @param type The type description.
	 */
	public Tool(String type)
	{
		this.action = DEFAULT_ACTION;
		this.type = type;	
	}
	
	
	//Getters and setters
	/**
	 * Returns the type of this tool.
	 * 
	 * @return The type.
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * Sets the type of this tool.
	 * 
	 * @param type The type to set.
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	
	/**
	 * Returns the shape of this tool.
	 * 
	 * @return The shape.
	 */
	public MeshView getShape() {
		return shape;
	}

	/**
	 * Sets the shape of this tool.
	 * 
	 * @param shape The shape to set.
	 */
	public void setShape(MeshView shape) {
		this.shape = shape;
	}

	/**
	 * The action of this tool.
	 * 
	 * @return The action.
	 */
	//Provisional
	public String getAction()
	{
		return this.action;
	}
	
	/**
	 * Sets the action of this tool.
	 * 
	 * @param action The action to set.
	 */
	//Provisional
	public void setAction(String action)
	{
		this.action = action;
	}
	
	/**
	 * Returns the parent transform. 
	 * 
	 * @return The parent transform.
	 */
	public Object3D getParent() 
	{
		return parent;
	}

	/**
	 * Sets the parent transform.
	 * 
	 * @param parent The parent transform to set.
	 */
	public void setParent(Object3D parent) 
	{
		this.parent = parent;
	}

	//Methods
	/**
	 * Executes the action of this tool.
	 * 
	 * @return the string description action of this tool
	 */
	public String act()
	{
		StringBuilder actionMessage = new StringBuilder(action);
		
		return actionMessage.toString();
	}
}
