package view.stage;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.controller.LoadController;
import view.controller.ViewController;
import view.scene.SceneChanger;
import view.utility.ViewUtils;

public class LoadingStage implements SceneChanger{

	private LoadController loader;

	@Override
    public void setStage(double width, double height, Controller controller) throws IOException {
    	final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JavaFX/FadePlay.fxml"));
        final Parent root = loader.load();
        this.loader = loader.getController();
        this.loader.init(controller);
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
		return this.loader;
	}

}
