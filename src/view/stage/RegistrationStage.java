package view.stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.scenes.SceneChanger;
import view.utility.GameStage;
import view.utility.ViewUtils;

/**
 * 
 * Implementation of the stage for the registration scene.
 *
 */
public class RegistrationStage implements SceneChanger {

    @Override
    public void setStage(ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage stage = ViewUtils.getStage();
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        stage.centerOnScreen();
        stage.setHeight(screen.getHeight() / 1.5);
        stage.setWidth(screen.getWidth() / 2);
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

	@Override
	public GameStage getCurrentStage() {
		return GameStage.REGISTRATION;
	}
}
