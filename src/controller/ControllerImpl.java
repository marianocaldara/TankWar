package controller;

import controller.collision.FactoryCollision;
import controller.collision.FactoryCollisionImpl;
import controller.levels.Level;
import controller.levels.LevelImpl;
import controller.loader.FileController;
import controller.loader.FileControllerImpl;
import controller.objects.AI;
import controller.objects.ControllerObjects;
import controller.utility.CheckCollision;
import controller.utility.Convertitor;
import model.Model;
import view.View;

/**
 * Concrete implementation of {@link Controller} interface.
 */
public class ControllerImpl implements Controller {
	private static final double MIN_DISTANCE = 70;
	private static final double DEFAULT_TIME_TO_SHOT = 3000;
	private ControllerObjects controllerObjects;
	private Level level;
	private FileController file;
	private Model world;
	private View view;
	private GameLoopImpl gameLoop;
	private double timeToShot;
	private FactoryCollision factoryCollision;
	
	/**
	 * Constructor
	 * @param world
	 * 			the {@link Model} of the game.
	 * @param view
	 * 			the {@link View} of the game.
	 */
	public ControllerImpl(Model world, View view){
		this.world = world;
		this.view = view;
		Convertitor.initialize(this.world, this.view);
		CheckCollision.initialize(this.world);
		this.file = new FileControllerImpl(this.world);
		this.level = new LevelImpl(this.file, this);
		this.timeToShot = DEFAULT_TIME_TO_SHOT;
		this.factoryCollision = new FactoryCollisionImpl(this.world.getBounds());
		
	}
	
	@Override
	public ControllerObjects getControllerObjects() {
		return this.controllerObjects;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public void startGameLoop() {
		new Thread(this.gameLoop = new GameLoopImpl(this, this.view)).start();
		
	}

	@Override
	public GameLoop getGameLoop() {
		return this.gameLoop;
	}

	@Override
	public void initializeObjects() {
		this.controllerObjects = new ControllerObjects(this.factoryCollision, this.world.getPlayer(), this.world.getEnemy(), this.world.getPlayerInput(), MIN_DISTANCE, 
				this.timeToShot);
		AI.initialize(this.world.getBounds(), MIN_DISTANCE, this.world.getEnemyInput());
		
	}
	
	@Override
	public void setTimeToShot(double time) {
		this.timeToShot = time;
	}

}
