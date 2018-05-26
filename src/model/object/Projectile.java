package model.object;

import javafx.geometry.Rectangle2D;
import model.utility.Direction;
import model.utility.Pair;
/**
 * Projectile implementation, is the object returned and handled by cannon in {@link Tank}.
 * <p>
 * 
 * Projectile have a speed, and angle, position is memorized in {@link Pair}.
 * This object does damage to {@link Tank} and can be used with {@link Tank#shot()}. The projectile could bounce.
 * 
 * @see Pair
 */
public class Projectile {
	final private Pair<Double, Double> position;
	private double speedX;
	private double speedY;
	private boolean alive = true;
	private int nrbounced;
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
	public Projectile(final Pair<Double, Double> position, final double angle, final double speed) {
		this.position = position;
		this.speedX = speed*Math.cos(Math.toRadians(angle));
		this.speedY = speed*Math.sin(Math.toRadians(angle));
	}
	/**
	 * Getter for position
	 * @return the position in x and y in Double
	 * @see Pair
	 */
	public Pair<Double, Double> getPosition() {
		return this.position;
	}
	/**
	 * Bounce a projectile in opposite direction
	 * @param dir the direction of the border where projectile bounce
	 * @see Direction
	 */
	public void bounce(final Direction dir) {
		if(nrbounced>=MAX_BOUNCE) {
			throw new IllegalStateException();
		}
		nrbounced++;
		if(dir.equals(Direction.UP) || dir.equals(Direction.DOWN)){
			this.speedY = -speedY; 
		}
		if(dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT)) {
			this.speedX = -speedX;
		}
	}
	/**
	 * Set dead projectile
	 */
	public void setDead() {
		this.alive = false;
	}
	/**
	 * Getter for {@link #isAlive}
	 * @return a boolean, true if is alive, false if this projectile have collided
	 */
	public boolean isAlive() {
		return this.alive;
	}
	/**
	 * Get projectile's bounds.
	 * @return {@link Rectangle2D} according to {@link #dimension} of Projectile
	 */
	public Rectangle2D getBounds() {
        return new Rectangle2D(this.position.getFirst(), this.position.getSecond(), DIMENSION.getFirst(), DIMENSION.getSecond());
	}
	/**
	 * update position of projectile according to speed
	 */
	public void update() {
		this.position.setFirst(this.position.getFirst()+speedX);
		this.position.setSecond(this.position.getSecond()+speedY);
	}
}
