package view.scenes;

import java.io.IOException;

import javafx.event.ActionEvent;

/**
 * 
 * Implementation of the interface for setting the stage.
 *
 */
public interface SceneChanger {

	/**
	 * This method allows to switch stages
	 * @param event
	 * 			the action event.
	 * @throws IOException
	 */
    public void setStage(ActionEvent event) throws IOException;

}
