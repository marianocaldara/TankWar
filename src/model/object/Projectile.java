package model.object;

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
	private Pair<Double, Double> position;
	private double speedX;
	private double speedY;
	private boolean isAlive = true;
	private int nr_bounced;
	private static int MAX_BOUNCE = 1;
	
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
	public Projectile(Pair<Double, Double> position, double angle, double speed) {
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
	public void bounce(Direction dir) {
		if(nr_bounced>=MAX_BOUNCE) {
			throw new IllegalStateException();
		}
		nr_bounced++;
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
		this.isAlive = false;
	}
	/**
	 * Getter for {@link #isAlive}
	 * @return a boolean, true if is alive, false if this projectile have collided
	 */
	public boolean isAlive() {
		return this.isAlive;
	}
	/**
	 * update position of projectile according to speed
	 */
	public void update() {
		this.position.setFirst(this.position.getFirst()+speedX);
		this.position.setSecond(this.position.getSecond()+speedY);
	}
}
