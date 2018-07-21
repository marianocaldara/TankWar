package controller.objects;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	void movePlayerTank(KeyEvent keyInput, boolean b);
	
	/**
	 * Rotate the player cannon in the position targeted by mouse.
	 * @param mouseInput
	 * 		the {@link MouseInput}.
	 */
	void movePlayerCannon(MouseEvent mouseInput);
	
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
	
	/**
	 * Getter of the lifes of the player {@link Tank}.
	 * @return the current lifes of the tank.
	 */
	int getPlayerLifes();
	
	/**
	 * Getter of the lifes of the enemy {@link Tank}.
	 * @return the current lifes of the tank.
	 */
	int getEnemyLifes();
	
	/**
	 * Getter of the player {@link Cannon} angle.
	 * @return the angle target by the player {@link Cannon}.
	 */
	double getPlayerAngle();
	
	/**
	 * Getter of the enemy {@link Cannon} angle.
	 * @return the angle target by the enemy {@link Cannon}.
	 */
	double getEnemyAngle();
	
	/**
	 * Getter of the tank dimension.
	 * @return a {@link Pair} of the {@link Tank} dimension.
	 */
	Pair<Double, Double> getTankDimension();
	
	/**
	 * Getter of the cannon dimension.
	 * @return a {@link Pair} of the {@link Cannon} dimension.
	 */
	Pair<Double, Double> getCannonDimension();
	
	/**
	 * Getter of the player cannon position.
	 * @return a {@link Pair} of the {@link Cannon} position.
	 */
	Pair<Double, Double> getPlayerCannonPosition();
	
	/**
	 * Getter of the enemy cannon position.
	 * @return a {@link Pair} of the {@link Cannon} position.
	 */
	Pair<Double, Double> getEnemyCannonPosition();
	

}
