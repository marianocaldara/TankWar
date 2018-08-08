package controller.objects;

import javafx.scene.input.MouseEvent;

/**
 * Interface to control {@link Projectile}.
 */
public interface ControllerProjectile {

    /**
     * Add to a map a new {@link Projectile} when the mouse is clicked.
     * 
     * @param event
     *            the {@link MouseInput}.
     */
    void playerShot(MouseEvent event);

    /**
     * Update all the {@link Projectile} and check the collision.
     */
    void updateProjectiles();

}
