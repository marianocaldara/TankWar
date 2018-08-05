package view.stage;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.controller.GameWorldController;
import view.controller.ViewController;
import view.scene.SceneChanger;

import view.utility.ViewUtils;

/**
 * 
 * Implementation of the stage for the game world scene.
 *
 */
public class GameWorldStage implements SceneChanger{
	
	private GameWorldController gameWorld;

	@Override
    public void setStage(double width, double height, Controller controller) throws IOException {
    	final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JavaFX/" + controller.getLevel().getCurrentLevel().getName() + ".fxml"));
        final Parent root = loader.load();
        this.gameWorld = loader.getController();
        this.gameWorld.init(controller);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage stage = ViewUtils.getStage();
        stage.centerOnScreen();       
        stage.hide();
        stage.setScene(scene);
        stage.show();
        controller.getLevel().setLevelStarted();
    }

	@Override
	public ViewController getController() {
		return this.gameWorld;
	}

}
