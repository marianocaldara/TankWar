package controller.filecontroller;

import controller.levels.Levels;

/**
 * Interface to read and write on files.
 */
public interface FileController {
	
	/**
	 * Method to read a level state from file.
	 */
	void loadLevel();
	
	/**
	 * Method to save a level state on file.
	 */
	void saveLevel();
	
	/**
	 * Setter for the level to load.
	 * @param currentLevel
	 * 		the level to load.
	 */
	void setLevel(Levels currentLevel);
	

}
