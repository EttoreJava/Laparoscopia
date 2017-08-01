package content.tool;

import java.net.URL;

/**
 * @author Manuel Gallina
 */
public class Scalpel extends Tool
{	
	private static final double SCALE = 2.0;
	private static final String ACTION = "Cutting";
	/*
	 * The file path is relative to the bin folder.
	 */
	private static final String MESH_PATH = "/models/Knife.obj";
	
	/**
	 * Constructor for the Forceps class.
	 * 
	 * @param type The tool type.
	 */
	public Scalpel(String type)
	{
		super(type);
		
		setAction(ACTION);
		
		URL objFileUrl = this.getClass().getResource(MESH_PATH);
		
		this.setShape(getParent().importObjMesh(objFileUrl));
		
		this.getParent().setScale(SCALE);
		this.getParent().setRotation(0, 180.0, 0.0);
		this.getParent().setPosition(-5.0, 0.0, -5.9);
		
		this.getParent().getChildren().add(this.getShape());
	}
	
	@Override
	public String act() 
	{		
		return super.act();
	}

}
