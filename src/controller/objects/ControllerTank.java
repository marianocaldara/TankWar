package controller.objects;

import controller.input.KeyboardInput;
import controller.input.MouseInput;
import model.utility.Pair;

/**
 * Interface to control {@link Tank}
 */
public interface ControllerTank {
	
	/**
	 * Set a map that rappresents the player {@link Tank} movements taken in input by keyboard {@link KeyboardInput}.
	 * @param keyInput
	 * 		the {@link KeyboardInput}
	 * @param b
	 * 		a boolean to specify if the key is pressed or released.
	 */
	void movePlayerTank(KeyboardInput keyInput, boolean b);
	
	/**
	 * Rotate the player cannon in the position targeted by mouse.
	 * @param mouseInput
	 * 		the {@link MouseInput}.
	 */
	void movePlayerCannon(MouseInput mouseInput);
	
	/**
	 * Update the position and the target of the two {@link Tank}.
	 */
	void updateTank();
	
	/**
	 * Getter of the {@link View} position of the player {@link Tank}.
	 * @return the new position as a {@link Pair}.
	 */
	Pair<Double, Double> getPlayerPosition();
	
	/**
	 * Getter of the {@link View} position of the enemy {@link Tank}.
	 * @return the new position as a {@link Pair}.
	 */		
	Pair<Double, Double> getEnemyPosition();

}
