package controller.utility;

import java.util.List;

import exceptions.ProjectileOutOfBordersException;
import exceptions.ProjectileWithProjectileException;
import exceptions.TankOutOfBordersException;
import exceptions.TankWithProjectileException;
import exceptions.TankWithTankException;
import model.Model;
import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Pair;

/**
 * Utility class that manages the collision between the game objects.
 */
public class Collision {
	
	private static Model WORLD;
	
	/**
	 * Initialize the fields of the class.
	 * @param world
	 * 		the {@link Model}.
	 */
	public static void initialize(Model world) {
		WORLD = world;
	}
	
	/**
	 * Manage the collision between the two {@link Tank}.
	 * @param movement
	 * 		the {@link Input} of the player tank.
	 * @throws TankWithTankException 
	 */
	public static void tankWithTank(AbstractTank playerTank, AbstractTank enemyTank) throws TankWithTankException {
		if(intersects(playerTank.getPosition(), playerTank.getDimension(), enemyTank.getPosition(), enemyTank.getDimension())) {
			throw new TankWithTankException();
		}
	}

	/**
	 * Manage the collision between a {@link Tank} and a {@link Projectile}.
	 * @param projectiles
	 * 		a {@link List} of projectiles.
	 * @throws TankWithProjectileException 
	 */
	public static void tankWithProjectile(List<Projectile> projectiles) throws TankWithProjectileException {
		if (projectiles.stream()
				.anyMatch(p -> intersects(p.getPosition(), p.getBounds(), WORLD.getPlayer().getPosition(),
						WORLD.getPlayer().getDimension()))
				|| projectiles.stream().anyMatch(p -> intersects(p.getPosition(), p.getBounds(),
						WORLD.getEnemy().getPosition(), WORLD.getEnemy().getDimension()))) {
			throw new TankWithProjectileException();
		}
	}

	/**
	 * Manage the collision between the two {@link Tank} and the {@link World} borders.
	 * @throws TankOutOfBordersException 
	 */
	public static void tankWithBorders(AbstractTank tank) throws TankOutOfBordersException {
		if (tank.getPosition().getFirst() < 0
				|| tank.getPosition().getFirst() + tank.getDimension().getFirst() > WORLD.getBounds().getFirst()
				|| (tank.getPosition().getSecond() < 0 || tank.getPosition().getSecond()
						+ tank.getDimension().getSecond() > WORLD.getBounds().getSecond())) {
			throw new TankOutOfBordersException();

		}
	}

	/**
	 * Manage the collision between the {@link Projectile} and the {@link World} borders.
	 * @param projectiles
	 * 			the {@link List} of projectiles.
	 * @throws ProjectileOutOfBordersException 
	 */
	public static void projectileWithBorders(Projectile projectile) throws ProjectileOutOfBordersException {
		if (projectile.getPosition().getFirst() + projectile.getBounds().getFirst() >= WORLD.getBounds().getFirst()
				|| projectile.getPosition().getFirst() <= 0 || projectile.getPosition().getSecond()
						+ projectile.getBounds().getSecond() >= WORLD.getBounds().getSecond()
				|| projectile.getPosition().getSecond() <= 0) {
			throw new ProjectileOutOfBordersException();

		}

	}
	
	/**
	 * Manage the collision between two {@link Projectile}.
	 * @param projectiles
	 * 			the {@link List} of projectiles.
	 * @throws ProjectileWithProjectileException 
	 */
	public static void projectileWithProjectile(List<Projectile> projectiles) throws ProjectileWithProjectileException {	
		for(Projectile p : projectiles) {
			for(Projectile x : projectiles) {
				if(intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds()) && x != p) {
					throw new ProjectileWithProjectileException();
				}
			}
		}		
	}

	/**
	 * Control if two objects collide.
	 * @param positionFirst
	 * 		the position of the first object.
	 * @param dimensionFirst
	 * 		the dimension of the first object.
	 * @param positionSecond
	 * 		the position of the second object.
	 * @param dimensionSecond
	 * 		the dimension of the second object.
	 * @return true if the two objects collide, false otherwise.
	 */
	private static boolean intersects(Pair<Double, Double> positionFirst, Pair<Double, Double> dimensionFirst, Pair<Double, Double> positionSecond, Pair<Double, Double> dimensionSecond) {
		if(positionFirst == null || dimensionFirst == null || positionSecond == null || dimensionSecond == null) {
			return false;
		}
		return positionFirst.getFirst() + dimensionFirst.getFirst() > positionSecond.getFirst() &&
				positionFirst.getSecond() + dimensionFirst.getSecond() > positionSecond.getSecond() &&
				positionFirst.getFirst() < positionSecond.getFirst() + dimensionSecond.getFirst() &&
				positionFirst.getSecond() < positionSecond.getSecond() + dimensionSecond.getSecond();
	}

}
