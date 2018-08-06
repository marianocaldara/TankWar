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

public class EndLevelController extends ViewController{
	
	private FadeTransition endLevel;
	
	@FXML
	private Label endLevelLabel;

	@FXML
	private GridPane endLevelGrid;

	@Override
	public void init(Controller controller) {
		this.endLevel = new FadeTransition(Duration.seconds(3), this.endLevelGrid);
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
