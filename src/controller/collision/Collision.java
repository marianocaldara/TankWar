package controller.collision;

import model.object.Projectile;

/**
 * Interface to control the objects' collisions.
 */

public interface Collision {
	
	/**
	 * Manage the collision between the two {@link Tank}.
	 */
	 void tankWithTank();
	 
	 /**
	  * Manage the collision between {@link Tank} and {@link Projectile}.
	  */
	 void tankWithProjectile();
	 
	 /**
	  * Manage the collision between {@link Tank} and the {@link World} borders.
	  */
	 void tankWithBorders();
	 
	 /**
	  * Manage the collision between the {@link Projectile} and the {@link World} borders.
	  */
	 void projectileWithBorders();

}
