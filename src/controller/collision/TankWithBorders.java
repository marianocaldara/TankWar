package controller.collision;

import model.object.Tank;
import model.utility.Pair;

/**
 *	Concrete implementation of {@link Collision} interface.
 */
public class TankWithBorders implements Collision {
	
	private Tank tank;
	private final Pair<Double, Double> worldBounds;
	
	/**
	 * Constructor.
	 * @param tank
	 * 			the colliding {@link Tank}.
	 * @param worldBounds
	 * 			the {@link Model} bounds.
	 */
	public TankWithBorders(Tank tank, final Pair<Double, Double> worldBounds) {
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
