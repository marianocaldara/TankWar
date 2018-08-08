package controller.utility;

import java.util.List;

import exceptions.ProjectileOutOfBordersException;
import exceptions.ProjectileWithProjectileException;
import exceptions.TankOutOfBordersException;
import exceptions.TankWithProjectileException;
import exceptions.TankWithTankException;
import model.Model;
import model.object.Projectile;
import model.object.Tank;

/**
 * Utility class that catch the collision between the game objects.
 */
public class CheckCollision {
	
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
	 * 		if there is any collision.
	 */
	public static void tankWithTank(Tank playerTank, Tank enemyTank) throws TankWithTankException {
		if(CheckIntersection.intersects(playerTank.getPosition(), playerTank.getDimension(), enemyTank.getPosition(), enemyTank.getDimension())) {
			throw new TankWithTankException();
		}
	}

	/**
	 * Manage the collision between a {@link Tank} and a {@link Projectile}.
	 * @param projectiles
	 * 		a {@link List} of projectiles.
	 * @throws TankWithProjectileException 
	 * 		if there is any collision.
	 */
	public static void tankWithProjectile(List<Projectile> projectiles) throws TankWithProjectileException {
		if (projectiles.stream()
				.anyMatch(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(), WORLD.getPlayer().getPosition(),
						WORLD.getPlayer().getDimension()))
				|| projectiles.stream().anyMatch(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(),
						WORLD.getEnemy().getPosition(), WORLD.getEnemy().getDimension()))) {
			throw new TankWithProjectileException();
		}
	}

	/**
	 * Manage the collision between the two {@link Tank} and the {@link World} borders.
	 * @throws TankOutOfBordersException 
	 * 		if the {@link Tank} goes out the {@link World} bounds.
	 */
	public static void tankWithBorders(Tank tank) throws TankOutOfBordersException {
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
	 * 			the list of {@link Projectile}.
	 * @throws ProjectileOutOfBordersException 
	 * 			if the {@link Projectile} goes out the {@link World} bounds.
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
	 * 			the list of {@link Projectile}.
	 * @throws ProjectileWithProjectileException 
	 * 			if there is any collision.
	 */
	public static void projectileWithProjectile(List<Projectile> projectiles) throws ProjectileWithProjectileException {	
		for(Projectile p : projectiles) {
			for(Projectile x : projectiles) {
				if(CheckIntersection.intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds()) && x != p) {
					throw new ProjectileWithProjectileException();
				}
			}
		}		
	}

}
