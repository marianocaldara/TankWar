package view.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.scenes.ViewScenes;

/**
 * 
 * Implementation of the view controller for the registration stage.
 *
 */
public class RegistrationController {

    @FXML
    private JFXButton back;

    /**
     * This method allow to switch to the login stage
     * @param event
     * 			the action event.
     * @throws IOException
     */
    public void setBack(ActionEvent event) throws IOException {
        ViewScenes.LOGIN.setGameStage(event);
    }
}
