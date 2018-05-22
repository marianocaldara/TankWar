package controller.input;

import javafx.scene.input.KeyEvent;

/**
 * Interface to control the keyboard's input.
 */

public interface KeyboardInput {
	
	/**
	 * Setter for the keyboard input.
	 * @param keyEvent
	 * 		the key event of the view.
	 */
	void setKeyInput(KeyEvent keyEvent);
	
	/**
	 * Getter of the keyboard input.
	 * @return the key event.
	 */
	KeyEvent getKeyInput();

}
