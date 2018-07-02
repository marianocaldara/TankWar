package view.utility;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Implementation of utility methods for the view.
 *
 */
public class ViewUtils {

    private static Stage CURRENT_STAGE;

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

}
