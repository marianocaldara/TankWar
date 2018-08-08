package controller.collision;

import java.util.List;
import java.util.Map;

import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Pair;
import model.utility.Direction;

/**
 * Concrete implementation of {@link FactoryCollision} interface.
 */
public class FactoryCollisionImpl implements FactoryCollision {

    private final Pair<Double, Double> worldBounds;

    /**
     * Instance a new {@link FactoryCollisionImpl}.
     * @param worldBounds
     *          the {@link Model} bounds.
     */
    public FactoryCollisionImpl(final Pair<Double, Double> worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    public final Collision tankWithTankCollision(final AbstractTank playerTank, final AbstractTank enemyTank, final Map<Direction, Boolean> movement) {
        return new TankWithTank(playerTank, enemyTank, movement);
    }

    @Override
    public final Collision tankWithBordersCollision(final AbstractTank tank) {
        return new TankWithBorders(tank, worldBounds);
    }

    @Override
    public final Collision tankWithProjectileCollision(final AbstractTank playerTank, final AbstractTank enemyTank, final List<Projectile> projectiles) {
        return new TankWithProjectile(playerTank, enemyTank, projectiles);
    }

    @Override
    public final Collision projectileWithBordersCollision(final Projectile projectile) {
        return new ProjectileWithBorders(projectile, this.worldBounds);
    }

    @Override
    public final Collision projectileWithProjectileCollision(final List<Projectile> projectiles) {
        return new ProjectileWithProjectile(projectiles);
    }

}
