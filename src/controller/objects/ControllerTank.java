package controller.objects;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Interface to control {@link Tank}.
 */
public interface ControllerTank {

    /**
     * Modify the player {@link Input} according to the keyboard input.
     * 
     * @param event
     *            the {@link KeyEvent}.
     * @param b
     *            a {@link Boolean}. It's true if the key is pressed, false
     *            otherwise.
     */
    void movePlayerTank(KeyEvent event, boolean b);

    /**
     * Update the two {@link Tank} and check the collisions.
     */
    void updateTank();

    /**
     * Rotate the player cannon in the position targeted by mouse.
     * 
     * @param event
     *            the {@link MouseEvent}
     */
    void movePlayerCannon(MouseEvent event);

}
