package view.stage;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.controller.EndLevelController;
import view.controller.ViewController;
import view.scene.SceneChanger;
import view.utility.ViewUtils;

public class EndingLevelStage implements SceneChanger{
	
	private EndLevelController endLevel = new EndLevelController();

	@Override
	public void setStage(double width, double height, Controller controller) throws IOException {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JavaFX/FadeLevelEnded.fxml"));
        final Parent root = loader.load();
        this.endLevel = loader.getController();
        this.endLevel.init(controller);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage stage = ViewUtils.getStage();
        stage.centerOnScreen();       
        stage.hide();
        stage.setScene(scene);
        stage.show();
		
	}

	@Override
	public ViewController getController() {
		return this.endLevel;
	}

}
