package controller.collision;

import java.util.Map;

import model.object.Tank;
import model.utility.Direction;
import model.utility.Pair;

/**
 *	Concrete implementation of {@link Collision} interface.
 */
public class TankWithTank implements Collision {
	
	private static final double MARGINAL_DISTANCE = 5;
	private Tank playerTank;
	private Tank enemyTank;
	private Map<Direction, Boolean> movement;
	
	/**
	 * Constructor.
	 * @param playerTank
	 * 			the player {@link Tank}.
	 * @param enemyTank
	 * 			the enemy {@link Tank}.
	 * @param movement
	 * 			the player movements.
	 */
	public TankWithTank(Tank playerTank, Tank enemyTank, Map<Direction, Boolean> movement) {
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
		this.movement = movement;
	}

	@Override
	public void manageCollision() {
		if(movement.get(Direction.RIGHT) && movement.get(Direction.UP) && this.playerTank.getPosition().getFirst() + 
				this.playerTank.getDimension().getFirst() >= this.enemyTank.getPosition().getFirst() + MARGINAL_DISTANCE) {
			this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
					this.enemyTank.getPosition().getSecond() + this.playerTank.getDimension().getSecond()));
		}
		else if(movement.get(Direction.RIGHT) && movement.get(Direction.DOWN) && this.playerTank.getPosition().getFirst() + 
				this.playerTank.getDimension().getFirst() >= this.enemyTank.getPosition().getFirst() + MARGINAL_DISTANCE) {
			this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
					this.enemyTank.getPosition().getSecond() - this.playerTank.getDimension().getSecond()));
			}
		else if(movement.get(Direction.LEFT) && movement.get(Direction.UP) && this.playerTank.getPosition().getFirst()
				<= this.enemyTank.getPosition().getFirst() + this.enemyTank.getDimension().getFirst() - MARGINAL_DISTANCE) {
			this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
					this.enemyTank.getPosition().getSecond() + this.playerTank.getDimension().getSecond()));
		}
		else if(movement.get(Direction.LEFT) && movement.get(Direction.DOWN) && this.playerTank.getPosition().getFirst()
				<= this.enemyTank.getPosition().getFirst() + this.enemyTank.getDimension().getFirst() - MARGINAL_DISTANCE) {
			this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
					this.enemyTank.getPosition().getSecond() - this.playerTank.getDimension().getSecond()));
		}
		else if(movement.get(Direction.RIGHT)) {
			this.playerTank.setPosition(new Pair<>(this.enemyTank.getPosition().getFirst() - this.playerTank.getDimension().getFirst(),
					this.playerTank.getPosition().getSecond()));
		}
		else if(movement.get(Direction.LEFT)) {
			this.playerTank.setPosition(new Pair<>(this.enemyTank.getPosition().getFirst() + this.playerTank.getDimension().getFirst(),
					this.playerTank.getPosition().getSecond()));
		}
		else if(movement.get(Direction.DOWN)) {
			this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
					this.enemyTank.getPosition().getSecond() - this.playerTank.getDimension().getSecond()));
		}
		else if(movement.get(Direction.UP)) {
			this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
					this.enemyTank.getPosition().getSecond() + this.playerTank.getDimension().getSecond()));
		}
	}

}
