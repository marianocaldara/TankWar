package controller.collision;

import java.util.List;
import java.util.Map;

import model.Model;
import model.object.Projectile;
import model.object.Tank;
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
	 * 			the {@link Model} bounds.
	 */
	public FactoryCollisionImpl(final Pair<Double, Double> worldBounds) {
		this.worldBounds = worldBounds;
	}

	@Override
	public Collision tankWithTankCollision(Tank playerTank, Tank enemyTank, Map<Direction, Boolean> movement) {
		return new TankWithTank(playerTank, enemyTank, movement);
	}

	@Override
	public Collision tankWithBordersCollision(Tank tank) {
		return new TankWithBorders(tank, worldBounds);
	}

	@Override
	public Collision tankWithProjectileCollision(Tank playerTank, Tank enemyTank, List<Projectile> projectiles) {
		return new TankWithProjectile(playerTank, enemyTank, projectiles);
	}

	@Override
	public Collision projectileWithBordersCollision(Projectile projectile) {
		return new ProjectileWithBorders(projectile, this.worldBounds);
		
	}

	@Override
	public Collision projectileWithProjectileCollision(List<Projectile> projectiles) {
		return new ProjectileWithProjectile(projectiles);
	}

}
