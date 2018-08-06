package controller.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controller.collision.FactoryCollision;
import controller.exceptions.ProjectileOutOfBordersException;
import controller.exceptions.ProjectileWithProjectileException;
import controller.exceptions.TankOutOfBordersException;
import controller.exceptions.TankWithProjectileException;
import controller.exceptions.TankWithTankException;
import controller.utility.Collision;
import controller.utility.Convertitor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.input.Input;
import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Calculate;
import model.utility.Direction;
import model.utility.Pair;

/**
 *  Concrete implementation of the interfaces {@link ControllerTank} and {@link ControllerProjectile}.
 *  This class control all the main objects of the game and connect the {@link View} to the {@link Model}.
 */
public class ControllerObjects implements ControllerTank, ControllerProjectile {
	
	private static final double MIN_DISTANCE_TO_SHOT = 350;
	private static double MIN_DISTANCE;
	private final AbstractTank playerTank;
	private final AbstractTank enemyTank;
	private final Input playerInput;
	private List<Projectile> projectiles;
	private double timeToShot;
	private long initialTime;
	private long finalTime;
	private FactoryCollision factoryCollision;
	
	/**
	 * Constructor.
	 * @param playerTank
	 * 		the player {@link Tank}.
	 * @param enemyTank
	 * 		the enemy {@link Tank}.
	 * @param playerInput
	 * 		the player {@link Input}.
	 * @param minDistance
	 * 		the minimum distance between a {@link Projectile} and the enemy {@link Tank}. If the distance is lower the tank targets the projectile.
	 * @param timeToShot
	 * 		the the time in ms between two enemy shots.
	 */
	public ControllerObjects(final FactoryCollision factoryCollision, final AbstractTank playerTank, final AbstractTank enemyTank, final Input playerInput, double minDistance, 
			double timeToShot) {
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
		this.playerInput = playerInput;
		MIN_DISTANCE = minDistance;
		this.timeToShot = timeToShot;
		this.projectiles = new ArrayList<>();
		this.factoryCollision = factoryCollision;
	}

	@Override
	public void playerShot(MouseEvent event) {
		switch(event.getButton()) {
		case PRIMARY: this.projectiles.add(this.playerTank.shot()); break; //da sistemare
		default: ;
		}

	}

	@Override
	public List<Pair<Double, Double>> getProjectiles() {
		return Collections.unmodifiableList(this.projectiles.stream().map(p -> Convertitor.modelToView(p.getPosition())).collect(Collectors.toList()));
	}

	@Override
	public void updateProjectiles() {
		this.projectiles.forEach(p -> p.update());
		this.checkProjectileCollision();
		this.deleteProjectiles(this.getDeadProjectiles());
	}

	@Override
	public Pair<Double, Double> getProjectileDimension() {
		return Convertitor.modelToView(this.projectiles.get(0).getBounds());
	}

	@Override
	public void movePlayerTank(KeyEvent event, boolean b) {
		switch(event.getCode()) {
		case UP: this.playerInput.getMovement().put(Direction.UP, b); break;
		case DOWN: this.playerInput.getMovement().put(Direction.DOWN, b); break;
		case LEFT: this.playerInput.getMovement().put(Direction.LEFT, b); break;
		case RIGHT: this.playerInput.getMovement().put(Direction.RIGHT, b); break;
		default: ;
		}

	}

	@Override
	public void updateTank() {
		this.playerTank.update(this.playerInput);
		this.enemyTank.update(AI.act(this.enemyTank, this.playerTank, this.getNearestProjectiles()));
		if(this.finalTime - this.initialTime > this.timeToShot && Calculate.distance(this.playerTank.getPosition(), this.enemyTank.getPosition()) < MIN_DISTANCE_TO_SHOT) {			
			this.initialTime = System.currentTimeMillis();
			this.projectiles.add(AI.shotEnemy(this.enemyTank)); // da sistemare
		}
		this.finalTime = System.currentTimeMillis();
		this.checkTankCollision();
	}

	@Override
	public Pair<Double, Double> getPlayerPosition() {
		return Convertitor.modelToView(this.playerTank.getPosition());
	}

	@Override
	public Pair<Double, Double> getEnemyPosition() {
		return Convertitor.modelToView(this.enemyTank.getPosition());
	}

	@Override
	public int getPlayerLifes() {
		return this.playerTank.getLifes();
	}

	@Override
	public int getEnemyLifes() {
		return this.enemyTank.getLifes();
	}

	@Override
	public Pair<Double, Double> getTankDimension() {
		return Convertitor.modelToView(this.playerTank.getDimension());
	}

	@Override
	public boolean isPlayerAlive() {
		return this.playerTank.isAlive();
	}

	@Override
	public boolean isEnemyAlive() {
		return this.enemyTank.isAlive();
	}

	@Override
	public Pair<Double, Double> getCannonDimension() {
		return Convertitor.modelToView(this.playerTank.getCannonDimension());
	}

	@Override
	public Pair<Double, Double> getPlayerCannonPosition() {
		return Convertitor.modelToView(this.playerTank.getCannonPosition());
	}

	@Override
	public Pair<Double, Double> getEnemyCannonPosition() {
		return Convertitor.modelToView(this.enemyTank.getCannonPosition());
	}

	@Override
	public void movePlayerCannon(MouseEvent event) {
		this.playerInput.setTarget(Convertitor.viewToModel(new Pair<Double, Double>(event.getSceneX(), event.getSceneY())));

	}

	@Override
	public double getPlayerAngle() {
		return this.playerTank.getAngle();
	}

	@Override
	public double getEnemyAngle() {
		return this.enemyTank.getAngle();
	}
	
	/**
	 * Getter of the list of dead projectiles.
	 * @return the list of dead {@link Projectile}.
	 */
	private List<Projectile> getDeadProjectiles() {
		return this.projectiles.stream().filter(p -> !p.isAlive()).collect(Collectors.toList());
	}
	
	/**
	 * Delete all the dead projectiles from the map.
	 * @param deadProjectiles
	 * 		the list of dead {@link Projectile}.
	 */
	private void deleteProjectiles(List<Projectile> deadProjectiles) {
		this.projectiles.removeAll(deadProjectiles);
	}
	
	/**
	 * Getter of all the {@link Projectile} nearest to the enemy {@link Tank}.
	 * @return the {@link List} of the nearest projectiles.
	 */
	private List<Projectile> getNearestProjectiles() {
		return this.projectiles.stream().filter(p -> (int)Calculate.distance(p.getPosition(), 
				new Pair<>(this.enemyTank.getPosition().getFirst() + this.enemyTank.getDimension().getFirst()/2, 
						this.enemyTank.getPosition().getSecond() + this.enemyTank.getDimension().getSecond()/2)) < MIN_DISTANCE).
				collect(Collectors.toList());
				
	}
	
	private void checkTankCollision() {
		try {
			Collision.tankWithBorders(this.playerTank);
		} catch (TankOutOfBordersException e) {
			this.factoryCollision.tankWithBordersCollision(this.playerTank).manageCollision();
		}
		try {
			Collision.tankWithBorders(this.enemyTank);
		} catch (TankOutOfBordersException e) {
			this.factoryCollision.tankWithBordersCollision(this.enemyTank).manageCollision();
		}
		try {
			Collision.tankWithTank(this.playerTank, this.enemyTank);
		} catch (TankWithTankException e) {
			this.factoryCollision.tankWithTankCollision(this.playerTank, this.enemyTank, this.playerInput.getMovement()).manageCollision();
		}
		
	}
	
	private void checkProjectileCollision() {
		this.projectiles.forEach(p -> {
			try {
				Collision.projectileWithBorders(p);
			} catch (ProjectileOutOfBordersException e) {
				this.factoryCollision.projectileWithBordersCollision(p).manageCollision();
			}
		});
		try {
			Collision.tankWithProjectile(projectiles);
		} catch (TankWithProjectileException e) {
			this.factoryCollision.tankWithProjectileCollision(this.playerTank, this.enemyTank, this.projectiles).manageCollision();
		}
		try {
			Collision.projectileWithProjectile(projectiles);
		} catch (ProjectileWithProjectileException e) {
			this.factoryCollision.projectileWithProjectileCollision(this.projectiles).manageCollision();
		}
	}

}
