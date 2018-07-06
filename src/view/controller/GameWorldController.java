package view.controller;


import controller.input.ControllerInputImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.utility.ViewUtils;

/**
 * 
 * Implementation of the view controller for the game world stage.
 *
 */
public class GameWorldController {
	
	private ControllerInputImpl controller;
    @FXML
    private Canvas worldCanvas;
    @FXML
    private ImageView playerTank;
    @FXML
    private ImageView enemyTank;
    @FXML
    private ImageView alliedCannon;
    @FXML
    private ImageView enemyCannon;
    @FXML
    private AnchorPane menuPane;
    
    @FXML
    void initialize() {
    	ViewUtils.setStageFullScreen();
    	worldCanvas.setWidth(ViewUtils.getScene().getWidth());
    	worldCanvas.setHeight(ViewUtils.getScene().getHeight());
    	menuPane.setLayoutX(worldCanvas.getBoundsInLocal().getMaxX()/(1.05));
    	alliedCannon.layoutXProperty().bind(playerTank.layoutXProperty().add(43));
    	alliedCannon.layoutYProperty().bind(playerTank.layoutYProperty().add(13));
    	playerTank.setLayoutX(worldCanvas.getBoundsInLocal().getMinX());
    	playerTank.setLayoutY(worldCanvas.getBoundsInLocal().getMaxY()/2);
    	enemyCannon.layoutXProperty().bind(enemyTank.layoutXProperty());
    	enemyCannon.layoutYProperty().bind(enemyTank.layoutYProperty().add(13));
    	enemyTank.setLayoutX(worldCanvas.getBoundsInLocal().getMaxX()-(enemyTank.getBoundsInLocal().getWidth()));
    	enemyTank.setLayoutY(worldCanvas.getBoundsInLocal().getMaxY()/2);
    }

    /**
     * This method allow to instance the controller inside this class
     * @param controller
     * 				the controller input.
     */
    public void setControllerInput(ControllerInputImpl controller) {
    	this.controller = controller;
    }
    
    /**
     * This method allows the tank movement
     * @param event
     * 			the key event.
     */
    public void moveTank(KeyEvent event) {
        controller.setKeyInput(event);
    }

    /**
     * This method allows the cannon movement
     * @param event
     * 			the mouse event.
     */
    public void moveCannon(MouseEvent event) {
    	controller.setMouseInput(event);
    }
    
    
    /**
     * This method allows the tank to shoot
     * @param event
     * 			the mouse event.
     */
    public void tankShot(MouseEvent event) {
        controller.setMouseInput(event);
    }
    
    /**
     * This method allows to exit the game
     * @param event
     * 			the action event.
     */
    public void setExitOption(ActionEvent event) {
    	System.exit(0);
    }
    
    /**
     * This method allows to update graphically the tanks positions after a movement.
     */
    public void updateTanksPos() {
    	playerTank.setLayoutX(ViewUtils.getController().getControllerObject().getPlayerPosition().getFirst());
    	playerTank.setLayoutX(ViewUtils.getController().getControllerObject().getPlayerPosition().getSecond());
    	enemyTank.setLayoutX(ViewUtils.getController().getControllerObject().getPlayerPosition().getSecond());
    	enemyTank.setLayoutY(ViewUtils.getController().getControllerObject().getPlayerPosition().getSecond());
    	
    }
}
