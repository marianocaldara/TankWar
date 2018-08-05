package controller;

import controller.levels.Level;
import controller.objects.AI;
import controller.objects.ControllerObjects;
import controller.objects.ControllerTank;

/**
 * Controller's interface
 */
public interface Controller {
	
	/**
	 * Getter of the {@link Tank} managed in the controller.
	 * @return the {@link ControllerTank}.
	 */
	ControllerObjects getControllerObjects();
	
	/**
	 * Getter of the levels.
	 * @return the levels.
	 */
	Level getLevel();
	
	/**
	 * Method that allows to start the {@link GameLoopImpl}.
	 */
	void startGameLoop();
	
	/**
	 * Getter of the {@link GameLoop}.
	 * @return the game loop.
	 */
	GameLoop getGameLoop();
	
	/**
	 * Method that initialize the {@link ControllerObjects} and the {@link AI}.
	 */
	void initializeObjects();
	
	/**
	 * Setter of the period of shot of the enemy {@link Tank}.
	 * @param time
	 * 			the time in ms between two enemy shots.
	 */
	void setTimeToShot(double time);

}
