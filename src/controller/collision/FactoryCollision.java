package controller.collision;

import java.util.List;
import java.util.Map;

import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Direction;

/**
 *  Interface of a factory for the different {@link Collision} of the game.
 */
public interface FactoryCollision {

    /**
     * Create a new {@link TankWithTank} collision.
     * @param playerTank
     *          the player {@link Tank}.
     * @param enemyTank
     *          the enemy {@link Tank}.
     * @param movement
     *          the player movements.
     * @return a new {@link TankWithTank} collision.
     */
    Collision tankWithTankCollision(AbstractTank playerTank, AbstractTank enemyTank, Map<Direction, Boolean> movement);

    /**
     * Create a new {@link TankWithBorders} collision.
     * @param tank
     *          the colliding {@link Tank}.
     * @return a new {@link TankWithBorders} collision.
     */
    Collision tankWithBordersCollision(AbstractTank tank);

    /**
     * Create a new {@link TankWithProjectile} collision.
     * @param playerTank
     *          the player {@link Tank}.
     * @param enemyTank
     *          the enemy {@link Tank}.
     * @param projectiles
     *          the list of {@link Projectile}.
     * @return a new {@link TankWithProjectile} collision.
     */
    Collision tankWithProjectileCollision(AbstractTank playerTank, AbstractTank enemyTank, List<Projectile> projectiles);

    /**
     * Create a new {@link ProjectileWithBorders} collision.
     * @param projectile
     *          the colliding {@link Projectile}.
     * @return a new {@link ProjectileWithBorders} collision.
     */
    Collision projectileWithBordersCollision(Projectile projectile);

    /**
     * Create a new {@link ProjectileWithProjectile} collision.
     * @param projectiles
     *          the list of {@link Projectile}.
     * @return a new {@link ProjectileWithProjectile} collision.
     */
    Collision projectileWithProjectileCollision(List<Projectile> projectiles);

}
