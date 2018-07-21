package controller.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.collision.Collision;
import controller.utility.Convertitor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.input.InputImpl;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Direction;
import model.utility.Pair;
import model.utility.AI;

/**
 * Implementation of the interfaces {@link ControllerProjectile} and {@link ControllerTank}.
 */
public class ControllerObjects implements ControllerProjectile, ControllerTank {
	
	private Tank playerTank;
	private Tank enemyTank;
	private InputImpl playerInput;
	private Map<Direction, Boolean> movements;
	private List<Projectile> projectiles;
	private Convertitor convertitor;
	private final Collision collision;
	
	/**
	 * Constructor.
	 * @param playerTank
	 * 		the player tank.
	 * 		@see Tank.
	 * @param enemyTank
	 * 		the enemy Tank.
	 * 		@see Tank.
	 * @param playerInput
	 * 		the input for the player.
	 * 		@see InputImpl.
	 * @param convertitor
	 * 		a convertitor for position and dimension.
	 * 		@see Convertitor.
	 */
	public ControllerObjects(Tank playerTank, Tank enemyTank, InputImpl playerInput, Convertitor convertitor, Collision collision) {
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
		this.playerInput = playerInput;
		this.movements = new HashMap<>();
		Arrays.asList(Direction.values()).forEach(d -> this.movements.put(d, false));
		this.projectiles = new ArrayList<>();
		this.convertitor = convertitor;
		this.collision = collision;
	}

	@Override
	public void movePlayerTank(KeyEvent keyInput, boolean b) {
		switch(keyInput.getCode()) {
		case UP: this.movements.put(Direction.UP, b); break;
		case DOWN: this.movements.put(Direction.DOWN, b); break;
		case LEFT: this.movements.put(Direction.LEFT, b); break;
		case RIGHT: this.movements.put(Direction.RIGHT, b); break;
		default: ;
		}
		this.playerInput.setMovement(this.movements);
	}
	
	@Override
	public void movePlayerCannon(MouseEvent mouseInput) {
		this.playerInput.setTarget(convertitor.viewToModel(new Pair<>(mouseInput.getSceneX(), mouseInput.getSceneY())));
	}

	@Override
	public void playerShot(MouseEvent mouseInput) {
		switch(mouseInput.getButton()) {
		case PRIMARY: this.projectiles.add(this.playerTank.shot()); break;
		default: ;
		}
	}

	@Override
	public List<Pair<Double, Double>> getProjectiles() {
		return Collections.unmodifiableList(this.projectiles.stream().map(p -> this.convertitor.modelToView(p.getBounds())).collect(Collectors.toList()));
	}	
	
	@Override
	public void updateTank() {
		this.playerTank.update(playerInput);
		this.enemyTank.update(AI.act(this.enemyTank, this.playerTank));
		this.collision.tankWithTank(this.movements);
		this.collision.tankWithBorders();
	}
	
	@Override
	public void updateProjectiles() {
		this.projectiles.forEach(p -> p.update());
		this.collision.projectileWithBorders(this.projectiles);
		this.collision.tankWithProjectile(projectiles);
		this.collision.projectileWithProjectile(projectiles);
		this.deleteProjectiles(this.getDeadProjectiles());
		
	}
	
	@Override
	public Pair<Double, Double> getPlayerPosition() {
		return this.convertitor.modelToView(this.playerTank.getPosition());
	}

	@Override
	public Pair<Double, Double> getEnemyPosition() {
		return this.convertitor.modelToView(this.enemyTank.getPosition());
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
	public double getPlayerAngle() {
		return this.playerTank.getAngle();
	}
	
	@Override
	public double getEnemyAngle() {
		return this.enemyTank.getAngle();
	}
	
	@Override
	public Pair<Double, Double> getTankDimension(){
		return this.convertitor.modelToView(this.playerTank.getDimension());
	}
	
	@Override
	public Pair<Double, Double> getCannonDimension(){
		return this.convertitor.modelToView(this.playerTank.getCannonDimension());
	}
	
	@Override
	public Pair<Double, Double> getProjectileDimension(){
		return this.convertitor.modelToView(this.projectiles.get(0).getBounds());
	}
	
	@Override
	public Pair<Double, Double> getPlayerCannonPosition(){
		return this.convertitor.modelToView(this.playerTank.getCannonPosition());
	}
	
	@Override
	public Pair<Double, Double> getEnemyCannonPosition(){
		return this.convertitor.modelToView(this.enemyTank.getCannonPosition());
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

}
