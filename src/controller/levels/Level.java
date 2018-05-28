package controller.levels;

/**
 * Interface for the levels' control.
 */
public interface Level {
	
	/**
	 * Control if the level is started.
	 * @return true if the level is started, false otherwise.
	 */
	boolean isLevelStarted();
	
	/**
	 * Control if the level is ended.
	 * @return true if the level is ended, false otherwise.
	 */
	boolean isLevelEnded();
	
	/**
	 * Set the current level started.
	 */
	void setLevelStarted();
		
	/**
	 * Set to current level ended.
	 */
	void setLevelEnded();
	
	/**
	 * Getter of the current level play.
	 * @return the current level.
	 */
	Levels getCurrentLevel();
	
	/**
	 * Setter for the current level.
	 * @param currentLevel
	 * 		the current level.
	 */
	/*
	void setCurrentLevel(Levels currentLevel);*/
	
	/**
	 * Update the current level.
	 */
	void updateLevel();
	
	/**
	 * Control if the game is ended
	 * @return true if the game is ended, false otherwise.
	 */
	boolean isGameEnded();

}
