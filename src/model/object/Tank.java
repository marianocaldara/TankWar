package model.object;

import javafx.geometry.Rectangle2D;
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
         * @return boolean true if it is alive, false if it losts all lifes
         */
	boolean isAlive();
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
	 * Keep between borders the tank.
	 * @param arenaWidth
	 *     Widht of border of Game.
	 * @param arenaHeight
	 *     Height of border of Game.
	 */
	void keepBetweenBorders(double arenaWidth, double arenaHeight);
	/**
	 * Reduce number of lifes
	 * @param damage
	 *     nr lifes to decrease
	 */
	void damage(int damage);
	/**
	 * Get a bounds of Tank
	 * @return
	 *     Rectangle according to height and widht of Tank
	 * @see controller.collision
	 */
	Rectangle2D getBounds();
}