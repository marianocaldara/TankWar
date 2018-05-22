package controller.input;

import javafx.scene.input.MouseEvent;

/**
 * Interface to control the mouse's input.
 */

public interface MouseInput {
	
	/**
	 * Setter for the mouse input.
	 * @param mouseEvent
	 * 		the mouse event of the view.
	 */
	void setMouseInput(MouseEvent mouseEvent);
	
	/**
	 * Getter of the mouse input.
	 * @return the mouse event.
	 */
	MouseEvent getMouseInput();

}
