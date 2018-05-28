package controller.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.input.KeyboardInput;
import controller.input.MouseInput;
import controller.utility.Convertitor;
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
	public ControllerObjects(Tank playerTank, Tank enemyTank, InputImpl playerInput, Convertitor convertitor) {
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
		this.playerInput = playerInput;
		this.movements = new HashMap<>();
		this.projectiles = new ArrayList<>();
		this.convertitor = convertitor;
	}

	@Override
	public void movePlayerTank(KeyboardInput keyInput, boolean b) {
		switch(keyInput.getKeyInput().getCode()) {
		case UP: this.movements.put(Direction.UP, b); break;
		case DOWN: this.movements.put(Direction.DOWN, b); break;
		case LEFT: this.movements.put(Direction.LEFT, b); break;
		case RIGHT: this.movements.put(Direction.RIGHT, b); break;
		default: ;
		}
		this.playerInput.setMovement(this.movements);
	}
	
	@Override
	public void moveEnemyTank() {
		AI.act(this.enemyTank, this.playerTank);		
	}
	
	@Override
	public void movePlayerCannon(MouseInput mouseInput) {
		this.playerInput.setTarget(convertitor.viewToModelPosition(
				new Pair<Double, Double>(mouseInput.getMouseInput().getSceneX(), mouseInput.getMouseInput().getSceneY())));
	}

	@Override
	public void playerShot(MouseInput mouseInput) {
		switch(mouseInput.getMouseInput().getButton()) {
		case PRIMARY: this.projectiles.add(this.playerTank.shot()); break;
		default: ;
		}
	}

	@Override
	public List<Projectile> getProjectiles() {
		this.deleteProjectiles(this.getDeadProjectiles());
		return Collections.unmodifiableList(this.projectiles);
	}	
	
	public void updateTank() {
		this.playerTank.update(playerInput);
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
