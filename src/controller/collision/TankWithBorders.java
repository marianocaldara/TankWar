package controller.collision;

import model.object.AbstractTank;
import model.utility.Pair;

public class TankWithBorders implements Collision {
	
	private AbstractTank tank;
	private final Pair<Double, Double> worldBounds;
	
	public TankWithBorders(AbstractTank tank, final Pair<Double, Double> worldBounds) {
		this.tank = tank;
		this.worldBounds = worldBounds;
	}

	@Override
	public void manageCollision() {
		if (tank.getPosition().getFirst() + tank.getDimension().getFirst() >= worldBounds.getFirst()) {       // Exceeding right
            tank.getPosition().setFirst(worldBounds.getFirst() - tank.getDimension().getFirst());
        } else if (tank.getPosition().getFirst() <= 0) {                // Exceeding left
        	tank.getPosition().setFirst(0.0);
        }
        if (tank.getPosition().getSecond() + tank.getDimension().getSecond() >= worldBounds.getSecond()) {      // Exceeding down
        	tank.getPosition().setSecond(worldBounds.getSecond() - tank.getDimension().getSecond());
        } else if (tank.getPosition().getSecond() <= 0) {                // Exceeding up
        	tank.getPosition().setSecond(0.0);
        }

	}

}
