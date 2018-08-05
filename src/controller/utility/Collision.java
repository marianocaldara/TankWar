package controller.utility;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Model;
import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Utility class that manages the collision between the game objects.
 */
public class Collision {
	
	private static final double MARGINAL_DISTANCE = 5;
	private static final int DAMAGE = 1;
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
	 */
	public static void tankWithTank(Map<Direction, Boolean> movement) {
		try {
			checkCollisionTankWithTank(WORLD.getPlayer(), WORLD.getEnemy());
		}
		catch(IllegalStateException e) {
			if(movement.get(Direction.RIGHT) && movement.get(Direction.UP) && WORLD.getPlayer().getPosition().getFirst() + 
					WORLD.getPlayer().getDimension().getFirst() >= WORLD.getEnemy().getPosition().getFirst() + MARGINAL_DISTANCE) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getPlayer().getPosition().getFirst(),
						WORLD.getEnemy().getPosition().getSecond() + WORLD.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.RIGHT) && movement.get(Direction.DOWN) && WORLD.getPlayer().getPosition().getFirst() + 
					WORLD.getPlayer().getDimension().getFirst() >= WORLD.getEnemy().getPosition().getFirst() + MARGINAL_DISTANCE) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getPlayer().getPosition().getFirst(),
						WORLD.getEnemy().getPosition().getSecond() - WORLD.getPlayer().getDimension().getSecond()));
				}
			else if(movement.get(Direction.LEFT) && movement.get(Direction.UP) && WORLD.getPlayer().getPosition().getFirst()
					<= WORLD.getEnemy().getPosition().getFirst() + WORLD.getEnemy().getDimension().getFirst() - MARGINAL_DISTANCE) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getPlayer().getPosition().getFirst(),
						WORLD.getEnemy().getPosition().getSecond() + WORLD.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.LEFT) && movement.get(Direction.DOWN) && WORLD.getPlayer().getPosition().getFirst()
					<= WORLD.getEnemy().getPosition().getFirst() + WORLD.getEnemy().getDimension().getFirst() - MARGINAL_DISTANCE) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getPlayer().getPosition().getFirst(),
						WORLD.getEnemy().getPosition().getSecond() - WORLD.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.RIGHT)) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getEnemy().getPosition().getFirst() - WORLD.getPlayer().getDimension().getFirst(),
						WORLD.getPlayer().getPosition().getSecond()));
			}
			else if(movement.get(Direction.LEFT)) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getEnemy().getPosition().getFirst() + WORLD.getPlayer().getDimension().getFirst(),
						WORLD.getPlayer().getPosition().getSecond()));
			}
			else if(movement.get(Direction.DOWN)) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getPlayer().getPosition().getFirst(),
						WORLD.getEnemy().getPosition().getSecond() - WORLD.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.UP)) {
				WORLD.getPlayer().setPosition(new Pair<>(WORLD.getPlayer().getPosition().getFirst(),
						WORLD.getEnemy().getPosition().getSecond() + WORLD.getPlayer().getDimension().getSecond()));
			}
		}
	}

	/**
	 * Manage the collision between a {@link Tank} and a {@link Projectile}.
	 * @param projectiles
	 * 		a {@link List} of projectiles.
	 */
	public static void tankWithProjectile(List<Projectile> projectiles) {
		updateTankLifeAndProjectiles(WORLD.getPlayer(), 
				projectiles.stream().filter(p -> intersects(p.getPosition(), p.getBounds(), WORLD.getPlayer().getPosition(), 
						WORLD.getPlayer().getDimension())).collect(Collectors.toList()));
		updateTankLifeAndProjectiles(WORLD.getEnemy(), 
				projectiles.stream().filter(p -> intersects(p.getPosition(), p.getBounds(), WORLD.getEnemy().getPosition(), 
						WORLD.getEnemy().getDimension())).collect(Collectors.toList()));
	}

	/**
	 * Manage the collision between the two {@link Tank} and the {@link World} borders.
	 */
	public static void tankWithBorders() {
		keepTankBetweenBorders(WORLD.getPlayer());
		keepTankBetweenBorders(WORLD.getEnemy());
	}

	/**
	 * Manage the collision between the {@link Projectile} and the {@link World} borders.
	 * @param projectiles
	 * 			the {@link List} of projectiles.
	 */
	public static void projectileWithBorders(List<Projectile> projectiles) {
		projectiles.forEach(p -> projectileBounce(p));
	}
	
	/**
	 * Manage the collision between two {@link Projectile}.
	 * @param projectiles
	 * 			the {@link List} of projectiles.
	 */
	public static void projectileWithProjectile(List<Projectile> projectiles) {	
		for(Projectile p : projectiles) {
			for(Projectile x : projectiles) {
				if(intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds()) && x != p) {
					p.setDead();
				}
			}
		}		
	}
	
	/**
	 * Check if there is any collision between the two {@link Tank}. Throws a new {@link IllegalStateException} if there is. 
	 * @param playerTank
	 * 		the player {@link Tank}.
	 * @param enemyTank
	 * 		the enemy {@link Tank}.
	 */
	private static void checkCollisionTankWithTank(AbstractTank playerTank, AbstractTank enemyTank) {
		if(intersects(playerTank.getPosition(), playerTank.getDimension(), enemyTank.getPosition(), enemyTank.getDimension())) {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Update the life of {@link Tank} and the {@link Projectile}. 
	 * @param tank
	 * 		the {@link Tank}.
	 * @param hitProjectiles
	 * 		a list of {@link Projectile} that collide with tank.
	 */
	private static void updateTankLifeAndProjectiles(AbstractTank tank, List<Projectile> hitProjectiles) {
		if(!hitProjectiles.isEmpty()) {
			tank.damage(DAMAGE * hitProjectiles.size());
			hitProjectiles.forEach(p -> p.setDead());
		}
	}	
	
	/**
	 * Keep a {@link Tank} between the {@link World} borders.
	 * @param tank
	 * 		the {@link Tank}.
	 */
	private static void keepTankBetweenBorders(AbstractTank tank) {
		if (tank.getPosition().getFirst() + tank.getDimension().getFirst() >= WORLD.getBounds().getFirst()) {       // Exceeding right
            tank.getPosition().setFirst(WORLD.getBounds().getFirst() - tank.getDimension().getFirst());
        } else if (tank.getPosition().getFirst() <= 0) {                // Exceeding left
        	tank.getPosition().setFirst(0.0);
        }
        if (tank.getPosition().getSecond() + tank.getDimension().getSecond() >= WORLD.getBounds().getSecond()) {      // Exceeding down
        	tank.getPosition().setSecond(WORLD.getBounds().getSecond() - tank.getDimension().getSecond());
        } else if (tank.getPosition().getSecond() <= 0) {                // Exceeding up
        	tank.getPosition().setSecond(0.0);
        }
	}
	
	/**
	 * Control if a {@link Projectile} is between the {@link World} borders.
	 * @param projectile
	 *		the {@link Projectile}.
	 */
	private static void projectileBounce(Projectile projectile) {
		try {
			if(projectile.getPosition().getFirst() + projectile.getBounds().getFirst() >= WORLD.getBounds().getFirst()) {
				projectile.bounce(Direction.RIGHT);
			}
			else if(projectile.getPosition().getFirst() <= 0) {
				projectile.bounce(Direction.LEFT);
			}
			else if(projectile.getPosition().getSecond() + projectile.getBounds().getSecond() >= WORLD.getBounds().getSecond()) {
				projectile.bounce(Direction.DOWN);
			}
			else if(projectile.getPosition().getSecond() <= 0) {
				projectile.bounce(Direction.UP);
			}
		} catch(IllegalStateException e) {
			projectile.setDead();
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
