package controller.objects;

import controller.input.KeyboardInput;
import controller.input.MouseInput;

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
	 * Call the static class {@link IA} that decides the next move of the enemy.
	 */
	void moveEnemyTank();
	
	/**
	 * Rotate the player cannon in the position targeted by mouse.
	 * @param mouseInput
	 * 		the {@link MouseInput}.
	 */
	void movePlayerCannon(MouseInput mouseInput);

}
