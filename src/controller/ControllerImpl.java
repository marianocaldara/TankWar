package controller;

import controller.collision.FactoryCollision;
import controller.collision.FactoryCollisionImpl;
import controller.levels.Level;
import controller.levels.LevelImpl;
import controller.loader.FileController;
import controller.loader.FileControllerImpl;
import controller.objects.AI;
import controller.objects.ControllerObjects;
import controller.output.ControllerOutput;
import controller.utility.CheckCollision;
import controller.utility.Convertitor;
import model.Model;
import view.View;

/**
 * Concrete implementation of {@link Controller} interface.
 */
public class ControllerImpl implements Controller {
	
	private static final ControllerImpl SINGLETON = new ControllerImpl();
	private static final double DEFAULT_TIME_TO_SHOT = 3000;
	private ControllerObjects controllerObjects;
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
	private ControllerImpl(){
			
	}
	
	@Override
	public void initializeController(Model world, View view) {
		this.world = world;
		this.view = view;
		Convertitor.initialize(this.world, this.view);
		CheckCollision.initialize(this.world);
		this.file = new FileControllerImpl(this.world);
		LevelImpl.getLevelImpl().initialize(this.file, this);
		this.timeToShot = DEFAULT_TIME_TO_SHOT;
		this.factoryCollision = new FactoryCollisionImpl(this.world.getBounds());
		
	}

	/**
	 * Getter of the object {@link ControllerImpl}.
	 * 
	 * @return the object {@link ControllerImpl}.
	 */
	public static ControllerImpl getController() {
		return SINGLETON;
	}
	
	@Override
	public ControllerOutput getControllerOutput() {
		return this.controllerObjects.getControllerOutput();
	}
	
	@Override
	public ControllerObjects getControllerObjects() {
		return this.controllerObjects;
	}

	@Override
	public Level getLevel() {
		return LevelImpl.getLevelImpl();
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
		this.controllerObjects = new ControllerObjects(this.factoryCollision, this.world.getPlayer(), this.world.getEnemy(), this.world.getPlayerInput(), 
				this.timeToShot);
		AI.initialize(this.world.getBounds(), this.world.getEnemyInput());
		
	}
	
	@Override
	public void setTimeToShot(double time) {
		this.timeToShot = time;
	}

	

}
