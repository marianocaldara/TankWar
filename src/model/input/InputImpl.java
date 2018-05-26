package model.input;

import java.util.*;

import model.utility.Direction;
import model.utility.Pair;
/**
 * Concrete model implementation of Input for a Tank
 * <p>
 * This class bind {@link DirectionInput} and {@link CannonInput}. Both describes the input for Tank.
 * 
 * @see TankImpl
 * @see Pair
 */
public class InputImpl implements DirectionInput, CannonInput {
	
	private Map<Direction, Boolean> movement;
	private Pair<Double, Double> target;
	/**
	 * Constructor that memorize all fields necessary for {@link DirectionInput} and {@link CannonInput} 	
	 * @param movement 
	 *     map for next movement
	 * @param target
	 *     position where is aiming the cannon
	 */
	public InputImpl(final Map<Direction, Boolean> movement, final Pair<Double, Double> target) {
		this.movement = movement;
		this.target = target;
	}
	@Override
	public Pair<Double, Double> getTargetPosition() {
		return this.target;
	}

	@Override
	public void setTarget(final Pair<Double, Double> target) {
		this.target = target;
		
	}

	@Override
	public void setMovement(final Map<Direction, Boolean> movement) {
		this.movement = movement;	
	}

	@Override
	public Map<Direction, Boolean> getMovement() {
		return this.movement;
	}
}