package model;

import model.object.Tank;
import model.utility.Pair;

public interface Model {
    /**
     * Create a {@link Tank} controlled by player.
     * 
     * @param position of Tank
     * @param lifes nr of lifes
     * @param speed speed of movement
     * @param width of Tank
     * @param height of Tank
     */
    void configPlayerTank(Pair<Double, Double> position, int lifes, double speed);
    /**
     * Create a {@link Tank} controlled by computer.
     * 
     * @param position of Tank
     * @param lifes nr of lifes
     * @param speed speed of movement
     * @param width of Tank
     * @param height of Tank
     */
    void configEnemyTank(Pair<Double, Double> position, int lifes, double speed);
    /**
     * Getter for player.
     * @return player's Tank
     */
    Tank getPlayer();
    /**
     * Getter for enemy
     * @return enemie's Tank
     */
    Tank getEnemy();
    /**
     * Getter for bounds of World
     * @return bounds First = width, Second = height
     * @see Pair
     */
    Pair<Double, Double> getBounds();
}
