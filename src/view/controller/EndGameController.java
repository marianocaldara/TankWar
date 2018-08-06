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

public class EndGameController extends ViewController{
	
	private FadeTransition endGame;

	@FXML
	private Pane endGamePane;

	@FXML
	private Text textEndGame;

	@Override
	public void init(Controller controller) {
		this.endGame = new FadeTransition(Duration.seconds(3), this.endGamePane);
		this.endGame.setFromValue(1.0);
		this.endGame.setToValue(0.5);
		this.endGame.setOnFinished(e -> {
			try {
				ViewScenes.MENU.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), controller);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		this.endGame.play();
	}

}
