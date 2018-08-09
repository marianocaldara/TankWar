package view.controller;

import java.io.IOException;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for the ending game.
 */
public class EndGameController extends ViewController {

    private FadeTransition endGame;

    @FXML
    private GridPane endGameGrid;

    @FXML
    private Label endGameLabel;

    @Override
    public final void init(final Controller controller) {
        this.endGame = new FadeTransition(Duration.seconds(3), this.endGameGrid);
        this.endGame.setFromValue(1.0);
        this.endGame.setToValue(0.5);
        this.endGame.setOnFinished(e -> {
            try {
                ViewScenes.MENU.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        controller);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.endGame.play();
    }

}
