package exceptions;

/**
 * Factory class to instance the game exceptions.
 */
public final class FactoryException {

    private FactoryException() { }

    /**
     * Throw a new {@link ProjectileOutOfBordersException}.
     * @return a {@link ProjectileOutOfBordersException}.
     */
    public static ProjectileOutOfBordersException throwProjectileOutOfBordersException() {
        throw new ProjectileOutOfBordersException();
    }

    /**
     * Throw a new {@link ProjectileOutOfBordersException}.
     * @param msg
     *          the message.
     * @return a {@link ProjectileOutOfBordersException}.
     */
    public static ProjectileOutOfBordersException throwProjectileOutOfBordersException(final String msg) {
        throw new ProjectileOutOfBordersException(msg);
    }

    /**
     * Throw a new {@link ProjectileWithProjectileException}.
     * @return a {@link ProjectileWithProjectileException}.
     */
    public static ProjectileWithProjectileException throwProjectileWithProjectileException() {
        throw new ProjectileWithProjectileException();
    }

    /**
     * Throw a new {@link ProjectileWithProjectileException}.
     * @param msg
     *          the message.
     * @return a {@link ProjectileWithProjectileException}.
     */
    public static ProjectileWithProjectileException throwProjectileWithProjectileException(final String msg) {
        throw new ProjectileWithProjectileException(msg);
    }

    /**
     * Throw a new {@link TankDeadException}.
     * @return a {@link TankDeadException}.
     */
    public static TankDeadException throwTankDeadException() {
        throw new TankDeadException();
    }

    /**
     * Throw a new {@link TankDeadException}.
     * @param msg
     *          the message.
     * @return a {@link TankDeadException}.
     */
    public static TankDeadException throwTankDeadException(final String msg) {
        throw new TankDeadException(msg);
    }

    /**
     * Throw a new {@link TankOutOfBordersException}.
     * @return a {@link TankOutOfBordersException}.
     */
   public static TankOutOfBordersException throwTankOutOfBordersException() {
       throw new TankOutOfBordersException();
   }

   /**
    * Throw a new {@link TankOutOfBordersException}.
    * @param msg
    *           the message.
    * @return a {@link TankOutOfBordersException}.
    */
   public static TankOutOfBordersException throwTankOutOfBordersException(final String msg) {
       throw new TankOutOfBordersException(msg);
   }

   /**
    * Throw a new {@link TankWithProjectileException}.
    * @return a {@link TankWithProjectileException}.
    */
   public static TankWithProjectileException throwTankWithProjectileException() {
       throw new TankWithProjectileException();
   }

   /**
    * Throw a new {@link TankWithProjectileException}.
    * @param msg
    *           the message.
    * @return a {@link TankWithProjectileException}.
    */
   public static TankWithProjectileException throwTankWithProjectileException(final String msg) {
       throw new TankWithProjectileException(msg);
   }

   /**
    * Throw a new {@link TankWithTankException}.
    * @return a {@link TankWithTankException}.
    */
   public static TankWithTankException throwTankWithTankException() {
       throw new TankWithTankException();
   }

   /**
    * Throw a new {@link TankWithTankException}.
    * @param msg
    *           the message.
    * @return a {@link TankWithTankException}.
    */
   public static TankWithTankException throwTankWithTankException(final String msg) {
       throw new TankWithTankException(msg);
   }

}
