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

public class EndLevelController extends ViewController{
	
	private FadeTransition endLevel;
	
	@FXML
	private Text textEndlLevel;

	@FXML
	private Pane endLevelPane;

	@Override
	public void init(Controller controller) {
		this.endLevel = new FadeTransition(Duration.seconds(3), this.endLevelPane);
		this.endLevel.setFromValue(1.0);
		this.endLevel.setToValue(0.5);
		this.endLevel.setOnFinished(e -> {
			try {
				ViewScenes.LOADING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), controller);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		this.endLevel.play();
	}

}
