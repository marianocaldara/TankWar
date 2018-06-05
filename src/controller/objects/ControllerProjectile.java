package controller.objects;

import java.util.List;

import controller.input.MouseInput;
import model.object.Projectile;
import model.utility.Pair;

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
	 * Transform the position the list of {@link Projectile} from the {@link Model} to the {@link View}.
	 * @return a list of position as {@link Pair}.
	 */
	 List<Pair<Double, Double>> getProjectiles();

}
