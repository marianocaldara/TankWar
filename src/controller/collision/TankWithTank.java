package controller.collision;

import java.util.Map;

import model.object.AbstractTank;
import model.utility.Direction;
import model.utility.Pair;

public class TankWithTank implements Collision {
	
	private static final double MARGINAL_DISTANCE = 5;
	private AbstractTank playerTank;
	private AbstractTank enemyTank;
	private Map<Direction, Boolean> movement;
	
	public TankWithTank(AbstractTank playerTank, AbstractTank enemyTank, Map<Direction, Boolean> movement) {
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
