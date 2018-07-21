package application;
	
import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewMain;
import model.Model;
import model.World;

/**
 *  The class containing the main method to start the application.
 */
public class OOPTankwar extends Application {
	
	@Override
    public void start(Stage primaryStage) {
		Model world = new World();
		ViewMain view = new ViewMain(primaryStage);
		Controller controller = new ControllerImpl(world, view);
		view.setController(controller);
		
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
