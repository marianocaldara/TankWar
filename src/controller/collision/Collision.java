package controller.collision;

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
