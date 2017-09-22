package userinterface.guihandler;

import java.text.NumberFormat;

import application.Simulation;
import content.Arm;
import content.Patient;
import content.Tissue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Manuel Gallina
 * @author Luca Benedetti
 */
public final class WindowHandler 
{
	/**
	 * The title of the window.
	 */
	public static final String WINDOW_TITLE = "Laparoscopy";
	
	/**
	 * The width resolution of the application window measured in pixels.
	 */
	public static final double MAIN_WINDOW_WIDTH_RESOLUTION = 1024;
	
	/**
	 * The height resolution of the application window measured in pixels.
	 */
	public static final double MAIN_WINDOW_HEIGHT_RESOLUTION = 768;
	
	//If main width is 1024 this option should be set to 2/3.
	//If main width is 1920 this option should be set to 4/5.
	/**
	 * The width of the 3d view window measured in pixels.
	 */
	public static final double SCENE3D_WIDTH_RESOLUTION = MAIN_WINDOW_WIDTH_RESOLUTION * 2/3;
	
	/**
	 * The height of the 3d view window measured in pixels.
	 */
	public static final double SCENE3D_HEIGHT_RESOLUTION = MAIN_WINDOW_HEIGHT_RESOLUTION;
	
	private static final String INFO_BOX_TITLE = "Info paziente";
	private static final double INFO_BOX_WIDTH_RESOLUTION = MAIN_WINDOW_WIDTH_RESOLUTION - SCENE3D_WIDTH_RESOLUTION;
	private static final double INFO_BOX_HEIGHT_RESOLUTION = MAIN_WINDOW_HEIGHT_RESOLUTION;
	private static final double INFO_BOX_GRID_VGAP = 8;
	private static final double INFO_BOX_GRID_HGAP = INFO_BOX_WIDTH_RESOLUTION * 2 / 3 - 50;
		
	private static final double SEPARATOR_MAX_WIDTH = INFO_BOX_WIDTH_RESOLUTION - 20;
	
	private static final String TOOL_SELECTION_TITLE = "Selezione strumento";
	private static final double TOOL_GRID_HGAP = INFO_BOX_WIDTH_RESOLUTION / 4 - 10;
	private static final double TOOL_GRID_VGAP = 0;
	
	private static final double COMMANDS_GRID_HGAP = 10;
	private static final double COMMANDS_GRID_VGAP = 2;
	
	private static final double TISSUE_VOLUME = Simulation.TISSUE.getVolume();
	private static final double PATIENT_VOLUME = (Patient.X_VALUE*Patient.Y_VALUE*Patient.Z_VALUE)-TISSUE_VOLUME;
	
	/**
	 * A private constructor to override the default public one.
	 * This is useful to prevent the instantiation of this class.
	 */
    private WindowHandler(){}
	
	/**
	 * This method creates the lateral window with the patient's informations and the 
	 * tool selection interface. 
	 * 
	 * @param patient The patient used in this simulation.
	 * @param leftArm The left arm.
	 * @param rightArm The right arm.
	 * 
	 * @return The lateral window.
	 */
	public static VBox lateralWindow(Patient patient, Arm leftArm, Arm rightArm)
	{
		VBox lateralWindow = new VBox();
			lateralWindow.setSpacing(10);
		
		VBox infoBox = infoBox(patient);
			infoBox.setId("infoBox");
				
		Separator separator = new Separator();
			separator.setId("separator");
			separator.setOrientation(Orientation.HORIZONTAL);
			separator.setMaxWidth(SEPARATOR_MAX_WIDTH);
			separator.setHalignment(HPos.CENTER);
			separator.setValignment(VPos.CENTER);
		
		lateralWindow.setPrefSize(INFO_BOX_WIDTH_RESOLUTION, INFO_BOX_HEIGHT_RESOLUTION);
		
		VBox toolSelection = toolSelection(leftArm, rightArm);
			toolSelection.setId("toolSelection");
		
		Separator separator2 = new Separator();
			separator2.setId("separator");
			separator2.setOrientation(Orientation.HORIZONTAL);
			separator2.setMaxWidth(SEPARATOR_MAX_WIDTH);
			separator2.setHalignment(HPos.CENTER);
			separator2.setValignment(VPos.CENTER);
			
		VBox percentage = percentage(0,0);
		
		Separator separator3 = new Separator();	
			separator3.setId("separator");
			separator3.setOrientation(Orientation.HORIZONTAL);
			separator3.setMaxWidth(SEPARATOR_MAX_WIDTH);
			separator3.setHalignment(HPos.CENTER);
			separator3.setValignment(VPos.CENTER);
			
		VBox commands = commands();	
				
		lateralWindow.getChildren().addAll(infoBox, separator, toolSelection, separator2, percentage, separator3, commands);
		
		return lateralWindow;
	}
	
	/**
	 * This method creates the patient's info box.
	 */
	private static VBox infoBox(Patient patient)
	{
		VBox infoBox = new VBox();
		
		Label title = new Label(INFO_BOX_TITLE);
		
		GridPane grid = new GridPane();
			grid.setId("infoBoxGrid");
			grid.setHgap(INFO_BOX_GRID_HGAP);
			grid.setVgap(INFO_BOX_GRID_VGAP);
	
		Label[] attributes = new Label[patient.getInfosLength()];
		Label[] data = new Label[patient.getInfosLength()];
		
		for(int i = 0; i < patient.getInfosLength(); i++)
		{
			attributes[i] = new Label(patient.getLabel(i));
			data[i] = new Label(patient.getData(i));
			
			grid.add(attributes[i], 0, i);
			grid.add(data[i], 1, i);
		}
		
		infoBox.getChildren().addAll(title, grid);
		
		return infoBox;
	}

	/**
	 * Creates the tool selection interface.
	 */
	private static VBox toolSelection(Arm leftArm, Arm rightArm) 
	{
		VBox toolSelection = new VBox();
	
		Label title = new Label(TOOL_SELECTION_TITLE);
	
		GridPane grid = new GridPane();
			grid.setId("toolSelectionGrid");
			grid.setHgap(TOOL_GRID_HGAP);
			grid.setVgap(TOOL_GRID_VGAP);
	
		ObservableList<String> leftOptions = FXCollections.observableArrayList();
		ObservableList<String> rightOptions = FXCollections.observableArrayList();
		
		for(int i = 0; i < leftArm.getToolList().size(); i++)
		{
			leftOptions.add(leftArm.getToolList().get(i).getType());
		}
		
		for(int i = 0; i < rightArm.getToolList().size(); i++)
		{
			rightOptions.add(rightArm.getToolList().get(i).getType());
		}
		
		ComboBox<String> rightTool = new ComboBox<>(rightOptions);
			rightTool.setId("tool");
			rightTool.getSelectionModel().selectedItemProperty().addListener(new  
				ChangeListener<String>() 
				{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					for(int i = 0; i < rightArm.getToolList().size(); i++)
						if(newValue == rightArm.getToolList().get(i).getType())
						{
							rightArm.setSelectedTool(rightArm.getToolList().get(i));
						}
				}
				});	
		
			ComboBox<String> leftTool = new ComboBox<>(leftOptions);
			leftTool.setId("tool");
			leftTool.getSelectionModel().selectedItemProperty().addListener(new  
				ChangeListener<String>() 
				{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					for(int i = 0; i < leftArm.getToolList().size(); i++)
						if(newValue == leftArm.getToolList().get(i).getType())
						{
							leftArm.setSelectedTool(leftArm.getToolList().get(i));
						}
				}
				});	
			
		Label selectionRight = new Label("Strumento dx");
		Label selectionLeft = new Label("Strumento sx");
		
		grid.add(selectionRight, 0, 0);
		grid.add(selectionLeft, 0, 1);
		grid.add(rightTool, 1, 0);
		grid.add(leftTool, 1, 1);
		
		toolSelection.getChildren().addAll(title, grid);
		
		return toolSelection;
	}
	
	/**
	 * @author Luca Benedetti
	 * create the progress' percentage window
	 */
	public static VBox percentage(int patient, int tumor){
		VBox percentage = new VBox();
		NumberFormat numForm = NumberFormat.getInstance();
	     numForm.setMaximumFractionDigits(2);
	     numForm.setMinimumFractionDigits(2);
		
		Label title = new Label("Percentuale completamento");
		Label patientPercent = new Label("Organo rimosso "+numForm.format((patient/PATIENT_VOLUME)*100)+"%");
		Label tumorPercent = new Label("Tumore rimosso "+numForm.format((tumor/TISSUE_VOLUME)*100)+"%");
		
		GridPane grid = new GridPane();
			grid.setId("infoBoxGrid");
			grid.setHgap(10);
			grid.setVgap(10);
			
		grid.add(patientPercent, 0, 0);
		grid.add(tumorPercent, 0, 1);
		
		percentage.getChildren().addAll(title, grid);
		
		return percentage;
		
	}
	
	/**
	 * @author Luca Benedetti
	 * create the commands interface
	 */
	public static VBox commands(){
		VBox commands = new VBox();
		Label title = new Label("Comandi");
		
		GridPane grid = new GridPane();
			grid.setId("infoBoxGrid");
			grid.setHgap(COMMANDS_GRID_HGAP);
			grid.setVgap(COMMANDS_GRID_VGAP);
		
		Label sx = new Label("Muovi a sinistra:");
		Label dx = new Label("Muovi a destra:");
		Label up = new Label("Alza:");
		Label down = new Label("Abbassa:");
		Label dig = new Label("Affonda:");
		Label extract = new Label("Estrai:");
		Label rotate = new Label("Ruota strumento:");
		Label bend = new Label("Fletti strumento:");
		Label reset = new Label("Reset:");
		Label axis = new Label("Hide axis:");
		
		grid.add(new Label("Braccio:"),0,0);
		grid.add(new Label("Destro"),1,0);
		grid.add(new Label("Sinistro"), 2,0);
		grid.add(sx,0,1);
		grid.add(new Label("A"),1,1);
		grid.add(new Label("J"),2,1);
		grid.add(dx,0,2);
		grid.add(new Label("D"),1,2);
		grid.add(new Label("L"),2,2);
		grid.add(up,0,3);
		grid.add(new Label("E"),1,3);
		grid.add(new Label("O"),2,3);
		grid.add(down,0,4);
		grid.add(new Label("Q"),1,4);
		grid.add(new Label("U"),2,4);
		grid.add(dig,0,5);
		grid.add(new Label("W"),1,5);
		grid.add(new Label("I"),2,5);
		grid.add(extract,0,6);
		grid.add(new Label("S"),1,6);
		grid.add(new Label("K"),2,6);
		grid.add(rotate,0,7);
		grid.add(new Label("V-B"),1,7);
		grid.add(new Label("T-Y"),2,7);
		grid.add(bend,0,8);
		grid.add(new Label("N-M"),1,8);
		grid.add(new Label("G-H"),2,8);
		
		grid.add(reset,0,9);
		grid.add(new Label("\tR"),1,9);
		grid.add(axis,0,10);
		grid.add(new Label("\tX"),1,10);
				
		commands.getChildren().addAll(title,grid);
		
	return commands;	
	}
}