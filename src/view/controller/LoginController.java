package view.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import view.scenes.ViewScenes;

/**
 * 
 * Implementation of the view controller for the login stage.
 *
 */
public class LoginController {

    @FXML
    private JFXButton login;
    @FXML
    private Label log_label;
    @FXML
    private JFXTextField usrname;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton subscribe;

    /**
     * This method shows the login interface
     * @param event
     * 			the action event.
     * @throws IOException
     */
    public void setLogin(ActionEvent event) throws IOException {
        if (usrname.getText().equals("usr") && pass.getText().equals("pas")) {
            ViewScenes.MENU.setGameStage(event);
        } else {
            log_label.setText("Login Failed");
        }
    }

    /**
     * This method allow to close the GUI
     * @param event
     * 			the action event.
     */
    public void setExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * This method allow to switch to the register stage
     * @param event
     * 			the action event.
     * @throws IOException
     */
    public void setSubscription(ActionEvent event) throws IOException {
        ViewScenes.SUBSCRIBTION.setGameStage(event);
    }
}
