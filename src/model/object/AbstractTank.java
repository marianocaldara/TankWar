package model.object;

import model.input.Input;
import model.input.InputImpl;
import model.utility.Pair;
/**
 * Abstract class of Tank. It's start for Enemy and Player Tank.
 */
public abstract class AbstractTank implements Tank{
	
    protected int lifes;
    protected Pair<Double, Double> position;
    protected final static Pair<Double, Double> DIMENSION = new Pair<>(25.0, 25.0);
    protected double speed;
    protected double speedX = 0;
    protected double speedY = 0;
    /**
     * Get if the tank is alive.
     * @return true if it's alive
     */
    public boolean isAlive() {
        return this.lifes!=0;
    }
    
    /**
     * Getter for nr. of lifes.
     * @return number of lifes
     */
    public int getLifes() {
        return this.lifes;
    }
    /**
     * Setter for position of tank.
     * @param position {@link Pair} with coordinates of Tank
     */
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }
    /**
     * Getter for position.
     * @return {@link Pair} with coordinates of Tank
     */
    public Pair<Double, Double> getPosition() {
        return this.position;
    }
    /**
     * Inflict damage to Tank's lifes.
     * @param damage nr of lifes to sub
     */
    public void damage(final int damage) {
    	this.lifes -= damage;
    }
    /**
     * Getter for dimension of tank.
     * @return {@link Pair} with height and widht of tank
     */
    public Pair<Double, Double> getDimension() {
    	return new Pair<Double, Double>(DIMENSION.getFirst(), DIMENSION.getSecond());
    }
    /**
     * Shoot a projectile and return it.
     * @return a {@link Projectile}
     */
    public abstract Projectile shot();
    /**
     * Set new position of Tank, and new position of cannon.
     * @param i the {@linkplain InputImpl} that contain the new position and angle 
     */
    public abstract void update(final Input i);
    /**
     * Getter for position of cannon's tank.
     * @return {@linkplain Pair} with coordinates of cannon's tank
     */
    public abstract Pair<Double, Double> getCannonPosition();
    /**
     * Getter for dimension of cannon's tank.
     * @return {@linkplain Pair}
     */
    public abstract Pair<Double, Double> getCannonDimension();
    /**
     * Getter for angle of cannon's tank.
     * @return angle of cannon's tank.
     */
    public abstract double getAngle();

}
