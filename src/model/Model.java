package model;

import model.input.InputImpl;
import model.object.Tank;
import model.utility.Pair;

public interface Model {
    /**
     * Create a {@link Tank} controlled by player.
     * 
     * @param position of Tank
     * @param lifes nr of lifes
     * @param speed speed of movement
     * @param friendly boolean for user or enemy
     */
    void configPlayerTank(Pair<Double, Double> position, int lifes, double speed, boolean friendly);
    /**
     * Create a {@link Tank} controlled by computer.
     * 
     * @param position of Tank
     * @param lifes nr of lifes
     * @param speed speed of movement
     * @param friendly boolean for user or enemy
     */
    void configEnemyTank(Pair<Double, Double> position, int lifes, double speed, boolean friendly);
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
     * Getter for bounds of World.
     * @return bounds First = width, Second = height
     * @see Pair
     */
    Pair<Double, Double> getBounds();
    /**
     * Getter for Input Player.
     * @return {@link InputImpl} of player.
     */
    InputImpl getInputPlayer();
    /**
     * Getter for Input Enemy
     * @return {@link InputImpl} of enemy.
     */
    InputImpl getInputEnemy();
}