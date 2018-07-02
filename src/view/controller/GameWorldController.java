package view.controller;


import controller.input.ControllerInputImpl;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * 
 * Implementation of the view controller for the game world stage.
 *
 */
public class GameWorldController {
	
	private ControllerInputImpl controller = new ControllerInputImpl(); // temporanea
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
    
    /*@FXML
    void initialize() {
        /*canvas.setWidth(ViewUtils.getStage().getWidth());
        canvas.setHeight(ViewUtils.getStage().getHeight());
        canvas.widthProperty().bind(ViewUtils.getStage().widthProperty());
        canvas.heightProperty().bind(ViewUtils.getStage().heightProperty());
        playerTank.fitWidthProperty().bind(canvas.widthProperty().divide(6));
        playerTank.fitHeightProperty().bind(canvas.heightProperty().divide(6));
        enemyTank.fitWidthProperty().bind(canvas.widthProperty().divide(6));
        enemyTank.fitHeightProperty().bind(canvas.heightProperty().divide(6));   
        alliedCannon.fitWidthProperty().bind(canvas.widthProperty().divide(12));
        alliedCannon.fitHeightProperty().bind(canvas.heightProperty().divide(12));
        enemyCannon.fitWidthProperty().bind(canvas.widthProperty().divide(12));
        enemyCannon.fitHeightProperty().bind(canvas.heightProperty().divide(12));
    }*/

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
}
