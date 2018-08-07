package model.object;

import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete implementation of {@link Projectile}.
 * @author Scu
 *
 */
public class ProjectileImpl implements Projectile{
	final private Pair<Double, Double> position;
	private double speedX;
	private double speedY;
	private boolean alive = true;
	private int nrbounced = 0;
	private final static int MAX_BOUNCE = 1;
	private final static Pair<Double, Double> DIMENSION = new Pair<>(5.0, 5.0);
	
	/**
	 * Constructor.
	 * @param position when projectile is shooted. Pay attention to shoot out of position of own Tank
	 * @param angle which projectile is shooted, depends about cannon angulation
	 * @param speed of prejectile, it make possible calculate {@link #speedX} and {@link #speedY}
	 *     
	 * @see Math#toRadians(double)
	 * @see Math#cos(double)
         * @see Math#sin(double)
	 */
	public ProjectileImpl(final Pair<Double, Double> position, final double angle, double speed) {
		this.position = position;
		this.speedX = speed*Math.cos(Math.toRadians(angle));
		this.speedY = speed*Math.sin(Math.toRadians(angle));
	}
	
	public Pair<Double, Double> getPosition() {
		return this.position;
	}
	
	public void bounce(final Direction dir) {
		if(nrbounced>=MAX_BOUNCE) {
			throw new IllegalStateException();
		}
		else {
			if(dir.equals(Direction.UP) || dir.equals(Direction.DOWN)){
				this.speedY = -this.speedY; 
			}
			if(dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT)) {
				this.speedX = -this.speedX;
			}
			nrbounced++;
		}
	}
	
	public void setDead() {
		this.alive = false;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public Pair<Double, Double> getBounds() {
        return new Pair<Double, Double>(DIMENSION.getFirst(), DIMENSION.getSecond());
	}
	
	public void update() {
		this.position.setFirst(this.position.getFirst()+this.speedX);
		this.position.setSecond(this.position.getSecond()+this.speedY);
	}		
	
}
