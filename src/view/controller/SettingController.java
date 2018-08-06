package view.controller;

import controller.Controller;
import controller.levels.Levels;

import java.io.IOException;
import java.util.Arrays;

import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.scene.ViewScenes;
import view.utility.ViewUtils;


public class SettingController extends ViewController{
	
	private Controller controller;
	
	@FXML
	private JFXComboBox<String> difficultBox;

	@FXML
	private AnchorPane gameSettings;

	@FXML
	private JFXComboBox<String> levelsBox;

	@FXML
	private ImageView confirmButton;

	@FXML
	void selectDifficult(ActionEvent event) {
		if(this.difficultBox.getValue().equals(Difficult.EASY.getName())){
			this.controller.setTimeToShot(1000);
		}
		else if(this.difficultBox.getValue().equals(Difficult.MEDIUM.getName())){
			this.controller.setTimeToShot(500);
		}
		else if(this.difficultBox.getValue().equals(Difficult.HARD.getName())){
			this.controller.setTimeToShot(300);
		}
	}

	@FXML
	void selectLevel(ActionEvent event) {
		
		if(this.levelsBox.getValue().equals(Levels.LEVEL_1.getName())){
			this.controller.getLevel().setCurrentLevel(Levels.LEVEL_1);
		}
		else if(this.levelsBox.getValue().equals(Levels.LEVEL_2.getName())){
			this.controller.getLevel().setCurrentLevel(Levels.LEVEL_2);
		}
		else if(this.levelsBox.getValue().equals(Levels.LEVEL_3.getName())){
			this.controller.getLevel().setCurrentLevel(Levels.LEVEL_3);
		}
	}

	@FXML
	void exitAction(MouseEvent event) {
		try {
			ViewScenes.MENU.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(Controller controller) {
		this.controller = controller;
		Arrays.asList(Levels.values()).forEach(l -> this.levelsBox.getItems().add(l.getName()));
		Arrays.asList(Difficult.values()).forEach(d -> this.difficultBox.getItems().add(d.getName()));
		this.gameSettings.setPrefWidth(ViewUtils.getScene().getWidth());
		this.gameSettings.setPrefHeight(ViewUtils.getScene().getHeight());
	}

}
