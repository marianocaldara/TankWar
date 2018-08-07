package controller.collision;

import java.util.List;

import controller.utility.CheckIntersection;
import model.object.Projectile;

/**
 *	Concrete implementation of {@link Collision} interface.
 */
public class ProjectileWithProjectile implements Collision {
	
	private List<Projectile> projectiles;

	/**
	 * Constructor
	 * @param projectiles
	 * 			the list of {@link Projectile}.
	 */
	public ProjectileWithProjectile(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	@Override
	public void manageCollision() {
		for (Projectile p : projectiles) {
			for (Projectile x : projectiles) {
				if (CheckIntersection.intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds())
						&& x != p) {
					p.setDead();
				}
			}
		}

	}

}
