package application;
	
import java.io.IOException;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.World;
import view.View;
import view.ViewImpl;

/**
 * The class containing the main method.
 */
public class OOPtankwar extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Model world = new World();
		View view = new ViewImpl(primaryStage);
		Controller controller = new ControllerImpl(world, view);
		view.launchView(controller);
	}

	/**
	 * The main entry point of the application.
	 * 
	 * @param args
	 *            CLI arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
