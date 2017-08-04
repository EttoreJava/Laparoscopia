package content;

/**
 * This class isn't been used yet.
 * 
 * @author Manuel Gallina
 * @author Michele Franceschetti
 */
//Not yet implemented
public class Tissue 
{
	private static final String DEFAULT_NAME = "Default";
	private static final String DEFAULT_TYPE = "Unknown";
	private String name;
	private String type;
	
	/**
	 * Default constructor.
	 */
	public Tissue()
	{
		this.name = DEFAULT_NAME;
		this.type = DEFAULT_TYPE;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param name The name of this tissue.
	 * @param type The type of this tissue.
	 */
	public Tissue(String name, String type)
	{
		this.name = name;
		this.type = type;
	}
	
	/** 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
