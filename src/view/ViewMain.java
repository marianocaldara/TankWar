package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.utility.Pair;
import view.controller.GameWorldController;
import view.utility.ViewUtils;

/**
 * 
 * Implementation of the main view class.
 *
 */
public class ViewMain {

	private Controller controller;
	private GameWorldController gwc;
	
	public ViewMain(Stage primaryStage) {
		this.gwc = new GameWorldController();
        try {
            ViewUtils.setPrimaryStage(primaryStage);
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/view/GameWorld.fxml")); // temporaneo
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Rectangle2D screen = Screen.getPrimary().getVisualBounds();
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.centerOnScreen();
            primaryStage.setHeight(screen.getHeight() / 1.5);
            primaryStage.setWidth(screen.getWidth() / 2);
            primaryStage.setMinHeight(screen.getHeight() / 1.5);
            primaryStage.setMinWidth(screen.getWidth() / 2);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * This method allows to set the controller classes inside the view
	 * @param controller
	 * 			the controller.
	 */
	public void setController(Controller controller) {
		this.controller = controller;
		this.gwc.setControllerInput(this.controller.getControllerInput());
	}
	
	/**
	 * This method allows to get the bounds of the scene
	 * @return
	 * 			a pair of two double values.
	 */
	public Pair<Double, Double> getBounds(){
		return new Pair<>(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight());
	}
}
