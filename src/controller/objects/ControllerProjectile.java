package controller.objects;

import java.util.List;

import controller.input.MouseInput;
import model.object.Projectile;

/**
 * Interface to control {@link Projectile}.
 */
public interface ControllerProjectile {
	
	/**
	 * Add to a map a new {@link Projectile} when the mouse is clicked.
	 * @param e
	 * 		the {@link MouseInput}.
	 */
	void playerShot(MouseInput e);
	
	/**
	 * Getter for the list of projectiles.
	 * @return the list of {@link Projectile}.
	 */
	List<Projectile> getProjectiles();

}
