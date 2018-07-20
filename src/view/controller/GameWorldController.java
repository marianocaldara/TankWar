package view.controller;


import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import view.utility.ViewUtils;

/**
 * 
 * Implementation of the view controller for the game world stage.
 *
 */
public class GameWorldController {
	
	private Controller controller;
	private List<ImageView> projectiles;
	private List<ImageView> playerLife;
	private List<ImageView> enemyLife;
	private ImageView bullet;
	private ImageView life;
	@FXML
	private GridPane gameWorld;
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
    
    public GameWorldController() {
		projectiles = new ArrayList<>();
		this.bullet = new ImageView("/images/bullet.png");
		this.life = new ImageView("/images/life.png");
		bullet.fitWidthProperty();
		bullet.fitHeightProperty();
		life.fitWidthProperty();
		life.fitHeightProperty();
	}
    
    @FXML
    void initialize() {
    	//ViewUtils.setStageFullScreen();
    	worldCanvas.setWidth(ViewUtils.getScene().getWidth());
    	worldCanvas.setHeight(ViewUtils.getScene().getHeight());
    	menuPane.setLayoutX(worldCanvas.getBoundsInLocal().getMaxX()/(1.11));
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
    public void setController(Controller controller) {
    	this.controller = controller;
    }
    
    /**
     * This method allows the tank movement
     * @param event
     * 			the key event.
     */
    public void moveTank(KeyEvent event) {
    	ViewUtils.getScene().setOnKeyPressed(e -> this.controller.getControllerObject().movePlayerTank(event, true));
    	ViewUtils.getScene().setOnKeyReleased(e -> this.controller.getControllerObject().movePlayerTank(event, false));
    }

    /**
     * This method allows the cannon movement
     * @param event
     * 			the mouse event.
     */
    public void moveCannon(MouseEvent event) {
    	//ViewUtils.getScene().setOnMouseMoved(e -> this.controller.getControllerObject().movePlayerCannon(event));
    	//this.alliedCannon.getTransforms().add(new Rotate());
    }
    
    
    /**
     * This method allows the tank to shoot
     * @param event
     * 			the mouse event.
     */
    public void tankShot(MouseEvent event) {
        ViewUtils.getScene().setOnMouseClicked(e -> this.controller.getControllerObject().playerShot(event));
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
    	/*Pair<Double, Double> playerPosition = this.controller.getControllerObject().getPlayerPosition();
    	Pair<Double, Double> enemyPosition = this.controller.getControllerObject().getEnemyPosition();
    	this.playerTank.setLayoutX(playerPosition.getFirst());
    	this.playerTank.setLayoutY(playerPosition.getSecond());
    	this.playerTank.setWidth(controller.getControllerObject().getTankDimension().getFirst());
    	this.playerTank.setHeight(controller.getControllerObject().getTankDimension().getSecond());
    	this.enemyTank.setWidth(controller.getControllerObject().getTankDimension().getFirst());
    	this.enemyTank.setHeight(controller.getControllerObject().getTankDimension().getSecond());
    	this.enemyTank.setLayoutX(enemyPosition.getFirst());
    	this.enemyTank.setLayoutY(enemyPosition.getSecond());
    	this.alliedCannon.setLayoutX(controller.getControllerObject().getPlayerCannonPosition().getFirst());
    	this.alliedCannon.setLayoutY(controller.getControllerObject().getPlayerCannonPosition().getSecond());
    	this.alliedCannon.setWidth(controller.getControllerObject().getCannonDimension().getFirst());
    	this.alliedCannon.setHeight(controller.getControllerObject().getCannonDimension().getSecond());
    	this.enemyCannon.setLayoutX(controller.getControllerObject().getEnemyCannonPosition().getFirst());
    	this.enemyCannon.setLayoutY(controller.getControllerObject().getEnemyCannonPosition().getSecond());
    	this.enemyCannon.setWidth(controller.getControllerObject().getCannonDimension().getFirst());
    	this.enemyCannon.setHeight(controller.getControllerObject().getCannonDimension().getSecond());
    	this.alliedCannon.setRotate(this.controller.getControllerObject().getPlayerAngle());
    	this.enemyCannon.setRotate(this.controller.getControllerObject().getEnemyAngle());*/
    	this.projectiles.forEach(p -> this.gameWorld.getChildren().removeAll(this.projectiles));
    	this.projectiles.removeAll(this.projectiles);
    	this.controller.getControllerObject().getProjectiles().forEach(p -> {
    	this.projectiles.add(bullet);});
    	this.projectiles.forEach(p -> this.gameWorld.getChildren().add(p));
    	this.playerLife.forEach(p -> this.gameWorld.getChildren().removeAll(this.playerLife));
    	this.playerLife.removeAll(this.playerLife);
    	for(int i = 0; i < this.controller.getControllerObject().getPlayerLifes(); i++) {
    		this.playerLife.add(life);
    	}
    	this.enemyLife.forEach(p -> this.gameWorld.getChildren().removeAll(this.enemyLife));
    	this.enemyLife.removeAll(this.enemyLife);
    	for(int i = 0; i < this.controller.getControllerObject().getEnemyLifes(); i++) {
    		this.playerLife.add(life);
    	}
    	this.playerLife.forEach(l -> this.gameWorld.getChildren().add(l));
    	this.enemyLife.forEach(l -> this.gameWorld.getChildren().add(l));
    	if(this.controller.getControllerObject().getEnemyLifes() == 0) {
    		this.gameWorld.getChildren().remove(this.enemyTank);
    		this.gameWorld.getChildren().remove(this.enemyCannon);
    	}
    	if(this.controller.getControllerObject().getPlayerLifes() == 0) {
    		this.gameWorld.getChildren().remove(this.playerTank);
    		this.gameWorld.getChildren().remove(this.alliedCannon);
    	}
    }
}
