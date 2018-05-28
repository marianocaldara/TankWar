package controller.collision;

import java.util.List;
import java.util.stream.Collectors;

import model.Model;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Direction;

/**
 * Concrete implementation of {@link Collision}.
 */
public class CollisionImpl implements Collision {
	
	private static final int DAMAGE = 1;
	private Model world;
	private List<Projectile> projectiles;
	
	/**
	 * Constructor.
	 * @param world
	 * 		the {@link Model}.
	 * @param projectiles
	 * 		a list of {@link Projectile}
	 */
	
	public CollisionImpl(Model world, List<Projectile> projectiles) {
		super();
		this.world = world;
		this.projectiles = projectiles;
	}

	@Override
	public void tankWithTank() {
		if(this.checkCollisionTankWithTank(this.world.getPlayer(), this.world.getEnemy())) {
			//da implementare
		}

	}

	@Override
	public void tankWithProjectile() {
		this.updateTankLifeAndProjectiles(this.world.getPlayer(), this.getCollisionTankWithProjectiles(this.world.getPlayer(), this.projectiles));
		this.updateTankLifeAndProjectiles(this.world.getEnemy(), this.getCollisionTankWithProjectiles(this.world.getEnemy(), this.projectiles));
	}

	@Override
	public void tankWithBorders() {
		this.keepTankBetweenBorders(this.world.getPlayer());
		this.keepTankBetweenBorders(this.world.getEnemy());
	}

	@Override
	public void projectileWithBorders() {
		this.projectiles.forEach(p -> this.bounceProjectileBetweenBorder(p));
	}
	
	/**
	 * Check if there is any collision between the two {@link Tank}.
	 * @param playerTank
	 * 		the player {@link Tank}.
	 * @param enemyTank
	 * 		the enemy {@link Tank}.
	 * @return true if there is any collision, false otherwise.
	 */
	private boolean checkCollisionTankWithTank(Tank playerTank, Tank enemyTank) {
		return playerTank.getBounds().intersects(enemyTank.getBounds());
	}
	
	/**
	 * Getter for the {@link Projectile} that collide with a {@link Tank}.
	 * @param tank
	 * 		the {@link Tank}.
	 * @param projectiles
	 * 		the {@link Projectile}.
	 * @return a list of {@link Projectile} that collide.
	 */
	private List<Projectile> getCollisionTankWithProjectiles(Tank tank, List<Projectile> projectiles) {
		return projectiles.stream().filter(p -> p.getBounds().intersects(tank.getBounds())).collect(Collectors.toList());
	}
	
	/**
	 * Update the life of {@link Tank} and the {@link Projectile}. 
	 * @param tank
	 * 		the {@link Tank}.
	 * @param hitProjectiles
	 * 		a list of {@link Projectile} that collide with tank.
	 */
	private void updateTankLifeAndProjectiles(Tank tank, List<Projectile> hitProjectiles) {
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
	private void keepTankBetweenBorders(Tank tank) {
		if (tank.getPosition().getFirst() + tank.getBounds().getWidth() >= this.world.getBounds().getFirst()) {       // Exceeding right
            tank.getPosition().setFirst(this.world.getBounds().getFirst() - tank.getBounds().getWidth());
        } else if (tank.getPosition().getFirst() < 0) {                // Exceeding left
        	tank.getPosition().setFirst(0.0);
        }
        if (tank.getPosition().getSecond() + tank.getBounds().getHeight() >= this.world.getBounds().getSecond()) {      // Exceeding down
        	tank.getPosition().setSecond(this.world.getBounds().getSecond() - tank.getBounds().getHeight());
        } else if (tank.getPosition().getSecond() < 0) {                // Exceeding up
        	tank.getPosition().setSecond(0.0);
        }
	}
	
	/**
	 * Keep a {@link Projectile} between the {@link World} borders.
	 * @param projectile
	 *		the {@link Projectile}.
	 */
	private void bounceProjectileBetweenBorder(Projectile projectile) {
		try {
			if(projectile.getPosition().getFirst() + projectile.getBounds().getWidth() >= this.world.getBounds().getFirst()) {
				projectile.bounce(Direction.RIGHT);
			}
			else if(projectile.getPosition().getFirst() < 0) {
				projectile.bounce(Direction.LEFT);
			}
			if(projectile.getPosition().getSecond() + projectile.getBounds().getHeight() >= this.world.getBounds().getSecond()) {
				projectile.bounce(Direction.DOWN);
			}
			else if(projectile.getPosition().getSecond() < 0) {
				projectile.bounce(Direction.UP);
			}
		} catch(IllegalStateException e) {
			projectile.setDead();
		}	
				
	}
	
	
	
	//fare metodo Per tank con dimension e projectile con ray divisi  per il check.
	

}
