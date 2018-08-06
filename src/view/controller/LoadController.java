package view.controller;

import java.io.IOException;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

public class LoadController extends ViewController {
	
	private FadeTransition play;

	@FXML
	private Text loaderText;

	@FXML
	private Pane loaderPane;

	@FXML
	private Group loaderGroup;

	@Override
	public void init(Controller controller) {
		this.play = new FadeTransition(Duration.seconds(3), this.loaderPane);
		this.play.setFromValue(1.0);
		this.play.setToValue(0.5);
		this.play.setOnFinished(e -> {
			try {
				ViewScenes.GAME_WORLD.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), controller);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		this.play.play();
	}

}


