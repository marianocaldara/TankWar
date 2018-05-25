package model.utility;
//DA VEDERE SE VA BENE
import java.util.function.Consumer;

/**
 * This {@link Consumer} is used to calculate {@link #speedX} and {@link #speedY} according direction.
 */
public class ConsumerSpeed implements Consumer<Direction>{

    private double speed;
    private double speedX;
    private double speedY;
    /**
     * Constructor
     * @param speed
     *          the speed for calculate {@link #speedX} and {@link #speedY}
     */
    public ConsumerSpeed(double speed) {
       this.speed = speed;
    }

    @Override
    public void accept(Direction dir) {
        if(dir.equals(Direction.UP) || dir.equals(Direction.DOWN)) {
            this.speedY = speed * (dir.equals(Direction.UP) ? Direction.UP.sign : Direction.DOWN.sign);
        }
        if(dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT)) {
            this.speedX = speed * (dir.equals(Direction.RIGHT) ? Direction.RIGHT.sign : Direction.LEFT.sign);
        }
    } 
    /**
     * Getter {@link #speedX} and {@link #speedY}
     * @return  {@link #speedX} and {@link #speedY}
     * 
     * @see Pair
     */
    public Pair<Double, Double> getSpeeds(){
        return new Pair<Double, Double>(speedX, speedY);
    }
}
