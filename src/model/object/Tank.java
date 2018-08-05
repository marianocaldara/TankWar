package model.object;

import model.input.Input;
import model.utility.Pair;
/**
 * Interface that represents a Tank
 */
public interface Tank {
    /**
     * Getter for position of Tank
     * @return {@linkplain Pair} with coordinates of Tank
     */
    Pair<Double, Double> getPosition();
    /**
     * Set the position of tank
     * @param position {@linkplain Pair} with coordinates of Tank
     */
    void setPosition(final Pair<Double, Double> position);
    /**
     * Inflict damage to itself
     * @param damage nr of lifes to subtract
     */
    void damage(final int damage);
    /**
     * Shot a projectile with the direction of cannon
     * @return {@linkplain Projectile}
     */
    Projectile shot();
    /**
     * Update status of tank with new input
     * @param i {@linkplain Input}
     */
    void update(final Input i);
}
