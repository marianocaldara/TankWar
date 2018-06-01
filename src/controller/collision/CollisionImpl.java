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
	public void tankWithTank(Map<Direction, Boolean> movement) {
		try {
			this.checkCollisionTankWithTank(this.world.getPlayer(), this.world.getEnemy());
		}
		catch (IllegalStateException e) {
			/* altro modo
			if(this.world.getPlayer().getPosition().getFirst() + this.world.getPlayer().getBounds().getWidth() >=
					this.world.getEnemy().getPosition().getFirst() && movement.get(Direction.RIGHT)){
						movement.remove(Direction.RIGHT);
						movement.put(Direction.LEFT, true);
				}
				else if(this.world.getPlayer().getPosition().getFirst() <= this.world.getEnemy().getPosition().getFirst() + 
						this.world.getEnemy().getBounds().getWidth() && movement.get(Direction.LEFT)) {
							movement.remove(Direction.LEFT);
							movement.put(Direction.RIGHT, true);
				}
				if(this.world.getPlayer().getPosition().getSecond() + this.world.getPlayer().getBounds().getHeight() >=
					this.world.getEnemy().getPosition().getSecond() && movement.get(Direction.DOWN)) {
						movement.remove(Direction.DOWN);
						movement.put(Direction.UP, true);
				}
				else if(this.world.getPlayer().getPosition().getSecond() <= this.world.getEnemy().getPosition().getSecond() +
						this.world.getEnemy().getBounds().getHeight() && movement.get(Direction.UP)) {
							movement.remove(Direction.UP);
							movement.put(Direction.DOWN, true);
				}*/
			
			if(this.world.getPlayer().getPosition().getFirst() + this.world.getPlayer().getDimension().getFirst() >=
				this.world.getEnemy().getPosition().getFirst() && movement.get(Direction.RIGHT)){
					this.world.getPlayer().setPosition(new Pair<>(this.world.getEnemy().getPosition().getFirst() -
					this.world.getPlayer().getDimension().getFirst(), this.world.getPlayer().getPosition().getSecond()));
					movement.remove(Direction.RIGHT);
			}
			else if(this.world.getPlayer().getPosition().getFirst() < this.world.getEnemy().getPosition().getFirst() + 
					this.world.getEnemy().getDimension().getFirst() && movement.get(Direction.LEFT)) {
						this.world.getPlayer().setPosition(new Pair<>(this.world.getEnemy().getPosition().getFirst() + 
						this.world.getEnemy().getDimension().getFirst(), this.world.getPlayer().getPosition().getSecond()));
						movement.remove(Direction.LEFT);
			}
			if(this.world.getPlayer().getPosition().getSecond() + this.world.getPlayer().getDimension().getSecond() >=
				this.world.getEnemy().getPosition().getSecond() && movement.get(Direction.DOWN)) {
					this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(), 
					this.world.getEnemy().getPosition().getSecond() - this.world.getPlayer().getDimension().getSecond()));
					movement.remove(Direction.DOWN);
			}
			else if(this.world.getPlayer().getPosition().getSecond() < this.world.getEnemy().getPosition().getSecond() +
					this.world.getEnemy().getDimension().getSecond() && movement.get(Direction.UP)) {
						this.world.getPlayer().setPosition(new Pair<>(this.world.getPlayer().getPosition().getFirst(),
						this.world.getEnemy().getPosition().getSecond() + this.world.getPlayer().getDimension().getSecond()));
						movement.remove(Direction.UP);
			}
			
			
		}

	}

	@Override
	public void tankWithProjectile() {
		this.updateTankLifeAndProjectiles(this.world.getPlayer(), 
				projectiles.stream().filter(p -> this.intersects(p.getPosition(), p.getBounds(), this.world.getPlayer().getPosition(), this.world.getPlayer().getDimension())).collect(Collectors.toList()));
		this.updateTankLifeAndProjectiles(this.world.getEnemy(), 
				projectiles.stream().filter(p -> this.intersects(p.getPosition(), p.getBounds(), this.world.getEnemy().getPosition(), this.world.getEnemy().getDimension())).collect(Collectors.toList()));
	}

	@Override
	public void tankWithBorders() {
		this.keepTankBetweenBorders(this.world.getPlayer());
		this.keepTankBetweenBorders(this.world.getEnemy());
	}

	@Override
	public void projectileWithBorders() {
		this.projectiles.forEach(p -> this.projectileBounce(p));
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
        } else if (tank.getPosition().getFirst() < 0) {                // Exceeding left
        	tank.getPosition().setFirst(0.0);
        }
        if (tank.getPosition().getSecond() + tank.getDimension().getSecond() >= this.world.getBounds().getSecond()) {      // Exceeding down
        	tank.getPosition().setSecond(this.world.getBounds().getSecond() - tank.getDimension().getSecond());
        } else if (tank.getPosition().getSecond() < 0) {                // Exceeding up
        	tank.getPosition().setSecond(0.0);
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
			else if(projectile.getPosition().getFirst() < 0) {
				projectile.bounce(Direction.LEFT);
			}
			if(projectile.getPosition().getSecond() + projectile.getBounds().getSecond() >= this.world.getBounds().getSecond()) {
				projectile.bounce(Direction.DOWN);
			}
			else if(projectile.getPosition().getSecond() < 0) {
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
