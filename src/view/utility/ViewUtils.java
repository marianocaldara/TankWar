package view.utility;

import controller.ControllerImpl;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * 
 * Implementation of utility methods for the view.
 *
 */
public class ViewUtils {

    private static Stage CURRENT_STAGE;
    private static ControllerImpl controller;

    /**
     * This method allows to know the initial stage of the application
     * @param stage
     * 			the actual stage.
     */
    public static void setPrimaryStage(Stage stage) {
        CURRENT_STAGE = stage;
    }

    /**
     * This method allows to get the current stage
     * @return
     * 			the current stage.
     */
    public static Stage getStage() {
        return CURRENT_STAGE;
    }

    /**
     * This method allows to get the current scene
     * @return
     * 			the current scene.
     */
    public static Scene getScene() {
        return CURRENT_STAGE.getScene();
    }
    
    /**
     * This method allows to set the stage to full screen, disabling also all the exit key combinations
     */
    public static void setStageFullScreen() {
    	CURRENT_STAGE.setFullScreen(true);
    	CURRENT_STAGE.setFullScreenExitHint("");
    	CURRENT_STAGE.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }
    
    /**
     * This method allows to get the controller class inside view methods
     * @return
     * 			the controller implementation class.
     */
    public static ControllerImpl getController() {
    	return controller;
    }

}
