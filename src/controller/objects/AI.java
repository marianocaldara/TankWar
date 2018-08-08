package controller.objects;

import java.util.Comparator;

import java.util.List;

import java.util.stream.Collectors;

import model.input.Input;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Calculate;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Class AI implements methods to make enemy tank act like a player.
 */
public class AI {

    private final static int CRITICAL_DISTANCE = 300;
    private static Pair<Double, Double> DIMENSION;
    private static Input ENEMY_INPUT;
    private static final double MIN_DISTANCE = 70;

    /**
     * Initialize the fields of the class.
     * 
     * @param dimension
     *            the dimension of the {@link World}.
     * @param enemyInput
     *            the enemy {@link Input}.
     */
    public static void initialize(Pair<Double, Double> dimension, Input enemyInput) {
        DIMENSION = dimension;
        ENEMY_INPUT = enemyInput;
    }

    /**
     * The method act manage the movement of the enemy {@link Tank} and
     * {@link Cannon}.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param p
     *            the {@link List} of {@link Projectile}.
     * @return the enemy tank {@link Input}.
     */
    public static Input act(Tank enemy, Tank player, List<Projectile> p) {
        move(enemy, player, p);
        target(enemy, player, p);
        return ENEMY_INPUT;

    }

    /**
     * Private method that manage the enemy {@link Tank} movement.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param p
     *            the {@link List} of {@link Projectile}.
     */
    private static void move(Tank enemy, Tank player, List<Projectile> projectiles) {
        if (projectiles.isEmpty()) {
            if ((int) Calculate.distance(enemy.getPosition(), player.getPosition()) > CRITICAL_DISTANCE) {
                moveEnemy(enemy, player, true);
            } else {
                moveEnemy(enemy, player, false);
            }
        } else {
            goAway(enemy, getNearest(projectiles, enemy));
        }
    }

    /**
     * Method that manage the enemy {@link Cannon} movement.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param p
     *            the {@link List} of {@link Projectile}.
     */
    private static void target(Tank enemy, Tank player, List<Projectile> p) {
        if (p.isEmpty()) {
            ENEMY_INPUT.setTarget(player.getPosition());
        } else {
            ENEMY_INPUT.setTarget(getNearest(p, enemy).getPosition());
        }
    }

    /**
     * Move the enemy {@link Tank} closer or farther than the player {@link Tank}
     * according to a boolean field.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param b
     *            a {@link Boolean}. It's true if the enemy tank has to go closer,
     *            false otherwise.
     */
    private static void moveEnemy(Tank enemy, Tank player, boolean b) {
        // RIGHT and LEFT
        if (enemy.getPosition().getFirst() > player.getPosition().getFirst()) {
            ENEMY_INPUT.getMovement().put(Direction.LEFT, b);
            ENEMY_INPUT.getMovement().put(Direction.RIGHT, !b);
        } else if (enemy.getPosition().getFirst() < player.getPosition().getFirst()) {
            ENEMY_INPUT.getMovement().put(Direction.RIGHT, b);
            ENEMY_INPUT.getMovement().put(Direction.LEFT, !b);
        } else if (enemy.getPosition().getFirst() == player.getPosition().getFirst()) {
            ENEMY_INPUT.getMovement().put(Direction.RIGHT, false);
            ENEMY_INPUT.getMovement().put(Direction.LEFT, false);
        }
        // UP and DOWN
        if (enemy.getPosition().getSecond() - player.getPosition().getSecond() > 0) {
            ENEMY_INPUT.getMovement().put(Direction.UP, b);
            ENEMY_INPUT.getMovement().put(Direction.DOWN, !b);
        } else if (enemy.getPosition().getSecond() - player.getPosition().getSecond() < 0) {
            ENEMY_INPUT.getMovement().put(Direction.DOWN, b);
            ENEMY_INPUT.getMovement().put(Direction.UP, !b);
        } else if (enemy.getPosition().getSecond() == player.getPosition().getSecond()) {
            ENEMY_INPUT.getMovement().put(Direction.DOWN, false);
            ENEMY_INPUT.getMovement().put(Direction.UP, false);
        }
    }

    /**
     * Method that allows to the enemy {@link Tank} to dodge the {@link Projectile}.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param p
     *            the {@link Projectile}.
     */
    private static void goAway(Tank enemy, Projectile p) {
        // RIGHT and LEFT
        if ((enemy.getPosition().getFirst().intValue() - p.getPosition().getFirst().intValue()) < MIN_DISTANCE
                && p.getPosition().getFirst().intValue() < enemy.getPosition().getFirst().intValue()) {
            ENEMY_INPUT.getMovement().put(Direction.LEFT, false);
            ENEMY_INPUT.getMovement().put(Direction.RIGHT, true);
        } else if ((p.getPosition().getFirst().intValue() - (enemy.getPosition().getFirst().intValue()
                + enemy.getDimension().getFirst().intValue())) < MIN_DISTANCE
                && p.getPosition().getFirst().intValue() > enemy.getPosition().getFirst().intValue()) {
            ENEMY_INPUT.getMovement().put(Direction.RIGHT, false);
            ENEMY_INPUT.getMovement().put(Direction.LEFT, true);
        }
        // UP and DOWN
        if ((enemy.getPosition().getSecond().intValue() - p.getPosition().getSecond().intValue()) < MIN_DISTANCE
                && p.getPosition().getSecond().intValue() < enemy.getPosition().getSecond().intValue()
                || enemy.getPosition().getSecond().intValue() <= 0) {
            ENEMY_INPUT.getMovement().put(Direction.DOWN, true);
            ENEMY_INPUT.getMovement().put(Direction.UP, false);
        } else if ((p.getPosition().getSecond().intValue() - (enemy.getPosition().getSecond().intValue()
                + enemy.getDimension().getSecond().intValue())) < MIN_DISTANCE
                && p.getPosition().getSecond().intValue() > enemy.getPosition().getSecond().intValue()
                || enemy.getPosition().getSecond().intValue() + enemy.getDimension().getSecond().intValue() > DIMENSION
                        .getSecond()) {
            ENEMY_INPUT.getMovement().put(Direction.UP, true);
            ENEMY_INPUT.getMovement().put(Direction.DOWN, false);
        }
    }

    /**
     * Method that make the enemy {@link Tank} shot.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @return a new {@link Projectile}.
     */
    public static Projectile shotEnemy(Tank enemy) {
        return enemy.shot();

    }

    /**
     * Getter of the nearest {@link Projectile} to the enemy {@link Tank}.
     * 
     * @param projectiles
     *            the {@link List} of {@link Projectile}.
     * @param enemy
     *            the enemy {@link Tank}.
     * @return the nearest projectile.
     */
    private static Projectile getNearest(List<Projectile> projectiles, Tank enemy) {
        return projectiles.stream().sorted(new Comparator<Projectile>() {

            @Override
            public int compare(Projectile o1, Projectile o2) {
                if ((int) Calculate.distance(o1.getPosition(), enemy.getPosition()) < (int) Calculate
                        .distance(o2.getPosition(), enemy.getPosition())) {
                    return 1;
                } else
                    return 0;
            }
        }).collect(Collectors.toList()).get(0);

    }

}
