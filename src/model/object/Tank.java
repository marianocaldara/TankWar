package model.object;

import model.input.InputImpl;
import model.utility.Pair;
/**
 * 
 * Model Inteface of Tank
 * <p>
 * Tank is the main object of game, he could be controlled by Player or by Computer.
 * <p>
 * Moving, shooting are the main features of Tank.
 * 
 * @see controller
 * @see Pair
 * @see InputImpl
 */
public interface Tank {
        /**
         * Getter for status of life of Tank.
         * @return boolean true if it is alive, false if it losts all lifes.
         */
	boolean isAlive();
	/**
	 * Getter for number of life of Tank.
	 * @return int of lifes.
	 */
	int getLifes();
	/**
	 * Setter for position of Tank
	 * @param position
	 *     in {@link Pair}
	 */
	void setPosition(Pair<Double, Double> position);
	/**
	 * Getter for position.
	 * @return the position in {@link Pair}
	 */
	Pair<Double, Double> getPosition();
	/**
	 * Order to its cannon to shot.
	 * @return the {@link Projectile} just shotted.
	 */
	Projectile shot();
	/**
	 * Update the position of Tank and the angle of its cannon.
	 * @param i 
	 *     {@link InputImpl} with next movement and angle to do.
	 */
	void update(InputImpl i); 
	/**
	 * Setter for speed.
	 * @param speed
	 *     the new speed of projectile
	 */
	void setSpeed(double speed);
	/**
	 * Reduce number of lifes
	 * @param damage
	 *     nr lifes to decrease
	 */
	void damage(int damage);
	/**
	 * Get a dimension of Tank
	 * @return {@link Pair} according to height and width of Tank
	 * @see controller.collision
	 */
	Pair<Double, Double> getDimension();
	/**
	 * Get angle of cannon of {@link Tank}
	 * @return angle in double
	 */
	double getAngle();
        /**
         * Getter for boolean Friendly of {@link Tank}
         * @return boolean, true for user, false for enemy.
         */
	boolean isFriendly();
	
}
