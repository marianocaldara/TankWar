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
    void configPlayerTank(Pair<Double, Double> position, int lifes, double speed, double width, double height);
    /**
     * Create a {@link Tank} controlled by computer.
     * 
     * @param position of Tank
     * @param lifes nr of lifes
     * @param speed speed of movement
     * @param width of Tank
     * @param height of Tank
     */
    void configEnemyTank(Pair<Double, Double> position, int lifes, double speed, double width, double height);
    /**
     * Getter for player.
     * @return player's Tank
     */
    Tank getPlayer();
    /**
     * Getter for enemie
     * @return enemie's Tank
     */
    Tank getEnemy();
    /**
     * Getter for bounds of World
     * @return bounds
     * @see Pair
     */
    Pair<Double, Double> getBounds();
    /**
     * Setter for bounds of world
     * @param dimensions of world
     * @see Pair
     */
    void setBounds(Pair<Double, Double> dimensions);
}
