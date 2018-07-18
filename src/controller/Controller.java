package controller;

import controller.levels.Level;
import controller.objects.ControllerObjects;

/**
 * Controller's interface
 */
public interface Controller {
	
	/**
	 * Getter of the objects managed in the controller.
	 * @return the controller object.
	 */
	ControllerObjects getControllerObject();
	
	/**
	 * Getter of the levels.
	 * @return the levels.
	 */
	Level getLevel();

}
