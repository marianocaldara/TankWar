package view.stage;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.controller.EndGameController;
import view.controller.ViewController;
import view.scene.SceneChanger;
import view.utility.ViewUtils;

/**
 * Concrete implementation of the {@link SceneChanger} interface. It manages the ending game stage.
 */
public class EndingGameStage implements SceneChanger{
	
	private EndGameController endGame = new EndGameController();
	
	@Override
	public void setStage(double width, double height, Controller controller) throws IOException {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JavaFX/FadeGameEnded.fxml"));
        final Parent root = loader.load();
        this.endGame = loader.getController();
        this.endGame.init(controller);
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
		return this.endGame;
	}

}
