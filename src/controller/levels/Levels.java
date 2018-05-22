package controller.levels;

/**
 * Enumeration for the game levels.
 */
public enum Levels {
	
	LEVEL_1("level1");
	
	private String levelName;
	
	/**
	 * Private constructor.
	 * @param levelName
	 * 		the name of the level.
	 */
	
	private Levels(String levelName) {
		this.levelName = levelName;
	}
	
	/**
	 * Getter of the levels' name.
	 * @return the level's name.
	 */
	public String getName() {
		return this.levelName;
	}

}
