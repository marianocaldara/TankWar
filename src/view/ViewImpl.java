package view;

import java.io.IOException;
import controller.Controller;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.utility.Pair;
import view.controller.GameWorldController;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Concrete implementation of the {@link View} interface.
 */
public class ViewImpl implements View {

    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth() / 2;
    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight() / 2;
    private Controller controller;

    /**
     * Instance a new {@link ViewImpl}.
     * 
     * @param primaryStage
     *            the first stage.
     * @throws IOException
     */
    public ViewImpl(Stage primaryStage) throws IOException {
        ViewUtils.setPrimaryStage(primaryStage);
    }

    @Override
    public Pair<Double, Double> getBounds() {
        return new Pair<>(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight());
    }

    @Override
    public GameWorldController getGameWorldController() throws IOException {
        return (GameWorldController) ViewScenes.GAME_WORLD.getGameStage().getController();
    }

    @Override
    public void launchView(Controller controller) {
        this.controller = controller;
        try {
            ViewScenes.MENU.setGameStage(WIDTH, HEIGHT, this.controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
