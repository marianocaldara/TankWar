package model.object;

import java.util.Map;
import java.util.stream.Collectors;

import javafx.geometry.Rectangle2D;
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
    private Pair<Double, Double> dimension = new Pair<Double, Double>(0.0, 0.0);
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
    public TankImpl(Pair<Double, Double> position, int lifes, double speed, double width, double height) {
        this.position = position;
        this.lifes = lifes;
        this.speed = speed;
        this.dimension.setFirst(width);
        this.dimension.setSecond(height);
    }

    @Override
    public boolean isAlive() {
        return this.lifes!=0;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {
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
    public void update(InputImpl i) {
        this.setDirection(i.getMovement());
        updatePosition();
        this.updateCannon(i.getTargetPosition());
    }

    @Override
    public void damage(int damage) {
        this.lifes -= damage;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(this.position.getFirst(), this.position.getSecond(), this.dimension.getFirst(), this.dimension.getSecond());
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    /**
     * Take movement by {@link InputImpl} and set direction by changing speedX and speedY
     * @param movement
     *     movement to do
     * @see Map
     */
    private void setDirection(Map<Direction, Boolean> movement) {
        // Da testare
        ConsumerSpeed cons = new ConsumerSpeed(speed);
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
    private void updateCannon(Pair<Double, Double> target) {
        Pair<Double, Double> cannon_pos = new Pair<Double, Double>(this.position.getFirst()-2.0, this.position.getSecond()-2.0);
        this.cannon.update(cannon_pos, Calculate.angle(cannon_pos, target));
    }
    @Override
    public void keepBetweenBorders(double arenaWidth, double arenaHeight) {
        if (this.position.getFirst() + this.dimension.getFirst() >= arenaWidth) {       // Exceeding right
            this.position.setFirst(arenaWidth - this.dimension.getFirst());
        } else if (this.position.getFirst() < 0) {                // Exceeding left
            this.position.setFirst(0.0);
        }
        if (this.position.getSecond() + this.dimension.getSecond() >= arenaHeight) {      // Exceeding down
            this.position.setSecond(arenaHeight - this.dimension.getSecond());
        } else if (this.position.getSecond() < 0) {                // Exceeding up
            this.position.setSecond(0.0);
        }
    }

    /**
     * Cannon is the object that allow shot by Tank.
     */
    private class Cannon {
        private Pair<Double, Double> cannon_position;
        private double angle;
        /**
         * Update the position, it follows the tank position.
         * @param position
         *      the position of cannon, it depends by {@link TankImpl#position}.
         * @param angle
         *      the angle in degrees of cannon, the horizon is 0.
         */
        public void update(Pair<Double, Double> position, double angle) {
            this.cannon_position = position;
            this.angle = angle;
        }
        /**
         * Shot according angle and return the projectile.
         * This method is always called by tank in {@link TankImpl#shot()}.
         * @return the projectile jet shooted
         */
        public Projectile shot() {
            return new Projectile(cannon_position, this.angle, 2);
        }
    }

}
