package view.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

public class GameWorldController extends ViewController{

	private static final double WIDTH_RAPPORT = 30; //serve per ridisegnare le vite
	private static final double HEIGHT_RAPPORT = 20; //serve per ridisegnare le vite
	private Image life = new Image("res/life.png", 40, 40, false, false); //immagine della vita
	private Image projectile = new Image("res/ball.png", 10, 10, false, false); //immagine del proiettile
	private List<ImageView> projectiles = new ArrayList<>();
	private List<ImageView> playerLives = new ArrayList<>();
	private List<ImageView> enemyLives = new ArrayList<>();
	private Controller controller;

	@FXML
	private ImageView backGround;
	
    @FXML
    private ImageView playerTank;

    @FXML
    private ImageView playerCannon;

    @FXML
    private Group worldGroup;

    @FXML
    private Canvas worldCanvas;

    @FXML
    private ImageView enemyTank;

    @FXML
    private ImageView enemyCannon;
    
    //inizializza il controller
    @Override
    public void init(Controller controller) {
    	this.controller = controller;
    }
    
    public void moveTank() {
    	ViewUtils.getScene().setOnKeyPressed(e -> this.controller.getControllerObjects().movePlayerTank(e, true));
    	ViewUtils.getScene().setOnKeyReleased(e -> this.controller.getControllerObjects().movePlayerTank(e, false));
    }
    
    public void moveCannon() {
    	ViewUtils.getScene().setOnMouseMoved(e -> this.controller.getControllerObjects().movePlayerCannon(e));
    }
    
    public void shot() {
    	ViewUtils.getScene().setOnMouseClicked(e -> this.controller.getControllerObjects().playerShot(e));
    }  
    
    /*
     * Fa l'update della view. Se il livello è finito e ho vinto richiama lo stage END_LEVEL, se il livello è finito e ho perso
     * richiama LOSE, se il gioco è finito richiama ENDING_GAME altrimenti repainta la view aggiornata.
     */
    public void updateView() {
		if (this.controller.getLevel().isGameEnded()) {
			try {
				ViewScenes.END_GAME.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
						this.controller);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} /*else if (this.controller.getControllerObjects().isPlayerAlive()) {
			// ViewScenes.END_LEVEL.setGameStage(ViewUtils.getScene().getWidth(),
			// ViewUtils.getScene().getHeight(), this.controller, GameState.ENDING_LEVEL);
		} else if (!this.controller.getControllerObjects().isPlayerAlive()) {
			// ViewScenes.LOSE.setGameStage(ViewUtils.getScene().getWidth(),
			// ViewUtils.getScene().getHeight(), this.controller, GameState.LOSING);
		}*/
		
		/*
		 * Questo else if va tolto una volta completato e decommentare il codice sopra.
		 */
		else if (this.controller.getLevel().isLevelEnded()) {
			try {
				ViewScenes.GAME_WORLD.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
						this.controller);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			this.repaint();
		}

	}
    
    //repainta la view.
    private void repaint() {
    	
    		this.drawBackGround();
    		this.playerTank.setLayoutX(this.controller.getControllerObjects().getPlayerPosition().getFirst());
    		this.playerTank.setLayoutY(this.controller.getControllerObjects().getPlayerPosition().getSecond());
    		this.enemyTank.setLayoutX(this.controller.getControllerObjects().getEnemyPosition().getFirst());
    		this.enemyTank.setLayoutY(this.controller.getControllerObjects().getEnemyPosition().getSecond());
    		this.playerTank.setFitWidth(this.controller.getControllerObjects().getTankDimension().getFirst());
    		this.playerTank.setFitHeight(this.controller.getControllerObjects().getTankDimension().getSecond());
    		this.enemyTank.setFitWidth(this.controller.getControllerObjects().getTankDimension().getFirst());
    		this.enemyTank.setFitHeight(this.controller.getControllerObjects().getTankDimension().getSecond());
    		this.playerCannon.setLayoutX(this.controller.getControllerObjects().getPlayerCannonPosition().getFirst());
    		this.playerCannon.setLayoutY(this.controller.getControllerObjects().getPlayerCannonPosition().getSecond());
    		this.enemyCannon.setLayoutX(this.controller.getControllerObjects().getEnemyCannonPosition().getFirst());
    		this.enemyCannon.setLayoutY(this.controller.getControllerObjects().getEnemyCannonPosition().getSecond());
    		this.playerCannon.setFitWidth(this.controller.getControllerObjects().getCannonDimension().getFirst());
    		this.playerCannon.setFitHeight(this.controller.getControllerObjects().getCannonDimension().getSecond());
    		this.enemyCannon.setFitWidth(this.controller.getControllerObjects().getCannonDimension().getFirst());
    		this.enemyCannon.setFitHeight(this.controller.getControllerObjects().getCannonDimension().getSecond());
    		this.playerCannon.setRotate(this.controller.getControllerObjects().getPlayerAngle());
    		this.enemyCannon.setRotate(this.controller.getControllerObjects().getEnemyAngle());
    		this.drawProjectiles();
    		this.drawLives();    		
    	
    }
    
    //disegna le vite
    private void drawLives() {
    	this.playerLives.forEach(p -> this.worldGroup.getChildren().removeAll(this.playerLives));
    	this.playerLives.removeAll(this.playerLives);
    	for(int i = 0; i < this.controller.getControllerObjects().getPlayerLifes(); i++) {
    		ImageView lifeImage = new ImageView(this.life);
    		lifeImage.setLayoutX(i*ViewUtils.getScene().getWidth()/WIDTH_RAPPORT);
    		lifeImage.setLayoutY(0);
    		lifeImage.setFitWidth(ViewUtils.getScene().getWidth()/WIDTH_RAPPORT);
    		lifeImage.setFitHeight(ViewUtils.getScene().getHeight()/HEIGHT_RAPPORT);
    		this.playerLives.add(lifeImage);
    	}
    	this.enemyLives.forEach(p -> this.worldGroup.getChildren().removeAll(this.enemyLives));
    	this.enemyLives.removeAll(this.enemyLives);
    	for(int i = 0; i < this.controller.getControllerObjects().getEnemyLifes(); i++) {
    		ImageView lifeImage = new ImageView(this.life);
    		lifeImage.setFitWidth(ViewUtils.getScene().getWidth()/WIDTH_RAPPORT);
    		lifeImage.setFitHeight(ViewUtils.getScene().getHeight()/HEIGHT_RAPPORT);
    		lifeImage.setLayoutX(ViewUtils.getScene().getWidth() - lifeImage.getFitWidth() - (i*ViewUtils.getScene().getWidth()/WIDTH_RAPPORT)); 
    		lifeImage.setLayoutY(0);
    		this.enemyLives.add(lifeImage);
    	}
    	this.worldGroup.getChildren().addAll(this.playerLives);
    	this.worldGroup.getChildren().addAll(this.enemyLives);
    }
    
    /*
     * Disegna il backGround. l'ho messo come ImageView perchè da CSS non lo resizava quando ingrandivo lo schermo
     */
    private void drawBackGround() {
    	this.worldCanvas.setWidth(ViewUtils.getScene().getWidth());
    	this.worldCanvas.setHeight(ViewUtils.getScene().getHeight());
    	this.backGround.setFitWidth(ViewUtils.getScene().getWidth());
    	this.backGround.setFitHeight(ViewUtils.getScene().getHeight());
    }
    
    //disegna i proiettili.
    private void drawProjectiles() {
    	this.worldGroup.getChildren().removeAll(this.projectiles);
    	this.projectiles.removeAll(this.projectiles);
    	this.controller.getControllerObjects().getProjectiles().forEach(p -> {
    		ImageView projectileImage = new ImageView(projectile);
    		projectileImage.setLayoutX(p.getFirst());
    		projectileImage.setLayoutY(p.getSecond());
    		projectileImage.setFitWidth(this.controller.getControllerObjects().getProjectileDimension().getFirst());
    		projectileImage.setFitHeight(this.controller.getControllerObjects().getProjectileDimension().getSecond());
    		this.projectiles.add(projectileImage);
    	});
    	this.worldGroup.getChildren().addAll(this.projectiles);
    }

}
