package controller.collision;

import java.util.List;
import java.util.Map;

import model.object.Projectile;
import model.utility.*;

/**
 * Interface to control the objects' collisions.
 */

public interface Collision {
	
	/**
	 * Manage the collision between the two {@link Tank}.
	 */
	 void tankWithTank(Map<Direction, Boolean> movement);
	 
	 /**
	  * Manage the collision between {@link Tank} and {@link Projectile}.
	  * @param projectiles
	  * 		the list of {@link Projectile}.
	  */
	 void tankWithProjectile(List<Projectile> projectiles);
	 
	 /**
	  * Manage the collision between {@link Tank} and the {@link World} borders.
	  */
	 void tankWithBorders();
	 
	 /**
	  * Manage the collision between the {@link Projectile} and the {@link World} borders.
	  * @param projectiles
	  * 		the list of {@link Projectile}.
	  */		
	 void projectileWithBorders(List<Projectile> projectiles);
	 
	 /**
	  * Manage the collision between two {@link Projectile}.
	  * @param projectiles
	  * 		the list of {@link Projectile}.
	  */		
	 void projectileWithProjectile(List<Projectile> projectiles);

}
