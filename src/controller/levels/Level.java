package controller.levels;

import controller.GameLoop;
import controller.objects.ControllerObjects;

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
	 * Set the current level started, load the current level file,initialize the {@link ControllerObjects} and start the {@link GameLoop}.
	 */
	void setLevelStarted();
		
	/**
	 * Set to current level ended and stop the {@link GameLoop}.
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
	void setCurrentLevel(Levels currentLevel);
	
	/**
	 * Control if the game is ended
	 * @return true if the game is ended, false otherwise.
	 */
	boolean isGameEnded();
	
}
