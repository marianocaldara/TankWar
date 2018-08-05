package model.utility;

/**
 * Enum for direction.
 * <p>
 * Memorize sign according to space.
 */
public enum Direction {
	UP(-1),
	DOWN(+1),
	LEFT(-1),
	RIGHT(+1);
	
	public int sign;
        /**
         * Constructor
         * @param sign
         *      Allow to speed up operation to calculate speed
         * @see TankImpl#update(model.input.InputImpl);
         */
        private Direction(final int sign) {
            this.sign = sign;
        }
        
        public int getSign() {
        	return this.sign;
        }
}