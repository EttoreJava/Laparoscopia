package content;

import java.util.ArrayList;
import java.util.List;

import content.tool.Tool;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * @author Michele Franceschetti
 */

public class Arm 
{
	private static final double ARM_RADIUS = 6;

	private static final double KNEE_RADIUS = 5;
	
	private static final double MAX_DISTANCE_FROM_AXIS = 400.0;
	
	private static final Color ARM_COLOR = Color.BLUE;
	private static final Color KNEE_COLOR = Color.CYAN;
	
	private Object3D upperArm = new Object3D();
	private Object3D foreArm = new Object3D();
	private Object3D hand = new Object3D();
	
	private float upperArmLenght;
	private float foreArmLenght;
		
	private Tool selectedTool;
	private ArrayList<Tool> toolList = new ArrayList<>();
	
	private Translate target;
	
	/**
	 * The constructor.
	 * 
	 * @param target The position of the target.
	 * @param upperArmLenght The length of the Upper arm.
	 * @param foreArmLenght The length of the Fore arm.
	 */
	public Arm(Translate target, float upperArmLenght, float foreArmLenght)
	{
		upperArm.reset();
		foreArm.reset();
		hand.reset();
		
		this.target = target;
		this.upperArmLenght = upperArmLenght;
		this.foreArmLenght = foreArmLenght;
		
		createArm();		
	}
	
	/**
	 * Returns the upper arm transform.
	 * 
	 * @return The upper arm transform.
	 */
	public Object3D getUpperArm()
	{
		return upperArm;
	}
	
	/**
	 * Sets the upper arm transform.
	 * 
	 * @param upperArm The upper arm transform to set.
	 */
	public void setUpperArm(Object3D upperArm)
	{
		this.upperArm = upperArm;
	}
	
	/**
	 * Returns the target.
	 * 
	 * @return The position of the target.
	 */
	public Translate getTarget()
	{
		return target;
	}
	
	/**
	 * Returns the selected tool on this arm.
	 * 
	 * @return The selected tool.
	 */
	public Tool getSelectedTool() 
	{
		return selectedTool;
	}
	
	/**
	 * Sets the selected tool on this arm.
	 * 
	 * @param tool The selected tool on this arm.
	 */
	public void setSelectedTool(Tool tool) 
	{
		if(this.selectedTool != null)
		{
			hand.getChildren().remove(this.selectedTool.getParent());
		}
		this.selectedTool = tool;
		
		hand.getChildren().add(tool.getParent());
	}
	
	/**
	 * Returns the list of tools.
	 * 
	 * @return The tool list.
	 */
	public List<Tool> getToolList() 
	{
		return toolList;
	}

	/**
	 * The tool list to set.
	 * 
	 * @param toolList The tool list to set.
	 */
	public void setToolList(List<Tool> toolList) 
	{
		this.toolList = (ArrayList<Tool>) toolList;
	}

	/**
	 * Creates the hierarchy of the arm.
	 */
	public void createArm()
	{
		//Materials
		
		final PhongMaterial armColor = new PhongMaterial();
		armColor.setDiffuseColor(ARM_COLOR);
		armColor.setSpecularColor(ARM_COLOR);
		
		final PhongMaterial kneeColor = new PhongMaterial();
		kneeColor.setDiffuseColor(KNEE_COLOR);
		kneeColor.setSpecularColor(KNEE_COLOR);
		
		//UpperArm
		
		Cylinder upperArmMesh = new Cylinder(ARM_RADIUS, upperArmLenght);
		Sphere upperArmKnee = new Sphere(KNEE_RADIUS);
		
		upperArmMesh.setMaterial(armColor);
		upperArmKnee.setMaterial(kneeColor);
		
		if(!upperArm.getChildren().contains(upperArmMesh))
		{
			upperArm.getChildren().add(upperArmMesh);
		}
		
		if(!upperArm.getChildren().contains(upperArmKnee))
		{
			upperArm.getChildren().add(upperArmKnee);
		}
		
		upperArmMesh.setRotate(90);
        upperArmMesh.setTranslateX(upperArm.getPosition().getX() + upperArmLenght / 2);
        upperArmMesh.setTranslateY(upperArm.getPosition().getY());
        upperArmMesh.setTranslateZ(upperArm.getPosition().getZ());
		
        
		foreArm.setPosition(upperArm.getPosition().getX() + (double)upperArmLenght, upperArm.getPosition().getY(), upperArm.getPosition().getZ());
		
		upperArm.getChildren().add(foreArm);
		
		//ForeArm
		
		Cylinder foreArmMesh = new Cylinder(ARM_RADIUS, foreArmLenght);
		Sphere foreArmKnee = new Sphere(KNEE_RADIUS);
		
		foreArmMesh.setMaterial(armColor);
		foreArmKnee.setMaterial(kneeColor);
		
		if(!foreArm.getChildren().contains(foreArmMesh))
		{
			foreArm.getChildren().add(foreArmMesh);
		}
		
		if(!foreArm.getChildren().contains(foreArmKnee))
		{
			foreArm.getChildren().add(foreArmKnee);
		}
		
		foreArmMesh.setRotate(90);
		foreArmMesh.setTranslateX(upperArm.getPosition().getX() + foreArmLenght / 2);
		foreArmMesh.setTranslateY(upperArm.getPosition().getY());
		foreArmMesh.setTranslateZ(upperArm.getPosition().getZ());
		
		hand.setPosition(upperArm.getPosition().getX() + (double)foreArmLenght, upperArm.getPosition().getY(), upperArm.getPosition().getZ());
		
		foreArm.getChildren().add(hand);
		
		//Tool
		
		if(this.selectedTool != null)
		{
			hand.getChildren().add(selectedTool.getShape());
		}
			
		setArm();
	
	}
	
	/**
	 * Sets the position and rotation of each node, so the hand position is the target.
	 */
	public void setArm()
	{
		double distanceFromTarget = Object3D.distance(upperArm.getPosition(), target);
		
		if(distanceFromTarget > (upperArmLenght + foreArmLenght))
		{
			distanceFromTarget = upperArmLenght + foreArmLenght;
		}
		
		double upperAngleX = Math.acos((foreArmLenght * foreArmLenght - upperArmLenght * upperArmLenght - distanceFromTarget * distanceFromTarget) / (- 2 * upperArmLenght * distanceFromTarget)) * Object3D.RAD_TO_DEG;
	 	
	 	if(target.getY() < upperArm.getPosition().getY())
	 	{
	 		upperAngleX = -upperAngleX + 180;
	 	}
	 	
	 	double distanceZ = target.getZ() - upperArm.getPosition().getZ();
	 	
	 	if(distanceZ > (upperArmLenght + foreArmLenght))
	 	{
	 		distanceZ = upperArmLenght + foreArmLenght;
	 	}
	 	
	 	double upperAngleCorrection = Math.asin(distanceZ / distanceFromTarget) * Object3D.RAD_TO_DEG;
	 	
	 	
	 	double upperAngle = upperAngleX + upperAngleCorrection;
	 	
	 	if(target.getY() < upperArm.getPosition().getY())
	 	{
	 		upperAngle = -upperAngle;
	 	}
	 	
	 	upperArm.reset();
	 	
	 	upperArm.getRotation().setZ(90);
	 	upperArm.getRotation().setX(upperAngle);
	 	
	 	upperArm.getPosition().setX(target.getX());
	 		 
	 	double heightZ = Math.sin(upperAngle / Object3D.RAD_TO_DEG) * (upperArmLenght + foreArmLenght);
	 	double heightY = Math.cos(upperAngle / Object3D.RAD_TO_DEG) * (upperArmLenght + foreArmLenght);
	 	double height = Math.sqrt((heightY - target.getY()) * (heightY - target.getY()) + (heightZ - target.getZ()) * (heightZ - target.getZ()));
	 	
	 	double deltaForeArmY = upperArm.getPosition().getY() + Math.cos(upperAngle / Object3D.RAD_TO_DEG) * upperArmLenght - target.getY();
	 	double deltaForeArmZ = upperArm.getPosition().getZ() + Math.sin(upperAngle / Object3D.RAD_TO_DEG) * upperArmLenght - target.getZ();
	 	double distance = Math.sqrt(deltaForeArmY * deltaForeArmY + deltaForeArmZ * deltaForeArmZ);
	 	
	 	if((distance - foreArmLenght) > 0.001)
	 	{
	 		height = 0;
	 	}
	 	
	 	double foreAngle = Math.acos(1 - height * height / (2 * foreArmLenght * foreArmLenght)) * Object3D.RAD_TO_DEG;
	 	
	 	foreArm.getRotation().setY(foreAngle);
	}
	
	/**
	 * Moves the target position.
	 * 
	 * @param deltaX The translation on the X axis.
	 * @param deltaY The translation on the Y axis.
	 * @param deltaZ The translation on the Z axis.
	 */
	public void moveTarget(double deltaX, double deltaY, double deltaZ)
	{
		target.setX(target.getX() + deltaX);
		target.setY(target.getY() + deltaY);
		target.setZ(target.getZ() + deltaZ);
		
		if(target.getX() > MAX_DISTANCE_FROM_AXIS)
			target.setX(MAX_DISTANCE_FROM_AXIS);
		
		if(target.getX() < -MAX_DISTANCE_FROM_AXIS)
			target.setX(-MAX_DISTANCE_FROM_AXIS);
		
		if(target.getY() > MAX_DISTANCE_FROM_AXIS)
			target.setY(MAX_DISTANCE_FROM_AXIS);
		
		if(target.getY() < -MAX_DISTANCE_FROM_AXIS)
			target.setY(-MAX_DISTANCE_FROM_AXIS);
		
		if(target.getZ() > MAX_DISTANCE_FROM_AXIS)
			target.setZ(MAX_DISTANCE_FROM_AXIS);
		
		if(target.getZ() < -MAX_DISTANCE_FROM_AXIS)
			target.setZ(-MAX_DISTANCE_FROM_AXIS);
		
		setArm();
	}
}
