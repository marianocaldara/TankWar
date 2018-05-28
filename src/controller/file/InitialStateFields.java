package controller.file;

/**
 * Enumeration for the initial fields to initialize the model world.
 */
public enum InitialStateFields {
	
	POSX("X"), 
	POSY("Y"),
	SPEED("speed"),
	LIFES("lifes");
	
	private String fieldName;
	
	/**
	 * Private constructor.
	 * @param fieldName
	 * 		the name of the field.
	 */
	
	private InitialStateFields(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
	 * Getter of the levels' name.
	 * @return the level's name.
	 */
	public String getName() {
		return this.fieldName;
	}

}
