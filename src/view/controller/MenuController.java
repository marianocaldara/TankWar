package view.controller;

import java.io.IOException;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.scene.ViewScenes;
import view.utility.ViewUtils;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * Implementation of the view controller for the menu stage.
 */
public class MenuController extends ViewController{
	
	private Controller controller;

    @FXML
    private JFXButton play;
    @FXML
    private JFXButton settings;
    @FXML
    private JFXButton exit;
    
    @Override
	public void init(Controller controller) {
		this.controller = controller;
		
	}

    /**
     * This method allow to switch to the game world stage
     * @param event
     * 			the action event.
     * @throws IOException
     */
    public void playAction(ActionEvent event) throws IOException {
    	ViewScenes.LOADING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), this.controller);
    }

    /**
     * This method allow to switch to the settings stage
     * @param event
     * 			the action event.
     * @throws IOException 
     */
    public void settingsAction(ActionEvent event) throws IOException {
    	ViewScenes.SETTING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(), this.controller);

    }

    /**
     * This method allow to exit the GUI
     * @param event
     * 			the action event.
     * @throws IOException
     */
    public void exitAction(ActionEvent event) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Requested");
        alert.setHeaderText("Are you really sure you wanna to quit ?");
        ButtonType yes = new ButtonType("Yes", ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonData.NO);
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            System.exit(0);
        } else if (result.get() == no) {
            alert.close();
        }

    }
    
}
