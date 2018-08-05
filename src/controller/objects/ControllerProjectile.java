package controller.objects;

import java.util.List;

import javafx.scene.input.MouseEvent;
import model.utility.Pair;

/**
 * Interface to control {@link Projectile}.
 */
public interface ControllerProjectile {
	
	/**
	 * Add to a map a new {@link Projectile} when the mouse is clicked.
	 * @param event
	 * 		the {@link MouseInput}.
	 */
	void playerShot(MouseEvent event);
	
	/**
	 * Transform the position the list of {@link Projectile} from the {@link Model} to the {@link View}.
	 * @return a list of position as {@link Pair}.
	 */
	 List<Pair<Double, Double>> getProjectiles();
	 
	 /**
	  * Update all the {@link Projectile} and check the collision.
	  */
	 void updateProjectiles();
	 
	 /**
	  * Getter of the {@link Projectile} dimension.
	  * @return a {@link Pair} of the projectile dimension.
	  */
	 Pair<Double, Double> getProjectileDimension();

}
