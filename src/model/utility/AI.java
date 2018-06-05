package model.utility;

import java.util.HashMap;
import java.util.Map;

import model.input.InputImpl;
import model.object.Tank;

/**
 * 
 * Class AI implements methods to make enemy tank act like a player
 *
 */
public class AI {

    private final static Pair<Double, Double> DIMENSION = new Pair<>(600.0, 400.0);
    private static Map<Direction, Boolean> movement = new HashMap<>();
    private final static double CRITICAL_DISTANNCE = (DIMENSION.getFirst() + DIMENSION.getSecond())/3;
    
    /**
     * The act method decides how to move the enemy and when to make it shot
     * 
     * @param enemy
     *  the enemy tank, the one to be moved by IA
     * @param player
     *  the player tank, the one to be followed by enemy
     * @return
     *  the the input for the enemy tank
     */
    public static InputImpl act(Tank enemy, Tank player) {
        if (Calculate.distance(enemy.getPosition(), player.getPosition()) > CRITICAL_DISTANNCE) {
            if (Calculate.probability() < 75) {
                goCloser(enemy, player);
            } else {
                goAway(enemy, player);
                enemy.shot();
            }
        } else {
            if (Calculate.probability() < 75) {
                goAway(enemy, player);
            } else {
                goCloser(enemy, player);
                enemy.shot();
            }
        }
        if (Calculate.probability() < 15) {
            enemy.shot();
        }
        return new InputImpl(movement, player.getPosition());
    }

    /**
     * Move the enemy tank closer to the player tank
     * 
     * @param enemy
     *          the enemy tank, the one to be moved by IA
     * @param player
     *          the player tank, the one to be followed by enemy
     */
    private static void goCloser(Tank enemy, Tank player) {
        // RIGHT and LEFT
        if (enemy.getPosition().getFirst() - player.getPosition().getFirst() > 0) {
            movement.put(Direction.LEFT, true);
            movement.put(Direction.RIGHT, false);
        } else {
            movement.put(Direction.RIGHT, true);
            movement.put(Direction.LEFT, false);
        }
        // UP and DOWN
        if (enemy.getPosition().getSecond() - player.getPosition().getSecond() > 0) {
            movement.put(Direction.UP, true);
            movement.put(Direction.DOWN, false);
        } else {
            movement.put(Direction.DOWN, true);
            movement.put(Direction.UP, false);
        }
    }

    /**
     * Move the enemy tank away from the player tank
     * 
     * @param enemy
     *          the enemy tank, the one to be moved by IA
     * @param player
     *          the player tank, the one to be followed by enemy
     */
    private static void goAway(Tank enemy, Tank player) {
        // Call goCloser() and then invert boolean values to make the opposite movement
        goCloser(enemy, player);
        movement.forEach((d,b) -> {
            if (b == true) {
                b = false;
            } else {
                b = true;
            }
        });
    }

}
