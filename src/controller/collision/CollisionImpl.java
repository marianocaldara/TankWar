package controller.collision;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Model;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete implementation of {@link Collision}.
 */
public class CollisionImpl implements Collision {
	
	private static final int DAMAGE = 1;
	private static final int LIMIT_COLLISION = 5;
	private static final int ORIGIN = 0;
	private static final double MIN_COORDINATE = 0.0;
	private Model world;
	
	/**
	 * Constructor.
	 * @param world
	 * 		the {@link Model}.
	 */
	
	public CollisionImpl(Model world) {
		super();
		this.world = world;
	}

	@Override
	public void tankWithTank(Map<Direction, Boolean> movement) {
		try {
			this.checkCollisionTankWithTank(this.world.getPlayer(), this.world.getEnemy());
		}
		catch (IllegalStateException e) {
			if(movement.get(Direction.RIGHT) && movement.get(Direction.UP) && this.world.getPlayer().getPosition().getFirst() + 
					this.world.getPlayer().getDimension().getFirst() >= this.world.getEnemy().getPosition().getFirst() + LIMIT_COLLISION) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
					this.world.getEnemy().getPosition().getSecond() + this.world.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.RIGHT) && movement.get(Direction.DOWN) && this.world.getPlayer().getPosition().getFirst() + 
					this.world.getPlayer().getDimension().getFirst() >= this.world.getEnemy().getPosition().getFirst() + LIMIT_COLLISION) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
						this.world.getEnemy().getPosition().getSecond() - this.world.getPlayer().getDimension().getSecond()));
				}
			else if(movement.get(Direction.LEFT) && movement.get(Direction.UP) && this.world.getPlayer().getPosition().getFirst()
					<= this.world.getEnemy().getPosition().getFirst() + this.world.getEnemy().getDimension().getFirst() - LIMIT_COLLISION) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
						this.world.getEnemy().getPosition().getSecond() + this.world.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.LEFT) && movement.get(Direction.DOWN) && this.world.getPlayer().getPosition().getFirst()
					<= this.world.getEnemy().getPosition().getFirst() + this.world.getEnemy().getDimension().getFirst() - LIMIT_COLLISION) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
						this.world.getEnemy().getPosition().getSecond() - this.world.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.RIGHT)) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getEnemy().getPosition().getFirst() - this.world.getPlayer().getDimension().getFirst(),
						this.world.getPlayer().getPosition().getSecond()));
			}
			else if(movement.get(Direction.LEFT)) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getEnemy().getPosition().getFirst() + this.world.getPlayer().getDimension().getFirst(),
						this.world.getPlayer().getPosition().getSecond()));
			}
			else if(movement.get(Direction.DOWN)) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
						this.world.getEnemy().getPosition().getSecond() - this.world.getPlayer().getDimension().getSecond()));
			}
			else if(movement.get(Direction.UP)) {
				this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
						this.world.getEnemy().getPosition().getSecond() + this.world.getPlayer().getDimension().getSecond()));
			}
		}

	}

	@Override
	public void tankWithProjectile(List<Projectile> projectiles) {
		this.updateTankLifeAndProjectiles(this.world.getPlayer(), 
				projectiles.stream().filter(p -> this.intersects(p.getPosition(), p.getBounds(), this.world.getPlayer().getPosition(), 
						this.world.getPlayer().getDimension())).collect(Collectors.toList()));
		this.updateTankLifeAndProjectiles(this.world.getEnemy(), 
				projectiles.stream().filter(p -> this.intersects(p.getPosition(), p.getBounds(), this.world.getEnemy().getPosition(), 
						this.world.getEnemy().getDimension())).collect(Collectors.toList()));
	}

	@Override
	public void tankWithBorders() {
		this.keepTankBetweenBorders(this.world.getPlayer());
		this.keepTankBetweenBorders(this.world.getEnemy());
	}

	@Override
	public void projectileWithBorders(List<Projectile> projectiles) {
		projectiles.forEach(p -> this.projectileBounce(p));
	}
	
	@Override
	public void projectileWithProjectile(List<Projectile> projectiles) {	
		for(Projectile p : projectiles) {
			for(Projectile x : projectiles) {
				if(this.intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds()) && x != p) {
					p.setDead();
				}
			}
		}
	}
	
	/**
	 * Check if there is any collision between the two {@link Tank}.
	 * @param playerTank
	 * 		the player {@link Tank}.
	 * @param enemyTank
	 * 		the enemy {@link Tank}.
	 */
	private void checkCollisionTankWithTank(Tank playerTank, Tank enemyTank) {
		if(this.intersects(playerTank.getPosition(), playerTank.getDimension(), enemyTank.getPosition(), enemyTank.getDimension())) {
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
		if (tank.getPosition().getFirst() + tank.getDimension().getFirst() >= this.world.getBounds().getFirst()) {       // Exceeding right
            tank.getPosition().setFirst(this.world.getBounds().getFirst() - tank.getDimension().getFirst());
        } else if (tank.getPosition().getFirst() < ORIGIN) {                // Exceeding left
        	tank.getPosition().setFirst(MIN_COORDINATE);
        }
        if (tank.getPosition().getSecond() + tank.getDimension().getSecond() >= this.world.getBounds().getSecond()) {      // Exceeding down
        	tank.getPosition().setSecond(this.world.getBounds().getSecond() - tank.getDimension().getSecond());
        } else if (tank.getPosition().getSecond() < ORIGIN) {                // Exceeding up
        	tank.getPosition().setSecond(MIN_COORDINATE);
        }
	}
	
	/**
	 * Control if a {@link Projectile} is between the {@link World} borders.
	 * @param projectile
	 *		the {@link Projectile}.
	 */
	private void projectileBounce(Projectile projectile) {
		try {
			if(projectile.getPosition().getFirst() + projectile.getBounds().getFirst() >= this.world.getBounds().getFirst()) {
				projectile.bounce(Direction.RIGHT);
			}
			else if(projectile.getPosition().getFirst() < ORIGIN) {
				projectile.bounce(Direction.LEFT);
			}
			if(projectile.getPosition().getSecond() + projectile.getBounds().getSecond() >= this.world.getBounds().getSecond()) {
				projectile.bounce(Direction.DOWN);
			}
			else if(projectile.getPosition().getSecond() < ORIGIN) {
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
	private boolean intersects(Pair<Double, Double> positionFirst, Pair<Double, Double> dimensionFirst, Pair<Double, Double> positionSecond, Pair<Double, Double> dimensionSecond) {
		if(positionFirst == null || dimensionFirst == null || positionSecond == null || dimensionSecond == null) {
			return false;
		}
		return positionFirst.getFirst() + dimensionFirst.getFirst() > positionSecond.getFirst() &&
				positionFirst.getSecond() + dimensionFirst.getSecond() > positionSecond.getSecond() &&
				positionFirst.getFirst() < positionSecond.getFirst() + dimensionSecond.getFirst() &&
				positionFirst.getSecond() < positionSecond.getSecond() + dimensionSecond.getSecond();
	}
	
	//fare metodo Per tank con dimension e projectile con ray divisi  per il check.
	

}
