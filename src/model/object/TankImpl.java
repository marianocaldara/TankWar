package model.object;

import java.util.Map;
import java.util.stream.Collectors;

import model.input.InputImpl;
import model.utility.Calculate;
import model.utility.ConsumerSpeed;
import model.utility.Direction;
import model.utility.Pair;
/**
 * Implementation of interface {@link Tank}.
 */
public class TankImpl implements Tank {
    private int lifes;
    private Pair<Double, Double> position;
    private final static Pair<Double, Double> DIMENSION = new Pair<>(37.5, 25.0);
    private double speed = 3;
    private double speedX;
    private double speedY;
    private final Cannon cannon = new Cannon();
    /**
     * Constructor.
     * @param position
     *     position of Tank
     *     @see Pair
     * @param lifes
     *     nr of lifes of Tank
     * @param speed
     *     speed of movement
     * @param width
     *     width of Tank
     * @param height
     *     height of Tank
     */
    public TankImpl(final Pair<Double, Double> position, final int lifes, final double speed) {
        this.position = position;
        this.lifes = lifes;
        this.speed = speed;
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
    public Projectile shot() {
        return cannon.shot();
    }

    @Override
    public void update(final InputImpl i) {
        this.setDirection(i.getMovement());
        updatePosition();
        this.updateCannon(i.getTargetPosition());
    }

    @Override
    public void damage(final int damage) {
        this.lifes -= damage;
    }

    @Override
    public Pair<Double, Double> getDimension() {
        return DIMENSION;
    }

    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }
    /**
     * Take movement by {@link InputImpl} and set direction by changing speedX and speedY
     * @param movement
     *     movement to do
     * @see Map
     */
    private void setDirection(final Map<Direction, Boolean> movement) {
        // Da testare
        final ConsumerSpeed cons = new ConsumerSpeed(speed);
        movement.entrySet().stream().filter(e->e.getValue().equals(true)).collect(Collectors.toSet()).forEach(e->cons.accept(e.getKey()));

        this.speedX = cons.getSpeeds().getFirst();
        this.speedX = cons.getSpeeds().getSecond();
        /*
	    if (movement.get(Direction.UP)) {
	        this.speedY = -speed;
	    } else if (movement.get(Direction.DOWN)) {
	        this.speedY = speed;
	    } else if (!(movement.get(Direction.DOWN) && movement.get(Direction.UP))) {
	        this.speedY = 0;
	    } else if (movement.get(Direction.RIGHT)) {
	        this.speedX = speed;
	    } else if (movement.get(Direction.LEFT)) {
	        this.speedX = -speed;
	    } else if (!(movement.get(Direction.LEFT) && movement.get(Direction.RIGHT))) {
	        this.speedX = 0;
	    }*/
    }
    /**
     * Update position in accordance to speed.
     */
    private void updatePosition() {
        this.position = new Pair<Double, Double>(this.position.getFirst()+speedX, this.position.getSecond()+speedY);
    }
    /**
     * Update angle of {@link Cannon}.
     * @param target
     *     target to aim.
     * @see InputImpl
     */
    private void updateCannon(final Pair<Double, Double> target) {
        final Pair<Double, Double> cannon_pos = new Pair<Double, Double>(this.position.getFirst()-2.0, this.position.getSecond()-2.0);
        this.cannon.update(cannon_pos, Calculate.angle(cannon_pos, target));
    }
    /**
     * Cannon is the object that allow shot by Tank.
     */
    private class Cannon {
        private Pair<Double, Double> cannonposition;
        private double angle;
        /**
         * Update the position, it follows the tank position.
         * @param position
         *      the position of cannon, it depends by {@link TankImpl#position}.
         * @param angle
         *      the angle in degrees of cannon, the horizon is 0.
         */
        public void update(final Pair<Double, Double> position, final double angle) {
            this.cannonposition = position;
            this.angle = angle;
        }
        /**
         * Shot according angle and return the projectile.
         * This method is always called by tank in {@link TankImpl#shot()}.
         * @return the projectile jet shooted
         */
        public Projectile shot() {
            return new Projectile(cannonposition, this.angle, 2);
        }
    }

}
