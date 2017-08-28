package userinterface.graphichandler;

import java.util.logging.Level;

import application.Main;
import content.Patient;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * @author Michele Franceschetti
 */

public class View 
{
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	private static final double CAMERA_INITIAL_DISTANCE = 500;
	
	private static final double CAMERA_INITIAL_Y_ANGLE = 0.0;
	private static final double CAMERA_INITIAL_X_ANGLE = -90.0;
	private static final double CAMERA_INITIAL_Z_ANGLE = 0.0;
	
	private static final double PIVOT_INITIAL_Y_ANGLE = 0.0;
	private static final double PIVOT_INITIAL_X_ANGLE = 0.0;
	private static final double PIVOT_INITIAL_Z_ANGLE = 0.0;
	
	private static final double CAMERA_SPEED = 0.5;
	private static final double CAMERA_ROTATION_SPEED = 0.2;
	private static final double CAMERA_ZOOM_SPEED = 5;
	
	private static final double MAX_HORIZONTAL_MOVEMENT = 500.0;
	private static final double MAX_ROTATION_ANGLE = 90.0;
	private static final double MIN_ROTATION_ANGLE = 0.0;
	private static final double MAX_ZOOM_OUT_DISTANCE = 800.0;
	private static final double MIN_ZOOM_IN_DISTANCE = 200.0;

	private final PerspectiveCamera camera = new PerspectiveCamera(true);
	private Object3D pivot = new Object3D();
	private Object3D pivot2 = new Object3D();
	 
	/**
	 * The constructor.
	 * 
	 * @param parent The parent group.
	 */
	public View(Group parent) 
	{
		resetCamera();
        
        parent.getChildren().add(pivot);
        pivot.getChildren().add(pivot2);
        pivot2.getChildren().add(camera);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        
        Main.LOG.log(Level.INFO, "buildCamera()");
    }
	
	/**
	 * Returns the translation pivot of the camera.
	 * 
	 * @return The translation pivot of the camera.
	 */
	public Object3D getPivot()
	{
		return this.pivot;
	}
	
	/**
	 * Returns the rotation pivot.
	 * 
	 * @return The rotation pivot of the camera.
	 */
	public Object3D getPivot2()
	{
		return this.pivot2;
	}
	
    /**
     * Returns the camera.
     * 
     * @return The perspective camera.
     */
	public PerspectiveCamera getCamera()
	{
		return camera;
	}

	/**
	 * Resets the camera to the initial values.
	 */
	public void resetCamera()
	{
		pivot.getRotation().setY(PIVOT_INITIAL_Y_ANGLE);
        pivot.getRotation().setX(PIVOT_INITIAL_X_ANGLE);
        pivot.getRotation().setZ(PIVOT_INITIAL_Z_ANGLE);
		
		pivot2.getRotation().setY(CAMERA_INITIAL_Y_ANGLE);
        pivot2.getRotation().setX(CAMERA_INITIAL_X_ANGLE);
        pivot2.getRotation().setZ(CAMERA_INITIAL_Z_ANGLE);
        
        pivot2.getPosition().setY(-CAMERA_INITIAL_DISTANCE);
        
        pivot.getPosition().setY(Patient.PIVOT_INITIAL_DISTANCE);
	}
	
	/**
	 * Translates the camera on the X axis.
	 * 
	 * @param horizontal A value of the delta movement in order to change the position in x.
	 */
	public void translateCamera(double horizontal)
	{
		pivot.getPosition().setX(pivot.getPosition().getX() + horizontal * CAMERA_SPEED);
		
		if(pivot.getPosition().getX() > MAX_HORIZONTAL_MOVEMENT)
			pivot.getPosition().setX(MAX_HORIZONTAL_MOVEMENT);
		
		if(pivot.getPosition().getX() < -MAX_HORIZONTAL_MOVEMENT)
			pivot.getPosition().setX(-MAX_HORIZONTAL_MOVEMENT);
	}
	
	/**
	 * Rotates the camera around the X axis.
	 * 
	 * @param vertical A value of the delta movement in order to change the rotation in x.
	 */
	public void rotateCamera(double vertical)
	{
		pivot.getRotation().setX(pivot.getRotation().getX() + vertical * CAMERA_ROTATION_SPEED); 
		
		if(pivot.getRotation().getX() > MAX_ROTATION_ANGLE - 90)
			pivot.getRotation().setX(MAX_ROTATION_ANGLE - 90);
		
		if(pivot.getRotation().getX() < MIN_ROTATION_ANGLE - 90)
			pivot.getRotation().setX(MIN_ROTATION_ANGLE - 90);
	}
	
	/**
	 * Zooms the camera.
	 * 
	 * @param delta A value of the delta scroll movement in order to change the distance of the camera to the x axis.
	 */
	public void zoomCamera(double delta)
	{
		pivot2.getPosition().setY(pivot2.getPosition().getY() + delta * CAMERA_ZOOM_SPEED);
		
		if(pivot2.getPosition().getY() < -MAX_ZOOM_OUT_DISTANCE)
			pivot2.getPosition().setY(-MAX_ZOOM_OUT_DISTANCE);
		
		if(pivot2.getPosition().getY() > -MIN_ZOOM_IN_DISTANCE)
			pivot2.getPosition().setY(-MIN_ZOOM_IN_DISTANCE);
	}
}
