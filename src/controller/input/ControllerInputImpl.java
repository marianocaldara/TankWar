package controller.input;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Concrete implementation of the MouseInput and KeyboardInput interfaces.
 */
public class ControllerInputImpl implements MouseInput, KeyboardInput {
	
	private KeyEvent keyEvent;
	private MouseEvent mouseEvent;
	
	/**
	 * Creates a new ControllerInput instance.
	 */
	public ControllerInputImpl() {
		super();
	}

	@Override
	public void setKeyInput(KeyEvent keyEvent) {
		this.keyEvent = keyEvent;

	}

	@Override
	public KeyEvent getKeyInput() {
		return this.keyEvent;
	}

	@Override
	public void setMouseInput(MouseEvent mouseEvent) {
		this.mouseEvent = mouseEvent;

	}

	@Override
	public MouseEvent getMouseInput() {
		return this.mouseEvent;
	}

}
