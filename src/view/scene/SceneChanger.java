package view.scene;

import java.io.IOException;

import controller.Controller;
import view.controller.ViewController;

/**
 * 
 * Implementation of the interface for setting the stage.
 *
 */
public interface SceneChanger {

    /**
     * This method allows to switch stages
     * 
     * @param event
     *            the action event.
     * @throws IOException
     */
    public void setStage(double width, double height, Controller controller) throws IOException;

    /**
     * Getter of the {@link ViewController}.
     * 
     * @return the {@link ViewController}.
     */
    public ViewController getController();

}
