package application;
	
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.stage.Stage;

import userinterface.graphichandler.Axis;
import userinterface.graphichandler.View;
import userinterface.guihandler.WindowHandler;
import userinterface.userinputshandlers.KeyboardInputsHandler;
import userinterface.userinputshandlers.MouseInputsHandler;
import userinterface.graphichandler.graphicobject.Object3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import content.tool.Forceps;
import content.tool.Scalpel;
import content.tool.Tool;
import content.tool.VacuumCleaner;

/**
 * @author Michele Franceschetti
 * @author Manuel Gallina
 * @author Ettore Gorni
 * @author Luca Benedetti
 */

public class Main extends Application 
{
	/**
	 * The logger of the application.
	 */
	public static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	/*
     * The list of tools available (can be improved).
     */
	
    private static final ArrayList<Tool> TOOL_LIST = new ArrayList<>(Arrays.asList(
    		new Forceps("Forcipe"), 
    		new Scalpel("Bisturi"), 
    		new VacuumCleaner("Aspiratore"))
    		);

	@Override
	public void start(Stage window) 
    {
		try 
		{	
			//Log settings
			LOG.setLevel(Level.SEVERE);		
			
			/////////////////////////////////////////////////////////////////////
			
			//3D view
			final Group root3D = new Group();
		    final Object3D world = new Object3D();
		    final Object3D axis = new Object3D();
		    
		    Axis axisMesh = new Axis(axis);
		    
		    final View camera = new View(world);
		    final SubScene scene3D = new SubScene(
		    		root3D, WindowHandler.SCENE3D_WIDTH_RESOLUTION, WindowHandler.SCENE3D_HEIGHT_RESOLUTION, 
		    		true, SceneAntialiasing.BALANCED);
		    
		    final KeyboardInputsHandler keyboard = new KeyboardInputsHandler();
		    final MouseInputsHandler mouse = new MouseInputsHandler();
		    

		    
		    Simulation.LEFT_ARM.setToolList(TOOL_LIST);
			Simulation.RIGHT_ARM.setToolList(TOOL_LIST);
		  
		    		   
		    root3D.getChildren().add(world);
		    		    		    
	        world.getChildren().addAll(axisMesh.getParent(), Simulation.PATIENT.getParent());
	        
	        world.getChildren().addAll(Simulation.TISSUE.getParent());
	        scene3D.setCamera(camera.getCamera());
			scene3D.setFill(Paint.valueOf("lightgray"));      
	        
			world.getChildren().addAll(Simulation.LEFT_ARM.getUpperArm(), Simulation.RIGHT_ARM.getUpperArm());
	        
			/////////////////////////////////////////////////////////////////////
			
			
			
			
	        //Main window
	        HBox mainPane = new HBox();
	        
	        Scene guiScene = new Scene(mainPane, WindowHandler.MAIN_WINDOW_WIDTH_RESOLUTION, WindowHandler.MAIN_WINDOW_HEIGHT_RESOLUTION);
	        guiScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
	        VBox lateralWindow = WindowHandler.lateralWindow(Simulation.PATIENT, Simulation.LEFT_ARM, 
	        													Simulation.RIGHT_ARM);
			lateralWindow.setId("lateralWindow");
	        
	        keyboard.handleKeyboard1(guiScene, Simulation.LEFT_ARM, Simulation.RIGHT_ARM, axisMesh, Simulation.PATIENT, Simulation.TISSUE, lateralWindow);
	        mouse.handleMouse(guiScene, camera);
	        
	        mainPane.getChildren().addAll(scene3D, lateralWindow);
			
			window.setScene(guiScene);
			window.setTitle(WindowHandler.WINDOW_TITLE);
			window.show();	
						
		}
		catch(Exception e) 
		{
			LOG.log(Level.INFO, "EXCEPTION: ", e);
		}
		finally
		{
			Object3D.OBJ_IMPORTER.close();
		}
    }

	/**
	 * The main method.
	 * 
	 * @param args The string arguments passed to the main from file or console.
	 */
	public static void main(String[] args) 
    {
		launch(args);
	}
}
