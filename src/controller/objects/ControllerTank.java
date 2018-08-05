package controller.objects;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.utility.Pair;

/**
 * Interface to control {@link Tank}
 */
public interface ControllerTank {
	
	/**
	 * Modify the player {@link Input} according to the keyboard input.
	 * @param event
	 * 		the {@link KeyEvent}.
	 * @param b
	 * 		a {@link Boolean}. It's true if the key is pressed, false otherwise.
	 */
	void movePlayerTank(KeyEvent event, boolean b);
	
	/**
	 * Update the two {@link Tank} and check the collisions.
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
	 * Getter of the {@link View} dimension of the {@link Tank}.
	 * @return a {@link Pair} of dimension of the tank.
	 */
	Pair<Double, Double> getTankDimension();
	
	/**
	 * Check if the player {@link Tank} is alive or not.
	 * @return true if it's alive, false otherwise.
	 */
	boolean isPlayerAlive();
	
	/**
	 * Check if the enemy {@link Tank} is alive or not.
	 * @return true if it's alive, false otherwise.
	 */
	boolean isEnemyAlive();
	
	/**
	 * Getter of the {@link View} dimension of the {@link Cannon}.
	 * @return a {@link Pair} of dimension of the cannon.
	 */
	Pair<Double, Double> getCannonDimension();
	
	/**
	 * Getter of the {@link View} position of the player {@link Cannon}.
	 * @return a {@link Pair} of position of the player cannon.
	 */
	Pair<Double, Double> getPlayerCannonPosition();
	
	/**
	 * Getter of the {@link View} position of the enemy {@link Cannon}.
	 * @return a {@link Pair} of position of the enemy cannon.
	 */
	Pair<Double, Double> getEnemyCannonPosition();
	
	/**
	 * Rotate the player cannon in the position targeted by mouse.
	 * @param event
	 * 		the {@link MouseEvent}
	 */
	void movePlayerCannon(MouseEvent event);
	
	/**
	 * Getter of the player {@link Cannon} angle.
	 * @return the angle.
	 */
	double getPlayerAngle();
	
	/**
	 * Getter of the enemy {@link Cannon} angle.
	 * @return the angle.
	 */
	double getEnemyAngle();
	

}
