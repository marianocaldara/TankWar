package view.controller;

import java.io.IOException;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

public class GameOverController extends ViewController{
	
	private FadeTransition gameOver;

    @FXML
    private Pane gameOverPane;

    @FXML
    private Text textGameOver;

	@Override
	public void init(Controller controller) {
		this.gameOver = new FadeTransition(Duration.seconds(3), this.gameOverPane);
		this.gameOver.setFromValue(1.0);
		this.gameOver.setToValue(0.5);
		this.gameOver.setOnFinished(e -> {
			try {
				ViewScenes.MENU.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), controller);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		this.gameOver.play();
	}

}
