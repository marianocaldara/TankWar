package exceptions;

import model.object.Projectile;

/**
 * Exception threw when a {@link Projectile} goes out from the {@link World}
 * borders.
 */
public class ProjectileOutOfBordersException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2435568105377350466L;

    /**
     * Constructor.
     */
    public ProjectileOutOfBordersException() {
        super();
    }

}
