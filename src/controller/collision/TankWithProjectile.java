package controller.collision;

import java.util.List;
import java.util.stream.Collectors;

import controller.utility.CheckIntersection;
import model.object.AbstractTank;
import model.object.Projectile;

/**
 *	Concrete implementation of {@link Collision} interface.
 */
public class TankWithProjectile implements Collision{
	
	private static final int DAMAGE = 1;
	private AbstractTank playerTank;
	private AbstractTank enemyTank;
	private List<Projectile> projectiles;
	
	/**
	 * Constructor
	 * @param playerTank
	 * 			the player {@link Tank}.
	 * @param enemyTank
	 * 			the enemy {@link Tank}.
	 * @param projectiles
	 * 			the list of {@link Projectile}.
	 */
	public TankWithProjectile(AbstractTank playerTank, AbstractTank enemyTank, List<Projectile> projectiles) {
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
		this.projectiles = projectiles;
	}

	@Override
	public void manageCollision() {
		updateTankLifeAndProjectiles(this.playerTank, 
				projectiles.stream().filter(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(), this.playerTank.getPosition(), 
						this.playerTank.getDimension())).collect(Collectors.toList()));
		updateTankLifeAndProjectiles(this.enemyTank, 
				projectiles.stream().filter(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(), this.enemyTank.getPosition(), 
						this.enemyTank.getDimension())).collect(Collectors.toList()));
		
	}
	
	private static void updateTankLifeAndProjectiles(AbstractTank tank, List<Projectile> hitProjectiles) {
		if(!hitProjectiles.isEmpty()) {
			tank.damage(DAMAGE * hitProjectiles.size());
			hitProjectiles.forEach(p -> p.setDead());
		}
	}	

}
