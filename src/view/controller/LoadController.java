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
 * Controller class for the stage between two levels.
 */
public class LoadController extends ViewController {

    private FadeTransition play;
    private String nameLevel = " ";

    @FXML
    private Label playLabel;

    @FXML
    private GridPane loaderGrid;

    @Override
    public void init(Controller controller) {
        String name = controller.getLevel().getCurrentLevel().getName();
        for (int i = 0; i < name.length(); i++) {
            this.nameLevel += name.substring(i, i + 1) + " ";
        }
        this.nameLevel += "!";
        this.playLabel.setText(this.nameLevel.toUpperCase());
        this.play = new FadeTransition(Duration.seconds(3), this.loaderGrid);
        this.play.setFromValue(1.0);
        this.play.setToValue(0.5);
        this.play.setOnFinished(e -> {
            try {
                ViewScenes.GAME_WORLD.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        controller);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.play.play();
    }

}
