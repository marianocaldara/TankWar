package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.utility.ViewUtils;

/**
 *  The class containing the main method to start the application.
 */
public class OOPTankwar extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        try {
            ViewUtils.setPrimaryStage(primaryStage);
            //Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/view/GameWorld.fxml")); // temporaneo
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
	 * Entry point of the application
	 * @param args
	 * 		additional argument.
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
