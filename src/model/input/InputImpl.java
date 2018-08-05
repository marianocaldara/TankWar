package model.input;

import java.util.*;

import model.utility.Direction;
import model.utility.Pair;
/**
 * Concrete model implementation of Input for a Tank
 * <p>
 * This class bind {@link TankInput} and {@link CannonInput}. Both describes the input for Tank.
 * 
 * @see TankImpl
 * @see Pair
 */
public class InputImpl implements Input {
	
	private Map<Direction, Boolean> movement;
	private Pair<Double, Double> target;
	
	public InputImpl() {
		this.movement = new HashMap<>();
		Arrays.asList(Direction.values()).forEach(d -> this.movement.put(d, false));
		this.target = new Pair<>(0.0, 0.0);
        }
	
	public InputImpl(Map<Direction, Boolean> movement, Pair<Double, Double> target) {
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