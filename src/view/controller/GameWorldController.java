package view.controller;


import controller.input.ControllerInputImpl;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import view.utility.ViewUtils;

/**
 * 
 * Implementation of the view controller for the game world stage.
 *
 */
public class GameWorldController {
	
	private Rectangle2D  screen;
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
    
    public GameWorldController() {
    	screen = Screen.getPrimary().getVisualBounds();
	}
    
    @FXML
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
        */
    	alliedCannon.layoutXProperty().bind(playerTank.layoutXProperty());
    	alliedCannon.layoutYProperty().bind(playerTank.layoutYProperty().add(13));
    	playerTank.setLayoutX(worldCanvas.getBoundsInLocal().getMinX());
    	playerTank.setLayoutY(worldCanvas.getBoundsInLocal().getMaxY()/2);
    	enemyCannon.layoutXProperty().bind(enemyTank.layoutXProperty());
    	enemyCannon.layoutYProperty().bind(enemyTank.layoutYProperty().add(13));
    	enemyTank.setLayoutX(worldCanvas.getBoundsInLocal().getMaxX());
    	enemyTank.setLayoutY(worldCanvas.getBoundsInLocal().getMaxY()/2);
    	//alliedCannon.setLayoutX(playerTank.getFitHeight()/2);
    	/*playerTank.setX(screen.getMinX()+10);
    	playerTank.setY(screen.getMaxY()/2);
    	enemyTank.setX(screen.getMaxX()-10);
    	enemyTank.setY(screen.getMaxY()/2);*/
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
}
