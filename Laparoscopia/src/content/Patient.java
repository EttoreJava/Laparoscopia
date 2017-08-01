package content;

import java.net.URL;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import userinterface.graphichandler.graphicobject.Object3D;

/**
 * @author Manuel Gallina
 */

public class Patient 
{
	private static final String[] DEFAULT_INFO = {"Età", "def", "Peso", "def"};
	
	private static final double SCALE = 15.0;
	public static final double PIVOT_INITIAL_DISTANCE = 200.0;
	
	private static final Color DIFFUSE_COLOR = Color.DARKRED;
	private static final Color SPECULAR_COLOR = Color.LIGHTPINK;
	
	private PatientInformation[] infos;
	
	private Object3D parent = new Object3D();
	
	//Constructors
	/**
	 * Default constructor for the Patient class.
	 */
	public Patient()
	{
		this.infos = new PatientInformation[DEFAULT_INFO.length / 2];
		this.initializeInfos(DEFAULT_INFO);
		
		createMesh();
	}
	
	/**
	 * Constructor for the Patient class.
	 * 
	 * The informations should be in the appropriate order to be correctly displayed.
	 * The order is: {Property1, Value1, ..., Property_n, Value_n}.
	 * 
	 * @param patientInfo The list of information data. 
	 */
	public Patient(String[] patientInfo)
	{
		this.infos = new PatientInformation[patientInfo.length / 2];
		this.initializeInfos(patientInfo);
	
		createMesh();
	}
	
	//Getters and setters
	
	/**
	 * Returns the length of the infos array.
	 * 
	 * @return The length of the infos array. 
	 */
	public int getInfosLength()
	{
		return infos.length;
	}
	
	/**
	 * Returns the label at the given position.
	 * 
	 * @param index The index.
	 * @return The label.
	 */
	public String getLabel(int index) 
	{
		return infos[index].label;
	}
	
	/**
	 * Sets the label at the given position.
	 * 
	 * @param index The index.
	 * @param label The label to set.
	 */
	public void setLabel(int index, String label) 
	{
		this.infos[index].label = label;
	}

	/**
	 * Returns the data at the given position.
	 * 
	 * @param index The index.
	 * @return The data.
	 */
	public String getData(int index) 
	{
		return infos[index].data;
	}

	/**
	 * Sets the data at the given position.
	 * 
	 * @param index The index.
	 * @param data The data to set.
	 */
	public void setData(int index, String data) 
	{
		this.infos[index].data = data;
	}
	
	/*
	 * Initializes the patient's attributes.
	 * 
	 * @param patientAttributes The patient attributes to set.
	 */
	private void initializeInfos(String[] patientAttributes)
	{
		for(int i = 0, j = 0; i < patientAttributes.length / 2; i++, j += 2)
		{
			infos[i] = new PatientInformation(patientAttributes[j], patientAttributes[j + 1]);
		}
	}
	
	/**
	 * Sets the patient's attributes.
	 * 
	 * @param patientAttributes The patient attributes to set.
	 */
	public void setInfos(String[] patientAttributes) 
	{
		PatientInformation[] temp = new PatientInformation[patientAttributes.length / 2];
		
		for(int i = 0, j = 0; i < patientAttributes.length / 2; i++, j += 2)
		{
			temp[i] = new PatientInformation(patientAttributes[j], patientAttributes[j + 1]);
		}
		
		this.infos = temp;
	}

	/**
	 * Returns the parent transform of the patient's mesh.
	 * 
	 * @return The parent.
	 */
	public Object3D getParent() 
	{
		return parent;
	}
	
	/**
	 * Sets the parent transform of the patient's mesh.
	 * 
	 * @param parent The parent to set.
	 */
	public void setParent(Object3D parent) 
	{
		this.parent = parent;
	}
	
	//Methods
	/**
	 * Creates the mesh for this patient and adds a default material.
	 */
	public void createMesh()
	{		
		URL objFileUrl = this.getClass().getResource("/models/Heart.obj");
		
		MeshView patientMesh = parent.importObjMesh(objFileUrl);
		 //TODO cancellare
		
		patientMesh.setRotate(90);
		patientMesh.setTranslateY(PIVOT_INITIAL_DISTANCE);
		patientMesh.setScaleX(SCALE);
		patientMesh.setScaleY(SCALE);
		patientMesh.setScaleZ(SCALE);
		
		final PhongMaterial patientColor = new PhongMaterial();
		patientColor.setDiffuseColor(DIFFUSE_COLOR);
		patientColor.setSpecularColor(SPECULAR_COLOR);
		
		patientMesh.setMaterial(patientColor);
		
		parent.getChildren().add(patientMesh);
	}
	
	/**
     * Returns the string representation of this patient.
     */
	@Override
	public String toString()
	{
		StringBuilder description = new StringBuilder();
		
		for(PatientInformation attribute : infos)
		{
			description.append(attribute.toString() + "%n");
		}
		
		return description.toString();
	}
	
	/*
	 * An inner class used to store the informations of the patient.
	 * It's made up of a label and a data.
	 */
	private class PatientInformation 
	{
		private static final String DEFAULT_LABEL = "Label";
		private static final String DEFAULT_DATA = "Data";
		
		private String label;
		private String data;
		
		/**
		 * Default constructor.
		 */
		@SuppressWarnings("unused")
		public PatientInformation()
		{
			this.label = DEFAULT_LABEL;
			this.data = DEFAULT_DATA;
		}
		
		/**
		 * Constructor.
		 * 
		 * @param label The label.
		 * @param data The information.
		 */
		public PatientInformation(String label, String data)
		{
			this.label = label;
			this.data = data;
		}
				
		@Override
		public String toString()
		{
			StringBuilder description = new StringBuilder();
			
			description.append(label + ": " + data);
			
			return description.toString();
		}
	}
 
}
