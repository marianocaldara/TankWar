package model.object;

import java.util.Map;
import java.util.stream.Collectors;

import exceptions.TankDeadException;
import model.input.Input;
import model.input.InputImpl;
import model.utility.Calculate;
import model.utility.Direction;
import model.utility.Pair;

public class PlayerTank implements Tank {

    protected int lifes;
    protected Pair<Double, Double> position;
    protected final static Pair<Double, Double> DIMENSION = new Pair<>(25.0, 25.0);
    protected double speed;
    protected double speedX = 0;
    protected double speedY = 0;
    protected Cannon cannon = new Cannon();
    protected double projectileSpeed;
    /**
     * Constructor.
     * @param position initial position in {@linkplain Pair} of tank
     * @param lifes initial nr of lifes of tank
     * @param speed of tank
     * @param projectileSpeed speed of {@linkplain Projectile}
     */
    public PlayerTank(final Pair<Double, Double> position, final int lifes, final double speed, final double projectileSpeed) {
        this.position = position;
        this.lifes = lifes;
        this.speed = speed;
        this.projectileSpeed = projectileSpeed;
        this.cannon.update(this.position, new Pair<Double, Double>(0.0, 0.0));
    }
    @Override
    public boolean isAlive() {
        return this.lifes!=0;
    }
    @Override
    public int getLifes() {
        return this.lifes;
    }
    @Override
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }
    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }
    @Override
    public void damage(final int damage) {
        this.lifes -= damage;
    }
    @Override
    public Pair<Double, Double> getDimension() {
        return new Pair<Double, Double>(DIMENSION.getFirst(), DIMENSION.getSecond());
    }
    
    @Override
    public Projectile shot() throws TankDeadException{
        if(!isAlive()) {
            throw new TankDeadException("This Tank is dead, cannot shot");
        }
        return this.cannon.shot();
    }

    @Override
    public void update(Input i) throws TankDeadException{
        if(!isAlive()) {
            throw new TankDeadException("This Tank is dead");
        }
        this.setDirection(i.getMovement());
        this.updatePosition();
        this.updateCannon(i.getTargetPosition());
    }
    @Override
    public Pair<Double, Double> getCannonPosition() {
        return this.cannon.getCannonPosition();
    }
    @Override
    public Pair<Double, Double> getCannonDimension() {
        return this.cannon.getCannonDimension();
    }
    @Override
    public double getAngle() {
        return this.cannon.getAngle();
    }
    /**
     * Set {@linkplain AbstractTank#speedX} and {@linkplain AbstractTank#speedY} according {@linkplain AbstractTank#speed}.
     * @param movement the couple Direction and true, is the direction which the tank have to go
     */
    private void setDirection(Map<Direction, Boolean> movement) {
        this.speedY = movement.keySet().stream().filter(d -> d.equals(Direction.UP) || d.equals(Direction.DOWN)).
                map(d -> movement.get(d) ? d.getSign() * speed : 0).collect(Collectors.summingDouble(x -> x)).doubleValue();
        this.speedX = movement.keySet().stream().filter(d -> d.equals(Direction.LEFT) || d.equals(Direction.RIGHT)).
                map(d -> movement.get(d) ? d.getSign() * speed : 0).collect(Collectors.summingDouble(x -> x)).doubleValue();
    }

    /**
     * Update position in accordance to speed.
     */
    private void updatePosition() {
        this.position.setFirst(this.position.getFirst()+speedX); 
        this.position.setSecond(this.position.getSecond()+speedY);
    }

    /**
     * Update angle of {@link Cannon}.
     * @param target
     *     target to aim.
     * @see InputImpl
     */
    private void updateCannon(final Pair<Double, Double> target) {
        this.cannon.update(this.position, target);
    }

    protected class Cannon{
        protected static final double MARGINAL_DISTANCE = 10;
        protected Pair<Double, Double> cannonDimension = new Pair<Double, Double>(22.5, 5.0);
        protected Pair<Double, Double> cannonPosition;
        protected double angle;

        /**
         * Update the position, it follows the tank position.
         * @param position
         *      the position of cannon, it depends by {@link TankImpl#position}.
         * @param angle
         *      the angle in degrees of cannon, the horizon is 0.
         */
        public void update(final Pair<Double, Double> position, final Pair<Double, Double> target) {
            this.cannonPosition = new Pair<Double, Double>(position.getFirst() + DIMENSION.getFirst()/2, position.getSecond() + DIMENSION.getSecond()/2 - cannonDimension.getSecond()/2);   		
            this.angle = Calculate.angle(new Pair<>(cannonPosition.getFirst() + cannonDimension.getFirst()/2, 
                    cannonPosition.getSecond() + cannonDimension.getSecond()/2), target);

        }
        /**
         * Shot according angle and return the projectile.
         * This method is always called by tank in {@link TankImpl#shot()}.
         * @return the projectile just shooted
         */
        public Projectile shot() {
            if (this.angle >= 90 && this.angle < 150) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        this.cannonPosition.getFirst() + this.cannonDimension.getFirst() / 2
                        - (DIMENSION.getSecond() * Math.tan(Math.toRadians(this.angle - 90))),
                        position.getSecond() + DIMENSION.getSecond()), this.angle, projectileSpeed);
            } else if (this.angle >= 150 && this.angle < 180) {
                return new ProjectileImpl(new Pair<Double, Double>(position.getFirst() - MARGINAL_DISTANCE,
                        position.getSecond() + DIMENSION.getSecond() / 2
                        + (this.cannonDimension.getFirst() * Math.tan(Math.toRadians(180 - this.angle)))),
                        this.angle, projectileSpeed);
            } else if (this.angle >= 180 && this.angle < 210) {
                return new ProjectileImpl(new Pair<Double, Double>(position.getFirst() - MARGINAL_DISTANCE,
                        position.getSecond() + DIMENSION.getSecond() / 2
                        - (this.cannonDimension.getFirst() * Math.tan(Math.toRadians(this.angle - 180)))),
                        this.angle, projectileSpeed);
            } else if (this.angle >= 210 && this.angle < 270) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        this.cannonPosition.getFirst() + +this.cannonDimension.getFirst() / 2
                        - (DIMENSION.getSecond() * Math.tan(Math.toRadians(270 - this.angle))),
                        position.getSecond() - 10), this.angle, projectileSpeed);
            } else {
                return new ProjectileImpl(
                        new Pair<Double, Double>(
                                this.cannonPosition.getFirst() + this.cannonDimension.getFirst() / 2
                                + this.cannonDimension.getFirst() / 2 * Math.cos(Math.toRadians(this.angle)),
                                this.cannonPosition.getSecond()
                                + this.cannonDimension.getFirst() / 2 * Math.sin(Math.toRadians(this.angle))),
                        this.angle, projectileSpeed);
            }
        }
        /**
         * Getter of angle
         * @return double of angle
         */
        public double getAngle() {
            return this.angle;
        }
        /**
         * Getter for position of cannon's tank.
         * @return {@linkplain Pair} with coordinates of cannon's tank
         */
        public Pair<Double, Double> getCannonPosition(){
            return new Pair<Double, Double>(this.cannonPosition.getFirst(), this.cannonPosition.getSecond());
        }
        /**
         * Getter for dimension of cannon's tank.
         * @return {@linkplain Pair}
         */
        public Pair<Double, Double> getCannonDimension(){
            return new Pair<Double, Double>(this.cannonDimension.getFirst(), this.cannonDimension.getSecond());
        }
    }
}