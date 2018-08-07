package exceptions;

import model.object.Projectile;

/**
 *	Exception threw when two {@link Projectile} collide each other.
 */
public class ProjectileWithProjectileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8127253338298911574L;
	
	/**
	 * Constructor.
	 */
	public ProjectileWithProjectileException() {
		super();
	}

}
